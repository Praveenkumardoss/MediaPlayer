package com.example.seekho.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("youtube_id")
    val youtubeId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("embed_url")
    val embedUrl: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        youtubeId = parcel.readString(),
        url = parcel.readString(),
        embedUrl = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(youtubeId)
        parcel.writeString(url)
        parcel.writeString(embedUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Trailer> {
        override fun createFromParcel(parcel: Parcel): Trailer {
            return Trailer(parcel)
        }

        override fun newArray(size: Int): Array<Trailer?> {
            return arrayOfNulls(size)
        }
    }
}
