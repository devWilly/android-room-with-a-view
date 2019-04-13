package com.devwilly.androidroomwordsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.devwilly.androidroomwordsample.db.Word

/**
 * Created by Willy on 2019/4/13.
 */
class WordListAdapter(private val context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mWordList: List<Word> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, p0, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {
        if (mWordList.isNotEmpty()){
            p0.wordItemTextView.text = mWordList[p1].word
        }
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }

    fun setWordList(wordList: List<Word>?) {
        if (wordList.isNullOrEmpty()) return

        mWordList = wordList
        notifyDataSetChanged()
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemTextView = itemView.findViewById<TextView>(R.id.textView)
    }
}

