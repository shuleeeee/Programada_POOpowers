package view.cli;

import java.util.Scanner;
import model.Banco;
import model.Cliente;

public class CLIBanco {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del banco: ");
        String nombre = sc.nextLine();

        Banco banco = new Banco(nombre);
        Cliente clienteActual = null;
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== MENÚ BANCO ===");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Eliminar cliente");
            System.out.println("3. Consultar cliente por ID");
            System.out.println("4. Registrar producto bancario");
            System.out.println("5. Consultar productos bancarios");
            System.out.println("6. Consultar clientes");
            System.out.println("0. Salir");

            System.out.print("\nSeleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID (9 dígitos): ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre del cliente: ");
                    String nombreCliente = sc.nextLine();

                    System.out.print("Correo electrónico: ");
                    String correo = sc.nextLine();

                    banco.agregarCliente(id, nombreCliente, correo);
                    break;

                case 2:
                    System.out.print("ID a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();

                    System.out.print("¿Está seguro que desea eliminar este cliente? (s/n): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("s")) {
                        banco.eliminarCliente(idEliminar);
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    break;

                case 3:
                    System.out.print("ID a consultar: ");
                    int idConsulta = sc.nextInt();
                    sc.nextLine();

                    clienteActual = banco.consultarClienteId(idConsulta);
                    if (clienteActual == null) {
                        System.out.println("Cliente no encontrado.");
                    } else {
                        System.out.println("\nCliente encontrado:");
                        System.out.println(clienteActual);
                    }
                    break;

                case 4:
                    if (clienteActual == null) {
                        System.out.println("Debe consultar un cliente antes de registrar un producto.");
                        break;
                    }

                    System.out.print("Tipo de producto (corriente, pactada, certificado): ");
                    String tipo = sc.nextLine();

                    System.out.print("Monto: ");
                    double monto = sc.nextDouble();

                    System.out.print("Periodo (días): ");
                    int periodo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Moneda (colones / dolares): ");
                    String moneda = sc.nextLine();

                    banco.registrarProductoBancario(tipo, monto, periodo, moneda);
                    break;

                case 5:
                    System.out.println("\n--- Productos registrados ---");
                    banco.consultarProductosBancarios();
                    break;

                case 6:
                    System.out.println("\n--- Lista de clientes ---");
                    banco.consultarClientes();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }
}