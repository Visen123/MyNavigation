package com.my.navigation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object{
        fun start(mContext:Context){
            val intent = Intent(mContext,DetailActivity::class.java)
            mContext.startActivity(intent)
        }
    }

}
