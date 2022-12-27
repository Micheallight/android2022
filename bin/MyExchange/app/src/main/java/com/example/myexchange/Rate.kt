package com.example.myexchange

import android.os.Parcel
import android.os.Parcelable

data class Rate(
    val buyCode: Int,
    val buyIso: String,
    val buyRate: Double,
    val date: String,
    val name: String,
    val quantity: Int,
    val sellCode: Int,
    val sellIso: String,
    val sellRate: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readDouble(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(buyCode)
        parcel.writeString(buyIso)
        parcel.writeDouble(buyRate)
        parcel.writeString(date)
        parcel.writeString(name)
        parcel.writeInt(quantity)
        parcel.writeInt(sellCode)
        parcel.writeString(sellIso)
        parcel.writeDouble(sellRate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rate> {
        override fun createFromParcel(parcel: Parcel): Rate {
            return Rate(parcel)
        }

        override fun newArray(size: Int): Array<Rate?> {
            return arrayOfNulls(size)
        }
    }
}