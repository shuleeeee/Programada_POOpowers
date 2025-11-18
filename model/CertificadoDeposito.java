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
    public CertificadoDeposito(double pMonto, int pPeriodoTotal, String pMonedaInversion, Cliente pClienteAsociado) {
        super(pMonto, pPeriodoTotal, pMonedaInversion, pClienteAsociado);
        retencionRendimiento = rendimiento * interesRetencion;
    }

    /**
     * Calcula el interes del producto bancario segun las reglas del producto
     *
     * @return    el porcentaje del interes
     */
    protected double calcularInteres() {
        if (periodoTotal < 30) {
            return 0;        
        } else if (periodoTotal < 60) {
            return 0.054;
        } else if (periodoTotal < 90) {
            return 0.057;
        } else if (periodoTotal < 150) {
            return 0.063;
        } else if (periodoTotal < 180) {
            return 0.0945;
        } else if (periodoTotal < 210){
            return 0.0995;
        } else if (periodoTotal < 240){
            return 0.1;
        } else {
            return 0.093;
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
