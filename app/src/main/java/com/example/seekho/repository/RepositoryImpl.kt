package com.example.seekho.repository

import com.example.seekho.data.CharactersResponse
import com.example.seekho.data.MoviesResponse
import com.example.seekho.retrofit.RetrofitInstance
import retrofit2.Response

class RepositoryImpl : Repository {
    override suspend fun getMoviesData(): Response<MoviesResponse> {
        return RetrofitInstance.api.getMoviesData()
    }

    override suspend fun getMovieCharacters(id: Int): Response<CharactersResponse> {
        return RetrofitInstance.api.getCharacterData(id)
    }
}