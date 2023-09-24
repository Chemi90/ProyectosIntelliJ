package com.example.ejerciciostema2

/*
Haz un ciclo for y while que obtenga la sumatoria de los números hasta
n (ejemplo: para 5 debes obtener 15(1+2+3+4+5), para 3 debes obtener 6).
Imprime el resultado así: “La suma es 15” usando formatos de String.
 */

fun main(){

    print("Introduce el numero para hacer el sumatorio: ")
    var num = readLine()!!.toInt()
    var resultado: Int = 0
    for(i in 1..num ) {
        resultado += i
    }

    println("La suma es $resultado")

    resultado = 0
    var i=1
    while(i<=num){
        resultado += i
        i++
    }
    println("La suma es $resultado")
}
