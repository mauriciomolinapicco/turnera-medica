package TurneraMedicaTP;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionFactory {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345678";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/turnera";
	
	public static Connection connect() throws DAOException{
		Connection connection = null;

		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			connection.setAutoCommit(false);
		}  catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("No se encontro el driver de la base de datos", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error al conectar a la base de datos", e);
        }
		
		return connection;
	}
	
	
	public static void transaccion(String sentenciaSQL) throws DAOException {
		Connection c = null;
		
		try {
			c = connect();
			Statement s = c.createStatement();
			s.executeUpdate(sentenciaSQL);
			c.commit();
			System.out.println("Transaccion realizada con exito!");
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollbackConexion(c);
			throw new DAOException("Hubo un error durante la transaccion");
		} finally {
			cerrarConexion(c);
		}
	}
	
	
	public static List<Object> consulta(String sentenciaSQL, ArrayList<String> nombreColumnas, Class<?> claseEntidad) throws DAOException {
	    Connection c = null;
	    ResultSet rs = null;
	    List<Object> lista = new ArrayList<>();

	    try {
	        c = connect();
	        Statement s = c.createStatement();
	        rs = s.executeQuery(sentenciaSQL);
	        while (rs.next()) {
	            List<Object> parametros = new ArrayList<>();
	            for (String nombreColumna : nombreColumnas) {
	                Object valor = rs.getObject(nombreColumna);
	                parametros.add(valor);
	            }

	            Object[] arrayParametros = parametros.toArray();

	            Constructor<?> constructor = claseEntidad.getConstructor(
	                parametros.stream().map(Object::getClass).toArray(Class[]::new)
	            );

	            // Crear una nueva instancia de la entidad
	            Object entidad = constructor.newInstance(arrayParametros);
	            lista.add(entidad);
	        }
	    } catch (Exception e) {
	        rollbackConexion(c);
	        throw new DAOException("Hubo un error durante la consulta");
	    } finally {
	        cerrarConexion(c);
	    }
	    return lista;
	}
	
	
	public static void cerrarConexion(Connection connection) throws DAOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Hubo un error cerrando la conexion", e);
            }
        }
    }

	
	public static void rollbackConexion(Connection connection) throws DAOException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Hubo un error haciendo el rollback", e);
            }
        }
    }

}
