package com.michaellight.wishlistv1

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishes")
data class Wish(
    @PrimaryKey val date: String,
    val name: String,
    var price: String,
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
        parcel.writeString(date)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeInt(photoId)
        parcel.writeParcelable(photo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wish> {
        override fun createFromParcel(parcel: Parcel): Wish {
            return Wish(parcel)
        }

        override fun newArray(size: Int): Array<Wish?> {
            return arrayOfNulls(size)
        }
    }
}