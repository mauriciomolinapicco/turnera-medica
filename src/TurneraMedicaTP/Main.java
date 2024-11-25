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
		manager.mostrarPanelPacientes();
		manager.showFrame();
	}
}