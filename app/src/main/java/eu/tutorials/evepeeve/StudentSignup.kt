package eu.tutorials.evepeeve

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import eu.tutorials.evepeeve.Models.Users
import eu.tutorials.evepeeve.Database.DatabaseManagement
import kotlinx.android.synthetic.main.activity_student_signup.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class StudentSignup : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_signup)
        listeners()



    }

    fun listeners()
    {
        validateLocally("UserName",StudentSignUpUserNameText,StudentSignUpUserNameField)
        validateLocally("Email",StudentSignUpEmailText,StudentSignUpEmailField)
        validateLocally("Password",StudentSignUpPasswordText,StudentSignUpPasswordField)
        validateLocally("ConfirmPassword",StudentSignUpConfirmPasswordText,StudentSignUpConfirmPasswordField)


    }

    fun signup(v:View){
        registerUser()
    }

    private fun registerUser() {
        val userName: String = StudentSignUpUserNameText.text.toString()
        var password: String = StudentSignUpPasswordText.text.toString()
        var againPass: String = StudentSignUpConfirmPasswordText.text.toString()
        var email: String = StudentSignUpEmailText.text.toString()
        if (validateForRegister(userName, email, password, againPass, this)) {
            DatabaseManagement().registerStudentForAuthorization(

                Users(
                    name = userName,
                    email = email,
                    designation = "Student",
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
        var toast:Toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM,Gravity.CENTER,10)
        toast.view = layout
        toast.show()
    }


}