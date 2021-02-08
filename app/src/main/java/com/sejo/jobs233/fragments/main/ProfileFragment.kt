package com.sejo.jobs233.fragments.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.sejo.jobs233.R
import com.sejo.jobs233.databinding.FragmentProfileBinding
import com.sejo.jobs233.network.BASE_SITE_URL
import com.sejo.jobs233.network.checkNetworkConnection
import com.sejo.jobs233.viewmodels.factories.ProfileViewModelFactory
import com.sejo.jobs233.viewmodels.profile.ProfileViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel
    private lateinit var viewModelFactory: ProfileViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        viewModelFactory = ProfileViewModelFactory(activity?.applicationContext!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.userInfo.observe(viewLifecycleOwner, {
            binding.profileName.text = it.name
            binding.profileUsername.text = it.username
            binding.profileEmail.text = it.email

        })

        viewModel.profileInfo.observe(viewLifecycleOwner, {
            binding.profilePhone.text = it.phone_number
            binding.profileGender.text = it.gender
            binding.profileBio.text = it.bio
            binding.profileCountry.text = it.country
            binding.profileCity.text = it.city
            binding.profileAddress.text = it.address
            Picasso.get().load(BASE_SITE_URL + it.cover_image).into(binding.profileCover)
            Picasso.get().load(BASE_SITE_URL + it.picture).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    binding.profilePic.setCircularBitmap(bitmap!!)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            })
        })
    }

    override fun onStart() {
        super.onStart()

        if (checkNetworkConnection(activity?.applicationContext!!)) {
            lifecycleScope.launch {
                viewModel.refreshUserInfo()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                lifecycleScope.launch {
                    viewModel.logout()
                }
                true
            }
            R.id.editProfileActivity -> {
                NavigationUI.onNavDestinationSelected(item, view?.findNavController()!!)
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}