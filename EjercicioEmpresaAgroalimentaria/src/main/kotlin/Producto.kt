open class Producto(var fechaCaducidad:String, var numeroLote:Int) {
    override fun toString(): String {
        return "Producto(fechaCaducidad='$fechaCaducidad', numeroLote=$numeroLote)"
    }
}