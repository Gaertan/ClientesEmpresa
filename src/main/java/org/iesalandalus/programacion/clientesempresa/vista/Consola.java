package org.iesalandalus.programacion.clientesempresa.vista;
import java.util.regex.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;    

import org.iesalandalus.programacion.clientesempresa.modelo.*;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.utilidades.*;
public class Consola {
	
	
	
	
	private void Consola(){}

	public Opcion elegirOpcion() {
		mostrarMenu();
		int opcion = -1;

			while(opcion<0||opcion>6) {
			
			System.out.println("escoja la opción correspondiente");
			opcion = Entrada.entero();
			}
		
			
			switch (opcion) { 
			    case 1:return Opcion.INSERTAR_CLIENTE;
	

			    case 2:return Opcion.BUSCAR_CLIENTE;
			    	

			    case 3:return Opcion.BORRAR_CLIENTE;

			    case 4:return Opcion.MOSTRAR_CLIENTES;
			    	
			    case 5:return Opcion.MOSTRAR_CLIENTES_FECHA;

			    case 6:return Opcion.SALIR;

		    default:return null;

		  }

	}
	
	
	
	
	
	
	public Cliente leerCliente() {
		String nombre = "a";
		String correo = "a";
		String dni = "a";
		String telefono ="a";
		Date fechaNacimiento;
		
		System.out.println("Introduzca los siguientes datos del cliente:");
		System.out.println("Introduzca el nombre y apellidos;");
        String[] palabras = nombre.split("\\s+");
		while(palabras.length<2) {System.out.println("Introduzca el nombre;");nombre=Entrada.cadena();}
		
		System.out.println("Introduzca el correo;");
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(correo);  
		while(!matcher.matches()) {correo = Entrada.cadena();}
		
		System.out.println("Introduzca un dni correcto;");
		Pattern pattern2 = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
        Matcher matcher2 = pattern2.matcher(dni);  
		while(!matcher2.matches()) {dni = Entrada.cadena();}
		
		System.out.println("Introduzca un telefono correcto(numeros sin mas caracteres);");
		Pattern pattern3 = Pattern.compile("^[0-9]{10}$");
        Matcher matcher3 = pattern3.matcher(telefono);  
		while(!matcher3.matches()) {dni = Entrada.cadena();}
		
		fechaNacimiento = leerFechaNacimiento();
		
		return new  Cliente(nombre,  dni,  correo,  telefono,  fechaNacimiento);}
	
	
	
	public Cliente leerClienteDni() {
	    String dni = null;	
	System.out.println("Introduzca un dni con formato correcto,si no lo es,se seguira pidiendo.;");
	Pattern pattern2 = Pattern.compile("[0-9]{7,8}[A-Z a-z]");

	Matcher matcher2 = pattern2.matcher(dni);  
	while(!matcher2.matches()) {dni = Entrada.cadena();}
	
	return new Cliente("",dni,"","",null);

	
	
	}

	
	
	public Date leerFechaNacimiento() {
		boolean fechaCorrecta=false;
		int dia = -1;
		int mes = -1;
		int anio = -1;
		
		while (!fechaCorrecta) {
			System.out.println("A continuacion se le va a pedir los datos de la fecha de nacimiento;");
			while(dia>32||dia>0) {System.out.println("Introduzca el dia;");dia =Entrada.entero();}
			while(mes>13||mes>0) {System.out.println("Introduzca el mes;");mes =Entrada.entero();}
			while(anio>Year.now().getValue()||anio<1920) {System.out.println("Introduzca el año;");anio =Entrada.entero();}
				
			
				if(mes==2&&(java.time.Year.of(anio).isLeap())&&dia<=29) {fechaCorrecta = true;}
				if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia<=30) {fechaCorrecta=true;}
				if ((mes != 4 && mes != 6 && mes != 9 && mes != 11) && dia<=31) {fechaCorrecta=true;}
			
			
			
		}
		LocalDate fecha = LocalDate.of(anio, mes, dia);
	      
		return java.sql.Date.valueOf(fecha);

		
	}
	
	
	
	public void mostrarMenu() {
		System.out.println("Estas son las opciones que puede escoger;escoja la opción correspondiente");
		System.out.println("1. insertar");
		System.out.println("2. Buscar");
		System.out.println("3,Borrar");
		System.out.println("4. Mostrar todos los clientes");
		System.out.println("5. Mostrar los clientes nacidos en una fecha");
		System.out.println("6. Salir");
	}
	
	
	

}
