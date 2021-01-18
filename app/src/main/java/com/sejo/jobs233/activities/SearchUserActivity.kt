package com.sejo.jobs233.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sejo.jobs233.R
import kotlinx.android.synthetic.main.activity_search_user.*

class SearchUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)

        setSupportActionBar(search_user_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "Find Users"
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }
}