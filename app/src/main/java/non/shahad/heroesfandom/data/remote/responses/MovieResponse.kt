package non.shahad.heroesfandom.data.remote.responses

import com.google.gson.annotations.SerializedName
import non.shahad.heroesfandom.data.local.entities.MovieEntity

data class MovieResponse(
    @SerializedName("page")var page: Int,
    @SerializedName("results")val results: List<MovieEntity>,
    @SerializedName("total_pages")val total_pages: Int,
    @SerializedName("total_results")val total_results: Int
)