package amalia.indocyber.common.entity.movie.movie_video


import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val video: List<MovieVideo>
)