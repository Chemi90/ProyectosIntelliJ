package com.example.ejerciciostema2

/*
Crea un programa que te pida una cantidad en miligramos para una poción mágica,
el valor debe ser de tipo entero, si el valor es mayor a 100 imprime
“¡Felicidades, es una buena poción!”, de lo contrario imprime
“La poción es mediocre, sangre sucia inmunda”.
 */

fun main(){

    print("Introduce la cantidad de la poción en miligramos: ")
    var cantidad = readLine()!!.toInt()

    if (cantidad>100){
        print("¡Felicidades,es una buena poción!")
    } else {
        print("La poción es mediocre, sangre sucia inmunda.")
    }

}