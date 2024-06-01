package TurneraMedicaTP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mec1303";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/turnera";
	
	public static Connection connect() throws SQLException, ClassNotFoundException {
		//DEBERIA GESTIONAR LAS EXCEPTIONS
		
		
		Class.forName(DB_DRIVER);
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		connection.setAutoCommit(false);
		return connection;
	}
	
	
	public static void transaccion(String sentenciaSQL) {
		Connection c = null;
		
		try {
			c = connect();
			Statement s = c.createStatement();
			s.executeUpdate(sentenciaSQL);
			c.commit();
			System.out.println("Transaccion realizada con exito!");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				if (c != null) {
					c.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static ResultSet consulta(String sentenciaSQL) {
		Connection c = null;
		ResultSet rs = null;
		
		
		try {
			c = connect();
			Statement s = c.createStatement();
			rs = s.executeQuery(sentenciaSQL);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;

	}
	
	
	public static void main(String[] args) throws Exception{
		Connection con = connect();
		if (con != null) {
			System.out.println("Conectado con exito");
			con.close();
		}
	}
}
