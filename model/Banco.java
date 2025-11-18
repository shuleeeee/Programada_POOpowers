package model;

import java.util.ArrayList;
import util.Validaciones;

/**
 * Administra clientes y registro de productos.
 */
public class Banco {

    private String nombreBanco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<ProductoBancario> listaProductos;

    /**
     * Constructor del banco.
     */
    public Banco(String pNombreBanco) {
        this.nombreBanco = pNombreBanco;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaProductos = new ArrayList<ProductoBancario>();
    }

    /**
     * Agrega un cliente al banco.
     */
    public void agregarCliente(int id, String nombreCliente, String correoElectronico) {

        if (!Validaciones.validarIdCliente(id)) {
            System.out.println("ERROR: El ID debe tener exactamente 9 dígitos.");
            return;
        }

        if (!Validaciones.validarNombreCliente(nombreCliente)) {
            System.out.println("ERROR: Nombre inválido.");
            return;
        }

        if (!Validaciones.validarCorreoCliente(correoElectronico)) {
            System.out.println("ERROR: Correo electrónico inválido.");
            return;
        }

        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                System.out.println("ERROR: Ya existe un cliente con ese ID.");
                return;
            }
        }

        Cliente nuevo = new Cliente(id, nombreCliente, correoElectronico);
        listaClientes.add(nuevo);

        System.out.println("Cliente agregado correctamente.");
    }

    /**
     * Elimina un cliente según su ID.
     */
    public void eliminarCliente(int id) {
        Cliente encontrado = consultarClienteId(id);

        if (encontrado == null) {
            System.out.println("ERROR: No existe un cliente con ese ID.");
            return;
        }

        listaClientes.remove(encontrado);
        System.out.println("Cliente eliminado correctamente.");
    }

    /**
     * Consulta un cliente por su ID.
     */
    public Cliente consultarClienteId(int id) {
        for (Cliente c : listaClientes) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

     /**
     * Registra un producto bancario.
     */
    public void registrarProductoBancario(String tipoCuenta,double monto,int periodo,String moneda,Cliente clienteAsociado) {

        if (clienteAsociado == null) {
            System.out.println("ERROR: Debe seleccionar un cliente asociado.");
            return;
        }

        if (!Validaciones.validarMoneda(moneda)) {
            System.out.println("ERROR: Moneda inválida.");
            return;
        }

        if (!Validaciones.validarMenor(0, monto)) {
            System.out.println("ERROR: El monto debe ser mayor que 0.");
            return;
        }

        if (!Validaciones.validarMenor(0, periodo)) {
            System.out.println("ERROR: El periodo debe ser mayor que 0.");
            return;
        }

        tipoCuenta = tipoCuenta.toLowerCase();
        ProductoBancario nuevo = null;

        if (tipoCuenta.equals("corriente")) {

            if (!Validaciones.validarCuentaCorriente(monto, moneda)) {
                System.out.println("ERROR: Monto mínimo ₡25,000 ó moneda inválida.");
                return;
            }

            nuevo = new CuentaCorriente(monto, periodo, moneda);
        }

        else if (tipoCuenta.equals("pactada")) {

            if (!Validaciones.validarInversionVista(monto, periodo, moneda)) {
                System.out.println("ERROR: Datos inválidos para Inversión Vista.");
                return;
            }

            nuevo = new InversionVista(monto, periodo, moneda);
        }

        else if (tipoCuenta.equals("certificado")) {

            if (!Validaciones.validarCertificadoDeposito(monto, periodo, moneda)) {
                System.out.println("ERROR: Datos inválidos para Certificado de Depósito.");
                return;
            }

            nuevo = new CertificadoDeposito(monto, periodo, moneda);
        }

        else {
            System.out.println("ERROR: Tipo de cuenta desconocido.");
            return;
        }

        listaProductos.add(nuevo);
        System.out.println("Producto registrado correctamente para el cliente: "
                + clienteAsociado.getNombre());
    }

    /**
     * Consulta los productos registrados.
     */
    public void consultarProductosBancarios() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (ProductoBancario p : listaProductos) {
            System.out.println(p);
        }
    }

    /**
     * Consulta los clientes del banco.
     */
    public void consultarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
    }
}
