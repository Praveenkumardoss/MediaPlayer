package com.example.seekho.data

import android.os.Parcel
import android.os.Parcelable

data class Images(
    val jpg: ImageType,
    val webp: ImageType? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        jpg = parcel.readParcelable(ImageType::class.java.classLoader) ?: ImageType(""),
        webp = parcel.readParcelable(ImageType::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(jpg, flags)
        parcel.writeParcelable(webp, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Images> {
        override fun createFromParcel(parcel: Parcel): Images {
            return Images(parcel)
        }

        override fun newArray(size: Int): Array<Images?> {
            return arrayOfNulls(size)
        }
    }
}