package dano.ZonaFit;

import dano.ZonaFit.modelo.Cliente;
import dano.ZonaFit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;
	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nextLine = System.lineSeparator();
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		SpringApplication.run(ZonaFitApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		boolean salir = false;
		while (!salir){
			int opcion = menu(scanner);
			salir = opciones(opcion);
		}
	}

	private int menu(Scanner scanner){
		logger.info("""
				----------------------------Zona Fit----------------------------
				1. Listar clientes
				2. Buscar cliente por id
				3. Agregar cliente
				4. Modificar cliente
				5. Eliminar Cliente
				6. Salir
				
				""");
		Integer opcion = Integer.parseInt(scanner.nextLine());
		return opcion;
	}

	private boolean opciones(Integer opcion){
		switch (opcion){
			case 1 -> {
				List<Cliente> clientes = clienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info(nextLine + clientes.toString() + nextLine));
			}
			case 2 -> {
				logger.info(nextLine + "Id del cliente a buscar: ");
				int id = Integer.parseInt(scanner.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(id);
				if (cliente != null)
					logger.info(nextLine + cliente.toString());
				else
					logger.info(nextLine + "Cliente no encontrado");
			}
			case 3 -> {
				logger.info(nextLine + "Nombre del cliente: ");
				String nombre = scanner.nextLine();
				logger.info(nextLine + "Apellido del cliente: ");
				String apellido = scanner.nextLine();
				logger.info(nextLine + "Membresia del cliente");
				Integer membresia = Integer.parseInt(scanner.nextLine());

				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);

				clienteServicio.guardarCliente(cliente);
				logger.info(nextLine + "Cliente agregdo con exito");
			}
			case 4 -> {
				logger.info(nextLine + "Dame el id del cliente: ");
				int id = Integer.parseInt(scanner.nextLine());
				logger.info(nextLine + "Nombre: ");
				String nombre = scanner.nextLine();
				logger.info(nextLine + "Apellido: ");
				String apellido = scanner.nextLine();
				logger.info(nextLine + "Membresia: ");
				Integer membresia = Integer.parseInt(scanner.nextLine());

				Cliente cliente = clienteServicio.buscarClientePorId(id);
				if (cliente != null){
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					clienteServicio.guardarCliente(cliente);
					logger.info(nextLine+ "Cliente modificado con exito");
				}else {
					logger.info(nextLine + "Cliente no encontrado");
				}
			}

			case 5 -> {
				logger.info(nextLine + "Dame el id del cliente: ");
				int id = Integer.parseInt(scanner.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(id);
				if (cliente != null){
					clienteServicio.eliminarCliente(cliente);
					logger.info(nextLine + "Cliente eliminado");
				}else {
					logger.info(nextLine + "Cliente no encontrado");
				}
			}

			case 6 -> {
				return true;
			}
		}
		return false;
	}
}
