package com.example.ejerciciostema2

/*
Escribe un programa que te diga si un Uber puede iniciar su recorrido.
Para esto se necesitan dos cosas, que el conductor esté cerca y que esté disponible,
el programa te pedirá dos valores, la distancia del conductor en kilómetros y su
disponibilidad, donde false = no disponible y true = disponible, según los datos
que insertes imprime lo siguiente :
    Ø Si la distancia es menor o igual a 0.5km y el conductor está disponible,
    imprime“ Listo para iniciar recorrido”
    Ø Si la distancia es menor o igual a 0.5km y el conductor NO está disponible,
    imprime, “Conductor cercano, pero no disponible”
    Ø Si la distancia es mayor a 0.5km y el conductor está disponible, imprime,
    “Conductor disponible pero muy lejos, aplicarán tarifas más altas”
    Ø Si la distancia es mayor a 0.5km y el conductor NO está disponible , imprime,
    “No hay conductores disponibles”
    RETO OPCIONAL: Si no se cumplen las condiciones de
    “Listo para iniciar recorrido”, después de imprimir el mensaje, vuelve a pedir
    los datos de distancia y disponibilidad
 */

fun main() {
    var distancia:Double = 1.00
    var disponible: Boolean = false

    println("Bienvenido, indica a continuacion la distancia y disponibilidad.")
    while(disponible==false || distancia>0.5) {
        print("Introduce la distancia del Uber en km: ")
        distancia = readLine()!!.toDouble()
        print("Introduce la disponibilidad del Uber: ")
        disponible = readLine()!!.toBoolean()
        if (distancia < 0.5 && disponible == true) {
            println("Listo para iniciar recorrido.")
        } else if (distancia < 0.5 && disponible == false) {
            println("Conductor cercano, pero no disponible")
        } else if (distancia > 0.5 && disponible == true) {
            println("Conductor disponible pero muy lejos, se aplicaran tarifas mas altas.")
        } else {
            println("No hay conductores disponibles")
        }
    }

}