package TurneraMedicaTP;

import java.util.List;

public interface MedicoDAO {
	//CRUD => create, read, update, delete
	void create(Medico medico) throws DAOException;
	
	Medico get(String matricula) throws DAOException;
	
	void update(Medico medico) throws DAOException;
	
	void delete(String matricula) throws DAOException;
	
	List<Medico> getAll() throws DAOException;
	
	boolean existeMatricula(String matricula);
	
}
