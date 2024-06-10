package TurneraMedicaTP;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class PanelMedicos extends JPanel implements ActionListener {
	private JTable tabla;
	private MedicoTableModel modelo;
	private JScrollPane scrollPane;
	private JButton borrarBtn;
	private PanelManager panelManager;
	
	private JButton botonCrear;
	private JButton botonActualizar;
	private JButton botonBuscar;

	
	private JTextField nombreField;
	private JTextField matriculaField;
	private JTextField especialidadField;
	private JTextField precioConsultaField;

	
	public PanelMedicos(PanelManager m) {
		super();
		this.panelManager = m;
	}
	
	public void armarPanel() {
		this.setLayout(new FlowLayout());
		
		botonCrear = new JButton("Crear Medico");
		botonActualizar = new JButton("Actualizar Medico");
		
		JLabel nombreLbl = new JLabel("Ingrese el nombre:");
		JLabel matriculaLbl = new JLabel("Ingrese la matricula:");
		JLabel especialidadLbl = new JLabel("Ingrese la especialidad:");
		JLabel precioLbl = new JLabel("Ingrese el precio por consulta:");
		
		this.nombreField = new JTextField(40);
		this.matriculaField = new JTextField(40);
		this.especialidadField = new JTextField(40);
		this.precioConsultaField = new JTextField(40);
		
		modelo = new MedicoTableModel();
		tabla= new JTable(modelo);
		
		scrollPane = new JScrollPane(tabla);
		
		
		MedicoDAOMySQLImpl bdd = new MedicoDAOMySQLImpl();
		List<Medico> lista = new ArrayList<Medico>();
		
		//Probablemente tenga q hacer try catch aca
		lista = bdd.getAllMedicos();
		
		borrarBtn = new JButton("Borrar medico seleccionado");
		
		//Busqueda por matricula
		
		
		botonBuscar = new JButton("Buscar medico");
		
		borrarBtn.addActionListener(this);
		botonCrear.addActionListener(this);
		botonActualizar.addActionListener(this);
		botonBuscar.addActionListener(this);

		
		this.add(nombreLbl);
		this.add(nombreField);
		this.add(matriculaLbl);
		this.add(matriculaField);
		this.add(especialidadLbl);
		this.add(especialidadField);
		this.add(precioLbl);
		this.add(precioConsultaField);
		this.add(botonCrear);
		this.add(botonActualizar);
		this.add(scrollPane);
		this.add(borrarBtn);
		this.add(botonBuscar);
		
		modelo.setContenido(lista);
		modelo.fireTableDataChanged();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonCrear || e.getSource() == botonActualizar ) {
			String inputNombre = nombreField.getText();
			String inputMatricula = matriculaField.getText();
			String inputEspecialidad = especialidadField.getText();
			double inputPrecio = 0.0;
			try {
                inputPrecio = Double.parseDouble(precioConsultaField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PanelMedicos.this, "Ingrese un número válido para el precio", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
			
			Medico medico = new Medico(inputNombre, inputMatricula, inputEspecialidad, inputPrecio);
			MedicoDAO dao = new MedicoDAOMySQLImpl();
			if (e.getSource() == botonCrear) {
				dao.createMedico(medico);	
				JOptionPane.showMessageDialog(PanelMedicos.this,"Medico de matricula"+medico.getMatricula()+" creado");
				modelo.getContenido().add(medico);
				modelo.fireTableDataChanged();	
			} else {
				dao.updateMedico(medico);
				JOptionPane.showMessageDialog(PanelMedicos.this,"Medico de matricula"+medico.getMatricula()+" actualizado");
				modelo.getContenido().add(medico);
				modelo.fireTableDataChanged();
			}
			
		} else if (e.getSource() == borrarBtn) {
			int filaSeleccionada = tabla.getSelectedRow();
			
			Medico medico = modelo.getContenido().get(filaSeleccionada);
			
			MedicoDAO dao = new MedicoDAOMySQLImpl();
			dao.deleteMedico(medico.getMatricula());
			JOptionPane.showMessageDialog(PanelMedicos.this,"Medico "+medico.getMatricula()+" eliminado");
			modelo.getContenido().remove(filaSeleccionada);
			modelo.fireTableDataChanged();
		} else if (e.getSource() == botonBuscar) {
			panelManager.mostrarPanelBusqueda();
		}
	}
}
