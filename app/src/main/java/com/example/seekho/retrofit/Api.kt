package com.example.seekho.retrofit

import com.example.seekho.data.CharactersResponse
import com.example.seekho.data.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("v4/top/anime")
    suspend fun getMoviesData(): Response<MoviesResponse>

    @GET("/v4/anime/{anime_id}/characters")
    suspend fun getCharacterData(
        @Path("anime_id") id: Int
    ): Response<CharactersResponse>
}
