package Models

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)