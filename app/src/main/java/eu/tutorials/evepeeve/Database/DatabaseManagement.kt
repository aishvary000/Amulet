package eu.tutorials.evepeeve.Database
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import com.google.firebase.firestore.auth.User
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
            .addOnSuccessListener {

                activity.showSucessToast(" added to Db")
            }
            .addOnFailureListener {

            }
    }
    fun updateAdminClubInfo(context: Context,id:String,newAdminName:String,newAdminEmail:String){
        var docRefClubs:CollectionReference = firestore.collection("Clubs")
        var clubName:String = ""
        docRefClubs.document(id).get()
            .addOnSuccessListener {
                if(it!=null) {
                    val doc = it.toObject(Clubs::class.java)
                    if (doc != null) {
                        clubName = doc.clubName
                    }
                }
            }
        docRefClubs.document(id).delete()
            .addOnSuccessListener {
                Log.e("This" , "Deleted")
            }
        //checking for any document with email id as new email id
        var userId:String? = checkExistingUserWithEmailId(newAdminEmail)
        if(userId == null) {
            //registering for new user

            firebaseAuth.createUserWithEmailAndPassword(newAdminEmail,"qwertyuiop")
                .addOnSuccessListener {
                    var user:Users = Users(name = newAdminName,email = newAdminEmail,designation = "clubAdmin")
                    val firebase: FirebaseUser? = it.user
                    if (firebase != null) {
                        user.id = firebase.uid
                        userId = user.id
                    }
                    //registering in users collection
                    firestore.collection("Users").document(user.id).set(user).addOnSuccessListener {
                        Log.e("this ","New User Registration Successful")
                    }


                }
                .addOnFailureListener {
                    Log.e("this ","New User Registration Failed")
                }


        }


        //registering in clubs collection
        var collectionReference:CollectionReference = firestore.collection("Clubs")
        if(userId!=null)
        {
            collectionReference.document(userId!!).set(Clubs(clubName = clubName,AdminName = newAdminName,AdminEmail = newAdminEmail,uid = userId!!)).addOnSuccessListener {
                Log.e("this","All set")
            }
                .addOnFailureListener {
                    Log.e("This ","Error here")
                }
        }







    }
    fun checkExistingUserWithEmailId(email:String): String? {
        var collectionReference:CollectionReference = firestore.collection("Users")
        var id:String? = null
        collectionReference.whereEqualTo("email",email)
            .get()
            .addOnSuccessListener {documents->
                for(document in documents){
                   id = document.id
                   }
                }

            return  id
            }

    fun updateUserInfo(id:String,map:HashMap<String,Any>){



    }
}




