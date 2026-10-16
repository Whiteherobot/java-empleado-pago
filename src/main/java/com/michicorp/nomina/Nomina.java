package com.michicorp.nomina;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Conjunto de empleados de la empresa y totales del mes.
 */
public class Nomina {

    private final List<Empleado> empleados = new ArrayList<>();

    /** Registra un empleado; devuelve false si su cédula ya existe. */
    public boolean registrar(Empleado empleado) {
        if (empleados.contains(empleado)) {
            return false;
        }
        return empleados.add(empleado);
    }

    public Optional<Empleado> buscar(String cedula) {
        return empleados.stream().filter(e -> e.getCedula().equals(cedula.strip())).findFirst();
    }

    public List<Empleado> empleados() {
        return Collections.unmodifiableList(empleados);
    }

    public double totalNeto(Year referencia) {
        return CalculadoraNomina.redondear(empleados.stream()
                .mapToDouble(e -> e.rolDePagos(referencia).netoAPagar()).sum());
    }

    public double totalAportesIess(Year referencia) {
        return CalculadoraNomina.redondear(empleados.stream()
                .mapToDouble(e -> e.rolDePagos(referencia).aporteIess()).sum());
    }

    public String resumen(Year referencia) {
        StringBuilder sb = new StringBuilder(String.format("%-20s %6s %12s%n", "Empleado", "Horas", "Neto"));
        for (Empleado e : empleados) {
            sb.append(String.format(Locale.ROOT, "%-20s %6d %12.2f%n",
                    e.getNombre(), e.getHorasTrabajadas(), e.rolDePagos(referencia).netoAPagar()));
        }
        sb.append(String.format(Locale.ROOT, "%-20s %6s %12.2f%n", "Total a pagar", "", totalNeto(referencia)));
        sb.append(String.format(Locale.ROOT, "%-20s %6s %12.2f%n", "Aportes al IESS", "", totalAportesIess(referencia)));
        return sb.toString();
    }
}
