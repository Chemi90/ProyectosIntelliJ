fun main(args: Array<String>) {

    val s1 = SerVivo(5)
    val s2 = SerVivo(3)

    println(s1.mayor(s2))
    println(s1.equals(s2))

    val h1 = Humano("Homer", 34)
    val h2 = Humano("Bart", 9)

    println(h1.mayor(h2))
    println(h1.equals(h2))
}