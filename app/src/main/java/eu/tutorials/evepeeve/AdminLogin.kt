package eu.tutorials.evepeeve

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
    fun adminoptions(v:View){
        if(AdminLoginEmailText.text.toString()==""||AdminLoginPasswordText.text.toString()==""){
            Toast.makeText(this,"Email can't be empty",Toast.LENGTH_SHORT).show()
        }
        else{
        val intent = Intent(this,AdminOptions::class.java)
        startActivity(intent)
        }
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