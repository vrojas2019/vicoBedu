package Clases

abstract class Dieta{

    protected var definicion: String = ""
    protected var nombre: String = ""
    protected open var caloriasXPlatillo : Int = 0
    protected open var platillosXdia : Int=0


    init {
        definicion = "La dieta es un control o regulación de la cantidad y tipo de alimentos que toma una persona o un animal, generalmente con un fin específico. "
    }

    constructor(nombre: String) {
        this.nombre = nombre
    }

    open fun TotalCalorias() : Int{
        return caloriasXPlatillo * platillosXdia
    }

    open fun ObtenerPlatillosXdia() : String{
        return "$platillosXdia platillos por día"
    }

    open fun MostrarDieta() : String{
        return """
            Te recomendamos la ${nombre}
            ${ObtenerPlatillosXdia()} 
            Calorias: ${TotalCalorias()}
            ${definicion}
            ${ObtenerDetalle()}
        """
    }

    abstract fun ObtenerDetalle() : String


}