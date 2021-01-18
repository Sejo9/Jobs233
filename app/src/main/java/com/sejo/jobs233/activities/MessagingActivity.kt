package com.sejo.jobs233.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sejo.jobs233.R
import kotlinx.android.synthetic.main.activity_messaging.*

class MessagingActivity : AppCompatActivity() {

    lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messaging)

        setSupportActionBar(messaging_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "Recipient"
    }

}