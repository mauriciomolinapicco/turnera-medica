package TurneraMedicaTP;
import javax.swing.*;

public class PanelBusquedaPacientes extends PanelBusqueda<Paciente> {

    public PanelBusquedaPacientes(PanelManager m) {
        super(m);
    }

    @Override
    protected Paciente buscarEntidad(String dni) {
        PacienteService service = new PacienteService();
        try {
			return service.get(dni);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
        return null;
    }

    @Override
    protected String getEntityDetails(Paciente paciente) {
        String string = "-- Paciente hallado -- \n";
        string += "Nombre: " + paciente.getNombreCompleto();
        string += "\nDNI: " + paciente.getDni();
        string += "\nFicha Medica: " + paciente.getFichaMedica();
        string += "\nTelefono: " + paciente.getTelefono();
        return string;
    }

	@Override
	protected String getLabel() {
		return "Buscar paciente por DNI";
	}

	@Override
	protected String getErrorMessage() {
		return "Paciente no encontrado. Intente nuevamente";
	}
}
