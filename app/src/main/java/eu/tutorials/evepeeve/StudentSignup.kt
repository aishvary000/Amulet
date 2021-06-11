package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.tutorials.evepeeve.Models.Students
import eu.tutorials.evepeeve.Database.DatabaseManagement
import kotlinx.android.synthetic.main.activity_student_signup.*

class StudentSignup : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_signup)
        sign_up_btn.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        var userName: String = st_username.text.toString()
        var password: String = st_password.text.toString()
        var againPass: String = st_confirm.text.toString()
        var email: String = st_email.text.toString()
        if (validate(userName, email, password, againPass, this)) {
            DatabaseManagement().registerStudentForAuthorization(
                Students(
                    name = userName,
                    email = email,
                    admin = "false",
                    password = password
                )
            )

        }

    }
}