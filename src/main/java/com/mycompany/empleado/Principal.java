/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empleado;


import java.util.Scanner;

/**
 *
 * @author ealex
 */
public class Principal {
    public static void main(String[] args) {

       var empleado1 = new Empleado();
       Scanner entradaEscaner=new Scanner(System.in);
       System.out.println("Introduzca el nombre del empleado: ");
       empleado1.nombre= entradaEscaner.nextLine().trim();
       System.out.println("Horas trabajadas: ");
       empleado1.horasTrabajadas= Integer.parseInt(entradaEscaner.nextLine().trim());
       System.out.println("Costo por hora: ");
       empleado1.costoHoras= Double.parseDouble(entradaEscaner.nextLine().trim().replace(',', '.'));
       System.out.println("Año de ingreso: ");
       empleado1.yearincome= Integer.parseInt(entradaEscaner.nextLine().trim());
       System.out.println(empleado1.nombre+" | "+
                          empleado1.horasTrabajadas+" | "+
                          empleado1.costoHoras+" | "+
                          empleado1.yearincome+" | ");
       var empleado2 = new Empleado();
       empleado2.nombre= "Gabriela";
       empleado2.horasTrabajadas= 100;
       empleado2.costoHoras= (9);
       empleado2.yearincome= 2010;
       System.out.println(empleado2.nombre+" | "+
                          empleado2.horasTrabajadas+" | "+
                          empleado2.costoHoras+" | "+
                          empleado2.yearincome+" | ");
System.out.println(empleado2.nombre+" recibe $"+ empleado2.calcularPago()+ " por "+ empleado2.horasTrabajadas+ " horas.");
System.out.println(empleado1.nombre+" recibe $"+ empleado1.calcularPago()+ " por "+ empleado1.horasTrabajadas+ " horas.");
System.out.println("Usted ha trabajado desde "+ empleado1.yearincome+ ", por lo tanto su ingreso adicional es: $"+ empleado1.calcularIngresos());
       
    }
    
}
