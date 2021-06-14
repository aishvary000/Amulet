package eu.tutorials.evepeeve.Models

import android.os.Parcel
import android.os.Parcelable

data class Users (
    var id:String="",
    var name:String ="",
    var email:String = "",
    var designation:String ="",
    var password:String = "",
    var image:String ="",
    var mobile:Long = 0,
    var fcmToken:String = "",
    var course:String ="",


):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) =with(parcel){
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(designation)
        parcel.writeString(password)
        parcel.writeString(image)
        parcel.writeLong(mobile)
        parcel.writeString(fcmToken)
        parcel.writeString(course)
    }

    override fun describeContents() =0

    companion object CREATOR : Parcelable.Creator<Users> {
        override fun createFromParcel(parcel: Parcel):Users {
            return Users(parcel)
        }

        override fun newArray(size: Int): Array<Users?> {
            return arrayOfNulls(size)
        }
    }
}