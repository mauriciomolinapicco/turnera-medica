package TurneraMedicaTP;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOMySQLImpl implements PacienteDAO{

	public void create(Paciente paciente) throws DAOException {
        try {
        	String nombreCompleto = paciente.getNombreCompleto();
    		String dni = paciente.getDni();
    		String fichaMedica = paciente.getFichaMedica();
    		int telefono = paciente.getTelefono();
    		
            String sql = "INSERT INTO pacientes (dni, nombreCompleto, fichaMedica, telefono) " +
                    "VALUES ('" + dni + "', '" + nombreCompleto + "', '" + fichaMedica + "', '" + telefono + "')";
			ConnectionFactory.transaccion(sql);
		} catch (SQLException e) {
			throw new DAOException("Hubo un error creando el paciente en la BDD", e);
		}        
	}

	public Paciente get(String dni) throws DAOException {
		String sql = "SELECT * FROM pacientes WHERE dni = '" +dni+ "'";
		Paciente paciente = null;
		Connection c = null;
		ResultSet rs = null;
		
		try {
			c = ConnectionFactory.connect();
			Statement s = c.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next()) {
				String d = rs.getString("dni");
				String nombreCompleto = rs.getString("nombreCompleto");
				String fichaMedica = rs.getString("fichaMedica");
				int telefono = rs.getInt("telefono");
				
				paciente = new Paciente(d, nombreCompleto, fichaMedica, telefono);
			}
		} catch (SQLException e) {
			throw new DAOException("Hubo un error creando el paciente en la BDD", e);
		} finally {
			ConnectionFactory.cerrarConexion(c);
		}
		
		return paciente;
	}
	

	public void update(Paciente paciente) throws DAOException {
		
		try {
			String nombreCompleto = paciente.getNombreCompleto();
    		String dni = paciente.getDni();
    		String fichaMedica = paciente.getFichaMedica();
    		int telefono = paciente.getTelefono();
			
			String sql = "UPDATE pacientes SET nombreCompleto = '" +nombreCompleto+ 
					"', fichaMedica = '" +fichaMedica+ "', telefono = '" +
					telefono +"' WHERE dni = '"+ dni + "'";
			ConnectionFactory.transaccion(sql);
		} catch (DAOException e) {
			throw new DAOException("Hubo un error al actualizar el paciente", e);
		}
	}

	public void delete(String dni) throws DAOException {
		try {
			String sql = "DELETE FROM pacientes WHERE dni = '" +dni+ "'";
			ConnectionFactory.transaccion(sql);
		} catch (SQLException e) {
			throw new DAOException("Hubo un error creando el paciente en la BDD", e);
		}
	}
	

	public List<Paciente> getAll() throws DAOException {
		String sql = "SELECT * FROM pacientes";
		List<Paciente> lista = new ArrayList<>();
		Connection c = null;
		ResultSet rs = null;
	
		try {
			c = ConnectionFactory.connect();
			Statement s = c.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombreCompleto = rs.getString("nombreCompleto");
				String fichaMedica = rs.getString("fichaMedica");
				int telefono = rs.getInt("telefono");
				
				Paciente paciente = new Paciente(dni, nombreCompleto, fichaMedica, telefono);
				lista.add(paciente);
			}
		} catch (SQLException e) {
			throw new DAOException("Hubo un error a la hora de obtener la lista de pacientes", e);
		} finally {
			ConnectionFactory.cerrarConexion(c);
		}
		
		return lista;
	}

	public boolean existeDni(String dni) {
		Paciente paciente = null;
		try {
			paciente = get(dni);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return paciente != null ? true : false;
	}
	

}
