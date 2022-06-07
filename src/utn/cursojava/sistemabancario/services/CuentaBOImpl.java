package utn.cursojava.sistemabancario.services;

import utn.cursojava.sistemabancario.dao.CuentaDAOImpl;
import utn.cursojava.sistemabancario.dao.ICuentaDAO;
import utn.cursojava.sistemabancario.exceptions.BusinessException;
import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Cuenta;

public class CuentaBOImpl implements ICuentaBO {

	ICuentaDAO cuentaDAO = new CuentaDAOImpl();
	
	@Override
	public void transferir(Long numeroCuentaOrigen, Long numeroCuentaOrigen2, Double dinero) throws BusinessException {
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		try {
			cuentaOrigen = cuentaDAO.getByNumero(numeroCuentaOrigen);		
			cuentaDestino = cuentaDAO.getByNumero(numeroCuentaOrigen2);	
			if(cuentaOrigen.getSaldo() >= dinero) { 
				cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()- dinero);
				cuentaDestino.setSaldo(cuentaDestino.getSaldo() + dinero);
				cuentaDAO.update(cuentaOrigen);
				cuentaDAO.update(cuentaDestino);
			} else {
				throw new BusinessException("El dinero disponible no es suficiente para transferir");
			}		
		} catch (PersistenceException e1) {
			throw new BusinessException("Error al intentar obtener una de las cuentas" + e1.getMessage());
		}		
	}

	@Override
	public void depositar(Long numero, Double dinero) throws BusinessException {
		Cuenta cuenta = new Cuenta();
		try {
			cuenta = cuentaDAO.getByNumero(numero);			
			cuenta.setSaldo(cuenta.getSaldo() + dinero);
			cuentaDAO.update(cuenta);									
		} catch (PersistenceException e1) {
			throw new BusinessException("Error al intentar obtener la cuenta numero " + numero + e1.getMessage());
		}
		
	}

	@Override
	public Cuenta buscarCuenta(Long numero) throws BusinessException {
		try {
			return cuentaDAO.getByNumero(numero);
		} catch (PersistenceException e) {
			throw new BusinessException("Error al intentar obtener la cuenta numero " + numero + e.getMessage());
		}
	}

	@Override
	public void extraerDinero(Long numero, Double dinero) throws BusinessException {
		Cuenta cuenta = new Cuenta();
		try {
			cuenta = cuentaDAO.getByNumero(numero);
			if(cuenta.getSaldo() >= dinero) { 
				cuenta.setSaldo(cuenta.getSaldo()- dinero);
				cuentaDAO.update(cuenta);				
			} else {
				throw new BusinessException("El dinero disponible no es suficiente");
			}			
		} catch (PersistenceException e1) {
			throw new BusinessException("Error al intentar obtener la cuenta numero " + numero + e1.getMessage());
		}
		
	}

}
