package utn.cursojava.sistemabancario;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import utn.cursojava.sistemabancario.dao.ISucursalDAO;
import utn.cursojava.sistemabancario.dao.SucursalDAOImpl;
import utn.cursojava.sistemabancario.exceptions.BusinessException;
import utn.cursojava.sistemabancario.exceptions.PersistenceException;
import utn.cursojava.sistemabancario.models.Cliente;
import utn.cursojava.sistemabancario.models.Cuenta;
import utn.cursojava.sistemabancario.models.Sucursal;
import utn.cursojava.sistemabancario.services.ClienteBOImpl;
import utn.cursojava.sistemabancario.services.CuentaBOImpl;
import utn.cursojava.sistemabancario.services.IClienteBO;
import utn.cursojava.sistemabancario.services.ICuentaBO;
import utn.cursojava.sistemabancario.services.ISucursalBO;
import utn.cursojava.sistemabancario.services.SucursalBOImpl;

/**
 * <h1> Academia Softtek UTN - Clase N° 12 </h1>  
 * <br>
 * 
 * TRABAJO PRÁCTICO POO
 * Tomando como punto de partida el diagrama de Clases del sistema de Gestión Bancaria construya una aplicación que permita realizar las siguientes operaciones:
 * *********MENU PRINCIPAL**********
 * 1) Agregar Cliente
 * 2) Agregar cuenta a Cliente
 * 3) Listar Clientes por sucursal
 * 4) Listar Clientes de una sucursal
 * 5) Extraer dinero
 * 6) Consultar Saldo
 * 7) Realizar Deposito
 * 8) Realizar transferencias
 * 9) Eliminar una sucursal
 * *********MENU CLIENTE-CUENTA**********
 * 1) Alta de Caja de Ahorro en Pesos
 * 2) Alta de Caja de Ahorro en dólares
 * 3) Alta de Cuenta Corriente en Pesos
 * 4) Alta de Cuenta Corriente en dólares
 * 
 * @author Cesar
 * @version 1.0.1
 */
public class Main {

	public static void main(java.lang.String[] args) {

		IClienteBO clienteBO = new ClienteBOImpl();
		ICuentaBO cuentaBO = new CuentaBOImpl();
		ISucursalBO sucursalBO = new SucursalBOImpl();

		Scanner input = new Scanner(System.in);
		int opcion = 5;
		boolean salir = false;		
		while (salir == false) {
			mostrarMenu();
			try {
				opcion = input.nextInt();
				if (0 < opcion && opcion <= 10) {					
					switch (opcion) {
					case 1:
						agregarCliente(clienteBO);						
						break;
					case 2:
						agregarCuentaACliente(clienteBO);
						break;
					case 3:
						listarClientesPorSucursal(clienteBO);
						break;
					case 4:
						listarClientesDeUnaSucursal(clienteBO);
						break;
					case 5:
						extraerDinero(clienteBO, cuentaBO);
						break;
					case 6:
						consultarSaldo(clienteBO, cuentaBO);
						break;
					case 7:
						realizarDeposito(clienteBO, cuentaBO);
						break;
					case 8:
						realizarTransferencia(cuentaBO);
						break;
					case 9:
						eliminarSucursal(sucursalBO);
						break;
					case 10:
						salir = true;
						break;
					}
				} else {
					System.out.println("Ingrese un numero entre 1 y 10");
				}
			} catch (InputMismatchException e) {
				System.out.println("El carater ingresado no es valido");
			}
			input.nextLine();
		}		
	}

	/**
	 * Este metodo se encarga de listar las sucursales del banco y 
	 * permitir al usuario que ingrese el numero de una sucursal a eliminar y el numero
	 * de otra sucursal donde se transladarán los cliente.
	 * @return void
	 */
	private static void eliminarSucursal(ISucursalBO sucursalBO) {
		List<Sucursal> sucursales = new ArrayList<>();
		Integer numeroSucursalEliminar;
		Integer numeroSucursalTranspaso;
		Sucursal sucursal = new Sucursal();
		System.out.println("Lista de Sucursales");
		try {
			sucursales = sucursalBO.listarSucursales();
			Iterator<Sucursal> iterator;
			iterator = sucursales.iterator();
			while (iterator.hasNext()) {
				sucursal = iterator.next();
				System.out.println("Numero: " + sucursal.getNumero() + " Nombre: " + sucursal.getNombre());
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el número de sucursal que desea eliminar");
		numeroSucursalEliminar = input.nextInt();
		System.out.println("Ingrese el número de sucursal donde van a trasladarse los clientes");
		numeroSucursalTranspaso = input.nextInt();
		try {
			sucursalBO.eliminarSucursal(numeroSucursalEliminar, numeroSucursalTranspaso);
			System.out.println("Se elimino exitosamente la sucursal numero: " + numeroSucursalEliminar);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Este metodo permite registrar una transferencia entre dos cuentas bancarias.
	 * Para ello permite al usuario ingresar el numero de la cuenta de origen y destino y el importe a transferir
	 * @return void
	 */
	private static void realizarTransferencia(ICuentaBO cuentaBO) {
		Long numeroCuentaOrigen;
		Long numeroCuentaDestino;
		Double dinero;
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el número de la cuenta de origen");
		numeroCuentaOrigen = input.nextLong();
		System.out.println("Ingrese el número de la cuenta destino");
		numeroCuentaOrigen = input.nextLong();
		System.out.println("Ingrese el importe a transferir");
		dinero = input.nextDouble();
		try {
			cuentaBO.transferir(numeroCuentaOrigen, numeroCuentaOrigen, dinero);
			System.out.println("Se transfirio exitosamente la suma de dinero " + dinero + " de la cuenta numero: "
					+ numeroCuentaOrigen);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Este metodo se encarga registrar un deposito de dinero en la cuenta de un cliente
	 * Para ello el usuario debe ingresar el cuil del cliente, luego el sistema listará las cuentas de dicho cliente.
	 * El usuario deberá ingresar el numero de cuenta donde va a realizar el deposito y el importe del mismo.
	 * @return void
	 */
	private static void realizarDeposito(IClienteBO clienteBO, ICuentaBO cuentaBO) {
		Long cuil;
		Double dinero;
		Cuenta cuenta;
		Long numero;
		Scanner input = new Scanner(System.in);
		Cliente cliente = new Cliente();
		Iterator iterator;
		System.out.println("**********MENU CLIENTE-CUENTA**********");
		System.out.println("Ingrese el cuil del cliente");
		cuil = input.nextLong();
		try {
			cliente = clienteBO.buscarPorCuil(cuil);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cliente.toString());
		System.out.println("**********CUENTAS**********");
		iterator = cliente.getCuentas().iterator();
		while (iterator.hasNext()) {
			cuenta = (Cuenta) iterator.next();
			System.out.println("Cuenta Nro: " + cuenta.getNumero() + " Tipo " + cuenta.getTipoCuenta() + " Moneda "
					+ cuenta.getMoneda() + " Saldo " + cuenta.getSaldo());
		}
		System.out.println("Ingrese el numero de cuenta");
		numero = input.nextLong();
		System.out.println("Ingrese el importe a depositar");
		dinero = input.nextDouble();
		try {
			cuentaBO.depositar(numero, dinero);
			cuenta = cuentaBO.buscarCuenta(numero);
			System.out.println("Información del saldo de cuenta");
			System.out.println("Cuenta Nro: " + cuenta.getNumero() + " Tipo " + cuenta.getTipoCuenta() + " Moneda "
					+ cuenta.getMoneda() + " Saldo " + cuenta.getSaldo());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Este metodo se encarga consultar de dinero disponible en la cuenta de un cliente
	 * Para ello el usuario debe ingresar el cuil del cliente, luego el sistema listará las cuentas de dicho cliente.
	 * El usuario deberá ingresar el numero de cuenta a consultar.
	 * @return void
	 */
	private static void consultarSaldo(IClienteBO clienteBO, ICuentaBO cuentaBO) {
		Long cuil;
		Long numero;
		Cuenta cuenta;
		Scanner input = new Scanner(System.in);
		Cliente cliente = new Cliente();
		Iterator iterator;
		System.out.println("**********MENU CLIENTE-CUENTA**********");
		System.out.println("Ingrese el cuil del cliente");
		cuil = input.nextLong();
		try {
			cliente = clienteBO.buscarPorCuil(cuil);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cliente.toString());
		System.out.println("**********CUENTAS**********");
		iterator = cliente.getCuentas().iterator();
		while (iterator.hasNext()) {
			cuenta = (Cuenta) iterator.next();
			System.out.println("Cuenta Nro: " + cuenta.getNumero() + " Tipo " + cuenta.getTipoCuenta() + " Moneda "
					+ cuenta.getMoneda() + " CBU " + cuenta.getCbu() + " CUIL DEL CLIENTE "
					+ cuenta.getCliente().getCuil());
		}
		System.out.println("Ingrese el numero de cuenta a consultar");
		numero = input.nextLong();

		try {
			cuenta = cuentaBO.buscarCuenta(numero);
			System.out.println("El saldo de la cuenta es: " + cuenta.getSaldo());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Este metodo se encarga registrar una extracción de dinero en la cuenta de un cliente
	 * Para ello el usuario debe ingresar el cuil del cliente, luego el sistema listará las cuentas de dicho cliente.
	 * El usuario deberá ingresar el numero de cuenta donde va a realizar la extracción y el importe de la misma.
	 * @return void
	 */
	private static void extraerDinero(IClienteBO clienteBO, ICuentaBO cuentaBO) {
		Long cuil;
		Double dinero;
		Cuenta cuenta;
		Long numero;
		Scanner input = new Scanner(System.in);
		Cliente cliente = new Cliente();
		Iterator iterator;
		System.out.println("**********MENU CLIENTE-CUENTA**********");
		System.out.println("Ingrese el cuil del cliente");
		cuil = input.nextLong();
		try {
			cliente = clienteBO.buscarPorCuil(cuil);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cliente.toString());
		System.out.println("**********CUENTAS**********");
		iterator = cliente.getCuentas().iterator();
		while (iterator.hasNext()) {
			cuenta = (Cuenta) iterator.next();
			System.out.println("Cuenta Nro: " + cuenta.getNumero() + " Tipo " + cuenta.getTipoCuenta() + " Moneda "
					+ cuenta.getMoneda() + " Saldo " + cuenta.getSaldo());
		}
		System.out.println("Ingrese el numero de cuenta");
		numero = input.nextLong();
		System.out.println("Ingrese el importe a extraer");
		dinero = input.nextDouble();
		try {
			cuentaBO.extraerDinero(numero, dinero);
			cuenta = cuentaBO.buscarCuenta(numero);
			System.out.println("Información del saldo de cuenta");
			System.out.println("Cuenta Nro: " + cuenta.getNumero() + " Tipo " + cuenta.getTipoCuenta() + " Moneda "
					+ cuenta.getMoneda() + " Saldo " + cuenta.getSaldo());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Este metodo se encarga de listar los clientes de una sucusral.
	 * Para ello permite al usuario ingresar el numero de sucursal, de la cual se quiere listar los clientes.
	 * @return void
	 */
	private static void listarClientesDeUnaSucursal(IClienteBO clienteBO) {
		List<Cliente> clientes = new ArrayList<>();
		Integer numeroSucursal;
		Iterator iterator;
		iterator = clientes.iterator();
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el número de sucursal");
		numeroSucursal = input.nextInt();
		try {
			clientes = clienteBO.listarClientesDeUnaSucursal(numeroSucursal);
			iterator = clientes.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Este metodo se encarga de listar los clientes ordenados por sucusral.
	 * @return void
	 */
	private static void listarClientesPorSucursal(IClienteBO clienteBO) {
		List<Cliente> clientes = new ArrayList<>();
		Iterator<Cliente> iterator;
		iterator = clientes.iterator();
		try {
			clientes = clienteBO.listarClientesPorSucursal();
			iterator = clientes.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Este metodo se encarga agregar una cuenta a un cliente.
	 * Para ello el usuario debe ingresar el cuil del cliente, luego el sistema listará los tipos de cuentas.
	 * El cliente ingresará el tipo de cuenta a agregar.
	 * El sistema agregará una nueva cuenta al cliente.
	 * @return void
	 */
	private static void agregarCuentaACliente(IClienteBO clienteBO) {
		Scanner input = new Scanner(System.in);
		Long cuil;
		Integer tipoCuenta;
		System.out.println("**********MENU CLIENTE-CUENTA**********");
		System.out.println("Ingrese el cuil del cliente");
		cuil = input.nextLong();
		System.out.println("1) Alta de Caja de Ahorro en Pesos\r\n" + "2) Alta de Caja de Ahorro en dólares\r\n"
				+ "3) Alta de Cuenta Corriente en Pesos\r\n" + "4) Alta de Cuenta Corriente en dólares");
		tipoCuenta = input.nextInt();
		try {
			clienteBO.agregarCuenta(cuil, tipoCuenta);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Este metodo se encarga agregar un nuevo cliente. El sistema solicitará que ingrese los datos de cliente.
	 * Para ello el usuario debe ingresar los datos del cliente: cuil, nombre, apellido, 
	 * numero de telefono, email, domicilio, numero de sucursal.
	 * Luego el sistema, soliciará que seleccione el tipo de cuenta a agregar al cliente.
	 * El cliente ingresará el tipo de cuenta a agregar.
	 * El sistema agregará el nuevo cliente y la nueva cuenta al cliente.
	 * @return void
	 */
	private static void agregarCliente(IClienteBO clienteBO) {
		Cliente cliente = new Cliente();
		Integer numeroSucursal;
		Integer tipoCuenta;
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el Cuil");
		cliente.setCuil(input.nextLong());
		System.out.println("Ingrese el Nombre");
		cliente.setNombre(input.next());
		System.out.println("Ingrese el Apellido");
		cliente.setApellido(input.next());
		System.out.println("Ingrese el Numero de Telefono");
		cliente.setNumeroTelefono(input.next());
		System.out.println("Ingrese el Email");
		cliente.setEmail(input.next());
		System.out.println("Ingrese el Domicilio Legal");
		cliente.setDomicilioLegal(input.next());
		System.out.println("Ingrese el Numero de Sucursal donde desea agregar al cliente");
		numeroSucursal = input.nextInt();
		System.out.println("**********MENU CLIENTE-CUENTA**********");
		System.out.println("1) Alta de Caja de Ahorro en Pesos\r\n" + "2) Alta de Caja de Ahorro en dólares\r\n"
				+ "3) Alta de Cuenta Corriente en Pesos\r\n" + "4) Alta de Cuenta Corriente en dólares");
		tipoCuenta = input.nextInt();
		try {
			clienteBO.agregarCliente(cliente, numeroSucursal, tipoCuenta);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Este metodo se encarga mostrar un menu principal para la aplicación.
	 * @return void
	 */
	public static void mostrarMenu() {
		System.out.println("*********BANCO DE LA UTN**********");
		System.out.println("**********MENU PRINCIPAL**********");
		System.out.println("1)  Agregar Cliente\r\n" + "2)  Agregar cuenta a Cliente\r\n"
				+ "3)  Listar Clientes por sucursal\r\n" + "4)  Listar Clientes de una sucursal\r\n"
				+ "5)  Extraer dinero\r\n" + "6)  Consultar Saldo\r\n" + "7)  Realizar Deposito\r\n"
				+ "8)  Realizar transferencias\r\n" + "9)  Eliminar una sucursal\r\n" + "10) Salir");
		System.out.println();
	}
}
