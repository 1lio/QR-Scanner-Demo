package vi.sukhov.scanner.data.remote

import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.entity.User
import java.util.*

object FirebaseUsersDatabase : UsersStorage {

    override fun createUser(user: User) {
        //
    }

    override fun getUserById(id: String): User {
        return User(UUID.randomUUID().toString(), "TEST")
    }

    override fun updateUser(user: User) {
        //
    }

    override fun removeUserById(id: String) {
        //
    }
}