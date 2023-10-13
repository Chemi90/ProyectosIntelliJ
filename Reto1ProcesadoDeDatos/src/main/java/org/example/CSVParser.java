package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static List<Cliente> parse(String archivoCSV) {
        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosCliente = linea.split(",");
                Cliente cliente = new Cliente();
                cliente.setCodigoCliente(datosCliente[0]);
                cliente.setNombreEmpresa(datosCliente[1]);
                cliente.setLocalidad(datosCliente[2]);
                cliente.setCorreoElectronico(datosCliente[3]);
                cliente.setNombreResponsable(datosCliente[4]);
                clientes.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
