package eu.tutorials.evepeeve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_faculty_signup.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_newuser.setOnClickListener {
            val intent = Intent(this,FacultySignup::class.java)
            startActivity(intent)
            finish()
        }
        admin_login.setOnClickListener {
            val intent = Intent(this,AdminLogin::class.java)
            startActivity(intent)
            finish()
        }



    }
}