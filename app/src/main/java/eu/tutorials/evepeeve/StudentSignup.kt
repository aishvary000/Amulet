package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.tutorials.evepeeve.Models.Students
import eu.tutorials.evepeeve.Database.DatabaseManagement
import kotlinx.android.synthetic.main.activity_student_signup.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class StudentSignup : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_signup)
        sign_up_btn.setOnClickListener {
            registerUser()

        }
    }

    private fun registerUser() {
        val userName: String = st_username.text.toString()
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