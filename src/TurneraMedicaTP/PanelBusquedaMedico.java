package TurneraMedicaTP;
import javax.swing.*;

public class PanelBusquedaMedico extends PanelBusqueda<Medico> {

    public PanelBusquedaMedico(PanelManager m) {
        super(m);
    }

    @Override
    protected Medico buscarEntidad(String matricula) {
        MedicoService service = new MedicoService();
        try {
			return service.get(matricula);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
        return null;
    }

    @Override
    protected String getEntityDetails(Medico medico) {
        String string = "-- Medico hallado -- \n";
        string += "Nombre: " + medico.getNombreCompleto();
        string += "\nMatricula: " + medico.getMatricula();
        string += "\nEspecialidad: " + medico.getEspecialidad();
        string += "\nPrecio consulta: $" + medico.getPrecioConsulta();
        return string;
    }

	@Override
	protected String getLabel() {
		return "Buscar medico por matricula";
	}

	@Override
	protected String getErrorMessage() {
		return "Medico no encontrado. Intente nuevamente";
	}
}
