package com.salesianos.dam.damkeep.victor.victorDamKeep.users
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.salesianos.dam.damkeep.victor.victorDamKeep.Nota
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
data class User(

        @Column(nullable = false, unique = true)
        private var username: String,

        private var password: String,

        var fullName : String,
        var email : String,

        @ElementCollection(fetch = FetchType.EAGER)
        val roles: MutableSet<String> = HashSet(),

        @JsonManagedReference @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
        var notas: List<Nota>?=null,

        private val nonExpired: Boolean = true,

        private val nonLocked: Boolean = true,

        private val enabled: Boolean = true,

        private val credentialsNonExpired : Boolean = true,

        @Id @GeneratedValue val id : UUID? = null


) : UserDetails {

    constructor(username: String, password: String, fullName: String, email: String, role: String) :
            this(username, password, fullName,email, mutableSetOf(role), null,true, true, true, true)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()

    override fun isEnabled() = enabled
    override fun getUsername() = username
    override fun isCredentialsNonExpired() = credentialsNonExpired
    override fun getPassword() = password
    override fun isAccountNonExpired() = nonExpired
    override fun isAccountNonLocked() = nonLocked


    /**
     * En JPA, dos entidades deberían considerarse iguales
     * sí tienen en mismo ID
     */

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other === null || other !is User)
            return false
        if (this::class != other::class)
            return false
        return id == other.id
    }

    override fun hashCode(): Int {
        if (id == null)
            return super.hashCode()
        return id.hashCode()
    }
}
