package amalia.indocyber.api_service.paging

import amalia.indocyber.api_service.service.movie.MovieListService
import amalia.indocyber.api_service.service.movie.MovieReviewsService
import amalia.indocyber.common.entity.movie.movie_list.Movie
import amalia.indocyber.common.entity.movie.movie_review.Review
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState

class MovieReviewsDataSource(
    private val movieReviewsService: MovieReviewsService,
    private val  movieId :Int
):PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return  null
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Review> {
        val page = params.key ?: 1
        val prevPage = if(page == 1) null else page-1
        try{
            val data = movieReviewsService.getMovieReviews(
                movieId, page
            )
            if (data.isSuccessful){
                data.body()?.let {
                    val nextPage = if(it.review.isEmpty()) null else page + 1
                    return PagingSource.LoadResult.Page(it.review, prevPage, nextPage)
                } ?: kotlin.run {
                    return PagingSource.LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else{
                return PagingSource.LoadResult.Error(Exception("Error Backend : ${data.code()}"))
            }
        } catch (e:Exception){
            return PagingSource.LoadResult.Error(e)
        }
    }
}

object MovieReviewPager{
    fun createPager(
        pageSize:Int,
        movieReviewsService: MovieReviewsService,
        movieId:Int
    ): Pager<Int, Review> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            MovieReviewsDataSource(movieReviewsService, movieId)
        }
    )
}