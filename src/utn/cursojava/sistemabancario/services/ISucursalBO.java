package utn.cursojava.sistemabancario.services;

import java.util.List;

import utn.cursojava.sistemabancario.exceptions.BusinessException;
import utn.cursojava.sistemabancario.models.Sucursal;

public interface ISucursalBO {

	void eliminarSucursal(Integer numeroSucursalEliminar, Integer numeroSucursalTranspaso) throws BusinessException ;

	List<Sucursal> listarSucursales() throws BusinessException ;

}
