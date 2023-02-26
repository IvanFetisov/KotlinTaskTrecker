import java.time.LocalDate

class InMemoryTaskRepository: TaskRepository {
    private val tasks = mutableListOf<Task>()
    private var currentId = 0
    override fun getAllTasks(): List<Task> {
        return tasks.toList()
    }

    override fun getTaskById(id: Int): Task? {
        return  tasks.find { it.id == id }
    }

    override fun createTask(title: String,
                            description: String,
                            dueDate: LocalDate?,
                            assignee: User,
                            creator: User,
                            status: TaskStatus,
                            priority: Priority
    ): Task {
        val task = Task(
            ++currentId,
            title,
            description,
            dueDate,
            assignee,
            creator,
            status,
            priority,
        )
        tasks.add(task)
        return task
    }

    override fun updateTask(  id: Int,
                              title: String?,
                              description: String?,
                              dueDate: LocalDate?,
                              assignee: User?,
                              creator: User?,
                              status: TaskStatus?,
                              priority: Priority?): Task? {
        val taskIndex = tasks.indexOfFirst { it.id == id }
        if (taskIndex == -1) {
            return null
        }

        val task = tasks[taskIndex]
        val newTitle = title ?: task.title
        val newDescription = description ?: task.description
        val newDueDate = dueDate ?: task.dueDate
        val newAssignee = assignee ?: task.assignee
        val newCreator = creator ?: task.creator
        val newStatus = status ?: task.status
        val newPriority = priority ?: task.priority

        val updatedTask = Task(
            id,
            newTitle,
            newDescription,
            newDueDate,
            newAssignee,
            newCreator,
            newStatus,
            newPriority,

        )
        tasks[taskIndex] = updatedTask

        return updatedTask


    }

    override fun deleteTaskById(id: Int): Boolean {
        val taskIndex = tasks.indexOfFirst { it.id == id }
        if (taskIndex == -1) {
            return false
        }

        tasks.removeAt(taskIndex)
        return true
    }

}
