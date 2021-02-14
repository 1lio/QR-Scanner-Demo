package vi.sukhov.scanner.data.repository

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.entity.User
import javax.inject.Inject

class UsersRepository @Inject constructor(private val storage: UsersStorage) : UsersStorage {

    override suspend fun createUser(user: User) {
        storage.createUser(user)
    }

    override suspend fun getUserById(id: String): Flow<User> {
        return storage.getUserById(id)
    }

    override suspend fun updateUser(user: User) {
        storage.updateUser(user)
    }

    override suspend fun removeUserById(id: String) {
        storage.removeUserById(id)
    }

}