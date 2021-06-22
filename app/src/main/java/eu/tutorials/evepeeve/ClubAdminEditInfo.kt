package eu.tutorials.evepeeve

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.type.TimeOfDay
import eu.tutorials.evepeeve.Database.DatabaseManagement
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
                if(newAdminNameText.text.toString() == "" || newAdminEmailText.text.toString() == "")
                {
                    Toast.makeText(this,"Both Fileds Are required",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    showProgressDialog("Update in progress")
                    DatabaseManagement().updateAdminClubInfo(this,id,newAdminNameText.text.toString(),newAdminEmailText.text.toString(),this)

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
        FailureToast(messgae)
        mDialog?.dismiss()

    }
    fun FailureToast(message:String)
    {
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