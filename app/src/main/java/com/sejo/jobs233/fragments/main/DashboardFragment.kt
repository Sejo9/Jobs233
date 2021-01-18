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
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDashboardInfo()

        /*share_link_btn.setOnClickListener {

            val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                .setText("http://jobs233.com/referal?=selomwise&u23")
                .setType("text/plain")
                .intent
            startActivity(shareIntent)
        }*/
    }

    private fun loadDashboardInfo() {
        dash_name.text = PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
            .getString("fullname", "")

        val profilePicURL =
            "http://192.168.43.161:8000" + PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
                .getString("profile_pic_url", "")

        if (profilePicURL.isNotEmpty()) Picasso.get().load(profilePicURL).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                dash_img.setCircularBitmap(bitmap!!)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }
        })
    }
}