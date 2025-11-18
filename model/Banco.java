package model;
import java.util.ArrayList;

/**
 * Administra clientes, validaciones y registro de productos.
 */
public class Banco {

    private String nombreBanco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<ProductoBancario> listaProductos;

    /**
     * Constructor.
     *
     * @param pNombreBanco nombre del banco.
     */
    public Banco (String pNombreBanco) {
        this.nombreBanco = pNombreBanco;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaProductos = new ArrayList<ProductoBancario>();
    }

    /**
     * Valida nombre del cliente.
     */
    private boolean validarNombreCliente(String nombreCliente) {
        if (nombreCliente == null) {
            return false;
        }
        return !nombreCliente.trim().equals("");
    }

    /**
     * Valida correo electrónico .
     */
    private boolean validarCorreoCliente(String correoCliente) {
        if (correoCliente == null) {
            return false;
        }

        correoCliente = correoCliente.trim();
        if (correoCliente.equals("")) {
            return false;
        }

        return correoCliente.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    /**
     * Valida un ID de exactamente 9 dígitos.
     */
    private boolean validarIdCliente(int idCliente) {
        if (idCliente <= 0) {
            return false;
        }

        String idStr = String.valueOf(idCliente);

        // Debe tener exactamente 9 dígitos.
        return idStr.length() == 9;
    }

    /**
     * Valida si num1 < num2.
     */
    private boolean validarMenor(double num1, double num2) {
        return num1 < num2;
    }

    /**
     * Valida monedas permitidas: colones o dolares.
     */
    private boolean validarMoneda(String moneda) {
        if (moneda == null) {
            return false;
        }

        return moneda.equalsIgnoreCase("colones")
                || moneda.equalsIgnoreCase("dolares");
    }

    /**
     * Agrega un cliente al banco.
     */
    public void agregarCliente(int id, String nombreCliente,String correoElectronico) {

        if (!validarIdCliente(id)) {
            System.out.println("ERROR: El ID debe tener exactamente 9 dígitos.");
            return;
        }

        if (!validarNombreCliente(nombreCliente)) {
            System.out.println("ERROR: Nombre inválido.");
            return;
        }

        if (!validarCorreoCliente(correoElectronico)) {
            System.out.println("ERROR: Correo electrónico inválido.");
            return;
        }

        // Verificar si el cliente ya existe
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
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Registra un producto bancario.
     */
    public void registrarProductoBancario(String tipoCuenta,double monto,int periodo,String moneda) {

        if (tipoCuenta == null || tipoCuenta.trim().equals("")) {
            System.out.println("ERROR: Tipo de cuenta inválido.");
            return;
        }

        if (!validarMoneda(moneda)) {
            System.out.println("ERROR: Moneda inválida.");
            return;
        }

        if (!validarMenor(0, monto)) {
            System.out.println("ERROR: El monto debe ser mayor que 0.");
            return;
        }

        if (!validarMenor(0, periodo)) {
            System.out.println("ERROR: El periodo debe ser mayor que 0.");
            return;
        }

        tipoCuenta = tipoCuenta.toLowerCase();
        moneda = moneda.toLowerCase();

        ProductoBancario nuevo = null;

        if (tipoCuenta.equals("corriente")) {
            nuevo = new CuentaCorriente(monto, periodo, moneda);
        } else if (tipoCuenta.equals("pactada")) {
            nuevo = new InversionVista(monto, periodo, moneda);
        } else if (tipoCuenta.equals("certificado")) {
            nuevo = new CertificadoDeposito(monto, periodo, moneda);
        } else {
            System.out.println("ERROR: Tipo de cuenta desconocido.");
            return;
        }

        listaProductos.add(nuevo);
        System.out.println("Producto bancario registrado correctamente.");
    }

    /**
     * Consulta los productos registrados.
     */
    public void consultarProductosBancarios() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos bancarios registrados.");
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
