package utn.cursojava.sistemabancario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Banco;
import utn.cursojava.sistemabancario.models.Cliente;
import utn.cursojava.sistemabancario.models.Conexion;
import utn.cursojava.sistemabancario.models.Sucursal;

public class ClienteDAOImpl implements IClienteDAO{
	ICuentaDAO cuentaDAO = new CuentaDAOImpl();
	//ISucursalDAO sucursalDAO = new SucursalDAOImpl();
	private final String UPDATE="UPDATE CLIENTE SET SUCURSAL_NUMERO=? WHERE CUIL=?";	
	private final String INSERT= "INSERT INTO CLIENTE(CUIL, NOMBRE, APELLIDO, NUMERO_TELEFONO, EMAIL, FECHA_ALTA, DOMICILIO_LEGAL, SUCURSAL_NUMERO)VALUES (?,?,?,?,?,?,?,?)";
	
	@Override
	public void save(Cliente cliente) throws PersistenceException {
		String pattern = "yyyy-MM-dd";  //hh:mm:ss
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		ResultSet rs;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = Conexion.getConnection().prepareStatement(INSERT);
			preparedStatement.setLong(1, cliente.getCuil());			
			preparedStatement.setString(2, cliente.getNombre());
			preparedStatement.setString(3,cliente.getApellido());
			preparedStatement.setString(4, cliente.getNumeroTelefono());
			preparedStatement.setString(5, cliente.getEmail());
			preparedStatement.setString(6, date);
			preparedStatement.setString(7, cliente.getDomicilioLegal());
			preparedStatement.setInt(8, cliente.getSucursal().getNumero());
			preparedStatement.executeUpdate();     
			/*
			 * Statement stmt = Conexion.getConnection().createStatement();
			 * stmt.executeQuery("INSERT CLIENTE VALUES ('" + cliente.getCuil() + "', '" +
			 * cliente.getNombre() + "', '" + cliente.getApellido() + "', '" +
			 * cliente.getNumeroTelefono() + "', '" + cliente.getEmail() + "', '" + date +
			 * "', '" + cliente.getDomicilioLegal() + "', '" +
			 * cliente.getSucursal().getNumero() + "' );");
			 */		
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
	}

	@Override
	public Cliente getByCuil(Long cuil) throws PersistenceException {		
		Cliente cliente = new Cliente();
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE WHERE CUIL = '" + cuil.toString() + "'");
			while(rs.next()) {
				cliente = new Cliente();
				cliente.setCuil(rs.getLong(1));
				cliente.setNombre(rs.getString(2));
				cliente.setApellido(rs.getString(3));
				cliente.setNumeroTelefono(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setFechaAlta(rs.getDate(6));
				cliente.setDomicilioLegal(rs.getString(7));				
				cliente.getSucursal().setNumero(rs.getInt(8));		
				cliente.setCuentas(cuentaDAO.getAllByCuilCliente(cliente.getCuil()));
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return cliente;
	}

	@Override
	public boolean delete(Long cuil) throws PersistenceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> getAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getBySucursal(Integer numeroSucursal) throws PersistenceException {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente;
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE WHERE SUCURSAL_NUMERO = '" + numeroSucursal.toString() + "'");
			while(rs.next()) {
				cliente = new Cliente();
				cliente.setCuil(rs.getLong(1));
				cliente.setNombre(rs.getString(2));
				cliente.setApellido(rs.getString(3));
				cliente.setNumeroTelefono(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setFechaAlta(rs.getDate(6));
				cliente.setDomicilioLegal(rs.getString(7));				
				cliente.getSucursal().setNumero(rs.getInt(8));	
				cliente.setCuentas(cuentaDAO.getAllByCuilCliente(cliente.getCuil()));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return clientes;
	}

	@Override
	public List<Cliente> getAllOrderBySucursal() throws PersistenceException {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente;
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE ORDER BY SUCURSAL_NUMERO ASC");
			while(rs.next()) {
				
				cliente = new Cliente();
				cliente.setCuil(rs.getLong(1));
				cliente.setNombre(rs.getString(2));
				cliente.setApellido(rs.getString(3));
				cliente.setNumeroTelefono(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setFechaAlta(rs.getDate(6));
				cliente.setDomicilioLegal(rs.getString(7));				
				cliente.getSucursal().setNumero(rs.getInt(8));	
				cliente.setCuentas(cuentaDAO.getAllByCuilCliente(cliente.getCuil()));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return clientes;
	}

	@Override
	public void updateSucursal(Cliente cliente) throws PersistenceException {
		PreparedStatement preparedStatement;
		try {						
			preparedStatement = Conexion.getConnection().prepareStatement(UPDATE);
            preparedStatement.setInt(1, cliente.getSucursal().getNumero());
            preparedStatement.setLong(2, cliente.getCuil());
            preparedStatement.executeUpdate();            
			// Statement stmt = Conexion.getConnection().createStatement();
			// ResultSet rs = stmt.executeQuery("UPDATE CLIENTE SET SUCURSAL_NUMERO = '" + cliente.getSucursal().getNumero() + "', WHERE CUIL = '" + cliente.getCuil() + "' );");	
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}				
	}

}

