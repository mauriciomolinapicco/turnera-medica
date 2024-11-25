package TurneraMedicaTP;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelManager {
	private JFrame frame;
	private PanelMedicos panelMedicos;
	private PanelBusquedaMedico panelBusquedaMedicos;
	private PanelPacientes panelPacientes;
	
	public PanelManager() {
	}
	
	public void armarManager() {
		panelMedicos = new PanelMedicos(this);
		panelPacientes = new PanelPacientes(this);
		panelBusquedaMedicos = new PanelBusquedaMedico(this);
		
		panelMedicos.armarPanel();
		panelBusquedaMedicos.armarPanel();
		panelPacientes.armarPanel();
		
		panelMedicos.setBackground(new Color(250,250,250));
		
		
		frame = new JFrame("Turnera medica");
		frame.setSize(500,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		//cuando el menu de la app contenga mas elementos seria bueno separar esta logica
		JMenuBar menuBar = new JMenuBar();
		
		//Menu de medicos (navbar)
        JMenu menuMedicos = new JMenu("Medicos");
        JMenuItem medicoMenuItem = new JMenuItem("Panel de Médicos");
        medicoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelMedicos();
            }
        });
        
        JMenuItem busquedaMedicoMenuItem = new JMenuItem("Buscar medico");
        busquedaMedicoMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPanelBusquedaMedicos();
        	}
        });
        menuMedicos.add(medicoMenuItem);
        menuMedicos.add(busquedaMedicoMenuItem);
        
        
        //Menu de pacientes
        JMenu menuPacientes = new JMenu("Pacientes");
        JMenuItem pacienteMenuItem = new JMenuItem("Panel de pacientes");
        pacienteMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPanelPacientes();
        	}
        });
        menuPacientes.add(pacienteMenuItem);
        
        menuBar.add(menuMedicos);
        menuBar.add(menuPacientes);
        frame.setJMenuBar(menuBar);
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
	
	public void mostrarPanelPacientes() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panelPacientes);
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
