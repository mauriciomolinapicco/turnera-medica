package TurneraMedicaTP;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BusquedaMedicoPanel extends JPanel{
	
	private JButton botonBuscar;
	private JButton botonVolver;
	private PanelManager panelManager;
	private JTextField busquedaXMatriculaField;
	
	public BusquedaMedicoPanel(PanelManager m) {
		super();
		this.panelManager = m;
	}
	
	public void armarPanel() { 
		this.setLayout(new GridLayout(10,10,10,10));
		
		JLabel busquedaLbl = new JLabel("Buscar un medico por matricula:");
		this.busquedaXMatriculaField = new JTextField(40);
		botonBuscar = new JButton("Buscar");
		botonVolver = new JButton("Volver al panel principal");
		
		this.add(busquedaLbl);
		this.add(busquedaXMatriculaField);
		this.add(botonBuscar);
		this.add(botonVolver);
		
		botonBuscar.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String input = busquedaXMatriculaField.getText();
					MedicoDAO dao = new MedicoDAOMySQLImpl();
					Medico medico = dao.getMedico(input);
					if (medico != null) {
						String string = "-- Medico hallado -- \n";
						string += "Nombre: "+medico.getNombreCompleto();
						string += "\nMatricula: "+medico.getMatricula();
						string += "\nEspecialidad: "+medico.getEspecialidad();
						string += "\nPrecio consulta: $"+medico.getPrecioConsulta();
						JOptionPane.showMessageDialog(BusquedaMedicoPanel.this, string);
						panelManager.mostrarPanelMedicos();
					} else {
						JOptionPane.showMessageDialog(BusquedaMedicoPanel.this, "Matricula no encontrada. Intente nuevamente");
					}
				}				
		});
		
		botonVolver.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelManager.mostrarPanelMedicos();
				}
			});
	}
}
