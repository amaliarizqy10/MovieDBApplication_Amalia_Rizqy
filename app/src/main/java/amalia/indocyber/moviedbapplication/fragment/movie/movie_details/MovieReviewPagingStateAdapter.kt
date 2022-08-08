package amalia.indocyber.moviedbapplication.fragment.movie.movie_details

import amalia.indocyber.moviedbapplication.databinding.LayoutMovieListStateBinding
import amalia.indocyber.moviedbapplication.databinding.LayoutMovieReviewStateBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class MovieReviewPagingStateAdapter : LoadStateAdapter<MovieReviewStateViewHolder>() {
    override fun onBindViewHolder(holder: MovieReviewStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieReviewStateViewHolder =
        MovieReviewStateViewHolder(
            LayoutMovieReviewStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            this.bind(loadState)
        }
}

class MovieReviewStateViewHolder(
    private val binding: LayoutMovieReviewStateBinding
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