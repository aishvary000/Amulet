package eu.tutorials.evepeeve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import eu.tutorials.evepeeve.Models.Users
import kotlinx.android.synthetic.main.activity_admin_options.*

class AdminOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_options)
        val intent:Intent = intent
        val userInfo: Users? = intent.getParcelableExtra("UserInfo")
        if(userInfo!=null)
        {
            AdminHomePageName.text = userInfo.name
            AdminHomePageEmail.text =userInfo.email
        }
    }
    fun test(v:View){
        val intent = Intent(this,clubs_display::class.java)
        startActivity(intent)
    }
}