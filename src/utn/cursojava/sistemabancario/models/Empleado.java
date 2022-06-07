package utn.cursojava.sistemabancario.models;

import java.util.Date;

public class Empleado extends Persona {

	private String legajo;
	private Date fechaIngres;
	private Sucursal sucursal;

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public Date getFechaIngres() {
		return fechaIngres;
	}

	public void setFechaIngres(Date fechaIngres) {
		this.fechaIngres = fechaIngres;
	}

}
