package vi.sukhov.scanner.data.gateway

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.entity.User

interface UsersStorage {

    suspend fun createUser(user: User)

    suspend fun getUserById(id: String): Flow<User>

    suspend fun updateUser(user: User)

    suspend fun removeUserById(id: String)

}