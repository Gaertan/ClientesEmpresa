package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
private int capacidad;
private int tamano;
public Cliente[] coleccionClientes /*= new Cliente[capacidad]*/;


public Clientes(int capacidad) {
	if (capacidad <= 0) {throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");}
	this.capacidad = capacidad;
	this.tamano = 0;
	coleccionClientes = new Cliente[capacidad];
}

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
	if(indice>=this.capacidad) {return true;}
	else return false;
};


private boolean tamanoSuperado(int indice){
	if(indice>this.tamano) {return true;}
	else return false;
}

private int buscarIndice(Cliente cliente){
	int indice = -1;
	for(int i=0; i<tamano;i++) {if(cliente.equals(coleccionClientes[i])) {indice = i;}}	
	//notese que devuelve solamente una ocurrencia del cliente/objeto(la ultima que ocurra) ya que no se nos pide que devuelva todas
	if (indice>=0) {return indice;}
	else return ((tamano)+1) ;
}

public void insertar(Cliente cliente) throws OperationNotSupportedException {
	if (cliente == null) {throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");}	

	
	//este codigo esta deprecated;no permite lanzar excepciones para cada caso
	/*for(int i = 0; i < coleccionClientes.length; i++)
	    if(coleccionClientes[i] == null) {
	    	coleccionClientes[i] = new Cliente(cliente);
	        break;
	    }*/
	if (capacidadSuperada(tamano)) {throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");}
	int indice = buscarIndice(cliente);
	//si el indice donde se encuentra el cliente es igual al tamaño(osea el ultimo cliente insertado,pues estan en orden) dara el error
	if (tamanoSuperado(indice)) {coleccionClientes[tamano] = new Cliente(cliente);}
	 else {throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");}
	tamano++;	
}

public void borrar(Cliente cliente) throws OperationNotSupportedException {
	if (cliente == null) {throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");	}
	int indice = buscarIndice(cliente);
	if (tamanoSuperado(indice)) {throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");	}

	desplazarUnaPosicionHaciaIzquierda(indice);
	


}


public Cliente buscar(Cliente cliente) {
	if (cliente == null) {throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");}
	Cliente cliente2 = null;
	for(int i=0; i<capacidad;i++)
		{if(cliente.equals(coleccionClientes[i])) {cliente2 = new Cliente(cliente);}
	}
	return cliente2;
}

private void desplazarUnaPosicionHaciaIzquierda(int indice) {
	
	for(int i = indice; i < capacidad; i++) {
		coleccionClientes[i] = coleccionClientes[(i+1)];		
	}
	coleccionClientes[indice] = null;if (tamano>0)tamano--;
}



}

