package TurneraMedicaTP;
import javax.swing.*;

public class NuevoBusquedaMedicoPanel extends BusquedaPanel<Medico> {

    public NuevoBusquedaMedicoPanel(PanelManager m) {
        super(m);
    }

    @Override
    protected Medico buscarEntidad(String matricula) {
        MedicoDAO dao = new MedicoDAOMySQLImpl();
        try {
			return dao.get(matricula);
		} catch (DAOException e) {
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
}
