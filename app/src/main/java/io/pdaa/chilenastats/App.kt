package io.pdaa.chilenastats

import android.app.Application
import androidx.room.Room
import io.pdaa.chilenastats.data.datasources.database.FootballDatabase

class App: Application() {
    lateinit var db: FootballDatabase


    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, FootballDatabase::class.java, "football.db").build()
    }
}