package vi.sukhov.scanner.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import vi.sukhov.scanner.data.gateway.PreferenceStorage
import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.entity.User
import java.util.*
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val usersStorage: UsersStorage,
    private val preferences: PreferenceStorage
) {

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult =
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = it.result?.user?.uid

                    GlobalScope.launch(Dispatchers.IO) {
                        usersStorage.getUserById(uid!!).collect { user -> saveUserInLocal(user) }
                    }
                }
            }.await()

    suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult =
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    val userId = it.result?.user?.uid
                    GlobalScope.launch(Dispatchers.IO) {
                        usersStorage.createUser(
                            // По умолчанию имя и ID одинаковы
                            User(userId ?: UUID.randomUUID().toString(), userId)
                        )
                    }
                }

            }.await()

    private fun saveUserInLocal(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            preferences.saveUser(user)
        }
    }

}
