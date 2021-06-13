package eu.tutorials.evepeeve

import android.os.Bundle
import android.view.View
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
        var password:String = AdminLoginPasswordText.text.toString()
        if(validateForLogin(email,password,this))
            {
                        DatabaseManagement().loginAdmin(email,password)
            }
    }
    fun Listeners()
    {
        validateLocally("Email",AdminLoginEmailText,AdminLoginEmailField)
        validateLocally("Password",AdminLoginPasswordText,AdminLoginPasswordField)
    }
}