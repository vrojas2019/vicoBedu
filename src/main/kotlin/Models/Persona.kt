import kotlinx.serialization.Serializable

@Serializable
data class Persona(
    val nombre: String
){

    enum class Genero{
        MASCULINO,
        FEMENINO,
        OTRO
    }

    var id: Int = 0
    lateinit var correo: String
    lateinit var genero: Genero
    var edad: Int = 0
    var peso: Int = 0
    var altura: Int = 0
}