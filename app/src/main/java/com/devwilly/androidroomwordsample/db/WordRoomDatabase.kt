package com.devwilly.androidroomwordsample.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Willy on 2019/4/13.
 */

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context) : WordRoomDatabase {
            return INSTANCE ?: synchronized(WordRoomDatabase::class) {
                val instatnce = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_table").build()

                INSTANCE = instatnce
                instatnce
            }
        }
    }
}