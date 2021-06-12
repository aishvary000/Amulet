package eu.tutorials.evepeeve.Database
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firestore.v1.ListenResponse
import eu.tutorials.evepeeve.BaseActivity
import eu.tutorials.evepeeve.Models.Students
import eu.tutorials.evepeeve.R
import eu.tutorials.evepeeve.StudentSignup
import kotlinx.android.synthetic.main.custom_toast.*

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


}