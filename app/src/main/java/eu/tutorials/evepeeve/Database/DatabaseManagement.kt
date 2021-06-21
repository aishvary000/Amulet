package eu.tutorials.evepeeve.Database
import android.content.Context
import android.util.Log
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import eu.tutorials.evepeeve.*
import eu.tutorials.evepeeve.Models.Clubs
import eu.tutorials.evepeeve.Models.Users

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
    fun getClubs(): FirestoreRecyclerOptions<Clubs> {
        var clubsReference:CollectionReference = firestore.collection("Clubs")
        return FirestoreRecyclerOptions.Builder<Clubs>().setQuery(clubsReference,Clubs::class.java).build()

    }
    fun registerStudentForAuthorization(userInfo: Users, activity: StudentSignup){
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

                            activity.showError("User Not in Databaase")

                            Log.e("here",it.toString())
                        }
            }
            .addOnFailureListener {

                activity.showError("User Not found")
                Log.e("error : ",it.toString())
            }


    }
    fun registerAdminForAuthorization(userInfo: Users, activity: SignUpByAdmin){
        firebaseAuth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
            .addOnSuccessListener { it ->
                //updating id of user to that of firebase
                val firebase: FirebaseUser? = it.user
                if (firebase != null) {
                    userInfo.id = firebase.uid
                }
                //registering for firebase
                registerAdminInFirestore(userInfo,activity)


            }
            .addOnFailureListener { it ->
                Log.e("this", it.toString())
            }
    }
    fun registerAdminInFirestore(userInfo: Users, activity: SignUpByAdmin) {
        firestore.collection(eu.tutorials.evepeeve.utils.Constants.studentsDb).document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener { it ->

                activity.showSucessToast(" added to Db")
            }
            .addOnFailureListener {

            }
    }





}