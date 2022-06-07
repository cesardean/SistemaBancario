package utn.cursojava.sistemabancario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Banco;
import utn.cursojava.sistemabancario.models.Conexion;
import utn.cursojava.sistemabancario.models.Sucursal;

public class SucursalDAOImpl implements ISucursalDAO{

	private final String DELETE="DELETE FROM SUCURSAL WHERE NUMERO=?";
	
	
	@Override
	public List<Sucursal> getAll() throws PersistenceException {
		Sucursal sucursal = new Sucursal();
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUCURSAL");
			while(rs.next()) {
				sucursal = new Sucursal();
				sucursal.setNumero(rs.getInt(1));
				sucursal.setNombre(rs.getString(2));	
				sucursales.add(sucursal);				
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return sucursales;
	}

	@Override
	public Sucursal getByNumero(Integer numero) throws PersistenceException {
		Sucursal sucursal = new Sucursal();
		try {						
			Statement stmt = Conexion.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUCURSAL WHERE NUMERO= '" + numero.toString() + "'");
			while(rs.next()) {
				sucursal.setNumero(rs.getInt(1));
				sucursal.setNombre(rs.getString(2));	
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}
		return sucursal;
	}

	@Override
	public void save(Sucursal sucursal) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Sucursal sucursal) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Sucursal sucursal) throws PersistenceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer numero) throws PersistenceException {
		PreparedStatement preparedStatement;
		Integer eliminado;
		try {						
			preparedStatement = Conexion.getConnection().prepareStatement(DELETE);
            preparedStatement.setInt(1, numero);            
            eliminado = preparedStatement.executeUpdate();                        
            if(eliminado == 1) {
            	return true;
            } else  if(eliminado == 0) {
            	return false;
            }
		} catch (SQLException e) {
			throw new PersistenceException("Error de base de datos : " + e.getMessage());
		}		
		return false;
	}

	}
