package eu.tutorials.evepeeve.Database
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firestore.v1.ListenResponse
import eu.tutorials.evepeeve.*
import eu.tutorials.evepeeve.Models.Admin
import eu.tutorials.evepeeve.Models.Students
import kotlinx.android.synthetic.main.custom_toast.*
import java.lang.Exception

class DatabaseManagement:BaseActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun registerStudentInFirestore(userInfo: Students,activity: StudentSignup) {
        firestore.collection(eu.tutorials.evepeeve.utils.Constants.studentsDb).document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener { it ->

                activity.showSucessToast("Student added to Db")
            }
            .addOnFailureListener {

            }
    }

    fun registerStudentForAuthorization(userInfo: Students,activity:StudentSignup){
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
    fun loginAdmin(email:String,password:String,activity:AdminLogin,context:AdminLogin)
    {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {it->

                    firestore.collection("admin").document(getCurrentUserId()).get()
                        .addOnSuccessListener {it1->
                            Log.e("hello ",it1.toString())
                            try{
                                var intent:Intent = Intent( context,AdminOptions::class.java)
                                context.startActivity(intent)
                            }
                            catch (e:Exception)
                            {
                                Log.e("HEre ",e.toString())

                            }

                        }
                        .addOnFailureListener {
                            Log.e("error ",it.toString())
                        }
            }
            .addOnFailureListener {
                Log.e("error : ",it.toString())
            }


    }



}