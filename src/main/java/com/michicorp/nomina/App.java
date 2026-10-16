package com.michicorp.nomina;

import java.io.PrintStream;
import java.time.Year;
import java.util.Scanner;

/**
 * Menú de consola para registrar empleados y generar sus roles de pago.
 */
public class App {

    private final Scanner entrada;
    private final PrintStream salida;
    private final Nomina nomina = new Nomina();
    private final Year anio;

    public App(Scanner entrada, PrintStream salida, Year anio) {
        this.entrada = entrada;
        this.salida = salida;
        this.anio = anio;
    }

    public static void main(String[] args) {
        App app = new App(new Scanner(System.in), System.out, Year.now());
        app.cargarEjemplos();
        app.ejecutar();
    }

    void cargarEjemplos() {
        Empleado gabriela = new Empleado("0102030405", "Gabriela", 9, 2010);
        gabriela.registrarHoras(100);
        Empleado mauricio = new Empleado("0105060708", "Mauricio", 4.5, anio.getValue());
        mauricio.registrarHoras(176);
        nomina.registrar(gabriela);
        nomina.registrar(mauricio);
    }

    public void ejecutar() {
        salida.println("=== Rol de pagos " + anio + " ===");
        while (true) {
            salida.print("""

                    1. Registrar empleado
                    2. Registrar horas del mes
                    3. Ver rol de pagos
                    4. Resumen de la nómina
                    5. Listar empleados
                    0. Salir
                    Opción:\s""");
            if (!entrada.hasNextLine()) {
                return;
            }
            String opcion = entrada.nextLine().strip();
            try {
                switch (opcion) {
                    case "1" -> registrarEmpleado();
                    case "2" -> registrarHoras();
                    case "3" -> verRol();
                    case "4" -> salida.print("\n" + nomina.resumen(anio));
                    case "5" -> nomina.empleados().forEach(e -> salida.println("  " + e));
                    case "0" -> {
                        salida.println("Hasta luego.");
                        return;
                    }
                    default -> salida.println("Opción no válida.");
                }
            } catch (IllegalArgumentException ex) {
                salida.println("Error: " + ex.getMessage());
            }
        }
    }

    private void registrarEmpleado() {
        String cedula = pedir("Cédula");
        String nombre = pedir("Nombre");
        double costo = pedirDecimal("Costo por hora");
        int ingreso = pedirEntero("Año de ingreso");
        Empleado e = new Empleado(cedula, nombre, costo, ingreso);
        e.registrarHoras(pedirEntero("Horas trabajadas este mes"));
        salida.println(nomina.registrar(e) ? "Empleado registrado." : "Ya existe un empleado con esa cédula.");
    }

    private void registrarHoras() {
        Empleado e = buscarEmpleado();
        if (e != null) {
            e.registrarHoras(pedirEntero("Horas trabajadas este mes"));
            salida.println("Horas actualizadas.");
        }
    }

    private void verRol() {
        Empleado e = buscarEmpleado();
        if (e != null) {
            salida.print("\n" + e.rolDePagos(anio).comprobante());
        }
    }

    private Empleado buscarEmpleado() {
        String cedula = pedir("Cédula");
        Empleado e = nomina.buscar(cedula).orElse(null);
        if (e == null) {
            salida.println("No hay un empleado con esa cédula.");
        }
        return e;
    }

    private String pedir(String campo) {
        salida.print(campo + ": ");
        return entrada.hasNextLine() ? entrada.nextLine().strip() : "";
    }

    private int pedirEntero(String campo) {
        String valor = pedir(campo);
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(campo + " debe ser un número entero");
        }
    }

    private double pedirDecimal(String campo) {
        String valor = pedir(campo).replace(',', '.');
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(campo + " debe ser un número");
        }
    }
}
