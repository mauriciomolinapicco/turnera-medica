package TurneraMedicaTP;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAOMySQLImpl implements TurnoDAO {

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.connect();
    }

    public void create(Turno turno) throws DAOException {
        try {
        	int id = turno.getId();
            String matriculaMedico = turno.getMedico().getMatricula();  
            String dniPaciente = turno.getPaciente().getDni(); 
            String fechaHora = turno.getFechaHora().toString();  
            
            
            
            String sql = "INSERT INTO turnos (id, matriculaMedico, dniPaciente, fechaHora) " +
                         "VALUES ('"+ id + "', '" + matriculaMedico + "', '" + dniPaciente + "', '" + fechaHora + "')";
            
            ConnectionFactory.transaccion(sql);
        } catch (SQLException e) {
            throw new DAOException("Hubo un error creando el turno en la BDD", e);
        }
    }
    
    
    
    @Override
    public Turno get(int id) throws DAOException {
        String query = "SELECT * FROM turnos WHERE id = ?";
        Turno turno = null;

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String matriculaMedico = rs.getString("matriculaMedico");
                String dniPaciente = rs.getString("dniPaciente");
                
                MedicoDAOMySQLImpl m = new MedicoDAOMySQLImpl();
                PacienteDAOMySQLImpl p = new PacienteDAOMySQLImpl();
                Medico medico = m.get(matriculaMedico);  
                Paciente paciente = p.get(dniPaciente);
                
                
                
                if (medico == null || paciente == null) {
                	throw new DAOException("EL MEDICO O PACIENTE DIERON NULL!!!");
                };
                
                

                turno = new Turno(
                    rs.getInt("id"),
                    medico,  
                    paciente,
                    rs.getTimestamp("fechaHora").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el turno", e);
        }
        return turno;
    }

    @Override
    public void update(Turno turno) throws DAOException {
        try {
            int id = turno.getId();  
            String matriculaMedico = turno.getMedico().getMatricula(); 
            String dniPaciente = turno.getPaciente().getDni();  
            String fechaHora = turno.getFechaHora().toString();  
            
            String sql = "UPDATE turnos SET matriculaMedico = '" + matriculaMedico + 
                         "', dniPaciente = '" + dniPaciente + "', fechaHora = '" + fechaHora + 
                         "' WHERE id = " + id; 
            
            ConnectionFactory.transaccion(sql);
        } catch (SQLException e) {
            throw new DAOException("Hubo un error al actualizar el turno en la BDD", e);
        }
    }


    @Override
    public void delete(int id) throws DAOException {
        String query = "DELETE FROM turnos WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el turno", e);
        }
    }
    

    @Override
    public List<Turno> getAll() throws DAOException {
        List<Turno> turnos = new ArrayList<>();
        String query = "SELECT * FROM turnos";
        

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Paciente paciente = buscarPacientePorId(rs.getString("dniPaciente")); 
                Medico medico = buscarMedicoPorId(rs.getString("matriculaMedico")); 
                
                Turno turno = new Turno(
                    rs.getInt("id"),
                    new Medico(medico.getNombreCompleto(), medico.getMatricula(), medico.getEspecialidad(), medico.getPrecioConsulta()),
                    new Paciente(paciente.getDni(), paciente.getNombreCompleto(), paciente.getFichaMedica(), paciente.getTelefono()),  
                    rs.getTimestamp("fechaHora").toLocalDateTime()
                );
                turnos.add(turno);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los turnos", e);
        }
        return turnos;
    }

    
    @Override
    public List<Turno> getTurnosByMedico(String matriculaMedico) throws DAOException {
        List<Turno> turnos = new ArrayList<>();
        String query = "SELECT * FROM turnos WHERE matriculaMedico = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, matriculaMedico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Turno turno = new Turno(
                    rs.getInt("id"),
                    new Medico(rs.getString("matriculaMedico"), "", "", 0), 
                    new Paciente(rs.getString("dniPaciente"), "", "", 0),  
                    rs.getTimestamp("fechaHora").toLocalDateTime()
                );
                turnos.add(turno);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener los turnos del médico", e);
        }
        return turnos;
    }

//    @Override
//    public boolean existeTurno(Turno turno) throws DAOException {
//        String query = "SELECT COUNT(*) FROM turnos WHERE matriculaMedico = ? AND fechaHora = ?";
//        boolean existe = false;
//
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, turno.getMedico().getMatricula());
//            stmt.setTimestamp(2, Timestamp.valueOf(turno.getFechaHora()));
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next() && rs.getInt(1) > 0) {
//                existe = true;
//            }
//        } catch (SQLException e) {
//            throw new DAOException("Error al verificar si existe el turno", e);
//        }
//        return existe;
//    }
    public boolean existeTurnoEnFecha(LocalDateTime fechaHora, String matriculaMedico) throws DAOException {
        String query = "SELECT COUNT(*) FROM turnos WHERE matriculaMedico = ? AND fechaHora = ?";
        boolean ocupado = false;

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, matriculaMedico);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaHora));
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                ocupado = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Error al verificar si el médico está ocupado en esa fecha y hora", e);
        }
        return ocupado;
    }    

    
    
    private Paciente buscarPacientePorId(String pacienteId) {
        PacienteService pacienteService = new PacienteService();
        try {
            return pacienteService.get(pacienteId); 
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Medico buscarMedicoPorId(String medicoId) {
        MedicoService medicoService = new MedicoService(); 
        try {
            return medicoService.get(medicoId); 
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }	

}
