
package com.mycompany.finalproyectlabpoo;

import java.io.Serializable;

public class Vehiculo implements Serializable {
    private String marca;
    private String modelo;
    private String placa;
  private int horasalida;
    private int horaingreso;

    public Vehiculo(String marca, String modelo, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(int horasalida) {
        this.horasalida = horasalida;
    }

    public int getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(int horaingreso) {
        this.horaingreso = horaingreso;
    }


    
    
}