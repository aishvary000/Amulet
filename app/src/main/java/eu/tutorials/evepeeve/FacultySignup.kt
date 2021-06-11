package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FacultySignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_signup)
    }
    private fun regsterUser() {
        var name: String = Name.text.toString().trim { it <= ' ' }
        var email: String = Email.text.toString().trim { it <= ' ' }
        var password: String = Password.text.toString().trim { it <= ' ' }
        if (validateForm(name, email, password)) {
            showProgressDialog("Please Wait")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { Task ->

                    if (Task.isSuccessful) {
                        val Firebase: FirebaseUser = Task.result!!.user!!
                        var emailfirebas = Firebase.email!!
                        val user = User(Firebase.uid, name, emailfirebas)
                        fireStoreClass().registerUser(this, user)
                    } else {
                        Toast.makeText(this, Task.exception!!.message, Toast.LENGTH_LONG).show()
                    }

                }
        }
    }
    private fun validateForm(Name:String,EmailId:String,password:String):Boolean{
        return when{
            TextUtils.isEmpty(Name)->{
                showErrroSnackBar("Please Enter a Name")
                false
            }
            TextUtils.isEmpty(EmailId)->{
                showErrroSnackBar("Please enter Email")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrroSnackBar("Please Enter Your Password")
                false
            }
            else->{
                true
            }
        }
    }
}