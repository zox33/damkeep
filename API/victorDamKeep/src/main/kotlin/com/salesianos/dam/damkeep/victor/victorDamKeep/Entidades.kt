package com.salesianos.dam.damkeep.victor.victorDamKeep

import com.fasterxml.jackson.annotation.JsonBackReference
import com.salesianos.dam.damkeep.victor.victorDamKeep.users.User
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne


@Entity
data class Nota(

        var titulo:String,

        var contenido:String,

        val fechaCreacion: LocalDate,

        var ultimaEdicion: LocalDate,

        @JsonBackReference @ManyToOne var user: User?=null,

        @Id @GeneratedValue val id: UUID? = null
)
