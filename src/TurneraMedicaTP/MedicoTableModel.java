package TurneraMedicaTP;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MedicoTableModel extends AbstractTableModel {

	private static final int COLUMNA_NOMBRE= 0;
	private static final int COLUMNA_MATRICULA = 1;
	private static final int COLUMNA_ESPECIALIDAD = 2;
	private static final int COLUMNA_PRECIO = 3;
	
	public String[] nombreColumnas = {"Nombre", "Matricula", "Especialidad", "Precio Consulta"};
	public Class[] tiposColumnas = {String.class, Integer.class, String.class, Double.class};
	
	private List<Medico> contenido;
	
	//CONSTRUCTORES
	public MedicoTableModel() {
		contenido = new ArrayList<Medico>();
	}
	
	public MedicoTableModel(List<Medico> contenidoInicial) {
		contenido = contenidoInicial;
	}
	
	//METODOS ABSTRACTOS DE AbstractTableModel
	public int getRowCount() {
		return contenido.size();
	}

	public int getColumnCount() {
		return nombreColumnas.length;
	}
	
	public String getColumnName(int col) {
		return nombreColumnas[col];
	}
	
	public Class getColumnClass(int col) {
		return tiposColumnas[col];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Medico m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case COLUMNA_NOMBRE:
			result = m.getNombreCompleto();
			break;
		case COLUMNA_MATRICULA:
			result = m.getMatricula();
			break;
		case COLUMNA_ESPECIALIDAD:
			result = m.getEspecialidad();
			break;
		case COLUMNA_PRECIO:
			result = m.getPrecioConsulta();
			break;
		default:
			result = "";
				
		}
		return result;
	}
	
	
	//GETTER - SETTER contenido
	public List<Medico> getContenido() {
		return contenido;
	}
	
	public void setContenido(List<Medico> contenido) {
		this.contenido = contenido;
	}
}
