package Clases

class DietaHipocalorica (nombre: String) : Dieta(nombre) {
    override var caloriasXPlatillo = 150
    override var platillosXdia = 5


    override fun ObtenerDetalle(): String {
        return "Una dieta hipocalórica se caracteriza por controlar el consumo de calorías en función de las características " +
                "físicas de una persona y su nivel de actividad diaria. Debido al bajo consumo de calorías, " +
                "la dieta está estructurada en cinco comidas diarias. Restringe el consumo de grasas saturadas y azúcar. " +
                "Incluye el consumo de carbohidratos con mucha moderación, y permite comer toda clase de frutas y verduras. "
    }


}