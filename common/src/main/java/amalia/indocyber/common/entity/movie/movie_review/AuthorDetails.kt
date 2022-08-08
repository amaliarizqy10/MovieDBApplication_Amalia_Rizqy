package amalia.indocyber.common.entity.movie.movie_review


import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Any,
    @SerializedName("username")
    val username: String
)