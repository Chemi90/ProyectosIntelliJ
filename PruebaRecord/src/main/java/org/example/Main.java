package org.example;

public class Main {

    public record Intervalo(Integer  ini, Integer fin){
        Integer tamaño(){
            return fin-ini;
        }
    }

    public static Intervalo expandir(Intervalo i){
        return new Intervalo(i.ini-1, i.fin+1);
    }

    public static void main(String[] args) {

        /*Intervalo i1 = new Intervalo(1,10);
        System.out.println(i1);
        System.out.println(i1.fin);
        System.out.println(i1.ini);
        System.out.println(i1.tamaño());

        i1 = new Intervalo(5, 40);
        System.out.println(i1);
        System.out.println(i1.fin);
        System.out.println(i1.ini);

        Intervalo i2 = expandir(i1);
        System.out.println(i2);
        System.out.println(i2.tamaño());*/

        Persona p1 = new Persona("Samuel", "Leiva", 28);
        var p2 = new Persona("Jose Miguel", "Ruiz", 32);
        var p3 = Persona.builder().edad(12).nombre("Francisco").build();

        p2.setApellido("Guevara");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}