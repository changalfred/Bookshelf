package com.example.bookshelf.repository.network.model

import com.squareup.moshi.JsonClass

const val UNAVAILABLE = "Unavailable"

@JsonClass(generateAdapter = true)
data class BooksResponse(
        val items: List<Book>
)

@JsonClass(generateAdapter = true)
data class Book(
        val id: String,
        val volumeInfo: VolumeInfo,
        val saleInfo: SaleInfo,
        val accessInfo: AccessInfo
)

@JsonClass(generateAdapter = true)
data class VolumeInfo(
        val title: String,
        val subtitle: String?,
        val authors: List<String>?,
        val publisher: String?,
        val publishedDate: String?,
        val description: String?,
        val industryIdentifiers: List<IsbnType>,
        val pageCount: Int?,
        val imageLinks: ImageLinks?,
        val language: String?,
        val previewLink: String?
)

@JsonClass(generateAdapter = true)
data class IsbnType(
        val type: String,
        val identifier: String
)

@JsonClass(generateAdapter = true)
data class ImageLinks(
        val smallThumbnail: String?,
        val thumbnail: String?
)

@JsonClass(generateAdapter = true)
data class SaleInfo(
        val country: String?
)

@JsonClass(generateAdapter = true)
data class AccessInfo(
        val country: String?
)

enum class Type {
    ISBN_10, ISBN_13
}