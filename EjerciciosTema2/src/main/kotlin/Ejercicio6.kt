package com.example.ejerciciostema2

/*
Escribe un programa que para un número N, imprima los números pares desde 1 hasta N, por ejemplo
si N=6, debe imprimir “2, 4, 6”. Hazlo con un for o un while. Si el número es menor o igual a 0,
debes imprimir “Inserta un número positivo”
 */

fun main(){

    print("Introduce un numero: ")
    var numero:Int = readLine()!!.toInt()
    if (numero<=0){
        println("Introduce un numero positivo")
    } else {
        for (i in 2..numero step 2){
            println(i)
        }
    }
}