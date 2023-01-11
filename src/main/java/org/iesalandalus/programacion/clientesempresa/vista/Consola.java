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

	public static Opcion elegirOpcion() {
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
	
	
	
	
	
	
	public static Cliente leerCliente() {
		String nombre = "a";
		String correo = "a";
		String dni = "a";
		String telefono ="a";
		LocalDate fechaNacimiento;
		boolean fail = true;
		Cliente cliente = null;
		
		do {
			fail = false;
			System.out.println("Introduzca los siguientes datos del cliente:");
			
	        String[] palabras = nombre.split("\\s+");
	        do {System.out.println("Introduzca el nombre y apellidos;");
	        nombre=Entrada.cadena(); palabras = nombre.split("\\s+");}
			while(palabras.length<2);
			
	        do{
				System.out.println("Introduzca el correo;");
				correo = Entrada.cadena();}
				while(!correo.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}"));
			
			do {
				System.out.println("Introduzca un dni correcto;");
				dni = Entrada.cadena();}
				while(!dni.matches("[0-9]{7,8}[A-Z a-z]"));
				
			do {System.out.println("Introduzca un telefono correcto(numeros sin mas caracteres);");
				telefono = Entrada.cadena();}
				while(!telefono.matches("^[0-9]{9}$"));
				
			fechaNacimiento = leerFechaNacimiento();
			
			try {cliente = new Cliente(nombre, dni, correo, telefono, fechaNacimiento);	} catch (Exception e) {fail = true;e.printStackTrace();}
		}
		while(fail);
		
		return cliente;
}
	
	
	
	public static Cliente leerClienteDni() {
	String dni = "a";	

	//Pattern pattern2 = Pattern.compile("[0-9]{7,8}[A-Z a-z]");

	//Matcher matcher2 = pattern2.matcher(dni);  
	//while(matcher2.matches()) {dni = Entrada.cadena();}


    boolean dniCorrecto = false;
    do{System.out.println("Introduzca un dni con formato correcto,si no lo es,se seguira pidiendo.;");
     
        dni = Entrada.cadena();   dniCorrecto=dni.matches("[0-9]{7,8}[A-Z a-z]");
    }while(!dniCorrecto);

    System.out.println("El dni "+dni+" tiene un formato correcto");
	
	return new Cliente("Andrés Rubio Del Río",dni,"andresrubio@iesalandalus.org","666223344",LocalDate.of(1992, 7, 3));

	
	
	}

	
	
	public static LocalDate leerFechaNacimiento() {
		boolean fechaCorrecta=false;
		int dia = -1;
		int mes = -1;
		int anio = -1;
		
		while (!fechaCorrecta) {
			System.out.println("A continuacion se le va a pedir los datos de la fecha de nacimiento;");
			while(dia>32||dia<0) {System.out.println("Introduzca el dia;");dia =Entrada.entero();}
			while(mes>13||mes<0) {System.out.println("Introduzca el mes;");mes =Entrada.entero();}
			while(anio>Year.now().getValue()||anio<1920) {System.out.println("Introduzca el año;");anio =Entrada.entero();}
				
			
				if(mes==2&&(java.time.Year.of(anio).isLeap())&&dia<=29) {fechaCorrecta = true;}
				if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia<=30) {fechaCorrecta=true;}
				if ((mes != 4 && mes != 6 && mes != 9 && mes != 11) && dia<=31) {fechaCorrecta=true;}
			
			
			
		}
		LocalDate fecha = LocalDate.of(anio, mes, dia);
	      
		return fecha;

		
	}
	
	
	
	public static void mostrarMenu() {
		System.out.println("Estas son las opciones que puede escoger;escoja la opción correspondiente");
		System.out.println("1. insertar");
		System.out.println("2. Buscar");
		System.out.println("3,Borrar");
		System.out.println("4. Mostrar todos los clientes");
		System.out.println("5. Mostrar los clientes nacidos en una fecha");
		System.out.println("6. Salir");
	}
	
	
	

}
