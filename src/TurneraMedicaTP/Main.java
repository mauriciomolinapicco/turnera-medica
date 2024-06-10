package TurneraMedicaTP;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;

public class Main {
	private PanelManager manager;
	
	public static void main(String[] args) {
		
		Main ppal = new Main();
		ppal.iniciarManager();
	}
	
	public void iniciarManager() {
		manager = new PanelManager();
		manager.armarManager();
		manager.mostrarPanelMedicos();
		manager.showFrame();
	}
	
	
		
		
		/*JFrame frame = new JFrame("Turnera Medica");
		frame.add(new TablaMedicosPanel());
		frame.add(new JButton("Hola"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);*/

		/*JPanel panelCrearMedico = new CrearMedicoPanel();
		JPanel panelMedicos = new TablaMedicosPanel();
		panelMedicos.setBackground(new Color(105,105,105));
		panelCrearMedico.setBackground(new Color(169,169,169));

		//panelMedicos.setBounds(0,0,500,400);
		
		
		JFrame f = new JFrame("Turnera medica");
		f.setSize(1000,750);
		f.setLayout(new FlowLayout());
		f.setVisible(true); 
		//f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(panelMedicos);
		f.add(panelCrearMedico);*/
		
		/*
		JFrame f = new JFrame("Turnera medica");
		//PanelXXX pb = new PanelXXX();

		//f.getContentPane().add(pb);
		
		f.setResizable(false);
		f.setSize(420,420);
		f.pack(); //ajusta automaticamente el tamano
		 ImageIcon image = new ImageIcon("image.png);
		 frame.setIconImage(image.getImage( ));
		f.getContentPane().setBackground(new Color(0,0,0));		 
		f.setVisible(true);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		*
		Medico medico = new Medico("Rodri DePaul", 1234, "Futebol", 76767.0);
		
		MedicoDAOMySQLImpl dao = new MedicoDAOMySQLImpl();
		
		dao.deleteMedico("3333");
		
		dao.updateMedico(medico);
		dao.createMedico(medico);
		List<Medico> lista = dao.getAllMedicos();
		
		Medico med = dao.getMedico("55555");
		System.out.println("Nombre del medico: "+med.getNombreCompleto());
		
		for (Medico m : lista) {
			System.out.println("Nombre: "+ m.getNombreCompleto());
			System.out.println("Especialidad: "+ m.getEspecialidad());
			System.out.println("Matricula: "+ m.getMatricula());
			System.out.println("Precio: "+ m.getPrecioConsulta());
		} 
		*
		*/
}