package jcolonia.daw2020.programas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase que controla las operaciones relativas al archivo que contiene los
 * datos y tambien el método main con un menú
 * 
 * @author Simón Marc
 *
 */
public class Control {
	static File archivo = new File("Clientes.csv");
	static Scanner csv;
	static Scanner entrada = new Scanner(System.in);
	static Cliente cliente = new Cliente();
	static ConjuntoClientes clientes = new ConjuntoClientes();

	public static void main(String[] args) throws Exception {
		leerArchivo();
		menu();
		// todo que tiene que ver con el archivo
	}

	/**
	 * Escribe en el archivo que se encuentra en la ruta descrita los datos de los
	 * clientes existentes en la lista de clientes.
	 * 
	 * @param clientes el conjunto de clientes
	 * @throws Exception cuando no se puede crear el archivo
	 */
	private static void escribir(ConjuntoClientes clientes, File archivo) throws Exception {
		FileWriter escribir = new FileWriter(archivo);
		if (archivo.exists()) {
			for (int i = 0; i < clientes.clientes.size(); i++) {
				escribir.append(clientes.clientes.get(i).prepararTextoArchivo() + "\n");
			}
		} else {
			for (int i = 0; i < clientes.clientes.size(); i++) {
				escribir.write(clientes.clientes.get(i).prepararTextoArchivo() + "\n");
			}
		}
		escribir.flush();
		escribir.close();

	}

	/**
	 * Vacia el conjunto de clientes y borra el archivo que contiene los datos.
	 * Trate con cuidado!
	 * 
	 * @param clientes el conjunto de clientes
	 * @param archivo  el archivo que contiene los datos
	 */
	private static void vaciar(ConjuntoClientes clientes, File archivo) {
		clientes.clientes.clear();
		archivo.delete();
		if (clientes.clientes.isEmpty()) {
			System.out.println("Datos eliminados con éxito !");
		}
	}

	/**
	 * Añade al programa los datos del archivo
	 */
	public static void leerArchivo() {
		if (archivo.exists()) {
			String linea;
			try {
				csv = new Scanner(archivo);
				while (csv.hasNextLine()) {
					linea = csv.nextLine();
					String[] campo = linea.split(";");
					Cliente cliente = new Cliente(campo[0], campo[1], campo[2], campo[3], campo[4], campo[5]);
					clientes.clientes.add(cliente);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				archivo.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String linea;
			try {
				csv = new Scanner(archivo);
				while (csv.hasNextLine()) {
					linea = csv.nextLine();
					String[] campo = linea.split(";");
					Cliente cliente = new Cliente(campo[0], campo[1], campo[2], campo[3], campo[4], campo[5]);
					clientes.clientes.add(cliente);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * El menú de la aplicación, pide al usuario elegir entre una de las opciones
	 * ofrecidas, el usuario solo puede elegír entre una de las opciones ofrecidas,
	 * según la opción elegida, el programa llama un método u otro.
	 * 
	 * @throws IOException
	 */
	public static void menu() throws Exception {
		int caso = 0;
		int caso_salir = 5;
		while (caso != caso_salir) {

			String menu = "";

			System.out.println();
			System.out.println("Elíje una de las opciones: ");
			System.out.println();
			System.out.println("1) Añadir un cliente nuevo");
			System.out.println("2) Ver datos");
			System.out.println("3) Eliminar un cliente");
			System.out.println("4) Eliminar todos los datos");
			System.out.println("5) Salir del programa");
			System.out.println();
			menu = entrada.nextLine();

			try {
				if (cliente.entradaValida("menu", menu)) {
					caso = Integer.parseInt(menu);
				} else {
					throw new Exception("\nSolo numeros del 1 a 5");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			switch (caso) {

			case 1:
				try {
					clientes.agregar(entrada, cliente);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			case 2:
				clientes.mostrarTodo();
				break;

			case 3:
				break;
			case 4:
				vaciar(clientes, archivo);
				break;
			case 5:
				escribir(clientes, archivo);
				System.exit(0);
				break;

			}

		}
	}
}
