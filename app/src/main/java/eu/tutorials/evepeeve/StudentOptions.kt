package eu.tutorials.evepeeve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class StudentOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_options)
    }
    fun clubs_page(v: View){
        val intent = Intent(this,Clubs::class.java)
        startActivity(intent)
    }
    fun classroom_page(v:View){
        val intent = Intent(this,Classroom::class.java)
        startActivity(intent)

    }
}