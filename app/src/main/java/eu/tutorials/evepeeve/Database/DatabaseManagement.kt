package eu.tutorials.evepeeve.Database
import android.app.Activity
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
    private val fireStoreUser: CollectionReference = firestore.collection("Users")
    private val fireStoreClubs: CollectionReference = firestore.collection("Clubs")
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
        var clubsReference: CollectionReference = firestore.collection("Clubs")
        return FirestoreRecyclerOptions.Builder<Clubs>().setQuery(clubsReference, Clubs::class.java)
            .build()

    }

    fun registerStudentForAuthorization(userInfo: Users, activity: StudentSignup) {
        firebaseAuth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
            .addOnSuccessListener { it ->
                //updating id of user to that of firebase
                val firebase: FirebaseUser? = it.user
                if (firebase != null) {
                    userInfo.id = firebase.uid
                }
                //registering for firebase
                registerStudentInFirestore(userInfo, activity)


            }
            .addOnFailureListener { it ->
                Log.e("this", it.toString())
            }
    }
    fun loginAdmin(email: String, password: String, context: Context, activity: MainActivity) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { it ->

                firestore.collection("Users").document(getCurrentUserId()).get()
                    .addOnSuccessListener { it ->

                        activity.signInUser(it, context, activity)

                    }
                    .addOnFailureListener {

                        activity.showError("User Not in Databaase")

                        Log.e("here", it.toString())
                    }
            }
            .addOnFailureListener {

                activity.showError("User Not found")
                Log.e("error : ", it.toString())
            }


    }
    fun registerAdminForAuthorization(userInfo: Users, activity: SignUpByAdmin) {
        firebaseAuth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
            .addOnSuccessListener { it ->
                //updating id of user to that of firebase
                val firebase: FirebaseUser? = it.user
                if (firebase != null) {
                    userInfo.id = firebase.uid
                }
                //registering for firebase
                registerAdminInFirestore(userInfo, activity)


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
    fun createUserwithPriviledges(
        name: String,
        email: String,
        password: String,
        Role: String,
        activity: ClubAdminEditInfo,
        clubId: String
    ) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user: Users = Users(
                    name = name,
                    email = email,
                    designation = Role
                )
                val firebase: FirebaseUser? = it.user
                if (firebase != null) {
                    Log.e("uid ", firebase.uid)
                    user.id = firebase.uid

                }

                //registering in users collection
                firestore.collection("Users").document(user.id).set(user)
                    .addOnSuccessListener {

                        Log.e("this ", "New User Registration Successful ${user.id}")
                        updateAdminClubInfo(clubId,user.id,name,email,activity)



                    }
            }
            .addOnFailureListener {
                Log.e("Here : ",it.toString())
            }
    }

    private fun updateAdminClubInfo(clubId: String, userId: String,name: String,email: String,activity: ClubAdminEditInfo) {
        val map:HashMap<String,Any> = HashMap()
        map["name"] = name
        map["email"] = email
        map["uid"] = userId
        fireStoreClubs.document(clubId).get()
            .addOnSuccessListener {
                val clubSnapshot:Clubs? = it.toObject(Clubs::class.java)
                if(clubSnapshot!=null)
                {
                    //updating designation of existing user
                        val mp:HashMap<String,Any> = HashMap()
                        mp["designation"] = "Student"
                        fireStoreUser.document(clubSnapshot.uid).set(mp, SetOptions.merge()).addOnSuccessListener {
                            fireStoreClubs.document(clubId).set(map, SetOptions.merge()).addOnSuccessListener {
                                activity.showSucessToast("Admin Updated Succesfully")
                                activity.finish()
                            }
                                .addOnFailureListener {
                                    Log.e("Here",it.toString())
                                    activity.showError(it.toString())
                                }
                        }
                }
            }



    }

    fun lookForEmail(email: String,activity: ClubAdminEditInfo,docId:String){
            var document:String = ""
            fireStoreUser.whereEqualTo("email",email).get().addOnSuccessListener { documents->
                if(documents.isEmpty)
                {

                        Log.e("This ","User Not found")

                        activity.userFoundorNot(document,docId)


                    }else
                {
                    for(docment in documents)
                    {
                        var item:Users? = docment.toObject(Users::class.java)
                        if (item != null) {
                            activity.userFoundorNot(item.id,docId)
                        }
                    }
                }
                }



            }
    fun checkForUserDesignation(userId:String,activity: ClubAdminEditInfo,clubId:String){

        fireStoreUser.document(userId).get()
            .addOnSuccessListener {
                val item: Users? = it.toObject(Users::class.java)
                if(item != null)
                {
                    if(item.designation == "clubAdmin")
                    {
                        Log.e("Clubadmin","Found")
                        activity.showError("User is already an admin of a club")
                    }
                    else if(item.designation == "Faculty")
                    {
                        activity.showError("Faculty can't be an admin of club")
                    }
                    else
                    {
                        //updating user designation
                        val map:HashMap<String,Any> = HashMap()
                        map["designation"] = "clubAdmin"
                        fireStoreUser.document(userId).set(map, SetOptions.merge()).addOnSuccessListener {
                            updateAdminClubInfo(clubId,userId,item.name,item.email,activity)
                        }

                    }
                }

            }
            .addOnFailureListener {
                Log.e("In checkforuage : ",it.toString())
            }
    }



}













