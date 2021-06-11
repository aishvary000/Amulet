package eu.tutorials.evepeeve

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun validate(userName:String,email:String,password:String,againPass:String,context:Context):Boolean{

        if(userName.isEmpty())
        {
            Toast.makeText(context,"User Name Can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(email.isEmpty())
        {
            Toast.makeText(context,"Email field can't be empty",Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.isEmpty())
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