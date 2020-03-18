package non.shahad.heroesfandom.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import non.shahad.heroesfandom.data.local.entities.PublisherEntity
import retrofit2.http.GET


interface PublisherAPI {
    @GET("publishers.json")
    fun getAllPublishers() : Observable<List<PublisherEntity>>
}