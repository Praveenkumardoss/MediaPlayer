package com.example.seekho.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        mal_id = parcel.readInt(),
        type = parcel.readString() ?: "",
        name = parcel.readString() ?: "",
        url = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mal_id)
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Genre> {
        override fun createFromParcel(parcel: Parcel): Genre {
            return Genre(parcel)
        }

        override fun newArray(size: Int): Array<Genre?> {
            return arrayOfNulls(size)
        }
    }
}

