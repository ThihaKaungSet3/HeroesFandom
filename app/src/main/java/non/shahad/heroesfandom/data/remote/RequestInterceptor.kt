package non.shahad.heroesfandom.data.remote

import non.shahad.heroesfandom.core.Constants
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", Constants.NetworkService.API_KEY)
            .addQueryParameter("with_genres",Constants.NetworkService.GENRE.toString())
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)

    }
}