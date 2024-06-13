package TurneraMedicaTP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAOMySQLImpl implements MedicoDAO{

	public void create(Medico medico) throws DAOException {
        try {
        	String nombreCompleto = medico.getNombreCompleto();
    		String matricula = medico.getMatricula();
    		String especialidad = medico.getEspecialidad();
    		double precioConsulta = medico.getPrecioConsulta();
    		
            String sql = "INSERT INTO medicos (nombreCompleto, matricula, especialidad, precioConsulta) " +
                    "VALUES ('" + nombreCompleto + "', '" + matricula + "', '" + especialidad + "', '" + precioConsulta + "')";
			ConnectionFactory.transaccion(sql);
		} catch (SQLException e) {
			throw new DAOException("Hubo un error creando el medico en la BDD", e);
		}        
	}

	public Medico get(String matricula) throws DAOException {
		String sql = "SELECT * FROM medicos WHERE matricula = '" +matricula+ "'";
		Medico medico = null;

		Connection c = null;
		ResultSet rs = null;
		
		try {
			c = ConnectionFactory.connect();
			Statement s = c.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next()) {
				String nombreCompleto = rs.getString("nombreCompleto");
				String m = rs.getString("matricula");
				String especialidad = rs.getString("especialidad");
				double precioConsulta = rs.getDouble("precioConsulta");
				
				medico = new Medico(nombreCompleto, m, especialidad, precioConsulta);
				c.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Hubo un error creando el medico en la BDD", e);
		} 
		
		return medico;
	}

	public void update(Medico medico) throws DAOException {
		
		try {
			String nombreCompleto = medico.getNombreCompleto();
			String matricula = medico.getMatricula();
			String especialidad = medico.getEspecialidad();
			double precioConsulta = medico.getPrecioConsulta();
			
			String sql = "UPDATE medicos SET nombreCompleto = '" +nombreCompleto+ 
					"', especialidad = '" +especialidad+ "', precioConsulta = '" +
					precioConsulta+"' WHERE matricula = '"+matricula+ "'";
			ConnectionFactory.transaccion(sql);
		} catch (DAOException e) {
			throw new DAOException("Hubo un error al actualizar el medico", e);
		}
	}

	public void delete(String matricula) throws DAOException {
		try {
			String sql = "DELETE FROM medicos WHERE matricula = '" +matricula+ "'";
			ConnectionFactory.transaccion(sql);
		} catch (SQLException e) {
			throw new DAOException("Hubo un error creando el medico en la BDD", e);
		}
	}

	public List<Medico> getAll() throws DAOException {
		String sql = "SELECT * FROM medicos";
		List<Medico> lista = new ArrayList<>();
		//ResultSet rs = ConnectionFactory.consulta(sql); 
		
		Connection c = null;
		ResultSet rs = null;
		
		
		try {
			c = ConnectionFactory.connect();
			Statement s = c.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				String nombreCompleto = rs.getString("nombreCompleto");
				String matricula = rs.getString("matricula");
				String especialidad = rs.getString("especialidad");
				double precioConsulta = rs.getDouble("precioConsulta");
				
				Medico medico = new Medico(nombreCompleto, matricula, especialidad, precioConsulta);
				lista.add(medico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public boolean existeMatricula(String matricula) {
		Medico medico = null;
		try {
			medico = get(matricula);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return medico != null ? true : false;
	}

}
