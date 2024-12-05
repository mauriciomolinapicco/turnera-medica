package TurneraMedicaTP;

import javax.swing.JOptionPane;

public class PanelPacientes extends PanelBase<Paciente> {
	private PanelManager panelManager;


    public PanelPacientes(PanelManager m) {
        super();
        this.panelManager = m;
    }

    @Override
    protected String getNombreLabelText() {
        return "Ingrese el nombre del paciente:";
    }

    @Override
    protected String getIdLabelText() {
        return "Ingrese el DNI:";
    }
    
    @Override
	protected String getFieldTresLabelText() {
		return "Ingrese los datos de la ficha medica";
	}

	@Override
	protected String getFieldCuatroLabelText() {
		return "Ingrese el telefono del paciente";
	}

    @Override
    protected BaseTableModel<Paciente> createTableModel() {
        return new PacienteTableModel();
    }

    @Override
    protected Paciente createEntityFromFields() throws ServiceException {
        String nombreCompleto = nombreField.getText();
        String dni = idField.getText();
        String fichaMedica = fieldTres.getText();        
        String textoTel = fieldCuatro.getText();
        
        if (nombreCompleto == null || nombreCompleto.isBlank() ||
        	    dni == null || dni.isBlank() ||
        	    fichaMedica == null || fichaMedica.isBlank() ||
        	    textoTel == null || textoTel.isBlank()) {
        	    throw new ServiceException("Todos los campos deben estar completos.");
        	}

        int telefono = 0;
        try {
        	telefono = Integer.parseInt(textoTel);
        } catch(NumberFormatException e) {
        	 throw new ServiceException("Ingrese un número válido para el telefono");
        }
        
        try {
            Integer.parseInt(dni);
        } catch (NumberFormatException e) {
            throw new ServiceException("El DNI debe ser un número válido.");
        }

        
        return new Paciente(dni, nombreCompleto, fichaMedica, telefono);
    }

	@Override
	protected String getEntityId(Paciente entity) {
		return entity.getDni();
	}

	@Override
	protected void mostrarPanelBusqueda() {
		panelManager.mostrarPanelBusquedaPacientes();
	}

	@Override
	protected String mensajeCreado() {
		return "Paciente creado con exito!";
	}
	
	@Override
	protected String mensajeActualizado() {
		return "Paciente actualizado con exito!";
	}
	
	@Override
	protected String mensajeBorrado() {
		return "Paciente eliminado con exito!";
	}

	@Override
	protected Service<Paciente> createService() {
		return new PacienteService();
	}

	
}