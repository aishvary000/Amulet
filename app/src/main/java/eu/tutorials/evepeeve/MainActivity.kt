package eu.tutorials.evepeeve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        student_button.setOnClickListener {
            val intent = Intent(this,StudentSignup::class.java)
            startActivity(intent)
            finish()
        }
        faculty_button.setOnClickListener {
            val intent = Intent(this,FacultySignup::class.java)
            startActivity(intent)
            finish()
        }



    }
}