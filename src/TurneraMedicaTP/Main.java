package TurneraMedicaTP;

import java.awt.Color;
import java.util.List;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		
		Medico medico = new Medico("Rodri DePaul", 1234, "Futebol", 76767.0);
		
		MedicoDAOMySQLImpl dao = new MedicoDAOMySQLImpl();
		
		//dao.deleteMedico("3333");
		
		//dao.updateMedico(medico);
		dao.createMedico(medico);
		//List<Medico> lista = dao.getAllMedicos();
		
		/*Medico med = dao.getMedico("55555");
		System.out.println("Nombre del medico: "+med.getNombreCompleto());
		
		for (Medico m : lista) {
			System.out.println("Nombre: "+ m.getNombreCompleto());
			System.out.println("Especialidad: "+ m.getEspecialidad());
			System.out.println("Matricula: "+ m.getMatricula());
			System.out.println("Precio: "+ m.getPrecioConsulta());
		} */
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
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
	}
}