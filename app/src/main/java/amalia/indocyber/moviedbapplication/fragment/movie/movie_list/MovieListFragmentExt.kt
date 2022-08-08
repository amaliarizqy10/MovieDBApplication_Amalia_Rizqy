package amalia.indocyber.moviedbapplication.fragment.movie.movie_list

import android.view.View
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieListFragment.observeLiveData(){
    vm.loadMovieList(movieListFragmentArgs.genre)
    adapter.addLoadStateListener {
        if(it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.retryButton.visibility = View.VISIBLE
            binding.progressBar.visibility=View.GONE
            binding.rvMovie.visibility=View.GONE
            binding.retryButton.setOnClickListener {
                vm.loadMovieList(movieListFragmentArgs.genre)
            }
        } else if(it.refresh is LoadState.Loading && adapter.itemCount == 0){
            binding.retryButton.visibility = View.GONE
            binding.progressBar.visibility=View.VISIBLE
            binding.rvMovie.visibility=View.GONE
        } else if(it.refresh is LoadState.NotLoading){
            binding.retryButton.visibility = View.GONE
            binding.progressBar.visibility=View.GONE
            binding.rvMovie.visibility=View.VISIBLE
        }
    }
    binding.rvMovie.adapter = adapter.withLoadStateFooter(loadStateAdapter)
    vm.movieData.observe(this){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
}