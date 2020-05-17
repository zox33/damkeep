package com.salesianos.dam.damkeep.victor.victorDamKeep.users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*
import javax.annotation.PostConstruct

interface UserRepository : JpaRepository<User, UUID> {

    fun findByUsername(username : String) : Optional<User>

}
/*
@Component
class InitDataComponent(
        val usuarioRepository: UserRepository,
        val notaRepository: NotaRepository
) {

    @PostConstruct
    fun initData() {
        val autor1 = User("vemiranda", "123456", "Víctor Miranda Alcántara", "victorxdr@gmail.com", "USER")
        usuarioRepository.save(autor1)

        val notasUsuario1 = listOf(
                Nota("Nota ejemplo-0", "contenido", LocalDate.now(), LocalDate.now()),
                Nota("Nota ejemplo-1", "contenido", LocalDate.now(), LocalDate.now()),
                Nota("Nota ejemplo-2", "contenido", LocalDate.now(), LocalDate.now()),
                Nota("Nota ejemplo-3", "contenido", LocalDate.now(), LocalDate.now()),
                Nota("Nota ejemplo-4", "contenido", LocalDate.now(), LocalDate.now())
        )

        notaRepository.saveAll(notasUsuario1)
    }
}*/
