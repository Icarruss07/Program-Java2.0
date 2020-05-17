package jcolonia.daw2020.programas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que contiene los datos de un cliente y los métodos relativos a dichos datos.
 * 
 * @author Simón Marc
 *
 */
public class Cliente {
	private String nombre = "";
	private String apellido = "";
	private String dni = "";
	private String domicilio = "";
	private int edad = 0;
	private int telefono = 0;
	public String[] campos = { "nombre", "apellido", "dni", "domicilio", "edad", "telefono" };

	/**
	 * Constructor sin argumentos
	 */
	public Cliente() {
	}

	/**
	 * Constructor de la clase, crea un objeto de esta clase asignando los valores
	 * pasados a las variables de la clase.
	 * 
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param domicilio
	 * @param edad
	 * @param telefono
	 */
	public Cliente(String nombre, String apellido, String dni, String domicilio, String edad, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
		this.edad = Integer.parseInt(edad);
		this.telefono = Integer.parseInt(telefono);
	}

	String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	String getApellido() {
		return apellido;
	}

	void setApellido(String apellido) {
		this.apellido = apellido;
	}

	String getDni() {
		return dni;
	}

	void setDni(String dni) {
		this.dni = dni;
	}

	String getDomicilio() {
		return domicilio;
	}

	void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	int getEdad() {
		return edad;
	}

	void setEdad(String edad) {
		this.edad = Integer.parseInt(edad);
	}

	int getTelefono() {
		return telefono;
	}

	void setTelefono(String telefono) {
		this.telefono = Integer.parseInt(telefono);
	}

	/**
	 * Construye una cadena de texto con todas las variables de esta clase y la
	 * devuelve
	 * 
	 * @return Devuelve la cadena construída
	 */
	@Override
	public String toString() {
		return "| " + nombre + " | " + apellido + " | " + dni + " | " + domicilio + " | " + edad + " | " + telefono
				+ " | ";
	}

	/**
	 * Construye una cadena de texto con todos los campos, esta será escrita después
	 * en un archivo CSV
	 * 
	 * @return La cadena de texto
	 */
	public String prepararTextoArchivo() {
		return nombre + ";" + apellido + ";" + dni + ";" + domicilio + ";" + edad + ";" + telefono + ";";
	}

	/**
	 * Comprueba si la entrada del usuario respeta las normas establecidas por las
	 * expresiones regulares
	 * 
	 * @param parametro El nombre del campo a comprobar
	 * @param entrada   La entrada del usuario que se desea comprobar
	 * @return Devuelve un true si la entrada es correcta , en caso contrario false
	 */
	public boolean entradaValida(String parametro, String entrada) {
		String expresion = expresiones(parametro);
		Boolean todoBien = true;
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(expresion);
		matcher = pattern.matcher(entrada);
		if (!matcher.matches()) {
			todoBien = false;
		} else {
			todoBien = true;
		}
		return todoBien;
	}

	/**
	 * Método que contiene todas las expresiones regulares de esta clase.
	 * 
	 * @param parametro El nombre del campo que se desea comprobar
	 * @return Una cadena de texto conteniendo la expresión regular que controla el
	 *         campo especificado
	 */
	private String expresiones(String parametro) {
		String expresion = "";
		switch (parametro) {
		case "nombre":
			expresion = "([a-zA-ZÄ-ÿ]{2,50}\\s*){1,5}";
			break;
		case "apellido":
			expresion = "([a-zA-ZÄ-ÿ]{2,50}\\s*){1,5}";
			break;
		case "dni":
			expresion = "[a-zA-Z]*[\\d]{7,8}[a-zA-Z]{1}";
			break;
		case "edad":
			expresion = "[1-9]{1}[0-9]{0,1}";
			break;
		case "telefono":
			expresion = "\\d{9}$";
			break;
		case "menu":
			expresion = "[1-5]{1}";
			break;
		}
		return expresion;
	}

	/**
	 * Set global de la clase, asigna un valor a un campo de esta clase
	 * 
	 * @param parametro El nombre del campo al que se desea asignar el valor pasado
	 * @param dato      El valor que se desea asignar
	 * @return Devuelve true si se ha podido asignar el valor y false en caso
	 *         contrario
	 * @throws Exception Lanza una excepción cuando el usuario ha introducido un
	 *                   valor que no se acepta para ese campo
	 */
	public boolean set(String parametro, String dato) throws Exception {
		boolean correcto = false;
		try {
			switch (parametro) {
			case "nombre":
				if (entradaValida(parametro, dato)) {
					setNombre(dato);
					correcto = true;
				} else {
					throw new Exception(parametro + " incorrecto!");
				}
				break;
			case "apellido":
				if (entradaValida(parametro, dato)) {
					setApellido(dato);
					correcto = true;
				} else {
					throw new Exception(parametro + " incorrecto!");
				}
				break;
			case "dni":
				if (entradaValida(parametro, dato)) {
					setDni(dato);
					correcto = true;
				}
				break;
			case "edad":
				if (entradaValida(parametro, dato)) {
					setEdad(dato);
					correcto = true;
				} else {
					throw new Exception(parametro + " incorrecto!");
				}
				break;
			case "telefono":
				if (entradaValida(parametro, dato)) {
					setTelefono(dato);
					correcto = true;
				} else {
					throw new Exception(parametro + " incorrecto!");
				}
				break;
			case "domicilio":
				setDomicilio(dato);
				correcto = true;
				break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return correcto;
	}

	/**
	 * Get global de la clase, devuelve cualquier campo solicitado
	 * 
	 * @param parametro El nombre del campo que tiene que devolver
	 * @return El campo pedido en formato String
	 */
	public String get(String parametro) {
		String dato = "";
		switch (parametro) {
		case "nombre":
			dato = getNombre();
			break;
		case "apellido":
			dato = getApellido();
			break;
		case "dni":
			dato = getDni();
			break;
		case "edad":
			dato = Integer.toString(getEdad());
			break;
		case "telefono":
			dato = Integer.toString(getTelefono());
			break;
		case "domicilio":
			dato = getDomicilio();
			break;
		}
		return dato;
	}
}
