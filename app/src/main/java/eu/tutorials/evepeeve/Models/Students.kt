package eu.tutorials.evepeeve.Models

import android.os.Parcel
import android.os.Parcelable

data class Students (
    var id:String="",
    var name:String ="",
    var email:String = "",
    var admin:String ="",
    var password:String = "",
    var image:String ="",
    var mobile:Long = 0,
    var fcmToken:String = "",


):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) =with(parcel){
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(admin)
        parcel.writeString(password)
        parcel.writeString(image)
        parcel.writeLong(mobile)
        parcel.writeString(fcmToken)
    }

    override fun describeContents() =0

    companion object CREATOR : Parcelable.Creator<Students> {
        override fun createFromParcel(parcel: Parcel):Students {
            return Students(parcel)
        }

        override fun newArray(size: Int): Array<Students?> {
            return arrayOfNulls(size)
        }
    }
}