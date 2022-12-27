package com.michaellight.asusrecyclev1

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class Product(
	val name: String,
	var price:Int,
	val photoId: Int,
	val rating: Double,
	val photo: Bitmap?): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString() ?: "",
		parcel.readInt(),
		parcel.readInt(),
		parcel.readDouble(),
		parcel.readParcelable(Bitmap::class.java.classLoader)
	) {}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeInt(price)
		parcel.writeInt(photoId)
		parcel.writeDouble(rating)
		parcel.writeParcelable(photo, flags)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Product> {
		override fun createFromParcel(parcel: Parcel): Product {
			return Product(parcel)
		}

		override fun newArray(size: Int): Array<Product?> {
			return arrayOfNulls(size)
		}
	}
}
