open class SerVivo(var edad:Byte){
    /*
    Funcion con la que se compara cual de los dos objetos SerVivo tiene mayor edad
     */
    fun mayor(otro:SerVivo):SerVivo?{
        return if(this.edad>otro.edad) this else otro
    }
    /*
    sobrecarga de la funcion equals
     */
    override fun equals(other: Any?): Boolean {
        if(this===other) return true
        if(other !is SerVivo) return false
        if(edad == other.edad) return true
        else return false
    }
    /*
    sobrecarga de la funcion toString
     */
    override fun toString(): String {
        return "SerVivo(edad=$edad)"
    }

}