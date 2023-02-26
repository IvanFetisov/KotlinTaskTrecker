import java.time.LocalDate

interface TaskRepository {
    fun getAllTasks(): List<Task>
    fun getTaskById(id: Int): Task?
    fun createTask(title: String,
                   description: String,
                   dueDate: LocalDate?,
                   assignee: User,
                   creator: User,
                   status: TaskStatus,
                   priority: Priority): Task
    fun updateTask( id: Int,
                    title: String?,
                    description: String?,
                    dueDate: LocalDate?,
                    assignee: User?,
                    creator: User?,
                    status: TaskStatus?,
                    priority: Priority?): Task?
    fun deleteTaskById(id: Int): Boolean
}