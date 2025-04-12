package com.example.seekho.data

import android.os.Parcel
import android.os.Parcelable

data class MovieItem(
    val mal_id: Int,
    val url: String,
    val title: String,
    val images: Images,
    val trailer: Trailer?,
    val genres: List<Genre>,
    var characters: List<Character>? = null,
    val score: Double?,
    val rank: Int?,
    val episodes: Int?,
    val type: String,
    val status: String,
    val synopsis: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        mal_id = parcel.readInt(),
        url = parcel.readString() ?: "",
        title = parcel.readString() ?: "",
        images = parcel.readParcelable(Images::class.java.classLoader) ?: Images(ImageType("")),
        trailer = parcel.readParcelable(Trailer::class.java.classLoader),
        genres = parcel.createTypedArrayList(Genre.CREATOR) ?: emptyList(),
        characters = parcel.createTypedArrayList(Character.CREATOR),
        score = parcel.readValue(Double::class.java.classLoader) as? Double,
        rank = parcel.readValue(Int::class.java.classLoader) as? Int,
        episodes = parcel.readValue(Int::class.java.classLoader) as? Int,
        type = parcel.readString() ?: "",
        status = parcel.readString() ?: "",
        synopsis = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mal_id)
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeParcelable(images, flags)
        parcel.writeParcelable(trailer, flags)
        parcel.writeTypedList(genres)
        parcel.writeTypedList(characters)
        parcel.writeValue(score)
        parcel.writeValue(rank)
        parcel.writeValue(episodes)
        parcel.writeString(type)
        parcel.writeString(status)
        parcel.writeString(synopsis)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<MovieItem> {
        override fun createFromParcel(parcel: Parcel): MovieItem {
            return MovieItem(parcel)
        }

        override fun newArray(size: Int): Array<MovieItem?> {
            return arrayOfNulls(size)
        }
    }
}