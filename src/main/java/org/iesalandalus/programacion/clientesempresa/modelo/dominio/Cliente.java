package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.sql.Date;
import java.util.Objects;

public class Cliente {
	private String ER_CORREO;
	private String ER_DNI;
	private String ER_TELEFONO;
	public String FORMATO_FECHA;
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private Date fechaNacimiento;
	
	
	
	
	public Cliente(String nombre, String dni, String correo, String telefono, Date fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	}



	public Cliente(Cliente cliente) {
		this.nombre = cliente.getNombre();
		this.dni = cliente.getDni();
		this.correo = cliente.getCorreo();
		this.telefono = cliente.getTelefono();
		this.fechaNacimiento = cliente.getFechaNacimiento();		
	}



	private String formateaNombre(String nombre) {
		//separa la string cuando encuentra cualquier tipo de espacio/separador

		String nombreRoto[] = nombre.split("\\s+");

		
		  for(String nomb:nombreRoto) {
			  //recorre el array de nombres y/o apellidos,elimina los espacios restantes y los reemplaza por vacíos
  		nomb.replaceAll("\\s\\W","");
  		nomb.toLowerCase();
  		nomb.toUpperCase().charAt(0);
	        }
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
		for (String s : this.nombre.split(" ")) {
		  iniciales.append(s.charAt(0));
		}
		return iniciales.toString();
		

		
		
		
	}
	
	
	

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = formateaNombre(nombre);
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
		return Objects.equals(ER_CORREO, other.ER_CORREO) && Objects.equals(ER_DNI, other.ER_DNI)
				&& Objects.equals(ER_TELEFONO, other.ER_TELEFONO) && Objects.equals(FORMATO_FECHA, other.FORMATO_FECHA)
				&& Objects.equals(correo, other.correo) && Objects.equals(dni, other.dni)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}
	
	
	
	

}
