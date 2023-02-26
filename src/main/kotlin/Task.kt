import java.time.LocalDate
import javax.xml.stream.events.Comment

open class Task (val id: Int,
                 val title: String,
                 val description: String,
                 val dueDate: LocalDate?,
                 val assignee: User,
                 val creator: User,
                 val status: TaskStatus,
                 val priority: Priority,

)