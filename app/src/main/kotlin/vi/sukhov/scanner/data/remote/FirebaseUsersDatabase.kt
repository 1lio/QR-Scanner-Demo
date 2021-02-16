package vi.sukhov.scanner.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.entity.User
import java.util.*

object FirebaseUsersDatabase : UsersStorage {
    private const val USERS_REFERENCE = "users"

    private val repository = Firebase.database
    private val usersRef = repository.getReference(USERS_REFERENCE)

    override suspend fun createUser(user: User) {
        val id = user.id
        if (id != null) usersRef.child(id).setValue(user)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getUserById(id: String): Flow<User> = callbackFlow {

        val eventListener = usersRef.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.let {
                    val user = snapshot.getValue(User::class.java)
                    this@callbackFlow.sendBlocking(user)
                }
            }

            override fun onCancelled(snapshot: DatabaseError) {
                this@callbackFlow.close(snapshot.toException())
            }
        })

        awaitClose {
            usersRef.removeEventListener(eventListener)
        }

    }

    override suspend fun updateUser(user: User) {
        val id = user.id
        if (id != null) usersRef.child(id).setValue(user)
    }

    override suspend fun removeUserById(id: String) {
        usersRef.child(id).removeValue()
    }
}