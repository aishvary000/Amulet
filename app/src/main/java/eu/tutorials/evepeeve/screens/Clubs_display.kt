package eu.tutorials.evepeeve.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import eu.tutorials.evepeeve.Adapters.clubAdapter
import eu.tutorials.evepeeve.Database.DatabaseManagement
import eu.tutorials.evepeeve.Models.Clubs
import eu.tutorials.evepeeve.R
import kotlinx.android.synthetic.main.activity_clubs_display.*

class clubs_display : AppCompatActivity() {
    private lateinit var ClubAdapter:clubAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_clubs_display)
        setUpClubList()

    }
    fun setUpClubList(){
        var Clubs: FirestoreRecyclerOptions<Clubs> = DatabaseManagement().getClubs()
        ClubAdapter = clubAdapter(Clubs)
        var recyclerView:RecyclerView = clubList
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClubAdapter
        ClubAdapter.setOnItemClickListener(object : clubAdapter.onItemClickListener {
            override fun onItemClick(documentSnapshot: DocumentSnapshot, position: Int) {
                var id:String = documentSnapshot.id
                var clubInfo: Clubs? = documentSnapshot.toObject(eu.tutorials.evepeeve.Models.Clubs::class.java)
                val intent = Intent(this@clubs_display, ClubAdminEditInfo::class.java)
                intent.putExtra("doc id",id)
                intent.putExtra("clubName",clubInfo?.clubName)
                intent.putExtra("AdminEmail",clubInfo?.email)
                intent.putExtra("AdminName",clubInfo?.name)
                startActivity(intent)


            }

        })

    }

    override fun onStart() {
        super.onStart()
        ClubAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        ClubAdapter.stopListening()
    }
}