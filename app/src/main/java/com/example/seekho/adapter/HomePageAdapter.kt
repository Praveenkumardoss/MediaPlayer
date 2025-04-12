package com.example.seekho.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.seekho.R
import com.example.seekho.data.CharactersResponse
import com.example.seekho.data.MoviesResponse
import com.example.seekho.ui.DetailActivity

class HomePageAdapter(private val context: Context, private var moviesList: MoviesResponse, private val onItemClicked: (id: Int, index: Int) -> Unit) :
    RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): HomePageAdapter.HomePageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_page_card_view, parent, false)
        return HomePageViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomePageAdapter.HomePageViewHolder, position: Int) {

        val movieData = moviesList.data[position]

        with(holder) {
            title.text = movieData.title
            noOfShows.text = "No Of Episodes: ${movieData.episodes}"
            ratings.text = "Ratings: ${movieData.score}"
            val imageUrl = movieData.images.jpg.imageUrl
            posterImage.load(imageUrl) {
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
                listener(onSuccess = { _, res ->
                    posterImage.setImageDrawable(res.drawable)
                }, onError = { _, _ ->
                    Log.d(tag, "Error to load icon ${title.text}")
                })
            }
        }
    }

    fun updateData(newMoviesList: MoviesResponse) {
        moviesList = newMoviesList
        notifyDataSetChanged()
    }

    fun itemClicked(characters: CharactersResponse, position: Int) {
        val movieData = moviesList.data[position]
        movieData.characters = characters.data.filter {it.role == "Main"} .map{it.character}
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("movie_item", movieData)
        context.startActivity(intent)
    }


    override fun getItemCount(): Int {
        return moviesList.data.size
    }

    inner class HomePageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val noOfShows: TextView
        val ratings: TextView
        val posterImage: ImageView
        val itemContainer: ConstraintLayout

        init {
            title = itemView.findViewById(R.id.movie_title)
            noOfShows = itemView.findViewById(R.id.no_of_shows)
            ratings = itemView.findViewById(R.id.movie_ratings)
            posterImage = itemView.findViewById(R.id.movie_poster)
            itemContainer = itemView.findViewById(R.id.home_main)

            itemContainer.setOnClickListener {
//                itemClicked(adapterPosition)
                onItemClicked(moviesList.data[adapterPosition].mal_id, adapterPosition)
            }
        }
    }

    companion object {
        const val tag = "HomePageAdapter"
    }

}