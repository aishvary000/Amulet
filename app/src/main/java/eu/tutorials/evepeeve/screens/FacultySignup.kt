package eu.tutorials.evepeeve.screens

import android.os.Bundle
import eu.tutorials.evepeeve.R
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