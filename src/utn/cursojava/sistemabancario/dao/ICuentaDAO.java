package utn.cursojava.sistemabancario.dao;

import java.util.List;

import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.CajaAhorro;
import utn.cursojava.sistemabancario.models.Cuenta;

public interface ICuentaDAO {

	public List<Cuenta> getAll() throws PersistenceException;

	public Cuenta getByNumero(Long numero) throws PersistenceException;

	void save(Cuenta cuenta) throws PersistenceException;

	void update(Cuenta cuenta) throws PersistenceException;

	boolean delete(Cuenta cuenta) throws PersistenceException;

	boolean delete(Long numero) throws PersistenceException;

	List<Cuenta> getAllByCuilCliente(Long cuil) throws PersistenceException;
	
}
