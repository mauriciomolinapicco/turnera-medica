package TurneraMedicaTP;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoDAO {
    void create(Turno turno) throws DAOException;

    Turno get(int id) throws DAOException;

    void update(Turno turno) throws DAOException;

    void delete(int id) throws DAOException;

    List<Turno> getAll() throws DAOException;

    // Obtener todos los turnos de un médico específico
    //List<Turno> getTurnosByMedico(String matriculaMedico) throws DAOException;

    // Verificar si existe un turno para el médico en la misma fecha y hora
    boolean existeTurnoEnFecha(LocalDateTime fechaHora, String matriculaMedico) throws DAOException;

	List<String[]> obtenerNombresYHorasPorFecha(String matriculaMedico, LocalDate fecha) throws DAOException;

	List<String[]> obtenerTurnosPorPaciente(String dniPaciente) throws DAOException;

	double calcularCobroEntreFechas(String matriculaMedico, LocalDate fechaInicio, LocalDate fechaFin) throws DAOException;
}
