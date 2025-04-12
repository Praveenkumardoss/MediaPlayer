package com.example.seekho.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.seekho.R
import com.example.seekho.data.MovieItem
import com.example.seekho.databinding.ActivityDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(binding.youtubePlayerView)

        val movieItemData = intent.getParcelableExtra<MovieItem>("movie_item")

        binding.detailTitle.text = movieItemData?.title

        val youtubeId = movieItemData?.trailer?.youtubeId
        val imageUrl = movieItemData?.images?.jpg?.imageUrl
        val fullSynopsis = movieItemData?.synopsis
        val synopsisFull = "Synopsis: ${movieItemData?.synopsis}"
        val synopsisTruncated = "Synopsis: ${fullSynopsis?.take(250)}"

        with(binding) {
            if (!youtubeId.isNullOrEmpty()) {
                youtubePlayerView.visibility = View.VISIBLE
                binding.youtubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(player: YouTubePlayer) {
                        player.loadVideo(youtubeId, 0f)
                    }
                })
            } else if (!imageUrl.isNullOrEmpty()) {
                moviePosterDetail.visibility = View.VISIBLE

                moviePosterDetail.load(imageUrl) {
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_launcher_background)
                    listener(onSuccess = { _, res ->
                        moviePosterDetail.setImageDrawable(res.drawable)
                    }, onError = { _, _ ->
                        Log.d(tag, "Error to load icon in detail page")
                    })
                }
            } else {
                Log.d(tag, "No poster or trailer")
            }
            btnShowMore.visibility = if (synopsisFull?.length!! > 200) View.VISIBLE else View.GONE
            detailTitle.text = movieItemData?.title
            detailRatings.text = "Ratings: ${movieItemData?.score}"
            val genres = movieItemData?.genres?.joinToString(", ") { it.name } ?: "N/A"
            detailGenres.text = "Genres: $genres"
            detailNoOfEpisodes.text = "No Of Episodes: ${movieItemData?.episodes}"
            detailSynopsis.text = synopsisTruncated
            detailMainCast.text = "Main Cast: " + movieItemData?.characters?.joinToString(separator = " | ") { it.name}
        }


        with(binding) {
            btnShowMore.setOnClickListener {
                if (detailSynopsis.maxLines == 6) {
                    detailSynopsis.maxLines = Int.MAX_VALUE
                    btnShowMore.text = "Less"
                    detailSynopsis.text = synopsisFull
                } else {
                    detailSynopsis.maxLines = 6
                    btnShowMore.text = "More"
                    detailSynopsis.text = synopsisTruncated
                }
            }
        }
    }

    companion object {
        const val tag = "DetailActivity"
    }
}