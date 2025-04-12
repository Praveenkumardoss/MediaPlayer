package com.example.seekho.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("mal_id")
    val id: Int,
    val url: String,
    val images: Images,
    val name: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        url = parcel.readString() ?: "",
        images = parcel.readParcelable(Images::class.java.classLoader) ?: Images(ImageType("")),
        name = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(url)
        parcel.writeParcelable(images, flags)
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}