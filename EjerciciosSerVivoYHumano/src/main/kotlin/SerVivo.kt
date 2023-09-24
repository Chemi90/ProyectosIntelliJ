open class SerVivo(var edad:Byte){
    fun mayor(otro:SerVivo):SerVivo?{
        return if(this.edad>otro.edad) this else otro
    }
    override fun equals(other: Any?): Boolean {
        if(this===other) return true
        if(other !is SerVivo) return false
        if(edad == other.edad) return true
        else return false
    }

    override fun toString(): String {
        return "SerVivo(edad=$edad)"
    }

}