package Clases

class DietaVegetariana(nombre: String) : Dieta(nombre) {
    override var caloriasXPlatillo = 200
    override var platillosXdia = 6


    override fun ObtenerDetalle(): String {
        return "Una dieta vegetariana se enfoca a la alimentación con verduras. Esto incluye frutas, verduras, guisantes y alubias secas, granos, semillas y nueces"
    }


}