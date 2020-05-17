package com.salesianos.dam.damkeep.victor.victorDamKeep

import com.salesianos.dam.damkeep.victor.victorDamKeep.users.User
import com.salesianos.dam.damkeep.victor.victorDamKeep.users.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*
import javax.annotation.PostConstruct

interface NotaRepository: JpaRepository<Nota, UUID> {

    @Query("select distinct s from Nota s left join fetch s.user")
    fun findAllNotas() : List<Nota>

    //Obtener todas las notas del usuario actual
    @Query("select distinct s from Nota s left join fetch s.user where s.user.id = :id")
    fun findAllNotasByUser(id: UUID?):List<Nota>

}



@Component
class InitDataComponent(
        val usuarioRepository: UserRepository,
        val notaRepository: NotaRepository,
        private val encoder: PasswordEncoder
) {

    @PostConstruct
    fun initData() {
        val autor1 = User("vemiranda", encoder.encode("12345"), "Víctor Miranda Alcántara", "victorxdr@gmail.com", "USER")
        usuarioRepository.save(autor1)

        val notasUsuario1 = listOf(
                Nota("Nota ejemplo-0", "contenido", LocalDate.now(), LocalDate.now(),autor1),
                Nota("Nota ejemplo-1", "contenido", LocalDate.now(), LocalDate.now(),autor1),
                Nota("Nota ejemplo-2", "contenido", LocalDate.now(), LocalDate.now(),autor1),
                Nota("Nota ejemplo-3", "contenido", LocalDate.now(), LocalDate.now(),autor1),
                Nota("Nota ejemplo-4", "contenido", LocalDate.now(), LocalDate.now(),autor1)
        )

        notaRepository.saveAll(notasUsuario1)
    }
}