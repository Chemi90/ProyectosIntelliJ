class Cuenta(var numeroCuenta: String, saldo: Double, var propietario: Persona) {
    /*
    Hacemos que el saldo no pueda ser negativo a la hora de crear el objeto cuenta
     */
    init {
        require(saldo >= 0) { "El saldo no puede ser negativo" }
    }
    /*
    metodo getter y setter de saldo en el que hacemos que al cambiarle su valor no pueda nunca ser negativo
     */
    var saldo: Double = saldo
        get() {
            return field
        }
        set(value) {
            require(value >= 0) { "El saldo no puede ser negativo" }
            println("Estableciendo saldo a $value")
            field = value
        }
    /*
    constructor vacio
     */
    constructor() : this("", 0.0, Persona("", "", ""))
    /*
    funcion para hacer transaccion de dinero, ya sea ingresar o hacer un reintegro, usando de nuevo el require
    para que el saldo nunca pueda ser menor a 0
     */
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
    /*
    sobrecarga del toString
     */
    override fun toString(): String {
        return "Cuenta(numeroCuenta='$numeroCuenta', propietario=$propietario, saldo=$saldo)"
    }
}