package utn.cursojava.sistemabancario.services;

import java.util.List;

import utn.cursojava.sistemabancario.exceptions.BusinessException;
import utn.cursojava.sistemabancario.models.Cliente;

public interface IClienteBO {
	
	//public Cliente agregarCliente(Cliente cliente) throws BusinessException;

	public void agregarCliente(Cliente cliente, Integer numeroSucursal, Integer tipoCuenta) throws BusinessException;

	public List<Cliente> listarClientesPorSucursal() throws BusinessException;

	public List<Cliente> listarClientesDeUnaSucursal(Integer numeroSucursal) throws BusinessException;

	public void agregarCuenta(Long cuil, Integer tipoCuenta) throws BusinessException;

	public Cliente buscarPorCuil(Long cuil) throws BusinessException;

}
