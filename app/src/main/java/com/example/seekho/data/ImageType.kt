package com.example.seekho.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ImageType(
    @SerializedName("image_url")
    val imageUrl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        imageUrl = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ImageType> {
        override fun createFromParcel(parcel: Parcel): ImageType {
            return ImageType(parcel)
        }

        override fun newArray(size: Int): Array<ImageType?> {
            return arrayOfNulls(size)
        }
    }
}