package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la sucesión de Fibonacci que quieres: ");
        var n = sc.nextInt();
        Fibonacci f = new Fibonacci(n);
        f.start();

        sc.close();
    }
}