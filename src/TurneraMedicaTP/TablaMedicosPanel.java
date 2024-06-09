package TurneraMedicaTP;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class TablaMedicosPanel extends JPanel {
	private JTable tabla;
	private MedicoTableModel modelo;
	private JScrollPane scrollPane;
	private JButton borrarBtn;
	
	public TablaMedicosPanel() {
		super();
		armarPanel();
	}
	
	public void armarPanel() {
		this.setLayout(new FlowLayout());
		
		modelo = new MedicoTableModel();
		tabla= new JTable(modelo);
		
		scrollPane = new JScrollPane(tabla);
		this.add(scrollPane);
		
		MedicoDAOMySQLImpl bdd = new MedicoDAOMySQLImpl();
		List<Medico> lista = new ArrayList<Medico>();
		
		//Probablemente tenga q hacer trty catch aca
		lista = bdd.getAllMedicos();
		
		borrarBtn = new JButton("Borrar medico");
		this.add(borrarBtn);
		borrarBtn.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int filaSeleccionada = tabla.getSelectedRow();
					
					Medico medico = modelo.getContenido().get(filaSeleccionada);
					
					MedicoDAO dao = new MedicoDAOMySQLImpl();
					dao.deleteMedico(medico.getMatricula());
					modelo.getContenido().remove(filaSeleccionada);
					modelo.fireTableDataChanged();

				}
			});
		
		modelo.setContenido(lista);
		modelo.fireTableDataChanged();
	}
	
	
	
}
