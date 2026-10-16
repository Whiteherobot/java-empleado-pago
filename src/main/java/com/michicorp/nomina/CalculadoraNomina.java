package com.michicorp.nomina;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;

/**
 * Reglas del rol de pagos mensual, simplificadas a partir del Código del Trabajo
 * y la normativa del IESS de Ecuador.
 */
public final class CalculadoraNomina {

    /** Horas ordinarias del mes (40 horas semanales). */
    public static final int HORAS_ORDINARIAS_MES = 160;
    /** Recargo de las horas suplementarias. */
    public static final double RECARGO_HORA_EXTRA = 0.50;
    /** Aporte personal del trabajador al IESS. */
    public static final double APORTE_IESS = 0.0945;
    /** Fondos de reserva: un doceavo de lo ganado, desde el primer año de servicio. */
    public static final double FONDOS_RESERVA = 0.0833;

    private CalculadoraNomina() {
    }

    public static RolDePagos calcular(Empleado e, Year referencia) {
        int horas = e.getHorasTrabajadas();
        int ordinarias = Math.min(horas, HORAS_ORDINARIAS_MES);
        int extra = horas - ordinarias;

        double sueldo = redondear(ordinarias * e.getCostoHora());
        double horasExtra = redondear(extra * e.getCostoHora() * (1 + RECARGO_HORA_EXTRA));
        double materiaGravada = sueldo + horasExtra;
        double fondosReserva = e.antiguedad(referencia) >= 1 ? redondear(materiaGravada * FONDOS_RESERVA) : 0;
        double aporteIess = redondear(materiaGravada * APORTE_IESS);

        return new RolDePagos(e.getNombre(), ordinarias, extra, sueldo, horasExtra, fondosReserva, aporteIess);
    }

    static double redondear(double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
