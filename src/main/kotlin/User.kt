import java.time.LocalDate
import java.time.LocalDateTime

data class User(
    val id: Int,
    var firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String?,
    val birthday: LocalDate?,
    val registeredAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var tasks: List<Task>
)