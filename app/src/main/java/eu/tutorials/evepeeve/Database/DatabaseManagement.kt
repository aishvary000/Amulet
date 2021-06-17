package eu.tutorials.evepeeve.Database
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import eu.tutorials.evepeeve.*
import eu.tutorials.evepeeve.Models.Users
import kotlinx.android.synthetic.main.custom_progress_bar.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast_error.*
import kotlinx.android.synthetic.main.custom_toast_error.view.*
import java.lang.Exception

class DatabaseManagement:BaseActivity() {

    private val firestore = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun registerStudentInFirestore(userInfo: Users, activity: StudentSignup) {
        firestore.collection(eu.tutorials.evepeeve.utils.Constants.studentsDb).document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener { it ->

                activity.showSucessToast("Student added to Db")
            }
            .addOnFailureListener {

            }
    }

    fun registerStudentForAuthorization(userInfo: Users, activity:StudentSignup){
        firebaseAuth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
            .addOnSuccessListener { it ->
                //updating id of user to that of firebase
                val firebase: FirebaseUser? = it.user
                if (firebase != null) {
                    userInfo.id = firebase.uid
                }
                //registering for firebase
                registerStudentInFirestore(userInfo,activity)


            }
            .addOnFailureListener { it ->
                Log.e("this", it.toString())
            }
    }
    fun loginAdmin(email:String,password:String,context:Context,activity:MainActivity)
    {

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {it->

                    firestore.collection("Users").document(getCurrentUserId()).get()
                        .addOnSuccessListener {it->

                           activity.signInUser(it,context,activity)

                        }
                        .addOnFailureListener {

                            Toast.makeText(this,"User not exist",Toast.LENGTH_SHORT).show()
                            activity.Failed()
                            Log.e("here",it.toString())
                        }
            }
            .addOnFailureListener {

                activity.Failed()
                Log.e("error : ",it.toString())
            }


    }





}