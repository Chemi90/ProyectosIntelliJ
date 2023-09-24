class ProductoRefrigerado(var codigoOrganismo:String, var supervisionAlimentaria:String, fechaCaducidad: String, numeroLote: Int) :Producto(fechaCaducidad, numeroLote){
    override fun toString(): String {
        return "ProductoRefrigerado(codigoOrganismo='$codigoOrganismo', supervisionAlimentaria='$supervisionAlimentaria') ${super.toString()}"
    }
}