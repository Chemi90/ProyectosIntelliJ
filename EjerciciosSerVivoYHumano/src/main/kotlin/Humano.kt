class Humano(var nombre:String, edad:Byte):SerVivo(edad){
    fun mayor(otro:Humano):Humano?{
        if(this.edad<otro.edad) return otro
        else if(this.edad==otro.edad){
            if(this.nombre<otro.nombre) return otro
            else return this
        } else return this
    }
    override fun equals(other: Any?): Boolean {
        if(this===other) return true
        if(other !is Humano) return false
        if(edad == other.edad&&nombre == other.nombre) return true
        else return false
    }

    override fun toString(): String {
        return "Humano(nombre='$nombre') ${super.toString()}"
    }

}