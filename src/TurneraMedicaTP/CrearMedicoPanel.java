package TurneraMedicaTP;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrearMedicoPanel extends JPanel{
	private JButton botonCrear;
	private JTextField nombre;
	private JTextField matricula;
	private JTextField especialidad;
	private JTextField precioConsulta;
	
	public CrearMedicoPanel() {
		armar();
	}
	
	public void armar() { 
		this.setLayout(new GridLayout(10,1,10,10));
		
		botonCrear = new JButton("Crear Medico");
		
		JLabel nombreLbl = new JLabel("Ingrese el nombre:");
		JLabel matriculaLbl = new JLabel("Ingrese la matricula:");
		JLabel especialidadLbl = new JLabel("Ingrese la especialidad:");
		JLabel precioLbl = new JLabel("Ingrese el precio por consulta:");
		
		this.nombre = new JTextField(50);
		this.matricula = new JTextField(50);
		this.especialidad = new JTextField(50);
		this.precioConsulta = new JTextField(10);
		
		this.add(nombreLbl);
		this.add(nombre);
		this.add(matriculaLbl);
		this.add(matricula);
		this.add(especialidadLbl);
		this.add(especialidad);
		this.add(precioLbl);
		this.add(precioConsulta);
		this.add(botonCrear);
		
		botonCrear.addActionListener(
			new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String inputNombre = nombre.getText();
					String inputMatricula = matricula.getText();
					String inputEspecialidad = especialidad.getText();
					String inputPrecio = precioConsulta.getText();
					
					Medico medico = new Medico(inputNombre, inputMatricula, inputEspecialidad, inputPrecio);
					
					MedicoDAO dao = new MedicoDAOMySQLImpl();
					dao.createMedico(medico);					
				}
				
			});

		
	}
}
