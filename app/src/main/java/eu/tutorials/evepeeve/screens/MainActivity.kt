package eu.tutorials.evepeeve.screens

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import eu.tutorials.evepeeve.Database.DatabaseManagement
import eu.tutorials.evepeeve.Models.Users
import eu.tutorials.evepeeve.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_progress_bar.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.custom_toast_error.*
import kotlinx.android.synthetic.main.custom_toast_error.view.*

class MainActivity : BaseActivity() {
    private var mDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

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

        if(validateForLogin(email,password,this,))
        {
            showProgressDialog("Please Wait")
            DatabaseManagement().loginAdmin(email,password,this, this)
        }
    }
    fun student_signup(v:View){
        val intent = Intent(this, StudentSignup::class.java)
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
    fun signInUser(document:DocumentSnapshot?,context: Context,activity: MainActivity)
    {
        mDialog?.dismiss()
        val item = document ?.toObject(Users::class.java)
        if (item != null) {
            if(item.designation == "Student")
            {
                    val intent =  Intent(context, StudentOptions::class.java)
                    intent.putExtra("UserInfo",item)
                    //intent.putExtras(item)
                    context.startActivity(intent)
            }
            if(item.designation == "Admin")
            {
                val intent =  Intent(context, AdminOptions::class.java)
                intent.putExtra("UserInfo",item)
                context.startActivity(intent)
            }
        }
    }
    fun showProgressDialog(text:String){
        mDialog= Dialog(this)
        mDialog?.setContentView(R.layout.custom_progress_bar)
        mDialog?.progressbartext?.text = text
        mDialog?.show()
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
    fun FailureToast(message:String)
    {
        var inflater: LayoutInflater = LayoutInflater.from(this)
        var layout: View = inflater.inflate(R.layout.custom_toast_error,customllError)
        layout.custom_Toast_text_Failure.text = message
        var toast:Toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM,Gravity.CENTER,10)
        toast.view = layout
        toast.show()
    }
    fun showError(messgae:String){
        FailureToast(messgae)
        mDialog?.dismiss()

    }
    fun showToast(messgae:String)
    {
        Toast.makeText(this,messgae,Toast.LENGTH_LONG).show()
    }



}