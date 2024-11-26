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
	private PanelBusquedaPacientes panelBusquedaPacientes;
	private PanelPacientes panelPacientes;
	private PanelTurnos panelTurnos;
	
    private PanelBuscarTurnos panelBusquedaTurnos; // NUEVO: Panel para buscar turnos

	
	public PanelManager() {
	}
	
	public void armarManager() {
		panelMedicos = new PanelMedicos(this);
		panelPacientes = new PanelPacientes(this);
		panelBusquedaMedicos = new PanelBusquedaMedico(this);
		panelBusquedaPacientes= new PanelBusquedaPacientes(this);
		panelTurnos = new PanelTurnos(this);
		panelBusquedaTurnos = new PanelBuscarTurnos(this);
		
		panelMedicos.armarPanel();
		panelBusquedaMedicos.armarPanel();
		panelBusquedaPacientes.armarPanel();
		panelPacientes.armarPanel();
		panelTurnos.armarPanel();
		panelBusquedaTurnos.armarPanel();
		
		
		
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
        
        JMenuItem busquedaPacientesMenuItem = new JMenuItem("Buscar paciente");
        busquedaPacientesMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPanelBusquedaPacientes();
        	}
        });
        menuPacientes.add(busquedaPacientesMenuItem);


        JMenu menuTurnos = new JMenu("Turnos");
        JMenuItem turnosMenuItem = new JMenuItem("Panel de turnos");
        turnosMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPanelTurnos();
        	}
        });
        menuTurnos.add(turnosMenuItem);
        
        
        JMenuItem buscarTurnosMenuItem = new JMenuItem("Buscar turnos");
        buscarTurnosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelBuscarTurnos(); // Llama al nuevo método
            }
        });
        menuTurnos.add(turnosMenuItem);
        menuTurnos.add(buscarTurnosMenuItem); // NUEVO: Añadir al menú


        
        
        menuBar.add(menuMedicos);
        menuBar.add(menuPacientes);
        menuBar.add(menuTurnos);
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
    
    public void mostrarPanelBusquedaPacientes() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelBusquedaPacientes);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    
    public void mostrarPanelTurnos() {
    	frame.getContentPane().removeAll();
    	frame.getContentPane().add(panelTurnos);
    	frame.getContentPane().validate();
    	frame.getContentPane().repaint();
    }
    
    public void mostrarPanelBuscarTurnos() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelBusquedaTurnos);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

}
