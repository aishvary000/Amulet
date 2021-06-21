package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import eu.tutorials.evepeeve.Adapters.clubAdapter
import eu.tutorials.evepeeve.Database.DatabaseManagement
import eu.tutorials.evepeeve.Models.Clubs
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
                if (clubInfo != null) {
                    Toast.makeText(this@clubs_display,"Position : $position AdminName : ${clubInfo.AdminName} Uid : $id",Toast.LENGTH_LONG).show()
                }
            }

        })

    }

    override fun onStart() {
        super.onStart()
        ClubAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        ClubAdapter.startListening()
    }
}