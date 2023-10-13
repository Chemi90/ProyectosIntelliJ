package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManejadorArchivos {

    public static String leerPlantilla(String ruta) throws IOException {
        return new String(Files.readAllBytes(Paths.get(ruta)));
    }

    public static void crearArchivo(String ruta, String contenido) throws IOException {
        Files.write(Paths.get(ruta), contenido.getBytes());
    }

    public static void limpiarCarpetaSalida(String rutaCarpetaSalida) throws IOException {
        File carpetaSalida = new File(rutaCarpetaSalida);

        // Crear la carpeta si no existe
        if (!carpetaSalida.exists()) {
            carpetaSalida.mkdirs();
        }

        for (File archivo : carpetaSalida.listFiles()) {
            if (archivo.isFile()) {
                archivo.delete();
            }
        }
    }
}