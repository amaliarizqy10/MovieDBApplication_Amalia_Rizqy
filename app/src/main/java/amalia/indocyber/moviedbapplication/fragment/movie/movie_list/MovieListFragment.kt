package amalia.indocyber.moviedbapplication.fragment.movie.movie_list

import amalia.indocyber.common.base.BaseFragment
import amalia.indocyber.moviedbapplication.R
import amalia.indocyber.moviedbapplication.databinding.LayoutMovieListFragmentBinding
import amalia.indocyber.moviedbapplication.view_model.MovieListViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MovieListViewModel, LayoutMovieListFragmentBinding>() {
    override val vm: MovieListViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_movie_list_fragment
    val adapter = MovieListPagingAdapter(){
        findNavController().navigate(MovieListFragmentDirections.fromMovieListToMovieDetails(it))
    }
    val loadStateAdapter = MovieListPagingStateAdapter()
    val movieListFragmentArgs: MovieListFragmentArgs by navArgs()

    override fun initBinding(binding: LayoutMovieListFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}