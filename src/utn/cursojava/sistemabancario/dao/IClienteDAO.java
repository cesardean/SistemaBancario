package utn.cursojava.sistemabancario.dao;

import java.util.List;

import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Cliente;

public interface IClienteDAO {
	
	public Cliente getByCuil(Long cuil) throws PersistenceException;
	
	public void save(Cliente cliente) throws PersistenceException;
	
	public boolean delete(Long cuil) throws PersistenceException;
	
	public List<Cliente> getAll() throws PersistenceException;
	
	public List<Cliente> getBySucursal(Integer numeroSucursal) throws PersistenceException;
	
	public List<Cliente> getAllOrderBySucursal() throws PersistenceException;

	public void updateSucursal(Cliente cliente) throws PersistenceException;	
	
}
