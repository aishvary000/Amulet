package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin_options.*

class AdminOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_options)
        setSupportActionBar(AdminOptionsAppbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}