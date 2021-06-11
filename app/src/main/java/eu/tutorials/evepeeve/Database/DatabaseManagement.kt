package eu.tutorials.evepeeve.Database
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import eu.tutorials.evepeeve.BaseActivity
import eu.tutorials.evepeeve.Models.Students

class DatabaseManagement:BaseActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun registerStudentInFirestore(userInfo: Students) {
        firestore.collection(eu.tutorials.evepeeve.utils.Constants.studentsDb).document(userInfo.id).set(userInfo, SetOptions.merge())
            .addOnSuccessListener { it->


            }
    }
    fun registerStudentForAuthorization(userInfo:Students)
    {
        firebaseAuth.createUserWithEmailAndPassword(userInfo.email,userInfo.password)
            .addOnSuccessListener { it->
            //updating id of user to that of firebase
            val firebase: FirebaseUser? = it.user
                if (firebase != null) {
                    userInfo.id = firebase.uid
                }
                //registering for firebase
                registerStudentInFirestore(userInfo)
        }
            .addOnFailureListener{it->
                Log.e("this",it.toString())
            }
    }

}