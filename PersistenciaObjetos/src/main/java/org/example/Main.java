package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Alumno;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Alumno fer = new Alumno();
        fer.setNombre("Fernando");
        fer.setApellido("Perez");

        Alumno pablo = new Alumno();
        pablo.setNombre("Pablo");
        pablo.setApellido("Postigo");

        var clase = new ArrayList<Alumno>();
        clase.add(fer);
        clase.add(pablo);

        System.out.println(clase);

        extracted(clase);

        var clase2 = new ArrayList<Alumno>();

        clase2 = getAlumnos();

        System.out.println(clase2);
        
        var mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
        
        
        
        try {
            mapper.writeValue(new File("clase.json"), clase);
            System.out.println(mapper.withDefaultPrettyPrinter().writeValueAsString(clase));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static ArrayList<Alumno> getAlumnos() {
        ArrayList<Alumno> clase2;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clase.obj"))){
            clase2 = (ArrayList<Alumno>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clase2;
    }

    private static void extracted(ArrayList<Alumno> clase) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clase.obj"))) {
            oos.writeObject(clase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main2(String[] args) {

        InputStream is = Main.class.getClassLoader().getResourceAsStream("database.properties");

        Properties config = new Properties();

        try {
            config.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        config.put("version", "1.0");
        try {
            config.store(new FileOutputStream("database"),"Añadida la version");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(config.get("url"));
        System.out.println(config.get("user"));
        System.out.println(config.get("port"));
        System.out.println(config.get("pass"));
        System.out.println(config.get("version"));
    }
}