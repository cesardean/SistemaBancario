package utn.cursojava.sistemabancario.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=DBUTN_SISTEMABANCARIO;encrypt=true;trustServerCertificate=true;";
	private static final String USER = "dbdesa";
	private static final String PASS = "dbdesa";	
	
	
	public static Connection getConnection() throws SQLException{
		Connection con = null;
		try {
			// Registro del driver 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			con = (Connection) DriverManager.getConnection(URL, USER, PASS);
		} catch(SQLException e) {
			throw new SQLException("Error al intentar conectarse con la base de datos: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	
}
	
