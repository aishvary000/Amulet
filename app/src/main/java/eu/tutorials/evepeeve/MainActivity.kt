package eu.tutorials.evepeeve

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import eu.tutorials.evepeeve.Database.DatabaseManagement
import eu.tutorials.evepeeve.Models.Users
import kotlinx.android.synthetic.main.activity_faculty_signup.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.custom_toast_error.*
import kotlinx.android.synthetic.main.custom_toast_error.view.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Listeners()




    }
    private fun Listeners()
    {
        validateLocally("Email",mainActivityLoginEmailText,mainActivityLoginEmailField)
        validateLocally("Password",mainActivityLoginPasswordText,mainActivityLoginPasswordField)
    }
    fun logInUnknown(v:View)
    {
        val email:String  = mainActivityLoginEmailText.text.toString()
        val password:String = mainActivityLoginPasswordText.text.toString()
        if(validateForLogin(email,password,this))
        {
            DatabaseManagement().loginAdmin(email,password,this, MainActivity())
        }
    }
    fun student_signup(v:View){
        val intent = Intent(this,StudentSignup::class.java)
        startActivity(intent)
    }
//    fun ErrorToast(message:String)
//    {
//        var inflater: LayoutInflater = LayoutInflater.from()
//        var layout: View = inflater.inflate(R.layout.custom_toast_error,customllError)
//        layout.custom_Toast_text_Failure.text = message
//        var toast: Toast = Toast(this)
//        toast.setGravity(Gravity.BOTTOM, Gravity.CENTER,10)
//        toast.view = layout
//        toast.show()
//    }
    fun signInUser(document:DocumentSnapshot?,context: Context)
    {

        val item = document ?.toObject(Users::class.java)
        if (item != null) {
            if(item.designation == "Student")
            {
                    val intent =  Intent(context,StudentOptions::class.java)
                    intent.putExtra("UserInfo",item)
                    //intent.putExtras(item)
                    context.startActivity(intent)
            }
            if(item.designation == "Admin")
            {
                val intent =  Intent(context,AdminOptions::class.java)
                context.startActivity(intent)
            }
        }
    }
}