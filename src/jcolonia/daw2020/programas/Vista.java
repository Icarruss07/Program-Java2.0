package jcolonia.daw2020.programas;

import java.util.Scanner;

public class Vista {
	static Cliente cliente;
	
	@SuppressWarnings("static-access")
	public Cliente agregarCliente(Scanner entrada) throws Exception {
		cliente = new Cliente();
		boolean correcto;
		String parametro = " ";
		
		for (int i = 0; i < cliente.campos.length; i++) {
			parametro = cliente.campos[i];
			do {
				System.out.printf("%s : ", parametro);
				correcto = cliente.set(parametro, entrada.nextLine());
			} while (!correcto);
		}
		
		return cliente;
	}

}
