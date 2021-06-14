package eu.tutorials.evepeeve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import eu.tutorials.evepeeve.Database.DatabaseManagement
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLogin : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        Listeners()
    }

    fun adminLogin(v: View)
    {

        val email:String = AdminLoginEmailText.text.toString()
        val password:String = AdminLoginPasswordText.text.toString()
        if(validateForLogin(email,password,this))
            {
                        DatabaseManagement().loginAdmin(email,password,this)
            }
        else
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
    }

    fun Listeners()
    {
        validateLocally("Email",AdminLoginEmailText,AdminLoginEmailField)
        validateLocally("Password",AdminLoginPasswordText,AdminLoginPasswordField)
    }
}