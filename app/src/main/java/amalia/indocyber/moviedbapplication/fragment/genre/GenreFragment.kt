package amalia.indocyber.moviedbapplication.fragment.genre

import amalia.indocyber.common.base.BaseFragment
import amalia.indocyber.moviedbapplication.R
import amalia.indocyber.moviedbapplication.databinding.LayoutGenreFragmentBinding
import amalia.indocyber.moviedbapplication.view_model.GenreViewModel
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, LayoutGenreFragmentBinding>(){
    override val vm: GenreViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_genre_fragment

    val adapter = GenreAdapter(){
        vm.selectionTracker?.isSelected(it) ?: false
    }

    override fun initBinding(binding: LayoutGenreFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}