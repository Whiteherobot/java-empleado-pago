package com.michicorp.nomina;

import java.time.Year;
import java.util.Locale;

/**
 * Empleado que cobra por horas. La cédula lo identifica.
 */
public class Empleado {

    private final String cedula;
    private final String nombre;
    private final double costoHora;
    private final int anioIngreso;
    private int horasTrabajadas;

    public Empleado(String cedula, String nombre, double costoHora, int anioIngreso) {
        this.cedula = requerirTexto(cedula, "La cédula");
        this.nombre = requerirTexto(nombre, "El nombre");
        if (costoHora <= 0 || Double.isNaN(costoHora)) {
            throw new IllegalArgumentException("El costo por hora debe ser mayor que cero");
        }
        if (anioIngreso < 1950 || anioIngreso > Year.now().getValue()) {
            throw new IllegalArgumentException("El año de ingreso no es válido");
        }
        this.costoHora = costoHora;
        this.anioIngreso = anioIngreso;
    }

    /** Registra las horas trabajadas en el mes actual. */
    public void registrarHoras(int horas) {
        if (horas < 0 || horas > 744) {
            throw new IllegalArgumentException("Las horas del mes deben estar entre 0 y 744");
        }
        horasTrabajadas = horas;
    }

    /** Años completos de servicio a la fecha indicada. */
    public int antiguedad(Year referencia) {
        return Math.max(0, referencia.getValue() - anioIngreso);
    }

    public RolDePagos rolDePagos(Year referencia) {
        return CalculadoraNomina.calcular(this, referencia);
    }

    public RolDePagos rolDePagos() {
        return rolDePagos(Year.now());
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Empleado otro && cedula.equals(otro.cedula));
    }

    @Override
    public int hashCode() {
        return cedula.hashCode();
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%s (%s) · $%.2f/h · desde %d", nombre, cedula, costoHora, anioIngreso);
    }

    private static String requerirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío");
        }
        return valor.strip();
    }
}
