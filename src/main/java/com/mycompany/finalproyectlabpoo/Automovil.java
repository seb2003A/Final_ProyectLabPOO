
package com.mycompany.finalproyectlabpoo;




public class Automovil extends Vehiculo {
    private int numeroPuertas;
  
    // Constructores, getters y setters

    // Otros métodos específicos para automóviles
    
    public Automovil(int numeroPuertas, String marca, String modelo, String placa, int par1, int par2) {
        super(marca, modelo, placa);
        this.numeroPuertas = numeroPuertas;
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }



    
    
}