package TurneraMedicaTP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PanelBuscarTurnosPorPaciente extends JPanel {

    private PanelManager panelManager;
    private JTable tablaTurnos;
    private JTextField textFieldDNI;
    private TurnoService service;

    public PanelBuscarTurnosPorPaciente(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void armarPanel() {
        service = new TurnoService();
        this.setLayout(new FlowLayout());
        
        // Etiqueta y campo para el DNI
        JLabel labelDNI = new JLabel("DNI del paciente:");
        this.add(labelDNI);

        textFieldDNI = new JTextField(10);
        this.add(textFieldDNI);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL); // Hacer que sea horizontal
        separator.setPreferredSize(new Dimension(500, 1));
        this.add(separator);
        
        JButton botonBuscar = new JButton("Buscar Turnos");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarTurnos(textFieldDNI.getText());
            }
        });
        this.add(botonBuscar);

        tablaTurnos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaTurnos);
        this.add(scrollPane);
    }

    private void buscarTurnos(String dniPaciente) {
        try {
            if (dniPaciente.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el DNI del paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String[]> turnos = service.obtenerTurnosPorPaciente(dniPaciente);
            
            if (turnos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron turnos para el paciente con DNI: " + dniPaciente, "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            String[] columnas = {"Medico", "Fecha"};
            Object[][] datos = new Object[turnos.size()][2];

            for (int i = 0; i < turnos.size(); i++) {
                String[] turno = turnos.get(i);
                datos[i][0] = turno[0]; 
                datos[i][1] = formatearFechaHora(turno[1]); 
            }

            tablaTurnos.setModel(new DefaultTableModel(datos, columnas));
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener los turnos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String formatearFechaHora(String fechaHora) {
        try {
            LocalDateTime fecha = LocalDateTime.parse(fechaHora); 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
            return fecha.format(formatter); 
        } catch (DateTimeParseException e) {
            return fechaHora;
        }
    }

}
