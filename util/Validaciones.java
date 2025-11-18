package util;

public class Validaciones {

    /**
     * Valida el nombre del cliente.
     */
    public static boolean validarNombreCliente(String nombreCliente) {
        if (nombreCliente == null) {
            return false;
        }
        return !nombreCliente.trim().equals("");
    }

    /**
     * Valida el correo electrónico.
     */
    public static boolean validarCorreoCliente(String correoCliente) {
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
     * Valida la cédula.
     */
    public static boolean validarIdCliente(int idCliente) {
        if (idCliente <= 0) {
            return false;
        }
        return String.valueOf(idCliente).length() == 9;
    }

    /**
     * Valida si num1 < num2.
     */
    public static boolean validarMenor(double num1, double num2) {
        return num1 < num2;
    }

    /**
     * Valida monedas permitidas: colones o dolares.
     */
    public static boolean validarMoneda(String moneda) {
        if (moneda == null) {
            return false;
        }
        moneda = moneda.toLowerCase();
        return moneda.equals("colones") || moneda.equals("dolares");
    }

    /**
     * Validación de cuenta corriente.
     * Mínimo: ₡25,000
     */
    public static boolean validarCuentaCorriente(double monto, String moneda) {
        if (!moneda.equalsIgnoreCase("colones")) {
            return false;
        }
        return validarMenor(25000, monto);
    }

    /**
     * Validación de inversión vista.
     * - Mínimo monto: ₡100,000 o $500
     * - Plazo mínimo: 15 días
     */
    public static boolean validarInversionVista(double monto, int periodo, String moneda) {

        moneda = moneda.toLowerCase();

        if (!moneda.equals("colones") && !moneda.equals("dolares")) {
            return false;
        }

        if (!validarMenor(15, periodo)) {
            return false;
        }

        if (moneda.equals("colones")) {
            return validarMenor(100000, monto);
        } else {
            return validarMenor(500, monto);
        }
    }

    /**
     * Validación de certificado de depósito.
     * - Plazo mínimo 30 días
     * - Plazo 30–89 mínimo ₡100,000
     * - Plazo >= 90 mínimo ₡50,000
     */
    public static boolean validarCertificadoDeposito(double monto, int periodo, String moneda) {

        if (!moneda.equalsIgnoreCase("colones")) {
            return false;
        }

        if (!validarMenor(30, periodo)) {
            return false;
        }

        if (periodo >= 30 && periodo <= 89) {
            return validarMenor(100000, monto);
        }

        if (periodo >= 90) {
            return validarMenor(50000, monto);
        }

        return false;
    }

}
