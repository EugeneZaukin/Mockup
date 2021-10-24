package com.eugene.mockup.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugene.mockup.R
import com.eugene.mockup.view.mainfragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}