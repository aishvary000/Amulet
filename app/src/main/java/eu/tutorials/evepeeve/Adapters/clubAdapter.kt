package eu.tutorials.evepeeve.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import eu.tutorials.evepeeve.Models.Clubs
import eu.tutorials.evepeeve.Models.Users
import eu.tutorials.evepeeve.R


class clubAdapter(options: FirestoreRecyclerOptions<Clubs>) :FirestoreRecyclerAdapter<Clubs,clubAdapter.itemHolder>(
    options
){

    var firestore = FirebaseFirestore.getInstance()
    class itemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var clubName:TextView = itemView.findViewById<TextView>(R.id.clubname)
        var adminName:TextView = itemView.findViewById<TextView>(R.id.clubAdminName)
        var adminEmail:TextView = itemView.findViewById(R.id.clubAdminEmail)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
        var v:View = LayoutInflater.from(parent.context).inflate(R.layout.club_item,parent,false)
        return itemHolder(v)
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int, model: Clubs) {

        var clubsReference: Task<DocumentSnapshot> = firestore.collection("Users").document(model.uid).get()
        clubsReference.addOnSuccessListener { it->
            var item = it.toObject(Users::class.java)
            if (item != null) {
                holder.adminEmail.text = item.email
                holder.adminName.text = item.name
            }
        }
        holder.clubName.text = model.clubName

    }
}