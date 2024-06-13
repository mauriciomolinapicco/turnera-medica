package TurneraMedicaTP;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelManager {
	private JFrame frame;
	private NuevoPanelMedicos panelMedicos;
	private NuevoBusquedaMedicoPanel panelBusquedaMedicos;
	
	public PanelManager() {
	}
	
	public void armarManager() {
		panelMedicos = new NuevoPanelMedicos(this);
		panelBusquedaMedicos = new NuevoBusquedaMedicoPanel(this);
		
		panelMedicos.armarPanel();
		panelBusquedaMedicos.armarPanel();
		
		panelMedicos.setBackground(new Color(250,250,250));
		
		
		frame = new JFrame("Turnera medica");
		frame.setSize(500,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	
	public void showFrame() {
		frame.setVisible(true); 
	}
	
	public void mostrarPanelMedicos() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panelMedicos);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}
	
	
    public void mostrarPanelBusquedaMedicos() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelBusquedaMedicos);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

}
