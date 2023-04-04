package com.jumrukovski.quotescompose.data.model.dto

import android.os.Parcel
import android.os.Parcelable

data class QuoteDTO(
    val _id: String,
    val author: String,
    val content: String,
    val tags: List<String>,
    val authorSlug: String,
    val length: Long,
    val dateAdded: String,
    val dateModified: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeStringList(tags)
        parcel.writeString(authorSlug)
        parcel.writeLong(length)
        parcel.writeString(dateAdded)
        parcel.writeString(dateModified)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuoteDTO> {
        override fun createFromParcel(parcel: Parcel): QuoteDTO {
            return QuoteDTO(parcel)
        }

        override fun newArray(size: Int): Array<QuoteDTO?> {
            return arrayOfNulls(size)
        }
    }
}