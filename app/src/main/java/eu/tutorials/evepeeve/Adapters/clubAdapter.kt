package eu.tutorials.evepeeve.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import eu.tutorials.evepeeve.Models.Clubs
import eu.tutorials.evepeeve.R


class clubAdapter(options: FirestoreRecyclerOptions<Clubs>) :FirestoreRecyclerAdapter<Clubs,clubAdapter.itemHolder>(
    options
){


    class itemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var clubName:TextView = itemView.findViewById<TextView>(R.id.clubname)
        var adminName:TextView = itemView.findViewById<TextView>(R.id.clubAdminName)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
        var v:View = LayoutInflater.from(parent.context).inflate(R.layout.club_item,parent,false)
        return itemHolder(v)
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int, model: Clubs) {
        holder.adminName.text = model.AdminName
        holder.clubName.text = model.clubName
    }
}