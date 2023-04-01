package Clases

class DietaProteica (nombre: String) : Dieta(nombre) {
    override var caloriasXPlatillo = 220
    override var platillosXdia = 6


    override fun ObtenerDetalle(): String {
        return "Una dieta proteica te ayudará a perder peso, a la vez que cambias tus hábitos alimenticios. " +
                "Y lo mejor de todo es que no tiene el temido efecto rebote. Te permite comer un 20% de carbohidratos, " +
                "un 50% de proteínas y el 30% restante de grasas."
    }


}