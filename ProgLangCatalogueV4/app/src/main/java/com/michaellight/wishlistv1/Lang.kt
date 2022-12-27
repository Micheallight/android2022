package com.michaellight.wishlistv1

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_of_languages")
data class Lang(
    @PrimaryKey val name: String,
    val date: String,
    var text: String,
    val photoId: Int,
    val photo: Bitmap?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readParcelable(Bitmap::class.java.classLoader)
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(date)
        parcel.writeString(text)
        parcel.writeInt(photoId)
        parcel.writeParcelable(photo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lang> {
        override fun createFromParcel(parcel: Parcel): Lang {
            return Lang(parcel)
        }

        override fun newArray(size: Int): Array<Lang?> {
            return arrayOfNulls(size)
        }
    }
}