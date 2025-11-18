package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Super clase que define el comportamiento básico de un producto bancario tradicional
 *
 * @author Esteban Ramirez Arrieta
 * @version 11/16/2025
 */
public abstract class ProductoBancario implements Comparable {
    protected double monto;
    protected int periodoTotal;
    protected double interes;
    protected String monedaInversion;
    protected double rendimiento;
    protected String fechaRegistro;
    protected String horaRegistro;
    private Cliente clienteAsociado;

    /**
     * Crea una instancia de un producto bancario
     */
    public ProductoBancario(double pMonto, int pPeriodoTotal, String pMonedaInversion, Cliente pClienteAsociado) {
        monto = pMonto;
        periodoTotal = pPeriodoTotal;
        clienteAsociado = pClienteAsociado;
        monedaInversion = pMonedaInversion;
        interes = calcularInteres();
        rendimiento = calcularRendimiento();
        fechaRegistro = establecerFechaRegistro();
        horaRegistro = establecerHoraRegistro();
    }

    /**
     * Calcula el rendimiento del producto bancario de acuerdo al monto, interes y periodo
     *
     * @return    el rendimiento esperado del producto bancario
     */
    private double calcularRendimiento() {
        double sumaRendimiento = 0;
        for (int i = 1; i <= periodoTotal; i++){
            rendimiento += monto * (interes / 360);
        }
        return sumaRendimiento;
    }
    
    /**
     * Calcula la fecha actual en formato dia-mes-año
     * 
     * @return  un string con la fecha actual
     */
    private String establecerFechaRegistro() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
        return LocalDate.now().format(formatoFecha); 
    }
    
    /**
     * Calcula la hora actual en formato horas:minutos:segundos
     * 
     * @return  un string con la hora actual
     */
    private String establecerHoraRegistro() {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss"); 
        return LocalDate.now().format(formatoHora); 
    }
    
    /**
     * Calcula el interes segun el monto o el periodo segun lo que corresponda
     * 
     * @return  el interes del producto bancario
     */
    protected abstract double calcularInteres();
    
    /**
     * Retorna el monto del objeto
     * 
     * @return  el monto del producto bancario
     */
    private double getMonto () {
        return monto;
    }

    /**
     * Verifica si la instancia tiene menor monto que otro pasado por parametro
     * 
     * @return  un objeto de tipo Comparable
     */
    public boolean menorQue (Comparable obj) {
        ProductoBancario otro = (ProductoBancario) obj;
        return this.getMonto() < otro.getMonto();
    }
    
    /**
     * Devuelve el estado del objeto
     * 
     * @return  estado del objeto
     */
    public String toString() {
        return "Monto de ahorro: " + monto +
                "\nPlazo de la inversion en días: " + periodoTotal +
                "\nInteres anual: " + interes +
                "\nRendimiento: " + rendimiento +
                "\nMoneda: " + monedaInversion +
                "\nFecha de registro: " + fechaRegistro +
                "\nHora de registro: " + horaRegistro + "\n";
                
    }
    
    /**
     * Calcula el saldo final sumando el monto con le rendimiento esperado
     * 
     * @return el saldo final
     */
    public double calcularSaldoFinal () {
        return monto + rendimiento;
    }
}
