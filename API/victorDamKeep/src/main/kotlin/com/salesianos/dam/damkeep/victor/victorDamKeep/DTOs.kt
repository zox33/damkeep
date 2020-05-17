package com.salesianos.dam.damkeep.victor.victorDamKeep

import com.fasterxml.jackson.annotation.JsonFormat
import com.salesianos.dam.damkeep.victor.victorDamKeep.users.User
import java.time.LocalDate
import java.util.*

data class NotaDtoLista(
        val id: UUID?,
        val titulo:String
)

data class NotaDto(
        val id: UUID?,
        val titulo:String,
        val contenido:String,
        val user: User?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val ultimaEdicion: LocalDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val fechaCreacion: LocalDate
)

data class NotaDtoMostrar(
        val id : UUID?,
        val titulo:String,
        val contenido:String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val ultimaEdicion: LocalDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        val fechaCreacion: LocalDate
)

data class NotaNuevaDto(
        val id : UUID?,
        val titulo:String,
        val contenido:String
)

fun Nota.toNotaDto() = NotaDto(id,titulo,contenido,user,ultimaEdicion,fechaCreacion)
fun NotaNuevaDto.toNota() = Nota(titulo,contenido,LocalDate.now(), LocalDate.now(),null , id)
fun NotaDto.toNota() = Nota(titulo,contenido,ultimaEdicion,fechaCreacion,user,id)
fun Nota.toNotaDtoMostrar() = NotaDtoMostrar(id,titulo,contenido,ultimaEdicion,fechaCreacion)
fun Nota.toNotaDtoLista() = NotaDtoLista(id,titulo)
