class ProductoCongelado(var temperaturaCongelacion:Int, fechaCaducidad: String, numeroLote: Int) :Producto(fechaCaducidad, numeroLote){
    override fun toString(): String {
        return "ProductoCongelado(temperaturaCongelacion=$temperaturaCongelacion) ${super.toString()}"
    }
}