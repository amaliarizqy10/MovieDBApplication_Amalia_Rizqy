package amalia.indocyber.moviedbapplication.fragment.genre

import amalia.indocyber.common.entity.base_response.ResponseError
import amalia.indocyber.common.entity.base_response.ResponseLoading
import amalia.indocyber.common.entity.base_response.ResponseSuccess
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy

fun GenreFragment.observeLiveData() {
    binding.rvGenre.adapter = adapter
    vm.selectionTracker = vm.selectionTracker?.let {
        createTracker().apply {
            it.selection.forEach {
                this.select(it)
            }
        }
    } ?: run{
        createTracker()
    }

    vm.genreData.observe(this@observeLiveData){
        when(it){
            is ResponseSuccess -> {
                binding.retryButton.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.rvGenre.visibility = View.VISIBLE
                it.data?.orEmpty()?.let { it -> adapter.submitData(it) }
            }
            is ResponseLoading -> {
                binding.retryButton.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                binding.rvGenre.visibility = View.GONE
            }
            is ResponseError -> {
                binding.retryButton.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.rvGenre.visibility = View.GONE
                binding.retryButton.setOnClickListener{
                    vm.loadAllGenre()
                }
            }
        }
    }
    binding.fabNext.setOnClickListener {
        findNavController().navigate(
            GenreFragmentDirections.fromGenreToMovieList(
                vm.selectionTracker?.selection?.toMutableList().orEmpty().joinToString(",")
            )
        )
    }
}

private fun GenreFragment.createTracker()=
    SelectionTracker.Builder<Long>(
        this::class.java.name,
        binding.rvGenre,
        adapter.getItemKeyProvider(),
        GenreItemDetailsLookUp(binding.rvGenre),
        StorageStrategy.createLongStorage()
    ).withOnItemActivatedListener { a, _ ->
        a.selectionKey?.let {
            vm.selectionTracker?.select(it)
        }
        true
    }.withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()