package com.devwilly.androidroomwordsample.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

/**
 * Created by Willy on 2019/4/13.
 */

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordRoomDatabase? = null
        private val sRoomDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        fun getDatabase(context: Context) : WordRoomDatabase {
            return INSTANCE ?: synchronized(WordRoomDatabase::class) {
                val instatnce = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_table").addCallback(sRoomDatabaseCallback).build()

                INSTANCE = instatnce
                instatnce
            }
        }
    }

    private class PopulateDbAsync(db: WordRoomDatabase): AsyncTask<Unit, Unit, Unit>() {

        private val mWordDao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Unit?) {
            mWordDao.deleteAll()
            mWordDao.insert(Word("Hello"))
            mWordDao.insert(Word("Word!!"))
        }

    }
}