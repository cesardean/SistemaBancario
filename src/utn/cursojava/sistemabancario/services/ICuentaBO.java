package utn.cursojava.sistemabancario.services;

import utn.cursojava.sistemabancario.exceptions.BusinessException;
import utn.cursojava.sistemabancario.models.Cuenta;

public interface ICuentaBO {

	public void transferir(Long numeroCuentaOrigen, Long numeroCuentaOrigen2, Double dinero) throws BusinessException;

	public void depositar(Long numero, Double dinero) throws BusinessException;

	public Cuenta buscarCuenta(Long numero) throws BusinessException;

	public void extraerDinero(Long numero, Double dinero) throws BusinessException;

}
