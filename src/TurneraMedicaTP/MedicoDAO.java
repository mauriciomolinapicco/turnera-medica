package TurneraMedicaTP;

import java.util.List;

public interface MedicoDAO {
	//CRUD => create, read, update, delete
	void createMedico(Medico medico);
	
	Medico getMedico(String matricula);
	
	void updateMedico(Medico medico);
	
	void deleteMedico(String matricula);
	
	List<Medico> getAllMedicos();
	
	boolean existeMatricula(String matricula);
	
}
