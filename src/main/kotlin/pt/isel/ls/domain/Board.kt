package pt.isel.ls.domain

data class Board(
    val name: String,
    val description: String,
    val users : List<User>,
    val lists: List<Column>
)