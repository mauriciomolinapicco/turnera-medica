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
    private PanelBuscarTurnos panelBusquedaTurnos; 
    private PanelBuscarTurnosPorPaciente panelBusquedaTurnosPaciente;
    private PanelReporteCobroMedico panelReporteCobro;
    private PanelReporteCobroMedicos panelReporteCobroTodos;

	
	public PanelManager() {
	}
	
	public void armarManager() {
		panelMedicos = new PanelMedicos(this);
		panelPacientes = new PanelPacientes(this);
		panelBusquedaMedicos = new PanelBusquedaMedico(this);
		panelBusquedaPacientes= new PanelBusquedaPacientes(this);
		panelTurnos = new PanelTurnos(this);
		panelBusquedaTurnos = new PanelBuscarTurnos(this);
		panelBusquedaTurnosPaciente = new PanelBuscarTurnosPorPaciente(this);
		panelReporteCobro = new PanelReporteCobroMedico(this);
		panelReporteCobroTodos = new PanelReporteCobroMedicos(this);
		
		panelMedicos.armarPanel();
		panelBusquedaMedicos.armarPanel();
		panelBusquedaPacientes.armarPanel();
		panelPacientes.armarPanel();
		panelTurnos.armarPanel();
		panelBusquedaTurnos.armarPanel();
		panelBusquedaTurnosPaciente.armarPanel();
		panelReporteCobro.armarPanel();
		panelReporteCobroTodos.armarPanel();
		
		
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

        // menu de los turnos
        JMenu menuTurnos = new JMenu("Turnos");
        JMenuItem turnosMenuItem = new JMenuItem("Panel de turnos");
        turnosMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPanelTurnos();
        	}
        });
        menuTurnos.add(turnosMenuItem);
        
        
        JMenuItem buscarTurnosMenuItem = new JMenuItem("Buscar turnos de un medico");
        buscarTurnosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelBuscarTurnos(); 
            }
        });
        menuTurnos.add(turnosMenuItem);
        menuTurnos.add(buscarTurnosMenuItem); 

        
        JMenuItem buscarTurnosPacienteMenuItem = new JMenuItem("Buscar turnos por paciente");
        buscarTurnosPacienteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarPanelBuscarTurnosPorPaciente(); 
            }
        });
        menuTurnos.add(turnosMenuItem);
        menuTurnos.add(buscarTurnosMenuItem); 
        menuTurnos.add(buscarTurnosPacienteMenuItem);

        
        // menu de los reportes
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem reporteCobroMenuItem = new JMenuItem("Reporte de cobro de un medico");
        reporteCobroMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPanelReporteCobro();
        	}
        });
        menuReportes.add(reporteCobroMenuItem);
        
        JMenuItem reporteCobroTodosMenuItem = new JMenuItem("Reporte de cobro de todos los medicos");
        reporteCobroTodosMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPanelReporteCobroTodos();
        	}
        });
        menuReportes.add(reporteCobroTodosMenuItem);


        
        // Agrego todo al menu bar
        menuBar.add(menuMedicos);
        menuBar.add(menuPacientes);
        menuBar.add(menuTurnos);
        menuBar.add(menuReportes);
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
    
    public void mostrarPanelBuscarTurnosPorPaciente() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelBusquedaTurnosPaciente);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }
    
    public void mostrarPanelReporteCobro() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelReporteCobro);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }
    
    public void mostrarPanelReporteCobroTodos() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelReporteCobroTodos);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }



}
