package com.michicorp.nomina;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    private String ejecutar(String teclado) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        App app = new App(new Scanner(teclado), new PrintStream(buffer, true, StandardCharsets.UTF_8), Year.of(2026));
        app.ejecutar();
        return buffer.toString(StandardCharsets.UTF_8);
    }

    @Test
    void registraEmpleadoYMuestraSuRol() {
        String salida = ejecutar("1\n0102030405\nAna\n5,00\n2025\n170\n3\n0102030405\n0\n");
        assertTrue(salida.contains("Empleado registrado."));
        assertTrue(salida.contains("Rol de pagos: Ana"));
        assertTrue(salida.contains("Hasta luego."));
    }

    @Test
    void informaErroresSinCerrarse() {
        String salida = ejecutar("1\n1\nAna\nabc\n9\n3\n404\n0\n");
        assertTrue(salida.contains("Error: Costo por hora debe ser un número"));
        assertTrue(salida.contains("Opción no válida."));
        assertTrue(salida.contains("No hay un empleado con esa cédula."));
        assertTrue(salida.contains("Hasta luego."));
    }
}
