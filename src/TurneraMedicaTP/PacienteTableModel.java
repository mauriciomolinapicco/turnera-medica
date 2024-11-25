package TurneraMedicaTP;

import java.util.ArrayList;
import java.util.List;

public class PacienteTableModel extends BaseTableModel<Paciente>{
	//extends AbstractTableModel 
	private static final int COLUMNA_NOMBRE= 0;
	private static final int COLUMNA_DNI = 1;
	private static final int COLUMNA_FICHA= 2;
	private static final int COLUMNA_TELEFONO = 3;
	
	public String[] nombreColumnas = {"Nombre", "DNI", "Ficha Medica", "Telefono"};
	public Class[] tiposColumnas = {String.class, String.class, String.class, Integer.class};
	
	private List<Paciente> contenido;
	
	//CONSTRUCTORES
	public PacienteTableModel() {
		contenido = new ArrayList<Paciente>();
	}
	
	public PacienteTableModel(List<Paciente> contenidoInicial) {
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
		Paciente m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case COLUMNA_NOMBRE:
			result = m.getNombreCompleto();
			break;
		case COLUMNA_DNI:
			result = m.getDni();
			break;
		case COLUMNA_FICHA:
			result = m.getFichaMedica();
			break;
		case COLUMNA_TELEFONO:
			result = m.getTelefono();
			break;
		default:
			result = "";
				
		}
		return result;
	}
	
	
	public List<Paciente> getContenido() {
		return contenido;
	}
	
	public void setContenido(List<Paciente> contenido) {
		this.contenido = contenido;
	}
}
