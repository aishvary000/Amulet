package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLogin : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        Listeners()
    }
    fun Listeners()
    {
        validateLocally("Email",AdminLoginEmailText,AdminLoginEmailField)
        validateLocally("Password",AdminLoginPasswordText,AdminLoginPasswordField)
    }
}