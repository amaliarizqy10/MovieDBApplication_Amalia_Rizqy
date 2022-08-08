package amalia.indocyber.moviedbapplication.fragment.movie.movie_details

import amalia.indocyber.common.entity.movie.movie_list.Movie
import amalia.indocyber.common.entity.movie.movie_review.Review
import amalia.indocyber.moviedbapplication.databinding.LayoutMovieListItemBinding
import amalia.indocyber.moviedbapplication.databinding.LayoutMovieReviewItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MovieReviewPagingAdapter :
    PagingDataAdapter<Review, MovieReviewViewHolder>(
    itemCallback) {
    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            LayoutMovieReviewItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    companion object{
        val itemCallback = object: DiffUtil.ItemCallback<Review>(){
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return true
            }

        }
    }
}

class MovieReviewViewHolder(val binding: LayoutMovieReviewItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(review: Review?){
        binding.data = review
    }
}