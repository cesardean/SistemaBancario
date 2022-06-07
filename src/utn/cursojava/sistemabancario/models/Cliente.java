package utn.cursojava.sistemabancario.models;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Persona {
	
	private Date fechaAlta;
	private String domicilioLegal;
	private Sucursal sucursal;
	private List<Cuenta> cuentas;
			
	public Cliente() {
		cuentas = new ArrayList<>();
		sucursal = new Sucursal();
	}

	public String getDomicilioLegal() {
		return domicilioLegal;
	}

	public void setDomicilioLegal(String domicilioLegal) {
		this.domicilioLegal = domicilioLegal;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return  ", Sucursal: " + getSucursal().getNumero() + ", Cuil: " + getCuil() + ", " + getApellido() + " " + getNombre() + ", Domicilio: " + getDomicilioLegal() 
				+ ", " + getEmail() + ", Numero Telefono: " + getNumeroTelefono() + ", Fecha Alta: " + getFechaAlta();
	}
	
	

}
