package com.devwilly.androidroomwordsample.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by Willy on 2019/4/12.
 */

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("DELETE from word_table")
    fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
//    @Query("SELECT * from word_table ORDER BY word DESC")
    fun getAllWords()
}