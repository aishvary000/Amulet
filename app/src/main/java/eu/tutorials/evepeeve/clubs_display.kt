package eu.tutorials.evepeeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
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