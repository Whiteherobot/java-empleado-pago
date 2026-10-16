/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.empleado;

/**
 *
 * 
 */
public class Empleado {
        String nombre;
        int horasTrabajadas;
        double costoHoras;
        int yearincome;
        
        public int calcularIngresos(){
        var retorno= 4197;
        if(this.yearincome> 2003 && this.yearincome< 2009){
            retorno= 4197;
            
        }else{
            if(this.yearincome>=2010 && this.yearincome <=2022){
                retorno= 0;
            }else{
                if(this.yearincome >= 2022 && this.yearincome <= 2030 ){
                    retorno= 0;
                }else{
                   retorno= 0;
                }
            }
        }
        return (int) retorno;
    }

        public double calcularPago(){
        return this.horasTrabajadas * this.costoHoras;
    }
        
}