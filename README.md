# java-empleado-pago

[![CI](https://github.com/Whiteherobot/java-empleado-pago/actions/workflows/ci.yml/badge.svg)](https://github.com/Whiteherobot/java-empleado-pago/actions/workflows/ci.yml)

Rol de pagos por horas en Java, desde la consola. Se registran los empleados con
su costo por hora y su año de ingreso, se cargan las horas del mes y el programa
calcula lo que recibe cada uno.

Las reglas son una versión simplificada de las que se usan en Ecuador:

- Las primeras 160 horas del mes se pagan al valor normal y las adicionales con un recargo del 50%.
- Se descuenta el aporte personal al IESS (9,45%) sobre el sueldo más las horas extra.
- Desde el primer año de servicio se suman los fondos de reserva (8,33%).

```
Rol de pagos: Mauricio
----------------------------------------
Sueldo (160 h)                    720.00
Horas extra (16 h)                108.00
Fondos de reserva                   0.00
Total ingresos                    828.00
Aporte IESS 9,45%                 -78.25
----------------------------------------
Neto a pagar                      749.75
```

El menú permite registrar empleados, actualizar sus horas, ver el rol de cada uno
y un resumen con el total a pagar y los aportes al IESS del mes. Arranca con dos
empleados de ejemplo para probarlo de una vez.

## Cómo correrlo

Necesitas JDK 17 o superior. El proyecto trae el wrapper de Maven.

```bash
./mvnw verify                             # compila y corre las pruebas
java -jar target/empleado-pago-1.0.0.jar
```

En Windows se usa `mvnw.cmd`.

## Estructura

```
src/main/java/com/michicorp/nomina/
├── App.java                 menú de consola
├── Empleado.java            datos del empleado y antigüedad
├── CalculadoraNomina.java   reglas del cálculo (recargo, IESS, fondos de reserva)
├── RolDePagos.java          resultado del mes y comprobante
└── Nomina.java              lista de empleados y totales
```

Las pruebas (JUnit 5) cubren el cálculo, la nómina y el menú, y corren en GitHub
Actions con Java 17 y 21.

## De dónde sale

Empezó como una tarea de POO de abril de 2022: una clase `Empleado` con nombre,
horas trabajadas, costo por hora y año de ingreso, y un método que intentaba
calcular un ingreso según el año. El programa leía el nombre por teclado sin
usarlo. En 2026 lo rehíce a partir de esos mismos atributos para que el cálculo
tuviera sentido, y el año de ingreso terminó definiendo los fondos de reserva.

La segunda parte de esa tarea (sueldo anual, bono por antigüedad e impuesto por
tramos) está en [java-sueldo-impuestos](https://github.com/Whiteherobot/java-sueldo-impuestos).

## Autor

Michael Lata ([@Whiteherobot](https://github.com/Whiteherobot)). Licencia MIT.
