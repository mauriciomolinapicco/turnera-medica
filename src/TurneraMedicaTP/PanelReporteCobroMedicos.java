package TurneraMedicaTP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PanelReporteCobroMedicos extends JPanel {

    private PanelManager panelManager;
    private JTable tablaReporte;
    private JTextField textFieldFechaInicio;
    private JTextField textFieldFechaFin;
    private TurnoService service;

    public PanelReporteCobroMedicos(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void armarPanel() {
        service = new TurnoService();
        this.setLayout(new FlowLayout());

        JLabel labelFechaInicio = new JLabel("Fecha inicio (yyyy-mm-dd):");
        this.add(labelFechaInicio);

        textFieldFechaInicio = new JTextField(10);
        this.add(textFieldFechaInicio);
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL); 
        separator.setPreferredSize(new Dimension(500, 1));
        this.add(separator);


        JLabel labelFechaFin = new JLabel("Fecha fin (yyyy-mm-dd):");
        this.add(labelFechaFin);

        textFieldFechaFin = new JTextField(10);
        this.add(textFieldFechaFin);
        
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.HORIZONTAL); // Hacer que sea horizontal
        separator1.setPreferredSize(new Dimension(500, 1));
        this.add(separator1);


        JButton botonGenerarReporte = new JButton("Generar Reporte");
        botonGenerarReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarReporte(textFieldFechaInicio.getText(), textFieldFechaFin.getText());
            }
        });
        this.add(botonGenerarReporte);

        tablaReporte = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaReporte);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        this.add(scrollPane);
    }

    private void generarReporte(String fechaInicio, String fechaFin) {
        try {
            LocalDate inicio = LocalDate.parse(fechaInicio.trim());
            LocalDate fin = LocalDate.parse(fechaFin.trim());

            List<String[]> reporte = service.reporteCobroAllMedicos(inicio, fin);

            String[] columnas = {"Matricula", "Nombre Médico", "Total Cobrado"};
            Object[][] datos = new Object[reporte.size()][3];

            for (int i = 0; i < reporte.size(); i++) {
                String[] fila = reporte.get(i);
                datos[i][0] = "  " + fila[0]; 
                datos[i][1] = fila[1]; 
                datos[i][2] = "$" + fila[2]; 
            }

            tablaReporte.setModel(new DefaultTableModel(datos, columnas));
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Por favor, use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
