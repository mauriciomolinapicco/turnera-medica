package TurneraMedicaTP;

import java.util.ArrayList;
import java.util.List;

public class PacienteService implements Service<Paciente> {
	private PacienteDAO dao;
	
	public PacienteService() {
		this.dao = new PacienteDAOMySQLImpl();
	}
	
	public void create(Paciente paciente) throws ServiceException {
		if (pacienteExiste(paciente.getDni())) {
			throw new ServiceException("Ya existe un paciente con el DNI ingresado");
		} else {
			try {
				dao.create(paciente);
			} catch (DAOException e) {
				throw new ServiceException("Hubo un error creando el paciente", e);
			}
		}
	}
	
	public Paciente get(String dni) throws ServiceException {
		Paciente paciente = null;
		try {
			paciente = dao.get(dni);
		} catch (DAOException e) {
			throw new ServiceException("Error obteniendo el paciente seleccionado", e);
		}
		return paciente;
	}

	public void update(Paciente paciente) throws ServiceException {
		if (pacienteExiste(paciente.getDni())) {
			try {
				dao.update(paciente);
			} catch (DAOException e) {
				throw new ServiceException("Error actualizando el paciente", e);
			}
		} else {
			throw new ServiceException("No existe ningun paciente con el DNI ingresadado");
		}
	}
	
	public void delete(String dni) throws ServiceException {
		try {
			dao.delete(dni);
		} catch (DAOException e) {
			throw new ServiceException("Error borrando el paciente", e);
		}
	}
	
	public List<Paciente> getAll() throws ServiceException {
		List<Paciente> pacientes = new ArrayList<>();
		try {
			pacientes = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException("Error obteniendo la lista de pacientes", e);
		}
		return pacientes;
	}
	
	public boolean pacienteExiste(String dni) {
		Paciente paciente = null;
		try {
			paciente = this.get(dni);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return paciente == null ? false : true;
	}
	
	
}
