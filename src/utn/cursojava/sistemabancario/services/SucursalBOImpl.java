package utn.cursojava.sistemabancario.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utn.cursojava.sistemabancario.dao.ClienteDAOImpl;
import utn.cursojava.sistemabancario.dao.IClienteDAO;
import utn.cursojava.sistemabancario.dao.ISucursalDAO;
import utn.cursojava.sistemabancario.dao.SucursalDAOImpl;
import utn.cursojava.sistemabancario.exceptions.BusinessException;
import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Cliente;
import utn.cursojava.sistemabancario.models.Sucursal;

public class SucursalBOImpl implements ISucursalBO {

	ISucursalDAO sucursalDAO = new SucursalDAOImpl();
	IClienteDAO clienteDAO = new ClienteDAOImpl();
	
	@Override
	public void eliminarSucursal(Integer numeroSucursalEliminar, Integer numeroSucursalTranspaso) throws BusinessException {
		Sucursal sucursalEliminar = new Sucursal();
		Sucursal sucursalTraslado = new Sucursal();
		List<Cliente> clientes = new ArrayList<>();
		Iterator<Cliente> iterator;
		Cliente cliente;
		try {
			sucursalEliminar = sucursalDAO.getByNumero(numeroSucursalEliminar);
			sucursalTraslado = sucursalDAO.getByNumero(numeroSucursalTranspaso);
			clientes = clienteDAO.getBySucursal(numeroSucursalEliminar);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar obtener una sucursal");
		}	
		iterator = clientes.iterator();
		while(iterator.hasNext()) {
			cliente = iterator.next();
			cliente.setSucursal(sucursalTraslado);
			try {
				clienteDAO.updateSucursal(cliente);			
			} catch (PersistenceException e) {
				throw new BusinessException("Error al intentar actualizar la sucursal del cliente cuyo cuil es " + cliente.getCuil());
			}			
		}
		try {
			sucursalDAO.delete(numeroSucursalEliminar);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar eliminar la sucursal numero " + numeroSucursalEliminar);
		}		
	}

	@Override
	public List<Sucursal> listarSucursales() throws BusinessException {
		try {
			return sucursalDAO.getAll();
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar obtener las sucursales");
		}
	}
	

}
