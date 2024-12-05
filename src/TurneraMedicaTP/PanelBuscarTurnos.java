package TurneraMedicaTP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PanelBuscarTurnos extends JPanel {

    private PanelManager panelManager; 
    private JTable tablaTurnos; 
    private JTextField textFieldFecha; 
    private JTextField textFieldMatricula; // Campo para la matrícula
    private TurnoService service;

    public PanelBuscarTurnos(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void armarPanel() {
    	service = new TurnoService();
        this.setLayout(new FlowLayout());
        JLabel labelMatricula = new JLabel("Matrícula del médico:");
        this.add(labelMatricula);

        textFieldMatricula = new JTextField(10);
        this.add(textFieldMatricula);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL); //hacer que sea horizontal
        separator.setPreferredSize(new Dimension(500, 1));
        this.add(separator);
        
        JLabel labelFecha = new JLabel("Fecha de búsqueda (yyyy-mm-dd):");
        this.add(labelFecha);

        textFieldFecha = new JTextField(10);
        this.add(textFieldFecha);


        JButton botonBuscar = new JButton("Buscar Turnos");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarTurnos(textFieldFecha.getText(), textFieldMatricula.getText());
            }
        });
        this.add(botonBuscar);

        tablaTurnos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaTurnos);
        this.add(scrollPane);
    }

    private void buscarTurnos(String fecha, String matriculaMedico) {
        try {
            LocalDate fechaLocal = LocalDate.parse(fecha.trim());
            List<String[]> turnos = service.obtenerTurnosPorMedicoYFecha(matriculaMedico, fechaLocal);
            
            if (turnos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron turnos para la matrícula: " + matriculaMedico + " en la fecha: " + fecha, "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String[] columnas = {"Paciente", "Hora"};
            Object[][] datos = new Object[turnos.size()][2];

            for (int i = 0; i < turnos.size(); i++) {
                String[] turno = turnos.get(i);
                datos[i][0] = turno[0]; // Nombre del paciente
                datos[i][1] = turno[1]; // Hora del turno
            }

            tablaTurnos.setModel(new DefaultTableModel(datos, columnas));
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Por favor, use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener los turnos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
