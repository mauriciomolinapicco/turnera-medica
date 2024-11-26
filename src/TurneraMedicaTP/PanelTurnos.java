package TurneraMedicaTP;

import java.util.List;
import java.time.LocalDateTime;

public class PanelTurnos extends PanelBase<Turno> {
	private PanelManager panelManager;

    public PanelTurnos(PanelManager m) {
    	super();
    	this.panelManager = m;
	}

	@Override
    protected String getNombreLabelText() {
        return "DNI del Paciente";
    }

    @Override
    protected String getIdLabelText() {
        return "ID Turno";
    }

    @Override
    protected String getFieldTresLabelText() {
        return "Matricula del médico";
    }

    @Override
    protected String getFieldCuatroLabelText() {
        return "Fecha y Hora";
    }

    @Override
    protected BaseTableModel<Turno> createTableModel() {
        return new TurnoTableModel(); 
    }

    @Override
    protected Turno createEntityFromFields() throws ServiceException {
        // Crear un objeto Turno con los valores de los campos
        String pacienteId = nombreField.getText();
        String turnoId = idField.getText(); 
        String medicoId = fieldTres.getText(); 
        String fechaHora = fieldCuatro.getText();
        
        if (pacienteId == null || pacienteId.isBlank() ||
                turnoId == null || turnoId.isBlank() ||
                medicoId == null || medicoId.isBlank() ||
                fechaHora == null || fechaHora.isBlank()) {
                throw new ServiceException("Todos los campos deben estar completos.");
            }

        Turno turno = new Turno();
        Paciente paciente = buscarPacientePorId(pacienteId); 
        Medico medico = buscarMedicoPorId(medicoId); 
        
        turno.setPaciente(paciente);
        turno.setId(Integer.parseInt(turnoId));
        turno.setMedico(medico);
        
        try {
        	turno.setFechaHora(LocalDateTime.parse(fechaHora)); 
        } catch (Exception e) {
        	throw new ServiceException("Fecha - hora incorrecta");
        }
        
        return turno;
    }
    
    private Paciente buscarPacientePorId(String pacienteId) {
        PacienteService pacienteService = new PacienteService();
        try {
            return pacienteService.get(pacienteId); 
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Medico buscarMedicoPorId(String medicoId) {
        MedicoService medicoService = new MedicoService(); 
        try {
            return medicoService.get(medicoId); 
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    @Override
    protected String getEntityId(Turno entity) {
    	return String.valueOf(entity.getId());
    }

    @Override
    protected String mensajeCreado() {
        return "Turno creado con éxito.";
    }

    @Override
    protected String mensajeActualizado() {
        return "Turno actualizado con éxito.";
    }

    @Override
    protected String mensajeBorrado() {
        return "Turno borrado con éxito.";
    }

    @Override
    protected Service<Turno> createService() {
        return new TurnoService(); 
    }

    @Override
    protected void mostrarPanelBusqueda() {
    }
}
