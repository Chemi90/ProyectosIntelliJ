class Persona(var nombre:String, var apellido:String, var telefono:String){
    init {
        require(telefono.length == 9) { "El número de teléfono debe tener 9 dígitos" }
    }

    override fun toString(): String {
        return "Persona(nombre='$nombre', apellido='$apellido', telefono='$telefono')"
    }

}