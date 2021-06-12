package eu.tutorials.evepeeve

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.custom_toast.*

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun validate(userName:String,email:String,password:String,againPass:String,context:Context):Boolean{

        if(userName=="")
        {
            Toast.makeText(context,"User Name Can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(email=="")
        {
            Toast.makeText(context,"Email field can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password=="")
        {
            Toast.makeText(context,"Password field can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password != againPass)
        {
            Toast.makeText(context,"Password Doesn't match Try again",Toast.LENGTH_SHORT).show()
            return false
        }
        return true




    }
    fun showErrorSnackBar(message:String)
    {

    }




}