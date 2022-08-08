package amalia.indocyber.moviedbapplication.fragment.movie.movie_list

import amalia.indocyber.moviedbapplication.databinding.LayoutMovieListStateBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class MovieListPagingStateAdapter() : LoadStateAdapter<MovieListStateViewHolder>() {
    override fun onBindViewHolder(holder: MovieListStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieListStateViewHolder =
        MovieListStateViewHolder(
            LayoutMovieListStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            this.bind(loadState)
        }
    }

class MovieListStateViewHolder(
    private val binding: LayoutMovieListStateBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(loadState: LoadState){
        when(loadState){
            is LoadState.Error ->{
                binding.errorContainer.visibility = View.VISIBLE
                binding.loadingContainer.visibility = View.GONE
            }
            is LoadState.Loading ->{
                binding.errorContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
            }
            is LoadState.NotLoading ->{
                binding.errorContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.GONE
            }
        }
    }
}