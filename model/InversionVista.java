package model;


/**
 * Subclase de ProductoBancario
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InversionVista extends ProductoBancario {
    
    /**
     * Crea la subclase InversionVista, que se encarga de administrar el tipo de interes segun sus reglas
     */
    public InversionVista(double pMonto, int pPeriodoTotal, String pMonedaInversion) {
        super(pMonto, pPeriodoTotal, pMonedaInversion);
    }

    /**
     * Calcula el interes del producto bancario segun las reglas del producto
     *
     * @return    el porcentaje del interes
     */
    protected double calcularInteres() {
        if (periodoTotal < 15) {
            return 0;
        } else if (periodoTotal < 30) {
            return monedaInversion == "colones" ? 0.0485 : 0.008;
        } else if (periodoTotal < 60) {
            return monedaInversion == "colones" ? 0.0494 : 0.0091;
        } else if (periodoTotal < 90) {
            return monedaInversion == "colones" ? 0.0523 : 0.0106;
        } else if (periodoTotal < 180) {
            return monedaInversion == "colones" ? 0.0581 : 0.0144;
        } else if (periodoTotal < 270) {
            return monedaInversion == "colones" ? 0.0883 : 0.0221;
        } else if (periodoTotal < 360) {
            return monedaInversion == "colones" ? 0.0869 : 0.0226;
        } else {
            return monedaInversion == "colones" ? 0.0869 : 0.024;
        }
    }
    
    /**
     * Retorna el estado del objeto
     * 
     * @return el estado del objeto
     */
    public String toString() {
        return "Producto Bancario: Inversion a la vista tasa pactada\n" +
             super.toString() + 
             "Saldo final: " + calcularSaldoFinal() + "\n";
    }
}
