package Models

data class DatosAPI(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)