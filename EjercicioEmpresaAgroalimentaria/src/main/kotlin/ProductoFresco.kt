class ProductoFresco(var fechaEnvasado:String, var paisOrigen:String, fechaCaducidad: String, numeroLote: Int) :Producto(fechaCaducidad, numeroLote){
    override fun toString(): String {
        return "ProductoFresco(fechaEnvasado='$fechaEnvasado', paisOrigen='$paisOrigen') ${super.toString()}"
    }
}