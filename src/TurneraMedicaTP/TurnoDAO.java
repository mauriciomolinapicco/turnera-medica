package TurneraMedicaTP;

import java.util.List;

public interface TurnoDAO {
    void create(Turno turno) throws DAOException;

    Turno get(int id) throws DAOException;

    void update(Turno turno) throws DAOException;

    void delete(int id) throws DAOException;

    List<Turno> getAll() throws DAOException;

    // Obtener todos los turnos de un médico específico
    List<Turno> getTurnosByMedico(String matriculaMedico) throws DAOException;

    // Verificar si existe un turno para el médico en la misma fecha y hora
    boolean existeTurno(Turno turno) throws DAOException;
}
