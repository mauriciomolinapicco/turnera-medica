package TurneraMedicaTP;
import java.util.ArrayList;
import java.util.List;


public class MedicoService implements Service<Medico> {
	private MedicoDAO dao;
	
	public MedicoService() {
		this.dao = new MedicoDAOMySQLImpl();
	}
	
	public void create(Medico medico) throws ServiceException {
		try {
			dao.create(medico);
		} catch (DAOException e) {
			throw new ServiceException("Hubo un error creando el medico", e);
		}
	}
	
	public Medico get(String matricula) throws ServiceException {
		Medico medico = null;
		try {
			medico = dao.get(matricula);
		} catch (DAOException e) {
			throw new ServiceException("Error obteniendo medico seleciconado", e);
		}
		return medico;
	}

	public void update(Medico medico) throws ServiceException {
		try {
			dao.update(medico);
		} catch (DAOException e) {
			throw new ServiceException("Error actualizando el medico", e);
		}
	}
	
	public void delete(String matricula) throws ServiceException {
		try {
			dao.delete(matricula);
		} catch (DAOException e) {
			throw new ServiceException("Error borrando el medico", e);
		}
	}
	
	public List<Medico> getAll() throws ServiceException {
		List<Medico> medicos = new ArrayList<>();
		try {
			medicos = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException("Error obteniendo la lista de medicos", e);
		}
		return medicos;
	}
	
	
}
