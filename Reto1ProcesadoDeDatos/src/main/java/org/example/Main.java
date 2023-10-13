package org.example;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            String plantilla = ManejadorArchivos.leerPlantilla("src/main/resources/template.txt");
            List<Cliente> clientes = CSVParser.parse("src/main/resources/data.csv");

            // Limpiar la carpeta de salida antes de empezar
            ManejadorArchivos.limpiarCarpetaSalida("src/main/resources/salida");

            for (Cliente cliente : clientes) {
                String contenidoCarta = plantilla
                        .replace("%%1%%", cliente.getCodigoCliente())
                        .replace("%%2%%", cliente.getNombreEmpresa())
                        .replace("%%3%%", cliente.getLocalidad())
                        .replace("%%4%%", cliente.getCorreoElectronico())
                        .replace("%%5%%", cliente.getNombreResponsable());

                String rutaArchivoSalida = "src/main/resources/salida/template-" + cliente.getCodigoCliente() + ".txt";
                ManejadorArchivos.crearArchivo(rutaArchivoSalida, contenidoCarta);
            }

            System.out.println("Combinación de correspondencia completada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}