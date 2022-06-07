package utn.cursojava.sistemabancario.models;

import utn.cursojava.sistemabancario.constants.Moneda;
import utn.cursojava.sistemabancario.constants.TipoCuenta;

public class Cuenta {
	
	private Long numero;
	private String cbu;
	private Cliente cliente;	
	private Moneda moneda;
	private TipoCuenta tipoCuenta;
	private Double saldo;
	private Banco banco;
	
	public Cuenta() {
		banco = new Banco();
		cliente = new Cliente();
	}		
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	
	
	
}
