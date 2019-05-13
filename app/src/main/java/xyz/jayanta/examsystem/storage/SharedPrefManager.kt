package xyz.jayanta.examsystem.storage

import android.content.Context
import xyz.jayanta.examsystem.models.SigninResponse

class SharedPrefManager private constructor(private val mCtx: Context) {

    private val SHARED_PREF_NAME: String? = null

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: SigninResponse
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return SigninResponse(
                sharedPreferences.getString("")
            )
        }
}