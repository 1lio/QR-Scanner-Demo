package vi.sukhov.scanner.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.entity.User
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val usersStorage: UsersStorage,
) {

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult =
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    val uid = it.result?.user?.uid

                    usersStorage.getUserById(uid!!)
                }
            }.await()


    suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult =
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    val userId = it.result?.user?.uid
                }

            }.await()

}
