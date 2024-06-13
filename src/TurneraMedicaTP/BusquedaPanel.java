package TurneraMedicaTP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BusquedaPanel<T> extends JPanel {

    protected JButton botonBuscar;
    protected JButton botonVolver;
    protected PanelManager panelManager;
    protected JTextField busquedaField;
    protected abstract String getLabel();

    public BusquedaPanel(PanelManager m) {
        super();
        this.panelManager = m;
    }

    public void armarPanel() {
        this.setLayout(new GridLayout(10, 10, 10, 10));
        
        String label = getLabel();
        JLabel busquedaLbl = new JLabel(label);
        this.busquedaField = new JTextField(40);
        botonBuscar = new JButton("Buscar");
        botonVolver = new JButton("Volver al panel principal");

        this.add(busquedaLbl);
        this.add(busquedaField);
        this.add(botonBuscar);
        this.add(botonVolver);

        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = busquedaField.getText();
                T entity = buscarEntidad(input);
                if (entity != null) {
                    String string = getEntityDetails(entity);
                    JOptionPane.showMessageDialog(BusquedaPanel.this, string);
                    panelManager.mostrarPanelMedicos(); 
                } else {
                    JOptionPane.showMessageDialog(BusquedaPanel.this, "Medico no encontrado. Intente nuevamente");
                }
            }
        });

        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelMedicos(); 
            }
        });
    }

    protected abstract T buscarEntidad(String id);

    protected abstract String getEntityDetails(T entity);
}

