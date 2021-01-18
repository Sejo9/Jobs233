package com.sejo.jobs233.fragments.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.sejo.jobs233.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()

        loadProfileData()
    }

    private fun loadProfileData() {
        val profilePicURL =
            "http://192.168.43.161:8000" + PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("profile_pic_url", "")
        if (profilePicURL.isNotEmpty()) Picasso.get().load(profilePicURL).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                profile_pic.setCircularBitmap(bitmap!!)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })

        profile_name.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("fullname", "")
        profile_username.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("username", "")
        profile_email.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("email", "")
        profile_phone.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("phone", "")
        profile_gender.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("gender", "")
        profile_bio.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("bio", "")
        profile_country.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("country", "")
        profile_city.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("city", "")
        profile_address.text =
            PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("address", "")
    }

}