package jcolonia.daw2020.programas;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que contiene el conjunto entero de clientes y los métodos relativos a
 * dicho conjunto.
 * 
 * @author Simón Marc
 *
 */
public class ConjuntoClientes {
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	/**
	 * Añade un cliente a la lista de clientes
	 * 
	 * @param entrada Los datos del cliente
	 * @param cliente El cliente
	 * @throws Exception Lanza una excepción cuando el usuario introduce datos incorrectos
	 */
	public void agregar(Scanner entrada, Cliente cliente) throws Exception {
		boolean correcto;
		String parametro = " ";
		for (int i = 0; i < cliente.campos.length; i++) {
			parametro = cliente.campos[i];
			do {
				System.out.printf("%s : ", parametro);
				correcto = cliente.set(parametro, entrada.nextLine());
			} while (!correcto);
		}
		clientes.add(cliente);
	}

	/**
	 * Muestra todos los datos de todos los clientes
	 */
	public void mostrarTodo() {
		System.out.println("| Nombre | Apellido | DNI | Direccion | Edad | Telefono |");
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).toString());
		}
	}

	/**
	 * Construye una cadena de texto con los datos del cliente que se encuentra en
	 * la posición indicada
	 * 
	 * @param cliente La posición del cliente en la lista de clientes
	 * @return Una cadena de texto preparada para ser escrita en el archivo con los
	 *         datos de ese cliente
	 */
	public String listar(int cliente) {
		return clientes.get(cliente).prepararTextoArchivo();
	}

	/**
	 * Busca en la lista de clientes el dato indicado, si lo encuentra elimina ese
	 * cliente
	 * 
	 * @param parametro El DNI o el nombre del cliente buscado
	 * @return Devuelve true si el tamaño de la lista de clientes ha cambiado
	 */
	public Boolean eliminar(String parametro) {
		boolean eliminado = false;
		int antes = clientes.size();
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDni() == parametro || clientes.get(i).getNombre() == parametro) {
				clientes.remove(i);
			}
		}
		int despues = clientes.size();
		if (antes != despues) {
			eliminado = false;
		}
		return eliminado;
	}
}
