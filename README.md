# java-empleado-pago

Tarea de Programación Orientada a Objetos (Unidad 2, abril de 2022). La clase
`Empleado` guarda el nombre, las horas trabajadas, el costo por hora y el año de
ingreso, y tiene dos métodos:

- `calcularPago()`: horas trabajadas × costo por hora.
- `calcularIngresos()`: un ingreso adicional que depende del año de ingreso
  ($4197 para quienes entraron entre 2004 y 2008; $0 en los demás casos).

`Principal` pide por teclado los datos de un empleado, los compara con un
segundo empleado de ejemplo y muestra el pago de cada uno.

## Ejecutar

Con JDK 11 o superior y Maven:

```bash
mvn compile
java -cp target/classes com.mycompany.empleado.Principal
```

```
Introduzca el nombre del empleado:
Mauricio
Horas trabajadas:
160
Costo por hora:
4,50
Año de ingreso:
2005
Mauricio | 160 | 4.5 | 2005 |
Gabriela | 100 | 9.0 | 2010 |
Gabriela recibe $900.0 por 100 horas.
Mauricio recibe $720.0 por 160 horas.
Usted ha trabajado desde 2005, por lo tanto su ingreso adicional es: $4197
```

## Notas

En la entrega original el programa pedía el nombre pero no lo usaba, el primer
empleado se mostraba con todos sus datos en cero y el `pom.xml` apuntaba a una
clase sin `main`. En 2026 se completó la lectura por teclado, se agregó
`calcularPago()` y se corrigieron los mensajes. La regla de `calcularIngresos()`
se dejó como estaba.

## Autor

Michael Lata ([@Whiteherobot](https://github.com/Whiteherobot))
