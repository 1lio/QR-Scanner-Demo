package vi.sukhov.scanner.data.gateway

import vi.sukhov.scanner.entity.User

interface UsersStorage {

    fun createUser(user:User)

    fun getUserById(id: String): User

    fun updateUser(user: User)

    fun removeUserById(id: String)

}