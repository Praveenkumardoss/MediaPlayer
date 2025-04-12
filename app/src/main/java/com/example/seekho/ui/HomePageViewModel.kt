package com.example.seekho.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekho.data.CharactersResponse
import com.example.seekho.data.MoviesResponse
import com.example.seekho.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel(private val repository: Repository) : ViewModel() {

    private val _movieList = MutableLiveData<MoviesResponse?>()
    val movieList = _movieList

    private val _movieCharacterList = MutableLiveData<CharactersResponse?>()
    val movieCharacterList = _movieCharacterList

    fun getMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMoviesData()
                if (response.isSuccessful) {
                    val data = response.body()
                    _movieList.postValue(data)
                } else {
                    Log.e(tag, "API Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.d(tag, "getMoviesList exception ${e.message}")
            }
        }
    }

    fun getCharacters(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMovieCharacters(id)
                if (response.isSuccessful) {
                    val data = response.body()
                    _movieCharacterList.postValue(data)
                } else {
                    Log.e(tag, "API Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.d(tag, "getCharacters exception ${e.message}")
            }
        }
    }

    companion object {
        const val tag = "HomePageViewModel"
    }
}