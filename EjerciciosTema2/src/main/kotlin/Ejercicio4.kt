package com.example.ejerciciostema2

/*
Escribe un programa que imprima “¿Cómo es el clima de hoy?”, dependiendo del número que insertes
te imprima los siguientes valores
    Ø 1=“Soleado”
    Ø 2=“Nublado”
    Ø 3=“Lluvioso”
    Ø 4=“Tormentoso”
    Ø 5=“Nevado”
    Si insertas cualquier otro valor debe imprimir “Pregúntale a Google” Recomendación: Usa When.
 */

fun main(){

    print("Introduce el valor del clima de hoy: ")
    var tiempo:Int = readLine()!!.toInt()

    when(tiempo){
        1 -> {
            println("Soleado")
        }
        2 -> {
            println("Nublado")
        }
        3 -> {
            println("Lluvioso")
        }
        4 -> {
            println("Tormentoso")
        }
        5 -> {
            println("Nevado")
        }
        else -> {
            println("Preguntale a Google")
        }
    }
}