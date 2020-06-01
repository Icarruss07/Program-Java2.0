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
	static File datos;
	static File archivo;
	static Scanner csv;
	static Scanner entrada = new Scanner(System.in);
	static Cliente cliente = new Cliente();
	static Vista vista = new Vista();
	static ConjuntoClientes clientes = new ConjuntoClientes();

	public static void main(String[] args) throws Exception {
		definirCarpetaEjecucion();
		leerArchivo();
		menu();
		// todo que tiene que ver con el archivo
	}

	/**
	 * Comprueba la carpeta desde la que se ejecuta el programa, si se ejecuta con
	 * la ayuda del JAR, guardará los datos en una carpeta superior llamada datos,
	 * de lo contrario en el directorio actual creará una carpeta llamada datos
	 */
	static void definirCarpetaEjecucion() {
		if (System.getProperty("user.dir").contains("dist")) {
			datos = new File("../datos");
			archivo = new File("../datos/Clientes.csv");
		} else {
			datos = new File("datos");
			archivo = new File("datos/Clientes.csv");
		}
		datos.mkdir();
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
				escribir.append(clientes.listar(i) + "\n");
			}
		} else {
			for (int i = 0; i < clientes.clientes.size(); i++) {
				escribir.write(clientes.listar(i) + "\n");
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
	 * Pide al usuario el DNI del cliente que desea eliminar y lo elimina
	 */
	public static void eliminarCliente() {

		String parametro = "";
		System.out.print("Introduce el DNI del cliente que desea eliminar : ");
		parametro = entrada.nextLine();

		if (cliente.entradaValida("dni", parametro) || cliente.entradaValida("nie", parametro)) {
			clientes.eliminar(parametro);
		}

	}

	/**
	 * Pregunta al usuario si quiere ver todos los datos o buscar un solo cliente
	 */
	public static void elegirVista() {
		String eleccion = "";
		System.out.println("Desea ver todos los clientes o buscar uno por el DNI ? (1/2) ");
		eleccion = entrada.nextLine();
		if (eleccion.compareToIgnoreCase("2") == 0) {
			try {
				buscarCliente();
			} catch (Exception e) {
				System.out.println();
				System.err.println(e.getMessage());
			}
		} else {
			clientes.mostrarTodo();
		}
	}

	/**
	 * Pregunta al usuario por un DNI y comprueba si existe un cliente con ese DNI.
	 * Si existe, devuelve ese cliente y sino muestra un mensaje.
	 * 
	 * @throws Exception Si la lista de clientes no contiene el cliente señalado
	 */
	public static void buscarCliente() throws Exception {
		String datoBuscado = "";
		System.out.println("Introduce el DNI del cliente que desea buscar");
		datoBuscado = entrada.nextLine();
		if (cliente.entradaValida("dni", datoBuscado) || cliente.entradaValida("nie", datoBuscado)) {
			System.out.println(clientes.buscarCliente(datoBuscado).toString());
		} else {
			System.err.println("No existen clientes con ese DNI !");
		}
	}

	/**
	 * Pregunta al usuario por un DNI y despues permite cambiar los datos del
	 * cliente al que pertenece ese DNI.
	 * 
	 * @param clientes La lista de clientes
	 * @throws Exception Cuando el dato nuevo no respeta las normas exigidas por las
	 *                   expresiones regulares
	 */
	public static void editarCliente(ConjuntoClientes clientes) throws Exception {
		String datoBuscado = "";
		String campo = "";
		String datoViejo = "";
		String datoNuevo = "";
		int caso = 0;
		int index = 0;

		System.out.println();
		System.out.println("Introduce el DNI del cliente que desea editar");
		datoBuscado = entrada.nextLine();

		if (cliente.entradaValida("dni", datoBuscado) || cliente.entradaValida("nie", datoBuscado)) {
			
			index=clientes.indexClientes(datoBuscado);
			campo="";
			
			System.out.println();
			System.out.println(clientes.buscarCliente(datoBuscado).toString());
			System.out.println();
			System.out.println("Que datos quiere cambiar ? Elije una de las siquientes");
			System.out.println();
			System.out.println("1) El nombre");
			System.out.println("2) El apellido");
			System.out.println("3) El DNI");
			System.out.println("4) El domicilio");
			System.out.println("5) La edad");
			System.out.println("6) El teléfono");
			System.out.println();

			campo = entrada.nextLine();
			System.out.println();

			try {
				if (cliente.entradaValida("menu", campo)) {
					caso = Integer.parseInt(campo) - 1;
				} else {
					throw new Exception("\nSolo numeros del 1 a 6");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			switch (caso) {

			case 0:

				System.out.printf("Introduce el nuevo %s \n", Cliente.campos[caso]);
				datoViejo = clientes.clientes.get(index).get(Cliente.campos[caso]);
				System.out.printf("El %s viejo : %s \n", Cliente.campos[caso], datoViejo);
				System.out.printf("El %s nuevo : ", Cliente.campos[caso]);
				datoNuevo = entrada.nextLine();
				if (cliente.entradaValida(Cliente.campos[caso], datoNuevo)) {
					try {
						clientes.clientes.get(clientes.indexClientes(datoBuscado)).set(Cliente.campos[caso], datoNuevo);
						System.out.println("\nDatos cambiados con éxito !");
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				} else {
					throw new Exception(Cliente.campos[caso] + " incorrecto !");
				}
				break;

			case 1:

				System.out.printf("Introduce el nuevo %s \n", Cliente.campos[caso]);
				datoViejo = clientes.clientes.get(index).get(Cliente.campos[caso]);
				System.out.printf("El %s viejo : %s \n", Cliente.campos[caso], datoViejo);
				System.out.printf("El %s nuevo : ", Cliente.campos[caso]);
				datoNuevo = entrada.next();
				if (cliente.entradaValida(Cliente.campos[caso], datoNuevo)) {
					try {
						clientes.clientes.get(clientes.indexClientes(datoBuscado)).set(Cliente.campos[caso], datoNuevo);
						System.out.println("Datos cambiados con éxito !");
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				} else {
					throw new Exception(Cliente.campos[caso] + " incorrecto !");
				}
				break;

			case 2:

				System.out.printf("Introduce el nuevo %s \n", Cliente.campos[caso]);
				datoViejo = clientes.clientes.get(index).get(Cliente.campos[caso]);
				System.out.printf("El %s viejo : %s \n", Cliente.campos[caso], datoViejo);
				System.out.printf("El %s nuevo : ", Cliente.campos[caso]);
				datoNuevo = entrada.nextLine();
				if (cliente.entradaValida(Cliente.campos[caso], datoNuevo) || cliente.entradaValida("nie", datoNuevo)) {
					try {
						clientes.clientes.get(clientes.indexClientes(datoBuscado)).set(Cliente.campos[caso], datoNuevo);
						System.out.println("\nDatos cambiados con éxito !");
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				} else {
					throw new Exception(Cliente.campos[caso] + " incorrecto !");
				}
				break;
				
			case 3:

				System.out.printf("Introduce el nuevo %s \n", Cliente.campos[caso]);
				datoViejo = clientes.clientes.get(index).get(Cliente.campos[caso]);
				System.out.printf("El %s viejo : %s \n", Cliente.campos[caso], datoViejo);
				System.out.printf("El %s nuevo : ", Cliente.campos[caso]);
				datoNuevo = entrada.nextLine();
				if (cliente.entradaValida(Cliente.campos[caso], datoNuevo)) {
					try {
						clientes.clientes.get(clientes.indexClientes(datoBuscado)).set(Cliente.campos[caso], datoNuevo);
						System.out.println("\nDatos cambiados con éxito !");
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				} else {
					throw new Exception(Cliente.campos[caso] + " incorrecto !");
				}
				break;

			case 4:

				System.out.printf("Introduce el nuevo %s \n", Cliente.campos[caso]);
				datoViejo = clientes.clientes.get(index).get(Cliente.campos[caso]);
				System.out.printf("El %s viejo : %s \n", Cliente.campos[caso], datoViejo);
				System.out.printf("El %s nuevo : ", Cliente.campos[caso]);
				datoNuevo = entrada.nextLine();
				if (cliente.entradaValida(Cliente.campos[caso], datoNuevo)) {
					try {
						clientes.clientes.get(clientes.indexClientes(datoBuscado)).set(Cliente.campos[caso], datoNuevo);
						System.out.println("\nDatos cambiados con éxito !");
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				} else {
					throw new Exception(Cliente.campos[caso] + " incorrecto !");
				}
				break;

			case 5:

				System.out.printf("Introduce el nuevo %s \n", Cliente.campos[caso]);
				datoViejo = clientes.clientes.get(index).get(Cliente.campos[caso]);
				System.out.printf("El %s viejo : %s \n", Cliente.campos[caso], datoViejo);
				System.out.printf("El %s nuevo : ", Cliente.campos[caso]);
				datoNuevo = entrada.nextLine();
				if (cliente.entradaValida(Cliente.campos[caso], datoNuevo)) {
					try {
						clientes.clientes.get(clientes.indexClientes(datoBuscado)).set(Cliente.campos[caso], datoNuevo);
						System.out.println("\nDatos cambiados con éxito !");
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				} else {
					throw new Exception(Cliente.campos[caso] + " incorrecto !");
				}
				break;

			case 6:

				System.out.printf("Introduce el nuevo %s \n", Cliente.campos[caso]);
				datoViejo = clientes.clientes.get(index).get(Cliente.campos[caso]);
				System.out.printf("El %s viejo : %s \n", Cliente.campos[caso], datoViejo);
				System.out.printf("El %s nuevo : ", Cliente.campos[caso]);
				datoNuevo = entrada.nextLine();
				if (cliente.entradaValida(Cliente.campos[caso], datoNuevo)) {
					try {
						clientes.clientes.get(clientes.indexClientes(datoBuscado)).set(Cliente.campos[caso], datoNuevo);
						System.out.println("\nDatos cambiados con éxito !");
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				} else {
					throw new Exception(Cliente.campos[caso] + " incorrecto !");
				}
				break;

			}
		}
	}

	/**
	 * El menú de la aplicación, pide al usuario elegir entre una de las opciones
	 * ofrecidas, el usuario solo puede elegír entre una de las opciones ofrecidas,
	 * según la opción elegida, el programa llama un método u otro.
	 * 
	 */
	public static void menu() {
		int caso = 0;
		int caso_salir = 6;
		while (caso != caso_salir) {

			String menu = "";

			System.out.println();
			System.out.println("Elige una de las opciones: ");
			System.out.println();
			System.out.println("1) Añadir un cliente nuevo");
			System.out.println("2) Ver datos");
			System.out.println("3) Editar datos de un cliente");
			System.out.println("4) Eliminar un cliente");
			System.out.println("5) Eliminar todos los datos");
			System.out.println("6) Salir del programa");
			System.out.println();
			menu = entrada.nextLine();

			try {
				if (cliente.entradaValida("menu", menu)) {
					caso = Integer.parseInt(menu);
				} else {
					throw new Exception("\nSolo numeros del 1 a 6");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			switch (caso) {

			case 1:
				try {
					cliente = vista.agregarCliente(entrada);
					clientes.agregar(cliente);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			case 2:
				elegirVista();
				break;
			case 3:
				try {
					editarCliente(clientes);
				} catch (Exception e) {
					System.out.println();
					System.err.println(e.getLocalizedMessage());
				}
				break;
			case 4:
				eliminarCliente();
				break;
			case 5:
				vaciar(clientes, archivo);
				break;
			case 6:
				try {
				escribir(clientes, archivo);
				} catch (Exception e) {
					System.err.println(e.getLocalizedMessage());
				}
				System.exit(0);
				break;

			}

		}
	}
}
