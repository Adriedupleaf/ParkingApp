package com.example.data.datasources

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GServiceDataStore @Inject constructor(
    private val cacheDataSource: CacheDataSource
){
    private val auth = Firebase.auth
suspend fun authenticate(email: String, password: String) = auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (!user?.displayName.isNullOrEmpty()) {
                        cacheDataSource.saveUsername(user?.displayName)
                    } else {
                        cacheDataSource.saveUsername(user?.email)
                        cacheDataSource.saveAccessToken(user?.uid)
                    }
                }}.await()


    fun register(email: String,password: String){
        // [START create_user_with_email]
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        Context,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    updateUI(null)
                }
            }
        // [END create_user_with_email]

    }
}
