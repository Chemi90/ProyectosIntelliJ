package com.example.ejerciciostema2

/*
Para un Array de String de títulos de películas, imprime el título de la película con el título
más largo. Por ejemplo si tenemos un array con los valores:
{“Jumanji”,“Toy Story”,“Pulp Fiction”,“Batman: El caballero de la noche”,“Kill Bill”}
Debería imprimir “Batman:El caballero de la noche”.
RETO OPCIONAL: Imprime el índice del título más largo, para el ejemplo anterior, debería imprimir
el 3, porque es el índice de “Batman: El caballero de la noche”
 */

fun main(){

    val peliculas = arrayOf("Jumanji","Toy Story","Pulp Fiction","Batman: El caballero de la noche","Kill Bill")

    var i:Int=0
    var aux:String = peliculas[0]
    var indice:Int=0
    for (elemento in peliculas) {
        if(peliculas[i].length>aux.length){
            aux = peliculas[i]
            indice=i
        }
        i++
    }
    println("La pelicula con el titulo mas largo es: $aux")
    println("Su indice es $indice")
}