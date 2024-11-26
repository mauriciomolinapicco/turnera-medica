package TurneraMedicaTP;

import java.util.ArrayList;
import java.util.List;

public class TurnoTableModel extends BaseTableModel<Turno> {
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_MEDICO = 1;
    private static final int COLUMNA_PACIENTE = 2;
    private static final int COLUMNA_FECHA_HORA = 3;
    
    public String[] nombreColumnas = {"ID", "Médico", "Paciente", "Fecha y Hora"};
    
    public Class[] tiposColumnas = {Integer.class, String.class, String.class, String.class};
    
    private List<Turno> contenido;
    
    public TurnoTableModel() {
        contenido = new ArrayList<Turno>();
    }
    
    public TurnoTableModel(List<Turno> contenidoInicial) {
        contenido = contenidoInicial;
    }
    
    @Override
    public int getRowCount() {
        return contenido.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return nombreColumnas[col];
    }
    
    @Override
    public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Turno turno = contenido.get(rowIndex);
        
        Object result = null;
        switch(columnIndex) {
        case COLUMNA_ID:
            result = turno.getId();
            break;
        case COLUMNA_MEDICO:
            result = (turno.getMedico() != null) ? turno.getMedico().getNombreCompleto() : "No asignado";
            break;
        case COLUMNA_PACIENTE:
            result = (turno.getPaciente() != null) ? turno.getPaciente().getNombreCompleto() : "No asignado"; 
            break;
        case COLUMNA_FECHA_HORA:
            result = turno.getFechaHora(); 
            break;
        default:
            result = "";
        }
        return result;
    }

    // Getter y Setter para el contenido
    public List<Turno> getContenido() {
        return contenido;
    }
    
    public void setContenido(List<Turno> contenido) {
        this.contenido = contenido;
    }
}
