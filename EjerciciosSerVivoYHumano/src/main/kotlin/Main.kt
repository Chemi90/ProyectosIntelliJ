fun main(args: Array<String>) {

    //creamos los dos seres vivos
    val s1 = SerVivo(5)
    val s2 = SerVivo(3)

    //usamos la funcion de mayor y la de equals
    println(s1.mayor(s2))
    println(s1.equals(s2))

    //creamos los dos objetos humano
    val h1 = Humano("Homer", 34)
    val h2 = Humano("Bart", 9)

    //usamos las funciones mayor y equals con los objetos humano
    println(h1.mayor(h2))
    println(h1.equals(h2))
}