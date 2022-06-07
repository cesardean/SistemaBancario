package utn.cursojava.sistemabancario.models;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {

	private Integer numero;
	private String nombre;	
	private Banco banco; 
	private List<Cliente> clientes;
	private List<Empleado> empleados;
	
	public Sucursal() {
		clientes = new ArrayList<Cliente>();
		empleados = new ArrayList<Empleado>();
		banco = new Banco();
	}
	
	
	
	public Banco getBanco() {
		return banco;
	}



	public void setBanco(Banco banco) {
		this.banco = banco;
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

}
