package org.iesalandalus.programacion.clientesempresa;

import java.util.Date;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {
	private int NUM_MAX_CLIENTES = 10;
	public  Clientes clientes;
	
	private  void insertarCliente() {
		
		Cliente cliente = new Cliente(Consola.leerCliente());
		if(cliente!=null) {for (int i = 0; i < clientes.getTamano(); i++) {if(clientes.getColeccionClientes()[i] == null) {clientes.insertar(cliente);break;}}}
		

		
	}
	private  void buscarCliente() {
		
		Cliente cliente = null;
		cliente = clientes.buscar(Consola.leerClienteDni());
		
		if(cliente == null) {System.out.println("El cliente no se encuentra entre los registrados");}
		else{System.out.println("El cliente buscado es " + cliente.toString());}
	
	}
	private  void borrarCliente() {
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
	
	private void mostrarClientes() {
		boolean empty = true;
		for (int i = 0; i < clientes.getTamano(); i++) {if(clientes.get()[i]!=null) {empty = false;}}
		
		if(empty==false) {for (int i = 0; i < clientes.getTamano(); i++) {clientes.get()[i].toString();}}
		else {System.out.println("La colección de clientes está vacía");}
		
		
		
	}
	
	private void mostrarClientesFecha() {
		boolean found = false;
		Date fechaNacimientoLeida = Consola.leerFechaNacimiento();
		for (int i = 0; i < clientes.getTamano(); i++) {if(clientes.get()[i].getFechaNacimiento().equals(fechaNacimientoLeida)) {found = true;clientes.get()[i].toString();}}
		if(found==false) {System.out.println("No se han encontrado clientes con fecha coincidente");}
	}
	
	private  void ejecutarOpcion (Opcion opcion) {
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
	
	public void main(String[] args) {
		try {Opcion opcion;
			Consola.mostrarMenu();opcion = Consola.elegirOpcion();
			while(opcion!=opcion.SALIR) {ejecutarOpcion(opcion);}
			if(opcion!=opcion.SALIR) {System.out.println("nos vemos");}
			
		}
		catch(Exception e) {e.printStackTrace();
			
		}
		
	}

}