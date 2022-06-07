package utn.cursojava.sistemabancario.models;

import java.util.List;

public class Banco {

	private Integer numero;
	private String nombre;
	private List<Sucursal> sucursales;
	private List<Cuenta> cuentas;	
	
	
	
	public Banco() {
		numero = 10;
		nombre = "BANCO DE LA UTN";
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	

}
