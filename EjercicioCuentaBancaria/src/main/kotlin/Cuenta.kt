class Cuenta(var numeroCuenta: String, saldo: Double, var propietario: Persona) {
    init {
        require(saldo >= 0) { "El saldo no puede ser negativo" }
    }

    var saldo: Double = saldo
        get() {
            return field
        }
        set(value) {
            require(value >= 0) { "El saldo no puede ser negativo" }
            println("Estableciendo saldo a $value")
            field = value
        }
    //constructor vacio
    constructor() : this("", 0.0, Persona("", "", ""))

    fun transaccion(cantidad: Double, tipoTransaccion: String) {
        when (tipoTransaccion.toLowerCase()) {
            "reintegro" -> {
                require(saldo - cantidad >= 0) { "Saldo insuficiente para el reintegro" }
                saldo -= cantidad
                println("Reintegro de $cantidad realizado. Nuevo saldo: $saldo")
            }
            "ingreso" -> {
                saldo += cantidad
                println("Ingreso de $cantidad realizado. Nuevo saldo: $saldo")
            }
            else -> println("Tipo de transacción no válido")
        }
    }

    override fun toString(): String {
        return "Cuenta(numeroCuenta='$numeroCuenta', propietario=$propietario, saldo=$saldo)"
    }
}