package Clases

class DietaMediterranea (nombre: String) : Dieta(nombre) {
    override var caloriasXPlatillo = 250
    override var platillosXdia = 5


    override fun ObtenerDetalle(): String {
        return "Una dieta mediterránea combate la obesidad y también te aporta grandes beneficios para tu salud cardiovascular. " +
                "El aceite de oliva, los cereales, los frutos secos, los cítricos, las carnes magras, frutas y verduras o pescados azules, " +
                "son algunos de los alimentos que componen esta deliciosa dieta."
    }


}