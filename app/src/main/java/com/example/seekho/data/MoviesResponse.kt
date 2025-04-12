package com.example.seekho.data

data class MoviesResponse(
    val data: List<MovieItem>
) {
    constructor() : this(emptyList())
}
