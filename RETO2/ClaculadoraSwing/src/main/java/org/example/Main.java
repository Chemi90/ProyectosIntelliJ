package org.example;

import ui.Ventana;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Ventana().mostrar();
        });
    }
}