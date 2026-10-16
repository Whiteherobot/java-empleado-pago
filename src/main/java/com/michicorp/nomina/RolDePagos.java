package com.michicorp.nomina;

import java.util.Locale;

/**
 * Resultado del cálculo mensual de un empleado. Los montos están en dólares.
 */
public record RolDePagos(
        String empleado,
        int horasOrdinarias,
        int horasExtra,
        double sueldo,
        double pagoHorasExtra,
        double fondosReserva,
        double aporteIess) {

    public double totalIngresos() {
        return CalculadoraNomina.redondear(sueldo + pagoHorasExtra + fondosReserva);
    }

    public double totalDescuentos() {
        return aporteIess;
    }

    public double netoAPagar() {
        return CalculadoraNomina.redondear(totalIngresos() - totalDescuentos());
    }

    /** Rol de pagos con formato de comprobante. */
    public String comprobante() {
        String linea = "-".repeat(40) + "\n";
        return "Rol de pagos: " + empleado + "\n" + linea
                + fila("Sueldo (" + horasOrdinarias + " h)", sueldo)
                + fila("Horas extra (" + horasExtra + " h)", pagoHorasExtra)
                + fila("Fondos de reserva", fondosReserva)
                + fila("Total ingresos", totalIngresos())
                + fila("Aporte IESS 9,45%", -aporteIess)
                + linea
                + fila("Neto a pagar", netoAPagar());
    }

    private static String fila(String concepto, double monto) {
        return String.format(Locale.ROOT, "%-28s%12.2f%n", concepto, monto);
    }
}
