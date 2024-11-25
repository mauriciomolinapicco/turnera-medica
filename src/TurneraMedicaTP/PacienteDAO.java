package TurneraMedicaTP;

import java.util.List;

public interface PacienteDAO {
	void create(Paciente paciente) throws DAOException;
	
	Paciente get(String dni) throws DAOException;
	
	void update(Paciente paciente) throws DAOException;
	
	void delete(String dni) throws DAOException;
	
	List<Paciente> getAll() throws DAOException;
	
	boolean existeDni(String dni);
	
}
