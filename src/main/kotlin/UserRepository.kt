interface UserRepository {
    fun createUser(user: User):User
    fun updateUser(user: User):User
    fun deleteUser(id:Int)
    fun getUserById(id:Int):User?
    fun getAllUsers():List<User>
    fun getUserByEmail(email:String):User?
    fun addTaskToUser(userId:Int, task: Task):User
    fun removeTaskFromUser(userId: Int,taskId:Int):User
}