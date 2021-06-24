package eu.tutorials.evepeeve

import android.app.Dialog
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.TimeOfDay
import eu.tutorials.evepeeve.Database.DatabaseManagement
import eu.tutorials.evepeeve.Models.Clubs
import kotlinx.android.synthetic.main.activity_club_admin_edit_info.*
import kotlinx.android.synthetic.main.custom_progress_bar.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.custom_toast_error.*
import kotlinx.android.synthetic.main.custom_toast_error.view.*

class ClubAdminEditInfo : AppCompatActivity() {
    private var mDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  intent:Intent = intent
        var id: String? = intent.getStringExtra("doc id")
        setContentView(R.layout.activity_club_admin_edit_info)
        newAdminSaveChanges.setOnClickListener {
            if(id!=null) {
                if(newAdminEmailText.text.toString() == "")
                {
                    Toast.makeText(this,"Both Fileds Are required",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    showProgressDialog("Update in progress")
                    DatabaseManagement().lookForEmail(newAdminEmailText.text.toString(),this,id)
                }

            }
        }


    }
    fun userFoundorNot(info:String,docId:String){

       if(info == "") //user not found
       {
           //register in db with designation
           mDialog?.dismiss()
           Toast.makeText(this,"User Not Found try registering First",Toast.LENGTH_LONG).show()
           if(newAdminNameVisibilty.visibility != View.VISIBLE)
                newAdminNameVisibilty.visibility = View.VISIBLE
           if(clubNameVisibilty.visibility != View.VISIBLE)
               clubNameVisibilty.visibility = View.VISIBLE
           if(paswordFieldVisibilty.visibility != View.VISIBLE)
                paswordFieldVisibilty.visibility = View.VISIBLE
           //setting clubName according to the docId captured
           FirebaseFirestore.getInstance().collection("Clubs").document(docId).get()
               .addOnSuccessListener {
                   val item:Clubs? = it.toObject(Clubs::class.java)
                   if (item != null) {
                       clubNameText.setText(item.clubName)
                       clubNameText.isEnabled = false
                   }
               }

       }
    }
    fun showProgressDialog(text:String){
        mDialog= Dialog(this)
        mDialog?.setContentView(R.layout.custom_progress_bar)
        mDialog?.progressbartext?.text = text
        mDialog?.show()
    }
    fun UpdateSuccessful()
    {
        mDialog?.dismiss()
        showSucessToast("User Updated Succesfully")
        finish()

    }
    fun showError(messgae:String){
        mDialog?.dismiss()
        FailureToast(messgae)


    }
    fun FailureToast(message:String)
    {
        mDialog?.dismiss()
        var inflater: LayoutInflater = LayoutInflater.from(this)
        var layout: View = inflater.inflate(R.layout.custom_toast_error,customllError)
        layout.custom_Toast_text_Failure.text = message
        var toast:Toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM,Gravity.CENTER,10)
        toast.view = layout
        toast.show()
    }
    fun showSucessToast(message:String)
    {
        mDialog?.dismiss()
        var inflater: LayoutInflater = LayoutInflater.from(this)
        var layout: View = inflater.inflate(R.layout.custom_toast,customll)
        layout.custom_Toast_text_Success.text = message
        var toast: Toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM, Gravity.CENTER,10)
        toast.view = layout
        toast.show()
    }
}