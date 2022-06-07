package utn.cursojava.sistemabancario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utn.cursojava.sistemabancario.constants.Moneda;
import utn.cursojava.sistemabancario.constants.TipoCuenta;
import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.CajaAhorro;
import utn.cursojava.sistemabancario.models.Cliente;
import utn.cursojava.sistemabancario.models.Conexion;
import utn.cursojava.sistemabancario.models.Cuenta;

public class CuentaDAOImpl implements ICuentaDAO{

	
	private final String UPDATE="UPDATE CUENTA SET SALDO=? WHERE NUMERO=?";
	private final String INSERT= "INSERT INTO CUENTA(CBU, MONEDA, TIPO_CUENTA, SALDO, CLIENTE_CUIL)VALUES (?,?,?,?,?)";
	
	@Override
	public List<Cuenta> getAll() throws PersistenceException {
		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUENTA");
			while(rs.next()) {		
				cuenta.setNumero(rs.getLong(1));
				cuenta.setCbu(rs.getString(2));
				if(rs.getInt(3) == 1)
					cuenta.setMoneda(Moneda.PESO);
				else 
					cuenta.setMoneda(Moneda.DOLAR);
				switch(rs.getInt(4)) {
				case 1:
					cuenta.setTipoCuenta(TipoCuenta.CA);
					break;
				case 2:
					cuenta.setTipoCuenta(TipoCuenta.CA);
					break;
				case 3:
					cuenta.setTipoCuenta(TipoCuenta.CC);
					break;
				case 4:
					cuenta.setTipoCuenta(TipoCuenta.CC);
					break;
				}				
				cuenta.setSaldo(rs.getDouble(5));
				
				cuenta.getCliente().setCuil(rs.getLong(6));		
				cuentas.add(cuenta);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return cuentas;
	}
	
	@Override
	public List<Cuenta> getAllByCuilCliente(Long cuil) throws PersistenceException {
		Cuenta cuenta = new Cuenta();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUENTA WHERE CLIENTE_CUIL = '" + cuil + "'");
			while(rs.next()) {		
				cuenta.setNumero(rs.getLong(1));
				cuenta.setCbu(rs.getString(2));
				if(rs.getInt(3) == 1)
					cuenta.setMoneda(Moneda.PESO);
				else 
					cuenta.setMoneda(Moneda.DOLAR);
				switch(rs.getInt(4)) {
				case 1:
					cuenta.setTipoCuenta(TipoCuenta.CA);
					break;
				case 2:
					cuenta.setTipoCuenta(TipoCuenta.CA);
					break;
				case 3:
					cuenta.setTipoCuenta(TipoCuenta.CC);
					break;
				case 4:
					cuenta.setTipoCuenta(TipoCuenta.CC);
					break;
				}				
				cuenta.setSaldo(rs.getDouble(5));
				cuenta.getCliente().setCuil(rs.getLong(6));		
				cuentas.add(cuenta);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return cuentas;
	}

	@Override
	public Cuenta getByNumero(Long numero) throws PersistenceException {
		Cuenta cuenta = new Cuenta();
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUENTA WHERE NUMERO = '" + numero + "'");
			while(rs.next()) {		
				cuenta.setNumero(rs.getLong(1));
				cuenta.setCbu(rs.getString(2));
				if(rs.getInt(3) == 1)
					cuenta.setMoneda(Moneda.PESO);
				else 
					cuenta.setMoneda(Moneda.DOLAR);
				switch(rs.getInt(4)) {
				case 1:
					cuenta.setTipoCuenta(TipoCuenta.CA);
					break;
				case 2:
					cuenta.setTipoCuenta(TipoCuenta.CA);
					break;
				case 3:
					cuenta.setTipoCuenta(TipoCuenta.CC);
					break;
				case 4:
					cuenta.setTipoCuenta(TipoCuenta.CC);
					break;
				}				
				cuenta.setSaldo(rs.getDouble(5));
				cuenta.getCliente().setCuil(rs.getLong(6));		
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return cuenta;
	}

	@Override
	public void save(Cuenta cuenta) throws PersistenceException {	
		PreparedStatement preparedStatement;
		try {					
			preparedStatement= Conexion.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1,cuenta.getCbu());
            preparedStatement.setInt(2,cuenta.getMoneda().ordinal());
            preparedStatement.setInt(3,cuenta.getTipoCuenta().ordinal());
            preparedStatement.setDouble(4,cuenta.getSaldo());
            preparedStatement.setLong(5, cuenta.getCliente().getCuil());
            preparedStatement.executeUpdate();            
			/*
			 * try { Statement stmt = Conexion.getConnection().createStatement(); ResultSet
			 * rs = stmt.executeQuery("INSERT CUENTA VALUES ( NULL, CBU, '" + "', '" +
			 * cajaAhorro.getMoneda() + "', '" + cajaAhorro.getSaldo() + "', '" +
			 * cajaAhorro.getCliente().getCuil() + "', NULL '" + "' );");
			 */
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
	}

	@Override
	public void update(Cuenta cuenta) throws PersistenceException {
		PreparedStatement preparedStatement;
		try {					
			preparedStatement= Conexion.getConnection().prepareStatement(UPDATE);
            preparedStatement.setDouble(1,cuenta.getSaldo());
            preparedStatement.setLong(2,cuenta.getNumero());
            preparedStatement.executeUpdate();
			//Statement stmt = Conexion.getConnection().createStatement();			
			//stmt.executeQuery("UPDATE CUENTA SET SALDO = " + cuenta.getSaldo() + " WHERE NUMERO = " + cuenta.getNumero());	            
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}	
		
	}

	@Override
	public boolean delete(Cuenta cajaAhorro) throws PersistenceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long numero) throws PersistenceException {
		// TODO Auto-generated method stub
		return false;
	}

}
