package com.salesianos.dam.damkeep.victor.victorDamKeep

import com.salesianos.dam.damkeep.victor.victorDamKeep.users.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/notas")
class NotasController(val notasRepository: NotaRepository) {

    // OBTENER TODAS LAS NOTAS
    @GetMapping("/")
    fun todasNotas(): List<NotaDtoLista> {
        val result: List<Nota> = notasRepository.findAll()
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay notas almacenadas")
        return result.map { it.toNotaDtoLista() }
    }

    // DETALLES DE NOTA
    @GetMapping("/{id}")
    fun getNotaDetail( @PathVariable id : UUID): NotaDtoMostrar? {
        return notasRepository.findById(id).map {it.toNotaDtoMostrar()
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
        }
    }

    //OBTENER NOTAS DE USUARIO INICIADO
    @GetMapping("/usuario/")
    fun getUsuarios(@AuthenticationPrincipal user : User): ResponseEntity<List<NotaDtoLista>> {
        val result= notasRepository.findAllNotasByUser(user.id)
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay notas guardadas")
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result.map { it.toNotaDtoLista() })
    }


    //AgregarNota
    @PostMapping("/nueva")
    fun postNuevaNota(@AuthenticationPrincipal user : User, @RequestBody nuevaNotaDto: NotaNuevaDto): ResponseEntity<Nota> {
        val nota = nuevaNotaDto.toNota()
        nota.user = user
        return ResponseEntity.status(HttpStatus.CREATED).body(notasRepository.save(nota))

    }

    //Editar Nota
    @PutMapping("/{id}")
    fun editarNota(@RequestBody notaEditada: NotaNuevaDto, @PathVariable id : UUID): NotaDtoMostrar? {
        return notasRepository.findById(id).map {
            notaObtenida ->
                val actualizarNota : Nota =
                        notaObtenida.copy(
                                titulo = notaEditada.titulo,
                                contenido = notaEditada.contenido,
                                ultimaEdicion = LocalDate.now()
                        )
                notasRepository.save(actualizarNota).toNotaDtoMostrar()
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
        }
    }

    //BorrarNota
    @DeleteMapping("/{id}")
    fun eliminarNota(@PathVariable id : UUID) : ResponseEntity<Void> {
        notasRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }


}
