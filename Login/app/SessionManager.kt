package YOUR.PACKAGE.NAME

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun setLoggedIn(username: String) {
        prefs.edit()
            .putBoolean("logged_in", true)
            .putString("username", username)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("logged_in", false)

    fun getUsername(): String? = prefs.getString("username", null)

    fun logout() {
        prefs.edit().clear().apply()
    }
}
