package TurneraMedicaTP;
import java.util.ArrayList;
import java.util.List;


public class MedicoService implements Service<Medico> {
	private MedicoDAO dao;
	
	public MedicoService() {
		this.dao = new MedicoDAOMySQLImpl();
	}
	
	public void create(Medico medico) throws ServiceException {
		if (medicoExiste(medico.getMatricula())) {
			throw new ServiceException("Ya existe un medico con la matricula ingresada");
		} else {
			try {
				dao.create(medico);
			} catch (DAOException e) {
				throw new ServiceException("Hubo un error creando el medico", e);
			}
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
		if (medicoExiste(medico.getMatricula())) {
			try {
				dao.update(medico);
			} catch (DAOException e) {
				throw new ServiceException("Error actualizando el medico", e);
			}
		} else {
			throw new ServiceException("No existe ningun medico con la matricula ingresadada");
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
	
	public boolean medicoExiste(String matricula) {
		Medico medico = null;
		try {
			medico = this.get(matricula);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return medico == null ? false : true;
	}
	
	
}
