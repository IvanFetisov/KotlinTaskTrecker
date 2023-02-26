import java.time.LocalDateTime

class InMemoryUserRepository:UserRepository {
    private val users = mutableListOf<User>()
    private var currentId = 0

    override fun createUser(user: User): User {
        if (!checkUser(user)) {
            throw IllegalArgumentException("User fields cannot be null or empty")
        }
        val newUser = user.copy(id = ++currentId, registeredAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
        users.add(newUser)
        return newUser
    }

    override fun updateUser(user: User): User {
        if (!checkUser(user)) {
            throw IllegalArgumentException("User fields cannot be null or empty")
        }
        val existingUser = getUserById(user.id) ?: throw IllegalArgumentException("User not found")
        val updatedUser = existingUser.copy(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            phoneNumber = user.phoneNumber ?: existingUser.phoneNumber,
            birthday = user.birthday ?: existingUser.birthday,
            updatedAt = LocalDateTime.now()
        )
        users.replaceAll { if (it.id == updatedUser.id) updatedUser else it }
        return updatedUser
    }

    override fun deleteUser(id: Int) {
        users.removeIf { it.id == id }
    }

    override fun getUserById(id: Int): User? {
        return users.find { it.id == id }
    }

    override fun getAllUsers(): List<User> {
        return users.toList()
    }

    override fun getUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }

    override fun addTaskToUser(userId: Int, task: Task): User {
        val user = getUserById(userId) ?: throw IllegalArgumentException("User not found")
        val updatedTasks = user.tasks.toMutableList().apply { add(task) }
        val updatedUser = user.copy(tasks = updatedTasks)
        users.replaceAll { if (it.id == updatedUser.id) updatedUser else it }
        return updatedUser
    }

    override fun removeTaskFromUser(userId: Int, taskId: Int): User {
        val user = getUserById(userId) ?: throw IllegalArgumentException("User not found")
        val updatedTasks = user.tasks.toMutableList().apply { removeAll { it.id == taskId } }
        val updatedUser = user.copy(tasks = updatedTasks)
        users.replaceAll { if (it.id == updatedUser.id) updatedUser else it }
        return updatedUser
    }

    private fun checkUser(user: User): Boolean {
        return user.firstName.isNotBlank() &&
                user.lastName.isNotBlank() &&
                user.email.isNotBlank() &&
                user.phoneNumber != null &&
                user.birthday != null
    }
}