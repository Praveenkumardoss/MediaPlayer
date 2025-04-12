package com.example.seekho.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seekho.adapter.HomePageAdapter
import com.example.seekho.data.MoviesResponse
import com.example.seekho.databinding.HomePageActivityBinding
import com.example.seekho.repository.RepositoryImpl

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: HomePageActivityBinding
    private lateinit var adapter: HomePageAdapter

    private val viewModel by viewModels<HomePageViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = RepositoryImpl()
                return HomePageViewModel(repository) as T
            }
        }
    })
    private var idx: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = HomePageAdapter(this, MoviesResponse()) { id, index ->
            idx = index
            viewModel.getCharacters(id)
        }
        viewModel.getMoviesList()
        with(binding) {
            header.text = "Anime Shows"
            homeRecyclerView.adapter = adapter
        }

        viewModel.movieList.observe(this) {
            it?.let {
                adapter.updateData(it)
            }
        }

        viewModel.movieCharacterList.observe(this) {
            it?.let {
                adapter.itemClicked(it, idx)
            }
        }

    }
}