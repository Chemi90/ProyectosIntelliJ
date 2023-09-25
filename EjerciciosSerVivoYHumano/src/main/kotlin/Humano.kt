class Humano(var nombre:String, edad:Byte):SerVivo(edad){
    /*
    funcion para saber que humano tiene mas edad, y si la tuvieran igual selecciona al que tiene el nombre mas largo
     */
    fun mayor(otro:Humano):Humano?{
        if(this.edad<otro.edad) return otro
        else if(this.edad==otro.edad){
            if(this.nombre<otro.nombre) return otro
            else return this
        } else return this
    }
    /*
    sobrecarga del metodo equals
     */
    override fun equals(other: Any?): Boolean {
        if(this===other) return true
        if(other !is Humano) return false
        if(edad == other.edad&&nombre == other.nombre) return true
        else return false
    }
    /*
    sobrecarga del metodo toString
     */
    override fun toString(): String {
        return "Humano(nombre='$nombre') ${super.toString()}"
    }

}