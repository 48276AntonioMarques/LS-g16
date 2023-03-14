package pt.isel.ls.domain

import java.sql.Date

data class Card(
    val name: String,
    val description: String,
    val creation: Date,
    val conclusionDate: Date,
)