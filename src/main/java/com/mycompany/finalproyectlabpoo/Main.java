
package com.mycompany.finalproyectlabpoo;


import static spark.Spark.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

public class Main {

    private static final String ARCHIVO_VEHICULOS = System.getProperty("user.home") + "/Desktop/vehiculos.dat";
    private static final LinkedList<Vehiculo> automoviles = new LinkedList<>();
    private static final LinkedList<Vehiculo> motos = new LinkedList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Gson gson = new Gson();

        cargarDatos();

        LinkedList<Vehiculo> automoviles = new LinkedList<Vehiculo>();
        LinkedList<Vehiculo> motos = new LinkedList<Vehiculo>();

        int valorHoraAuto = 2000;

        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987", 12, 0);
        automoviles.add(auto);

        Motocicleta moto = new Motocicleta(600, "Honda", "CBR600", "XYZ789", 11, 0);
        motos.add(moto);

       
        get("/motocicleta", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motos);
        });

        
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            LinkedList<Vehiculo> temporalAutomoviles = new LinkedList<>();

            for (Vehiculo vehiculo : automoviles) {
                if (vehiculo.getHorasalida() != 0) {
                    temporalAutomoviles.add(vehiculo);
                }
            }

            return gson.toJson(temporalAutomoviles);
        });

        get("/motocicleta", (req, res) -> {
            res.type("application/json");
            LinkedList<Vehiculo> temporalMotos = new LinkedList<>();

            for (Vehiculo vehiculo : motos) {
                if (vehiculo.getHorasalida() != 0) {
                    temporalMotos.add(vehiculo);
                }
            }

            return gson.toJson(temporalMotos);
        });

        
        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {

         
            res.type("application/json");

           
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            for (Vehiculo vehiculo : automoviles) {
                if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                    return gson.toJson(null);
                }
            }

           
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));

            
            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);

            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo, placa, hour, 0);
            automoviles.add(nuevoAuto);

            return gson.toJson(nuevoAuto);
        });

        get("/reproteAutomoviles", (req, res) -> {
            res.type("application/json");
            LinkedList<Vehiculo> temporalAutomoviles = new LinkedList<>();
            String reporte = "";

            for (Vehiculo vehiculo : automoviles) {
                if (vehiculo.getHorasalida() != 0) {
                    reporte
                            += " Placa: " + vehiculo.getPlaca()
                            + " Ingreso: " + vehiculo.getHoraingreso()
                            + " Salida: " + vehiculo.getHorasalida()
                            + " Ganancia: " + (vehiculo.getHorasalida() - vehiculo.getHoraingreso()) * valorHoraAuto;
                } else {
                    reporte
                            += " Placa: " + vehiculo.getPlaca()
                            + " Ingreso: " + vehiculo.getHoraingreso()
                            + " Salida: " + vehiculo.getHorasalida()
                            + " Ganancia: Aun aquí";
                }
            }

            return gson.toJson(temporalAutomoviles);
        });

        before((req, res) -> guardarDatos());
    }

    private static void cargarDatos() throws IOException, ClassNotFoundException {
        Object data = Persistencia.cargarObjeto(ARCHIVO_VEHICULOS);
        if (data instanceof DatosPersistencia) {
            DatosPersistencia datosPersistencia = (DatosPersistencia) data;
            automoviles.addAll(datosPersistencia.getAutomoviles());
            motos.addAll(datosPersistencia.getMotos());
        }
    }

    private static void guardarDatos() throws IOException {
        DatosPersistencia datosPersistencia = new DatosPersistencia(automoviles, motos);
        Persistencia.guardarObjeto(datosPersistencia, ARCHIVO_VEHICULOS);
    }

    private static class DatosPersistencia implements Serializable {

        private final LinkedList<Vehiculo> automoviles;
        private final LinkedList<Vehiculo> motos;

        public DatosPersistencia(LinkedList<Vehiculo> automoviles, LinkedList<Vehiculo> motos) {
            this.automoviles = automoviles;
            this.motos = motos;
        }

        public LinkedList<Vehiculo> getAutomoviles() {
            return automoviles;
        }

        public LinkedList<Vehiculo> getMotos() {
            return motos;
        }
    }
}
