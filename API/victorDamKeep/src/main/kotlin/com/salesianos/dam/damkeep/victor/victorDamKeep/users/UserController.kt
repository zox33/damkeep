package com.salesianos.dam.damkeep.victor.victorDamKeep.users
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Controller
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @PostMapping("/")
    fun nuevoUsuario(@RequestBody newUser : CreateUserDTO): ResponseEntity<UserDTO> =
        userService.create(newUser).map { ResponseEntity.status(HttpStatus.CREATED).body(it.toUserDTO()) }.orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ${newUser.username} ya existe")
        }

    // OBTENER UN USUARIO
    @GetMapping("/{id}")
    fun notasUsuario(@PathVariable id: UUID): ResponseEntity<UserDTO> {
        val result = userService.findById(id)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el usuario con el identificador $id")}.toUserDTO())
    }


    // OBTENER TODOS LOS USUARIOS
    @GetMapping("/all")
    fun listaUsuarios(): ResponseEntity<MutableList<User>> {
        val result = userService.findAll()
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios almacenados")
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result)
    }

}