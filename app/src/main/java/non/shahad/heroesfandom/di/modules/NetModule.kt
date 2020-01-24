package non.shahad.heroesfandom.di.modules

import dagger.Module
import dagger.Provides
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.remote.GetComicAPI
import non.shahad.heroesfandom.data.remote.MoviesAPI
import non.shahad.heroesfandom.data.remote.RequestInterceptor
import non.shahad.heroesfandom.data.remote.SuperHeroAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient().newBuilder()

    @Singleton
    @Provides
    fun provideHeroesRetrofit() : Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Singleton
    @Provides
    fun provideHeroesAPI(retrofit: Retrofit.Builder,okHttpClientBuilder: OkHttpClient.Builder) : SuperHeroAPI =
        retrofit.baseUrl(Constants.NetworkService.SUPERHEROAPI_URL)
            .client(okHttpClientBuilder.build())
            .build()
            .create(SuperHeroAPI::class.java)

    @Singleton
    @Provides
    fun provideMoviesAPI(retrofit: Retrofit.Builder,okHttpClientBuilder : OkHttpClient.Builder) : MoviesAPI =
        retrofit.baseUrl(Constants.NetworkService.MOVIESAPI_URL)
            .client(okHttpClientBuilder.addInterceptor(RequestInterceptor()).build())
            .build()
            .create(MoviesAPI::class.java)

    @Singleton
    @Provides
    fun provideGetComicAPI(retrofit: Retrofit.Builder,okHttpClientBuilder: OkHttpClient.Builder) : GetComicAPI =
        retrofit.baseUrl(Constants.NetworkService.GETCOMIC_URL)
            .client(okHttpClientBuilder.build())
            .build()
            .create(GetComicAPI::class.java)

}