package eu.tutorials.evepeeve.screens

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import eu.tutorials.evepeeve.Database.DatabaseManagement
import eu.tutorials.evepeeve.Models.Users
import eu.tutorials.evepeeve.R
import kotlinx.android.synthetic.main.activity_sign_up_by_admin.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class SignUpByAdmin : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_by_admin)
        listeners()

    }
    fun signupAdmin(v:View){
        register()
    }
    private fun register(){
//
        var userName:String = UserNameAdmin.text.toString()
        var password: String = passwordAdmin.text.toString()
        var againPass: String = confirmPasswordAdmin.text.toString()
        var email: String = emailAdmin.text.toString()
        val designation :String = designationAdmin.text.toString()
        if (validateForRegisterAdmin(userName,designation, email, password, againPass, this)) {
            DatabaseManagement().registerAdminForAuthorization(

                Users(
                    name = userName,
                    email = email,
                    designation = designation,
                    password = password
                ),this
            )
        }

    }
    fun showSucessToast(message:String)
    {
        var inflater: LayoutInflater = LayoutInflater.from(this)
        var layout: View = inflater.inflate(R.layout.custom_toast,customll)
        layout.custom_Toast_text_Success.text = message
        var toast: Toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM, Gravity.CENTER,10)
        toast.view = layout
        toast.show()
    }
    fun listeners()
    {
        validateLocally("UserName",UserNameAdmin,AdminSignUpUserNameField)
        validateLocally("Email",emailAdmin,AdminSignUpEmailField)
        validateLocally("Password",passwordAdmin,AdminSignUpPasswordField)
        validateLocally("ConfirmPasswordAdmin",confirmPasswordAdmin,AdminSignUpConfirmPasswordField)
        validateLocally("Designation",designationAdmin,AdminSignDesignationField)


    }
}
