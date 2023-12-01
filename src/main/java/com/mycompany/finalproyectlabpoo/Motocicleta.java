
package com.mycompany.finalproyectlabpoo;



 
public class Motocicleta extends Vehiculo  {
    private int cilindrada;

  

    
    public Motocicleta(int cilindrada, String marca, String modelo, String placa, int par1, int par2) {
        super(marca, modelo, placa);
        this.cilindrada = cilindrada;
    }

    
}