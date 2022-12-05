package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
private int capacidad;
private int tamano;
public Cliente[] coleccionClientes = new Cliente[capacidad];

public Cliente[] get() {return coleccionClientes;}

public int getCapacidad() {
	return capacidad;
}

public int getTamano() {
	return tamano;
}
public Cliente[] getColeccionClientes() {
	return coleccionClientes;
}

private boolean capacidadSuperada(int indice){
	if(indice>this.capacidad) {return true;}
	else return false;
};


private boolean tamanoSuperado(int indice){
	if(indice>this.tamano) {return true;}
	else return false;
	
	
}

private int buscarIndice(Cliente cliente){
	int indice = -1;
	
	for(int i=0; i<coleccionClientes.length;i++) {if(cliente.equals(coleccionClientes[i])) {indice = i;}}
	
	//notese que devuelve solamente una ocurrencia del cliente/objeto(la ultima que ocurra) ya que no se nos pide que devuelva todas
	if (indice>=0) {return indice;}
	else return ((coleccionClientes.length)+1) ;
}

public void insertar(Cliente cliente) {
	
	for(int i = 0; i < coleccionClientes.length; i++)
	    if(coleccionClientes[i] == null) {
	    	coleccionClientes[i] = new Cliente(cliente);
	        break;
	    }

}

public void borrar(Cliente cliente) throws Exception {

	for(int i = 0; i < coleccionClientes.length; i++) {
		if(cliente.equals(coleccionClientes[i])) {desplazarUnaPosicionHaciaIzquierda(i);break;
			
		}
		
	}
	throw new Exception("WTF ese cliente no se encuentra en la coleccion");

}


public Cliente buscar(Cliente cliente) {
	Cliente cliente2 = null;
	for(int i=0; i<coleccionClientes.length;i++)
		{if(cliente.equals(coleccionClientes[i])) {
			cliente2 = new Cliente(cliente);
		}
	}
	return cliente2;
}

private void desplazarUnaPosicionHaciaIzquierda(int indice) {
	
	for(int i = indice; i < coleccionClientes.length; i++) {
		coleccionClientes[i] = coleccionClientes[(i+1)];		
	}
	
}



}

