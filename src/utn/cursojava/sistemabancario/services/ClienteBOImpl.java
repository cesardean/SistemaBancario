package utn.cursojava.sistemabancario.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utn.cursojava.sistemabancario.constants.Moneda;
import utn.cursojava.sistemabancario.constants.TipoCuenta;
import utn.cursojava.sistemabancario.dao.ClienteDAOImpl;
import utn.cursojava.sistemabancario.dao.CuentaDAOImpl;
import utn.cursojava.sistemabancario.dao.IClienteDAO;
import utn.cursojava.sistemabancario.dao.ICuentaDAO;
import utn.cursojava.sistemabancario.dao.ISucursalDAO;
import utn.cursojava.sistemabancario.dao.SucursalDAOImpl;
import utn.cursojava.sistemabancario.exceptions.BusinessException;
import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Banco;
import utn.cursojava.sistemabancario.models.CajaAhorro;
import utn.cursojava.sistemabancario.models.Cliente;
import utn.cursojava.sistemabancario.models.Cuenta;
import utn.cursojava.sistemabancario.models.Sucursal;

public class ClienteBOImpl implements IClienteBO {

	IClienteDAO clienteDAO = new ClienteDAOImpl();
	ISucursalDAO sucursalDAO = new SucursalDAOImpl();	
	ICuentaDAO cuentaDAO = new CuentaDAOImpl();
	
	@Override
	public void agregarCliente(Cliente cliente, Integer numeroSucursal, Integer tipoCuenta) throws BusinessException {
		Sucursal sucursal = new Sucursal();
		Cuenta cuenta = new Cuenta();
		cliente.setFechaAlta(new Date());		
		try {
			sucursal = sucursalDAO.getByNumero(numeroSucursal);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar obtener una sucursal"+ e.getMessage());
		}
		cliente.setSucursal(sucursal);
		switch(tipoCuenta) {
		case 1:			
			cuenta.setMoneda(Moneda.PESO);
			cuenta.setTipoCuenta(TipoCuenta.CA);				
			break;
		case 2:
			cuenta.setMoneda(Moneda.DOLAR);
			cuenta.setTipoCuenta(TipoCuenta.CA);	
			break;
		case 3:
			cuenta.setMoneda(Moneda.PESO);
			cuenta.setTipoCuenta(TipoCuenta.CC);	
			break;
		case 4:
			cuenta.setMoneda(Moneda.DOLAR);
			cuenta.setTipoCuenta(TipoCuenta.CC);	
			break;
		}
		cuenta.setCliente(cliente);
		cuenta.setSaldo(0D);
		cliente.getCuentas().add(cuenta);	
		try {
			clienteDAO.save(cliente);
			cuentaDAO.save(cuenta);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar guardar un cliente" + e.getMessage());
		}
	}

	@Override
	public List<Cliente> listarClientesPorSucursal() throws BusinessException {
		 List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = clienteDAO.getAllOrderBySucursal();
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar obtener los clientes"+ e.getMessage());
		}
		return clientes;
	}

	@Override
	public List<Cliente> listarClientesDeUnaSucursal(Integer numeroSucursal) throws BusinessException {
		try {
			return clienteDAO.getBySucursal(numeroSucursal);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar obtener los clientes"+ e.getMessage());
		}
	}

	@Override
	public void agregarCuenta(Long cuil, Integer tipoCuenta) throws BusinessException {
		Cliente cliente;
		Cuenta cuenta = new Cuenta();
		try {
			cliente = clienteDAO.getByCuil(cuil);
		} catch (PersistenceException e1) {
			throw new BusinessException("Error al intentar obtener un cliente por su cuit" + e1.getMessage());
		}
		switch(tipoCuenta) {
		case 1:			
			cuenta.setMoneda(Moneda.PESO);
			cuenta.setTipoCuenta(TipoCuenta.CA);				
			break;
		case 2:
			cuenta.setMoneda(Moneda.DOLAR);
			cuenta.setTipoCuenta(TipoCuenta.CA);	
			break;
		case 3:
			cuenta.setMoneda(Moneda.PESO);
			cuenta.setTipoCuenta(TipoCuenta.CC);	
			break;
		case 4:
			cuenta.setMoneda(Moneda.DOLAR);
			cuenta.setTipoCuenta(TipoCuenta.CC);	
			break;
		}		
		cuenta.setCliente(cliente);
		cuenta.setSaldo(0D);
		try {
			cuentaDAO.save(cuenta);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar guardar una cuenta"+ e.getMessage());
		}						
	}

	@Override
	public Cliente buscarPorCuil(Long cuil) throws BusinessException {
		try {
			return clienteDAO.getByCuil(cuil);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar obtener los clientes"+ e.getMessage());
		}
	}
}
