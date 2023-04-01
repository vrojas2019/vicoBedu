package Clases

class DietaPaleo(nombre: String) : Dieta(nombre) {
    override var caloriasXPlatillo = 150
    override var platillosXdia = 4


    override fun ObtenerDetalle(): String {
        return "Una dieta paleo incluye frutas, verduras, carnes sin grasas, pescado, huevos, frutos secos y semillas. " +
                "Los frutos secos también están incluidos. Puedes disfrutar de las riquísimas avellanas o pistachos. " +
                "Cuando llevas a cabo la dieta paleo tienes que acompañarla de un buen entrenamiento deportivo."
    }


}