package eu.tutorials.evepeeve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_options)
    }
    fun test(v:View){
        val intent = Intent(this,SignUpByAdmin::class.java)
        startActivity(intent)
    }
}