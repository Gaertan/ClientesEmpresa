package org.iesalandalus.programacion.clientesempresa;


import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {
	private static int NUM_MAX_CLIENTES = 10;
	public static  Clientes clientes;
	
	private static  void insertarCliente() {

		Cliente cliente = new Cliente(Consola.leerCliente());
		if(cliente!=null) {try {clientes.insertar(cliente);} catch (OperationNotSupportedException e) {e.printStackTrace();}}
		}
		

	
	
	
	private static  void buscarCliente() {

		Cliente clienteBuscar = null;
		
		Cliente cliente = null;
		cliente = new Cliente(Consola.leerClienteDni());
		try{ clienteBuscar = clientes.buscar(cliente);}catch(Exception e) {e.printStackTrace();}

		if(clienteBuscar!=null) {System.out.println("El cliente buscado es " );System.out.println(clienteBuscar.toString()); }
			else {System.out.println("El cliente no se ha encontrado o era nulo");}
			
	}
		
	
	private static  void borrarCliente() {
		boolean match = false;
		Cliente cliente = null;
		cliente = Consola.leerClienteDni();
		
		for (int i = 0; i < clientes.getTamano(); i++) {
			if( clientes.get()[i].equals(cliente)){
				try {clientes.borrar(cliente); match = true;}
				catch (Exception e) {e.printStackTrace();}
				} 
			
		}
		if(match == false) {System.out.println("Algo ha ocurrido, el cliente no pudo borrarse");}
		
	}
	
	private static void mostrarClientes() {


		for (int i = 0;i<clientes.getTamano(); i++) {
		/*	if(clientes.get()[i]!=null) empty = false;
			
		if(empty==false) {for (int i1 = 0; i1 < clientes.getTamano(); i1++) {*/
			
			try{System.out.println(clientes.get()[i].toString());}
		catch(Exception e){e.getMessage();}}//}	
		//}
		

		if(clientes.getTamano()<1){System.out.println("La colección de clientes está vacía");}
		
		
		
	}
	
	private static void mostrarClientesFecha() {
		boolean found = false;
		LocalDate fechaNacimientoLeida = Consola.leerFechaNacimiento();
		for (int i = 0; i < clientes.getTamano(); i++) {if(clientes.get()[i].getFechaNacimiento().equals(fechaNacimientoLeida)) {found = true;System.out.println(clientes.get()[i].toString());}}
		if(found==false) {System.out.println("No se han encontrado clientes con fecha coincidente");}
	}
	
	private static  void ejecutarOpcion (Opcion opcion) {
		switch (opcion) { 

	    case INSERTAR_CLIENTE:insertarCliente();break;
		

	    case BUSCAR_CLIENTE: buscarCliente();break;
	    	

	    case BORRAR_CLIENTE: borrarCliente();break;
	    	

	    case MOSTRAR_CLIENTES:mostrarClientes();break;
	    	
	    	
	    case MOSTRAR_CLIENTES_FECHA:mostrarClientesFecha();break;
	    	

	    case SALIR:;
	    	
	    default:
	    	
	  }
		
	}
	
	public static void main(String[] args) {
	clientes = new Clientes(NUM_MAX_CLIENTES);		
		try {Opcion opcion = null;

			
			while(opcion!=Opcion.SALIR) {opcion = Consola.elegirOpcion();ejecutarOpcion(opcion);}
			if(opcion==Opcion.SALIR) {System.out.println("nos vemos");}
			
		}
		catch(Exception e) {e.printStackTrace();}
		
	}

}