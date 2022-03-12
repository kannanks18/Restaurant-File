package com.machine.restaurants.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.machine.restaurants.basket.local.OrderDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi():Moshi = Moshi.Builder().build()



    @Provides
    @Singleton
    fun provideOrderDatabase(
        app: Application
    ) = Room.databaseBuilder(app, OrderDatabase::class.java, "order_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideOrderDao(db: OrderDatabase) = db.orderDao()

    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)


    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope