package eu.tutorials.evepeeve.screens

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import eu.tutorials.evepeeve.R
import kotlinx.android.synthetic.main.activity_sign_up_by_admin.*
import kotlinx.android.synthetic.main.activity_student_signup.*

open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
    fun validateForLogin(email:String,password:String,context: Context):Boolean{
        if(email=="")
        {
            Toast.makeText(context,"Email field can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.length < 8)
        {
            Toast.makeText(context,"Minimum Password size is 8",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun validateForRegister(userName:String,email:String,password:String,againPass:String,context:Context):Boolean{

        if(userName=="")
        {
            Toast.makeText(context,"User Name Can't be empty",Toast.LENGTH_SHORT).show()
            StudentSignUpUserNameField.error
            return false
        }
        if(email=="")
        {
            Toast.makeText(context,"Email field can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.length < 8)
        {
            Toast.makeText(context,"Minimum Password size is 8",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password != againPass)
        {
            Toast.makeText(context,"Password Doesn't match Try again",Toast.LENGTH_SHORT).show()

            return false
        }
        return true




    }
    fun validateLocally(toValidate: String,TextView:TextInputEditText,Layout:TextInputLayout)
    {
        TextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validateLocal(toValidate,s,TextView, Layout)
            }

        })
    }
    fun validateLocal(toValidate: String, Value: Editable?,TextView:TextInputEditText,Layout:TextInputLayout){
        if(toValidate == "UserName") {
            if (Value.toString() == "") {
                Layout.error = "UserName can't be empty"

            }
            else
                Layout.error = null

        }
        else if(toValidate == "Email") {
            if (Value.toString() == "") {
                Layout.error = "Email field can't be empty"

            }
            else
                Layout.error = null

        }
        else if(toValidate == "Password") {
            if (Value != null) {
                if (Value.toString().length< 8) {
                    Layout.error = "Minimum Password size is 8"

                }
                else
                    Layout.error = null
            }

        }
        else if(toValidate == "Designation") {
            if (Value.toString() == "") {
                Layout.error = "Designation field can't be empty"

            }
            if(Value.toString()!="Admin" && Value.toString()!="Faculty")
            {
                Layout.error = "Designation Field can be Admin/Faculty"
            }
            else
                Layout.error = null

        }
        else if(toValidate == "ConfirmPasswordAdmin")
        {

            if(passwordAdmin.text.toString() != Value.toString())
            {
                Layout.error  = "Password Mismatch"
            }
            else
                Layout.error = null
        }
        else if(toValidate == "ConfirmPassword")
        {

            if(StudentSignUpPasswordText.text.toString() != Value.toString())
            {
                Layout.error  = "Password Mismatch"
            }
            else
                Layout.error = null
        }
        else if(toValidate == "Course")
        {
            if (Value.toString() == "") {
                Layout.error = "Course field can't be empty"

            }
            else
                Layout.error = null
        }





    }

    fun getCurrentUserId(): String {
        var currentUserId = ""
        var currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser!=null)
            currentUserId = currentUser.uid
        return currentUserId
    }
    fun validateForRegisterAdmin(userName: String,designation:String,email:String,password:String,againPass:String,context:Context):Boolean{

        if(userName == "")
        {
            Toast.makeText(context,"UserName Can't be empty",Toast.LENGTH_SHORT).show()
            StudentSignUpUserNameField.error
            return false
        }
        if(designation=="")
        {
            Toast.makeText(context,"Designation Can't be empty",Toast.LENGTH_SHORT).show()
            StudentSignUpUserNameField.error
            return false
        }

        if(email=="")
        {
            Toast.makeText(context,"Email field can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.length < 8)
        {
            Toast.makeText(context,"Minimum Password size is 8",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password != againPass)
        {
            Toast.makeText(context,"Password Doesn't match Try again",Toast.LENGTH_SHORT).show()

            return false
        }
        return true




    }





}