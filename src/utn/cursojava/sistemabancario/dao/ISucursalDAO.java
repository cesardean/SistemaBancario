package utn.cursojava.sistemabancario.dao;

import java.util.List;

import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Cuenta;
import utn.cursojava.sistemabancario.models.Sucursal;

public interface ISucursalDAO {

	public List<Sucursal> getAll() throws PersistenceException;

	public Sucursal getByNumero(Integer numero) throws PersistenceException;

	void save(Sucursal sucursal) throws PersistenceException;

	void update(Sucursal sucursal) throws PersistenceException;

	boolean delete(Sucursal sucursal) throws PersistenceException;

	boolean delete(Integer numero) throws PersistenceException;
	
}
