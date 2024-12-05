package TurneraMedicaTP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PanelReporteCobroMedico extends JPanel {

    private PanelManager panelManager;
    private JTextField textFieldMatricula;
    private JTextField textFieldFechaInicio;
    private JTextField textFieldFechaFin;
    private JLabel labelResultado;
    private TurnoService turnoService;

    public PanelReporteCobroMedico(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void armarPanel() {
        turnoService = new TurnoService();
        this.setLayout(new FlowLayout());

        JLabel labelMatricula = new JLabel("Matrícula del médico:");
        this.add(labelMatricula);

        textFieldMatricula = new JTextField(10);
        this.add(textFieldMatricula);

        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(500, 1));
        this.add(separator1);

        JLabel labelFechaInicio = new JLabel("Fecha de inicio (yyyy-mm-dd):");
        this.add(labelFechaInicio);

        textFieldFechaInicio = new JTextField(10);
        this.add(textFieldFechaInicio);
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(500, 1));
        this.add(separator);


        JLabel labelFechaFin = new JLabel("Fecha de fin (yyyy-mm-dd):");
        this.add(labelFechaFin);

        textFieldFechaFin = new JTextField(10);
        this.add(textFieldFechaFin);
        
        JSeparator separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(500, 1));
        this.add(separator2);


        // Botón para calcular
        JButton botonCalcular = new JButton("Calcular Cobro");
        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularCobro();
            }
        });
        this.add(botonCalcular);

        // Etiqueta para mostrar el resultado
        labelResultado = new JLabel(" ");
        labelResultado.setPreferredSize(new Dimension(500, 20));
        labelResultado.setForeground(Color.BLUE);
        this.add(labelResultado);
    }

    private void calcularCobro() {
        String matricula = textFieldMatricula.getText().trim();
        String fechaInicioTexto = textFieldFechaInicio.getText().trim();
        String fechaFinTexto = textFieldFechaFin.getText().trim();

        try {
            // Convertir las fechas
            LocalDate fechaInicio = LocalDate.parse(fechaInicioTexto);
            LocalDate fechaFin = LocalDate.parse(fechaFinTexto);

            // Validar rango de fechas
            if (fechaFin.isBefore(fechaInicio)) {
                JOptionPane.showMessageDialog(this, "La fecha de fin no puede ser anterior a la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular cobro usando el servicio
            double totalCobro = turnoService.calcularCobroEntreFechas(matricula, fechaInicio, fechaFin);

            // Mostrar resultado
            labelResultado.setText("   El médico con matrícula " + matricula + " cobró un total de: $" + totalCobro);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Por favor, use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Error al calcular el cobro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
