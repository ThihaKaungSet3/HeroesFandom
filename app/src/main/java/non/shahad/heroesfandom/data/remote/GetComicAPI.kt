package non.shahad.heroesfandom.data.remote

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface GetComicAPI {

    @GET("cat/{universe}/page/{page_}")
    fun getComicsByUniverse(@Path("universe")universe : String, @Path("page_")page_ : Int) : Single<ResponseBody>

    @GET("tag/{name}/page/{page_}")
    fun getComicsByTag(@Path("name")name : String,@Path("page_")page_: Int) : Single<ResponseBody>
}