package com.devwilly.androidroomwordsample.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.devwilly.androidroomwordsample.db.Word
import com.devwilly.androidroomwordsample.db.WordDao
import com.devwilly.androidroomwordsample.db.WordRoomDatabase

/**
 * Created by Willy on 2019/4/13.
 */
class WordRepository internal constructor(application: Application){

    private var wordDao: WordDao
    private var allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application.applicationContext)
        wordDao = db.wordDao()
        allWords = wordDao.getAllWords()
    }

    fun insert(word: Word) {
        InsertAsyncTask(wordDao).execute(word)
    }

    fun getAllWords(): LiveData<List<Word>> {
        return allWords
    }

    private class InsertAsyncTask internal constructor(
        private val asyncWordDao: WordDao) : AsyncTask<Word, Unit, Unit>() {

        override fun doInBackground(vararg params: Word) {
            asyncWordDao.insert(params[0])
        }

    }
}