package com.devwilly.androidroomwordsample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import com.devwilly.androidroomwordsample.db.Word
import com.devwilly.androidroomwordsample.repository.WordRepository

/**
 * Created by Willy on 2019/4/13.
 */
class WordViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository = WordRepository(application)
    private val allwords: LiveData<List<Word>> = repository.getAllWords()

    fun insert(word: Word) {
        repository.insert(word)
    }

    fun getAllWords() : LiveData<List<Word>> {
        return allwords
    }
}