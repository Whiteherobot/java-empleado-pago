package com.michicorp.nomina;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraNominaTest {

    private static final Year ANIO = Year.of(2026);

    private Empleado empleado(double costoHora, int ingreso, int horas) {
        Empleado e = new Empleado("0102030405", "Ana", costoHora, ingreso);
        e.registrarHoras(horas);
        return e;
    }

    @Test
    void sinHorasExtraNiFondosDeReserva() {
        RolDePagos rol = empleado(5, 2026, 160).rolDePagos(ANIO);
        assertEquals(800.00, rol.sueldo());
        assertEquals(0, rol.pagoHorasExtra());
        assertEquals(0, rol.fondosReserva());
        assertEquals(75.60, rol.aporteIess());
        assertEquals(724.40, rol.netoAPagar());
    }

    @Test
    void horasExtraConRecargoDel50() {
        RolDePagos rol = empleado(5, 2026, 170).rolDePagos(ANIO);
        assertEquals(160, rol.horasOrdinarias());
        assertEquals(10, rol.horasExtra());
        assertEquals(75.00, rol.pagoHorasExtra());
        assertEquals(82.69, rol.aporteIess());
    }

    @Test
    void fondosDeReservaDesdeElPrimerAnio() {
        assertEquals(0, empleado(5, 2026, 100).rolDePagos(ANIO).fondosReserva());
        RolDePagos rol = empleado(5, 2025, 100).rolDePagos(ANIO);
        assertEquals(41.65, rol.fondosReserva());
        assertEquals(541.65, rol.totalIngresos());
        assertEquals(494.40, rol.netoAPagar());
    }

    @Test
    void sinHorasNoHayPago() {
        RolDePagos rol = empleado(5, 2010, 0).rolDePagos(ANIO);
        assertEquals(0, rol.netoAPagar());
    }
}
