package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_faculty_signup.*

class FacultySignup : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_signup)
        Listeners()
    }
    fun Listeners()
    {
        validateLocally("UserName",FacultySignUpUserNameText,FacultySignUpUserNameField)
        validateLocally("Password",FacultySignUpPasswordText,FacultySignUpPasswordField)
        validateLocally("ConfirmPassword",FacultySignUpConfirmPasswordText,FacultySignUpConfirmPasswordField)
        validateLocally("Email",FacultySignUpEmailText,FacultySignUpEmailField)
        validateLocally("Course",FacultySignUpCourseText,FacultySignUpCourseField)
    }

}