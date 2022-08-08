package amalia.indocyber.moviedbapplication.fragment.movie.movie_details

import amalia.indocyber.common.base.BaseFragment
import amalia.indocyber.moviedbapplication.R
import amalia.indocyber.moviedbapplication.databinding.LayoutMovieDetailsFragmentBinding
import amalia.indocyber.moviedbapplication.view_model.MovieDetailsViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel, LayoutMovieDetailsFragmentBinding>() {
    override val vm: MovieDetailsViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_movie_details_fragment
    val adapter = MovieReviewPagingAdapter()
    val loadStateAdapter = MovieReviewPagingStateAdapter()
    val movieDetailsFragmentArgs:MovieDetailsFragmentArgs by navArgs()

    override fun initBinding(binding: LayoutMovieDetailsFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}