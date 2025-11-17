package model;


/**
 * Subclase de CertificadoDeposito
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CertificadoDeposito extends ProductoBancario {
    
    private double retencionRendimiento;
    private static double interesRetencion = 0.08;
    /**
     * Crea la subclase CuentaCorriente, que se encarga de administrar el tipo de interes segun sus reglas
     */
    public CertificadoDeposito(double pMonto, int pPeriodoTotal, String pMonedaInversion) {
        super(pMonto, pPeriodoTotal, pMonedaInversion);
        retencionRendimiento = rendimiento * interesRetencion;
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
     * Retorna el monto por retencion del rendimiento
     * 
     * @return  la retencion por rendimiento
     */
    public double getRetencionRedimiento() {
        return interesRetencion;
    }
    
    /**
     * Retorna el estado del objeto
     * 
     * @return el estado del objeto
     */
    public String toString() {
        double saldoFinal = monto + rendimiento - retencionRendimiento;
        return "Producto Bancario: Certificado de deposito a plazo\n" +
             super.toString() + 
             "Retencion de rendimiento: " + retencionRendimiento + "\n" +
             "Saldo final: " + calcularSaldoFinal() + "\n";
    }
    
    /**
     * Calcula el saldo final sumando el monto con le rendimiento esperado - la retencion
     * 
     * @return el saldo final
     */
    @Override
    public double calcularSaldoFinal () {
        return monto + rendimiento - retencionRendimiento;
    }
}
