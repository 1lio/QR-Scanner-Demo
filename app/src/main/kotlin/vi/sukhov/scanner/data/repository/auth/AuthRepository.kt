package vi.sukhov.scanner.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    fun getAuthCreds(): FirebaseAuth = firebaseAuth

}