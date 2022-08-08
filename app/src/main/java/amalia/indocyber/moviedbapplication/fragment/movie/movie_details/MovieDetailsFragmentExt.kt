package amalia.indocyber.moviedbapplication.fragment.movie.movie_details

import android.view.View
import androidx.paging.LoadState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieDetailsFragment.observeLiveData(){
    vm.getMovieDetails(movieDetailsFragmentArgs.movieId)
    adapter.addLoadStateListener {
        if(it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.retryButton.visibility = View.VISIBLE
            binding.progressBar.visibility= View.GONE
            binding.pageDetails.visibility= View.GONE
            binding.retryButton.setOnClickListener {
                vm.getMovieDetails(movieDetailsFragmentArgs.movieId)
            }
        } else if(it.refresh is LoadState.Loading && adapter.itemCount == 0){
            binding.retryButton.visibility = View.GONE
            binding.progressBar.visibility= View.VISIBLE
            binding.pageDetails.visibility= View.GONE
        } else if(it.refresh is LoadState.NotLoading){
            binding.retryButton.visibility = View.GONE
            binding.progressBar.visibility= View.GONE
            binding.pageDetails.visibility= View.VISIBLE
        }
    }
    binding.recycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)
    vm.movieReviewsData.observe(this){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    vm.movieDetailsData.observe(this){ responseApp ->
       binding.data = responseApp.data
        binding.genre = responseApp.data?.genres?.joinToString(separator = ","){it.name}
    }

    vm.movieVideoData.observe(this){
        val listener = object:AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoId = it
                youTubePlayer.cueVideo(videoId, 0f)

                val defaultUIController =
                    DefaultPlayerUiController(binding.videoTrailer, youTubePlayer)
                binding.videoTrailer.setCustomPlayerUi(defaultUIController.rootView)
            }
        }
        val option = IFramePlayerOptions.Builder().controls(0).build()
        binding.videoTrailer.initialize(listener,option)
    }
}