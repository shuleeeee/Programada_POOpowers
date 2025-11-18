package model;

import java.util.ArrayList;
/**
 * Clase que simula el comportamiento de un cliente dentro de un banco, guarda información
 * básica como su nombre, correo y un id asociado
 *
 * @author Esteban Ramírez Arrieta
 * @version 11/16/2025
 */
public class Cliente implements Comparable{
    private int id;
    private String nombre;
    private String correoElectronico;
    private String codigoCliente = "CLI-";
    private static int cantClientes;
    private ArrayList<ProductoBancario> misProductos;
    
    /**
     * Crea una nueva instancia de Cliente con un codigo unico y id unico
     * 
     * @param pID el id del cliente
     * @param pNombre el nombre completo del cliente
     * @param pCorreoElectronico el correo electronico del cliente
     */
    public Cliente(int pId, String pNombre, String pCorreoElectronico) {
        id = pId;
        nombre = pNombre;
        correoElectronico = pCorreoElectronico;
        cantClientes++;
        codigoCliente += String.format("%03d", cantClientes);
        misProductos = new ArrayList<>();
    }

    /**
     * Retorna el codigo del cliente
     *
     * @return    codigo del cliente
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }
    
    /**
     * Retorna el id del cliente
     *
     * @return    id del cliente
     */
    public int getId() {
        return id;
    }
    
    /**
     * Retorna el nombre del cliente
     *
     * @return    nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Añade un nuevo producto a misProductos
     *
     * @param    la instancia del producto bancario por añadir
     */
    public void añadirProducto(ProductoBancario nuevoProducto) {
        misProductos.add(nuevoProducto);        
    }
    
    /**
     * Verifica que otro objeto de la misma clase tenga un nombre mayor que la instancia
     *
     * @param obj un objeto de tipo Comparable
     */
    public boolean menorQue(Comparable obj) {
        Cliente otro = (Cliente) obj;  
        return this.getNombre().compareToIgnoreCase(otro.getNombre()) < 0;
    }
    
    /**
     * Retorna el estado del objeto 
     * 
     * @return    el estado del objeto
     */
    public String toString() {
        return "\nId del cliente: " + id +
                "\nNombre del cliente: " + nombre +
                "\nCorreo electronico: " + correoElectronico +
                "\nCodigo del cliente: " + codigoCliente + "\n";
    }
}
