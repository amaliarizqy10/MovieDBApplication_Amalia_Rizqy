package amalia.indocyber.api_service.paging

import amalia.indocyber.api_service.service.movie.MovieListService
import amalia.indocyber.common.entity.movie.movie_list.Movie
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState

class MovieListDataSource(
    private val movieListService: MovieListService,
    private val  genres :String
):PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return  null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        val prevPage = if(page == 1) null else page-1
        try{
            val data = movieListService.getAllMovie(
                genres, page
            )
            if (data.isSuccessful){
                data.body()?.let {
                    val nextPage = if(it.movie.isEmpty()) null else page + 1
                    return LoadResult.Page(it.movie, prevPage, nextPage)
                } ?: kotlin.run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else{
                return LoadResult.Error(Exception("Error Backend : ${data.code()}"))
            }
        } catch (e:Exception){
            return LoadResult.Error(e)
        }
    }
}

object MovieListPager{
    fun createPager(
        pageSize:Int,
        movieListService:MovieListService,
        genres:String
    ):Pager<Int, Movie> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            MovieListDataSource(movieListService, genres)
        }
    )
}