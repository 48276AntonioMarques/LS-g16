package pt.isel.ls.domain

data class Column(
    val name: String,
    val description: String,
    val cards: List<Card>
)