class Persona(var nombre:String, var apellido:String, var telefono:String){
    /*
    con el init y el require limitamos que el telefono tenga 9 digitos exactamente, sino, saldra un error en el que
    mostrara ese String
     */
    init {
        require(telefono.length == 9) { "El número de teléfono debe tener 9 dígitos" }
    }
    /*
    Sobrecargamos el metodo toString
     */
    override fun toString(): String {
        return "Persona(nombre='$nombre', apellido='$apellido', telefono='$telefono')"
    }

}