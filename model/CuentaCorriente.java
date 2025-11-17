package model;


/**
 * Subclase de ProductoBancario
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CuentaCorriente extends ProductoBancario {
    
    /**
     * Crea la subclase CuentaCorriente, que se encarga de administrar el tipo de interes segun sus reglas
     */
    public CuentaCorriente(double pMonto, int pPeriodoTotal, String pMonedaInversion) {
        super(pMonto, pPeriodoTotal, pMonedaInversion);
    }

    /**
     * Calcula el interes del producto bancario segun las reglas del producto
     *
     * @return    el porcentaje del interes
     */
    protected double calcularInteres() {
        if (monto < 25000) {
            return 0;        
        } else if (monto <= 500000) {
            return 0.01;
        } else if (monto <= 1000000) {
            return 0.02;
        } else if (monto <= 2500000) {
            return 0.0225;
        } else if (monto <= 10000000) {
            return 0.025;
        } else {
            return 0.0275;
        }
    }
        
    /**
     * Retorna el estado del objeto
     * 
     * @return el estado del objeto
     */
    public String toString() {
        return "Producto Bancario: Cuenta corriente\n" +
             super.toString() + 
             "Saldo final: " + calcularSaldoFinal() + "\n";
    }
}
