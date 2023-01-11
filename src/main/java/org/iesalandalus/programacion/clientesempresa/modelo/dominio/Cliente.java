package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Cliente {
	private String ER_CORREO = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$";
	private String ER_DNI = "\\d{8}[A-Za-z]";
	private String ER_TELEFONO = "\\d{9}";
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;
	
	
	
	
	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento);
	} 


	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		this.nombre = cliente.getNombre();
		this.dni = cliente.getDni();
		this.correo = cliente.getCorreo();
		this.telefono = cliente.getTelefono();
		this.fechaNacimiento = cliente.getFechaNacimiento();
	}


	private String formateaNombre(String nombre) {
		nombre = nombre.toLowerCase(Locale.ROOT);
		//separa la string cuando encuentra cualquier tipo de espacio/separador

		String nombreRoto[] = nombre.split("\\s+");		
		  for(int i = 0;i<nombreRoto.length;i++) {
			  nombreRoto[i].replaceAll("\\s\\W","");
			  try {
				  nombreRoto[i].trim();
		//deshace la string en el primer caracter y el resto,lo pasa a mayus y la vuelve a juntar
				  String palabra = nombreRoto[i].substring(nombreRoto[i].indexOf(" ") + 1);
				  palabra = Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1);
				  if(palabra!=" ")nombreRoto[i] = palabra;}		  
			  catch(Exception e) {}
		  }		
		  //crea una nueva String uniendo los elementos de la arraay separados por un espacio
		 String resultado = String.join(" ", nombreRoto);
		 
		 return resultado.trim();
	}
	
	
	private boolean comprobarLetraDni(String DNI){
		char tempLetraDNI = DNI.charAt(DNI.length()-1);
		//elimina todo lo que sea un numero y convierte la caadena en uno.
		String dniFixed = DNI.replaceAll("\\D","");
		int dniNumber = Integer.parseInt(dniFixed);
		char[] LETRAS_DNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		if (LETRAS_DNI[dniNumber%23]==tempLetraDNI) {return true;}
		else return false;
				
		
	}


	private String getIniciales() {
		StringBuilder iniciales = new StringBuilder();
		for (String s : this.nombre.split(" ")) {iniciales.append(s.charAt(0));}
		return iniciales.toString();

	}
	
	
	

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		if (nombre == null) {throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");}
		if (nombre.trim().isEmpty()) {throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");}
		this.nombre = formateaNombre(nombre);
	}


	public String getDni() {
	return dni;

	}


	public void setDni(String dni){
		if (dni == null) {throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");}
		String dni1 = dni;
		dni1.replaceAll("\\W","");
		dni1.toUpperCase();
		if (!dni1.matches(ER_DNI)) {throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");}		
		if(!comprobarLetraDni(dni1)) {throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");}		
		this.dni = dni1;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo){
		if (correo == null) {throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");}
		if (!correo.matches(ER_CORREO)) {throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");		}
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		if (telefono == null) {throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");}
		if (!telefono.matches(ER_TELEFONO)) {throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");}
		this.telefono = telefono;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	private void setFechaNacimiento(LocalDate fechaNacimiento2) {	
		if (fechaNacimiento2 == null) {throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");}
		this.fechaNacimiento = fechaNacimiento2;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ER_CORREO, ER_DNI, ER_TELEFONO, FORMATO_FECHA, correo, dni, fechaNacimiento, nombre,
				telefono);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);

	}
	
	@Override
	public String toString() {
		return "nombre=" + nombre + " (" + getIniciales() + ")" + ", DNI=" + dni + ", correo=" + correo + ", teléfono="
				+ telefono + ", fecha nacimiento=" + fechaNacimiento.format(FORMATO_FECHA);
	}
	

}
