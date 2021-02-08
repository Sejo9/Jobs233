package com.sejo.jobs233.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sejo.jobs233.databinding.ActivityMessagingBinding

class MessagingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessagingBinding
    lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.messagingToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "Recipient"
    }

}