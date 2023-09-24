fun main(args: Array<String>) {
    val persona1 = Persona("John", "Doe", "123456789")
    val persona2 = Persona("Jane", "Smith", "987654321")

    val cuenta1 = Cuenta("001", 1000.0, persona1)
    val cuenta2 = Cuenta("002", 500.0, persona2)

    println("Datos de las personas:")
    println(persona1)
    println(persona2)

    println("\nDatos de las cuentas:")
    println(cuenta1)
    println(cuenta2)

    println("\nRealizando transacciones:")
    cuenta1.transaccion(200.0, "reintegro")
    cuenta2.transaccion(300.0, "ingreso")
}