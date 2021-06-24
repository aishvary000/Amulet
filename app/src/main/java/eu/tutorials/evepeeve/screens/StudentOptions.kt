package eu.tutorials.evepeeve.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import eu.tutorials.evepeeve.Models.Users
import eu.tutorials.evepeeve.R
import kotlinx.android.synthetic.main.activity_student_options.*

class StudentOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_options)
        var intent:Intent = intent
        val userInfo: Users? = intent.getParcelableExtra("UserInfo")
        if (userInfo != null) {
            loggedinStudentname.text = userInfo.name

        }


    }
    fun clubs_page(v: View){
        val intent = Intent(this, Clubs::class.java)
        startActivity(intent)
    }
    fun classroom_page(v:View){
        val intent = Intent(this, Classroom::class.java)
        startActivity(intent)

    }
}