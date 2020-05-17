package jcolonia.daw2020.programas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	private String nombre = "";
	private String apellido = "";
	private String dni = "";
	private String domicilio = "";
	private int edad = 0;
	private int telefono = 0;
	public String[] campos = { "nombre", "apellido", "dni", "domicilio", "edad", "telefono" };

	public Cliente() {
	}

	/**
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

	@Override
	public String toString() {
		return "| " + nombre + " | " + apellido + " | " + dni + " | " + domicilio + " | " + edad + " | " + telefono
				+ " | ";
	}

	public String prepararTextoArchivo() {
		return nombre + ";" + apellido + ";" + dni + ";" + domicilio + ";" + edad + ";" + telefono + ";";
	}

	public boolean entradaValida(String parametro, String entrada) {
		String expresion = expresiones(parametro);
		Boolean todoBien = true;
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(expresion);
		try {
			matcher = pattern.matcher(entrada);
			if (!matcher.matches()) {
				todoBien = false;
				throw new Exception(parametro + " incorrecto");
			} else {
				todoBien = true;
			}
		} catch (Exception e) {
		}
		return todoBien;
	}

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

	public boolean set(String parametro, String dato) throws Exception {
		boolean correcto = false;
		try {
			switch (parametro) {
				case "nombre":
					if (entradaValida(parametro, dato)) {
						setNombre(dato);
						correcto = true;
					}
					break;
				case "apellido":
					if (entradaValida(parametro, dato)) {
						setApellido(dato);
						correcto = true;
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
					}
					break;
				case "telefono":
					if (entradaValida(parametro, dato)) {
						setTelefono(dato);
						correcto = true;
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
