package com.example.radiostattion.di

import android.content.Context
import androidx.room.Room
import com.example.radiostattion.data.network.APIService
import com.example.radiostattion.data.repositories.IRadioStationRepository
import com.example.radiostattion.data.repositories.RadioStationRepository
import com.example.radiostattion.data.room.RadioStationRoomDatabase
import com.example.radiostattion.data.room.favourite.FavoriteDAO
import com.example.radiostattion.data.room.radioStation.RadioStationDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://www.3ds.gr/apptest/"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun RetrofitInstance(): APIService {
        val retrofitService: APIService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
        return retrofitService
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRadio3dsRepository(
        apiService: APIService,
        radioStationDao: RadioStationDAO,
        favoriteStationDao: FavoriteDAO,
        context: Context
    ): IRadioStationRepository {
        return RadioStationRepository(apiService, radioStationDao, favoriteStationDao, context)
    }

    @Provides
    @Singleton
    fun provideRadioStationDatabase(@ApplicationContext context: Context): RadioStationRoomDatabase {
        return Room.databaseBuilder(
            context,
            RadioStationRoomDatabase::class.java,
            "radio_station_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRadioStationDao(database: RadioStationRoomDatabase): RadioStationDAO {
        return database.radioStationDao()
    }

    @Provides
    @Singleton
    fun provideFavouriteDao(database: RadioStationRoomDatabase): FavoriteDAO {
        return database.favoriteStationDao()
    }

}