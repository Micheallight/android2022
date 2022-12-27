package com.testapp.project

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="currencies")
data class Exchange(
    val USD_in: String,
    val USD_out: String,
    val EUR_in: String,
    val EUR_out: String,
    val RUB_in: String,
    val RUB_out: String,
    val filial_id: String,
    val sap_id: String,
    val info_worktime: String,
    val street_type: String,
    val street: String,
    val filials_text: String,
    val home_number: String,
    val name: String,
    val name_type: String
) : Parcelable {

    @PrimaryKey(autoGenerate = true) var id = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(USD_in)
        parcel.writeString(USD_out)
        parcel.writeString(EUR_in)
        parcel.writeString(EUR_out)
        parcel.writeString(RUB_in)
        parcel.writeString(RUB_out)
        parcel.writeString(filial_id)
        parcel.writeString(sap_id)
        parcel.writeString(info_worktime)
        parcel.writeString(street_type)
        parcel.writeString(street)
        parcel.writeString(filials_text)
        parcel.writeString(home_number)
        parcel.writeString(name)
        parcel.writeString(name_type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exchange> {
        override fun createFromParcel(parcel: Parcel): Exchange {
            return Exchange(parcel)
        }

        override fun newArray(size: Int): Array<Exchange?> {
            return arrayOfNulls(size)
        }
    }

}