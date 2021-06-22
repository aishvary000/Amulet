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

)

{
    private lateinit var  listener:onItemClickListener


    class itemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var clubName:TextView = itemView.findViewById<TextView>(R.id.clubname)
        var adminName:TextView = itemView.findViewById<TextView>(R.id.clubAdminName)
        var adminEmail:TextView = itemView.findViewById(R.id.clubAdminEmail)
        var clubAdminInfo:TextView = itemView.findViewById(R.id.EditClubAdminInfo)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
        var v:View = LayoutInflater.from(parent.context).inflate(R.layout.club_item,parent,false)
        return itemHolder(v)
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int, model: Clubs) {

        holder.adminEmail.text = model.email
        holder.adminName.text = model.name
        holder.clubName.text = model.clubName
        var uid: String = model.uid
        holder.clubAdminInfo.setOnClickListener {
            if(position != RecyclerView.NO_POSITION && listener!=null)
            {
                listener.onItemClick(snapshots.getSnapshot(position),position)
            }


        }
    }
    public interface onItemClickListener{
        fun onItemClick(documentSnapshot: DocumentSnapshot,position:Int)
    }
    public fun setOnItemClickListener(onItemClickListener: onItemClickListener){
            this.listener = onItemClickListener

    }
}