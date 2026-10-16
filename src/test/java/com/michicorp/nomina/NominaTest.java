package com.michicorp.nomina;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NominaTest {

    private static final Year ANIO = Year.of(2026);

    @Test
    void noRegistraCedulasRepetidas() {
        Nomina n = new Nomina();
        assertTrue(n.registrar(new Empleado("0102030405", "Ana", 5, 2020)));
        assertFalse(n.registrar(new Empleado("0102030405", "Otra", 7, 2021)));
        assertEquals(1, n.empleados().size());
    }

    @Test
    void sumaElNetoDeTodos() {
        Nomina n = new Nomina();
        Empleado a = new Empleado("1", "Ana", 5, 2026);
        a.registrarHoras(160);
        Empleado b = new Empleado("2", "Luis", 5, 2026);
        b.registrarHoras(160);
        n.registrar(a);
        n.registrar(b);
        assertEquals(1448.80, n.totalNeto(ANIO));
        assertEquals(151.20, n.totalAportesIess(ANIO));
        assertTrue(n.resumen(ANIO).contains("1448.80"));
    }

    @Test
    void buscaPorCedula() {
        Nomina n = new Nomina();
        n.registrar(new Empleado("0102030405", "Ana", 5, 2020));
        assertEquals("Ana", n.buscar(" 0102030405 ").orElseThrow().getNombre());
        assertTrue(n.buscar("999").isEmpty());
    }

    @Test
    void validaLosDatosDelEmpleado() {
        assertThrows(IllegalArgumentException.class, () -> new Empleado("", "Ana", 5, 2020));
        assertThrows(IllegalArgumentException.class, () -> new Empleado("1", "Ana", 0, 2020));
        assertThrows(IllegalArgumentException.class, () -> new Empleado("1", "Ana", 5, 1900));
        Empleado e = new Empleado("1", "Ana", 5, 2020);
        assertThrows(IllegalArgumentException.class, () -> e.registrarHoras(-1));
    }
}
