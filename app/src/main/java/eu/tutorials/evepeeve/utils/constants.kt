package eu.tutorials.evepeeve.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.core.app.ActivityCompat.startActivityForResult


public object  Constants{
    const val studentsDb:String = "Users"
    const val BOARDS:String="boards"
    const val BOARD_CREATED:String="personCreated"
    const val IMAGE:String="image"
    const val NAME:String="name"
    const val MOBILE:String="mobile"
    const val EMAIL:String="email"
    const val ASSINGED_TO:String="assignedTo"
    const val READ_STORAGE_PERMISSSION_CODE =1
    const val PICK_IMAGE_REQUEST_CODE = 2
    const val DOCUMENT_ID:String ="documentId"
    fun getFileType(activity: Activity,uri: Uri?):String?{
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(uri?.let { activity.contentResolver.getType(it) })
    }
    fun showImageChooser(activity:Activity) {
        var galleryInent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(galleryInent, PICK_IMAGE_REQUEST_CODE)
    }


}