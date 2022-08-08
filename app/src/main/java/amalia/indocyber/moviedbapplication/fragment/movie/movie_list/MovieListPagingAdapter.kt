package amalia.indocyber.moviedbapplication.fragment.movie.movie_list

import amalia.indocyber.common.entity.movie.movie_list.Movie
import amalia.indocyber.moviedbapplication.databinding.LayoutMovieListItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MovieListPagingAdapter(val selectedMovie :(Int) -> Unit):PagingDataAdapter<Movie,MovieListViewHolder>(
    itemCallback) {
    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.binding.data = getItem(position)
        holder.binding.root.setOnClickListener {
            getItem(position)?.let {
                it -> selectedMovie(it.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(LayoutMovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    companion object{
        val itemCallback = object:DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return true
            }

        }
    }
}

class MovieListViewHolder(val binding:LayoutMovieListItemBinding):RecyclerView.ViewHolder(binding.root){

}