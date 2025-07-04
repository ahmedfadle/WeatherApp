package com.vodafonetask.app.di

import android.content.Context
import androidx.room.Room
import com.vodafonetask.data.Repository.CityRepositoryImp
import com.vodafonetask.data.Repository.WeatherRepositoryImp
import com.vodafonetask.data.local.CityDao
import com.vodafonetask.data.local.CityDataBase
import com.vodafonetask.data.local.LocalSourceImp
import com.vodafonetask.data.local.LocalSourceInterface
import com.vodafonetask.data.remote.RemoteSourceImp
import com.vodafonetask.data.remote.RemoteSourceInterface
import com.vodafonetask.data.remote.Services
import com.vodafonetask.domain.repository.CityRepositoryInterface
import com.vodafonetask.domain.repository.WeatherRepositoryInterface
import com.vodafonetask.domain.usecase.GetAllCitiesUseCase
import com.vodafonetask.domain.usecase.GetWeatherUseCase
import com.vodafonetask.domain.usecase.InsertCityUseCase
import com.vodafonetask.domain.usecase.SearchForCityByNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/3.0/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Services =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(Services::class.java)

    @Provides
    @Singleton
    fun provideCityDatabase(@ApplicationContext context: Context): CityDataBase =
        Room.databaseBuilder(context, CityDataBase::class.java, "Database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCityDao(database: CityDataBase): CityDao = database.getCityDao()

    @Provides
    @Singleton
    fun provideLocalSource(cityDao: CityDao): LocalSourceInterface =
        LocalSourceImp(cityDao)

    @Provides
    @Singleton
    fun provideRemoteSource(services: Services): RemoteSourceInterface =
        RemoteSourceImp(services)


    @Provides
    @Singleton
    fun provideWeatherRepository(
        remoteSource: RemoteSourceInterface
    ): WeatherRepositoryInterface =
        WeatherRepositoryImp(remoteSource)

    @Provides
    @Singleton
    fun provideCityRepository(
        localSource: LocalSourceInterface
    ): CityRepositoryInterface =
        CityRepositoryImp(localSource)

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(repository: WeatherRepositoryInterface): GetWeatherUseCase =
        GetWeatherUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchCityUseCase(repository: WeatherRepositoryInterface): SearchForCityByNameUseCase =
        SearchForCityByNameUseCase(repository)

    @Provides
    @Singleton
    fun provideGetAllCitiesUseCase(repository: CityRepositoryInterface): GetAllCitiesUseCase =
        GetAllCitiesUseCase(repository)

    @Provides
    @Singleton
    fun provideInsertCityUseCase(repository: CityRepositoryInterface): InsertCityUseCase =
        InsertCityUseCase(repository)
}
