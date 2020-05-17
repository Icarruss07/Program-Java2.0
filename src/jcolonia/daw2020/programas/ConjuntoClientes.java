package jcolonia.daw2020.programas;

import java.util.ArrayList;
import java.util.Scanner;

public class ConjuntoClientes {
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

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

	public void mostrarTodo() {
		System.out.println("| Nombre | Apellido | DNI | Direccion | Edad | Telefono |");
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).toString());
		}
	}

	public void listar(int cliente) {
		clientes.get(cliente).prepararTextoArchivo();
	}

	public Boolean eliminar(String dni) {
		boolean eliminado = false;
		int antes = clientes.size();
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDni() == dni) {
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
