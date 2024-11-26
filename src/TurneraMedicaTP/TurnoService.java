package TurneraMedicaTP;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TurnoService implements Service<Turno> {

    private TurnoDAO dao;

    public TurnoService() {
        this.dao = new TurnoDAOMySQLImpl();
    }

    @Override
    public void create(Turno turno) throws ServiceException {
        try {
			if (turnoExiste(String.valueOf(turno.getId()))) {
			    throw new ServiceException("Ya existe un turno con el mismo ID.");
			} 
			
			if (this.dao.existeTurnoEnFecha(turno.getFechaHora(), turno.getMedico().getMatricula())) {
				throw new ServiceException("El medico esta ocupado en esa fecha y hora");
			}
			
		    try {
		        dao.create(turno);
		    } catch (DAOException e) {
		    	e.printStackTrace();
		        throw new ServiceException("Hubo un error creando el turno", e);
		    }
			
		} catch (DAOException e) {
			throw new ServiceException("Hubo un error creando el turno", e);
		}
    }

    @Override
    public Turno get(String id) throws ServiceException {
        Turno turno = null;
        try {
            int idInt = Integer.parseInt(id); 
            turno = dao.get(idInt);
        } catch (NumberFormatException e) {
            throw new ServiceException("El ID proporcionado no es válido: " + id, e);
        } catch (DAOException e) {
            throw new ServiceException("Error obteniendo el turno seleccionado", e);
        }
        return turno;
    }

    @Override
    public void update(Turno turno) throws ServiceException {
        if (turnoExiste(String.valueOf(turno.getId()))) {
            try {
                dao.update(turno);
            } catch (DAOException e) {
                throw new ServiceException("Error actualizando el turno", e);
            }
        } else {
            throw new ServiceException("No existe ningún turno con el ID ingresado");
        }
    }

    @Override
    public void delete(String id) throws ServiceException {
        try {
            int idInt = Integer.parseInt(id); // Convertimos el id a int antes de pasar al DAO
            dao.delete(idInt);
        } catch (NumberFormatException e) {
            throw new ServiceException("El ID proporcionado no es válido: " + id, e);
        } catch (DAOException e) {
            throw new ServiceException("Error borrando el turno", e);
        }
    }

    @Override
    public List<Turno> getAll() throws ServiceException {
        List<Turno> turnos = new ArrayList<>();
        try {
            turnos = dao.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Error obteniendo la lista de turnos", e);
        }
        return turnos;
    }

    public boolean turnoExiste(String id) {
        Turno turno = null;
        try {
            turno = this.get(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return turno == null ? false : true;
    }
    
    
    public List<String[]> obtenerTurnosPorMedicoYFecha(String matriculaMedico, LocalDate fecha) throws ServiceException {
        try {
        	return dao.obtenerNombresYHorasPorFecha(matriculaMedico, fecha);
        } catch (DAOException e) {
        	e.printStackTrace();
        	throw new ServiceException("Error obteniendo turnos de medico", e);
        }
    }

    

}
