package com.example.seekho.repository

import com.example.seekho.data.CharactersResponse
import com.example.seekho.data.MoviesResponse
import retrofit2.Response

interface Repository {
    suspend fun getMoviesData(): Response<MoviesResponse>
    suspend fun getMovieCharacters(id: Int): Response<CharactersResponse>
}