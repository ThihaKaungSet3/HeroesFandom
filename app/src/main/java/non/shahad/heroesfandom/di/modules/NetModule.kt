package non.shahad.heroesfandom.di.modules

import dagger.Module
import dagger.Provides
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.remote.SuperHeroAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun provideHeroesRetrofit() : Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Singleton
    @Provides
    fun provideHeroesAPI(retrofit: Retrofit.Builder) : SuperHeroAPI =
        retrofit.baseUrl(Constants.NetworkService.SUPERHEROAPI_URL)
            .build()
            .create(SuperHeroAPI::class.java)

}