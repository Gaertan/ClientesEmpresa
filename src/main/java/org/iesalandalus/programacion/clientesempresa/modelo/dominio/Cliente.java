package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Cliente {
	private String ER_CORREO;
	private String ER_DNI;
	private String ER_TELEFONO;
	public String FORMATO_FECHA;
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;
	
	
	
	
	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {

		try{
			if(nombre!=null&&nombre!="")this.nombre = formateaNombre(nombre);
		if(dni!=null&&dni!="")setDni(dni);
		//if(correo!=null&&correo!="")setCorreo(correo);
		setCorreo(correo);
		if(telefono!=null&&telefono!="")setTelefono(telefono);
		if(fechaNacimiento!=null)setFechaNacimiento(fechaNacimiento);}
		catch(Exception e) {e.printStackTrace();}
	}



	public Cliente(Cliente cliente) throws Exception {
		if (cliente==null) throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		else{
			setNombre(cliente.nombre);
			setDni(cliente.dni);
			setCorreo(cliente.correo);
			setTelefono(cliente.telefono);
			setFechaNacimiento(cliente.fechaNacimiento);}	
	}



	private String formateaNombre(String nombre) {
		//separa la string cuando encuentra cualquier tipo de espacio/separador

		String nombreRoto[] = nombre.split("\\s+");		
		  for(String nomb:nombreRoto) {
			  //recorre el array de nombres y/o apellidos,elimina los espacios restantes y los reemplaza por vacíos
  		nomb.replaceAll("\\s\\W","");
  		nomb.toLowerCase();
  		nomb.toUpperCase().charAt(0);}
		  //crea una nueva String uniendo los elementos de la arraay separados por un espacio
		 String resultado = String.join(" ", nombreRoto);
		 
		 return resultado;
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


	public void setNombre(String nombre) throws OperationNotSupportedException {
		if ( nombre==null ||nombre== "") {throw new OperationNotSupportedException("el nombre no puede ser nulo");}
		this.nombre = formateaNombre(nombre);
	}


	public String getDni() {
	return dni;

	}


	public void setDni(String dni) throws Exception {
		if (dni == null) {throw new IllegalArgumentException("El dni no puede ser nulo.");}
		String dni1 = dni;
		dni1.replaceAll("\\W","");
		dni1.toUpperCase();
		if (!dni1.matches("\\d{8}[A-Za-z]")) {throw new IllegalArgumentException("El formato del dni no es correcto.");}		
		if(!comprobarLetraDni(dni1)) {throw new Exception("la letra del dni no es la correcta");}		
		this.dni = dni1;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		if (correo == null) {
			throw new IllegalArgumentException("El correo no puede ser nulo.");
		}
		if (!correo.matches("\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}")) {
			throw new IllegalArgumentException("El formato del correo no es correcto.");
		}
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		if (telefono == null) {throw new IllegalArgumentException("El teléfono no puede ser nulo.");}
		if (!telefono.matches("\\d{9}")) {throw new IllegalArgumentException("El formato del teléfono no es correcto.");}
		this.telefono = telefono;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	private void setFechaNacimiento(LocalDate fechaNacimiento2) {	
		if (fechaNacimiento2 == null) {throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");}
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
	
	
	

}
