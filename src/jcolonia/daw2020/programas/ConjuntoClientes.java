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
	 * @throws Exception Lanza una excepción cuando el usuario introduce datos
	 *                   incorrectos
	 */
	@SuppressWarnings("static-access")
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
		System.out.printf("| %-40.40s | %-40.40s | %-10s | %-40.40s | %-4s | %-13s | \n\n", Cliente.campos[0],
				Cliente.campos[1], Cliente.campos[2], Cliente.campos[3], Cliente.campos[4], Cliente.campos[5]);
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).toString());
		}
	}

	/**
	 * Facilita una cadena de texto con los datos del cliente que se encuentra en la
	 * posición indicada.
	 * 
	 * @param numCliente La posición del cliente en la lista de clientes
	 * @return Una cadena de texto preparada para ser escrita en el archivo con los
	 *         datos de ese cliente
	 */
	public String listar(int numCliente) {
		return clientes.get(numCliente).prepararTextoArchivo();
	}

	/**
	 * Busca en la lista de clientes el dato indicado, si lo encuentra elimina ese
	 * cliente.
	 * 
	 * @param dni El DNI del cliente buscado
	 */
	public void eliminar(String dni) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDni().compareToIgnoreCase(dni) == 0) {
				clientes.remove(i);
				break;
			}
		}
	}

	/**
	 * Si existe un cliente en la lista de clientes que tenga el DNI pasado, lo
	 * devuelve
	 * 
	 * @param dato El DNI del cliente a buscar
	 * @return El cliente buscado
	 * @throws Exception Si no existen clientes con ese DNI
	 */
	public Cliente buscarCliente(String dato) throws Exception {
		Cliente cliente = null;
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDni().compareToIgnoreCase(dato) == 0) {
				cliente = clientes.get(i);
			}
		}

		if (cliente == null) {
			throw new Exception("No existen clientes con ese DNI !");
		}
		return cliente;
	}

	/**
	 * Busca el cliente indicado por el DNI en la lista y devuelve su index
	 * 
	 * @param dato El DNI del cliente
	 * @return El index
	 */
	public int indexClientes(String dato) {
		int index = 0;
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDni().compareToIgnoreCase(dato) == 0) {
				index = i;
			}
		}
		return index;
	}
}
