package com.sejo.jobs233.fragments.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sejo.jobs233.R
import com.sejo.jobs233.databinding.FragmentDashboardBinding
import com.sejo.jobs233.network.BASE_SITE_URL
import com.sejo.jobs233.viewmodels.factories.DashboardViewModelFactory
import com.sejo.jobs233.viewmodels.main.DashboardViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DashboardViewModel
    private lateinit var viewModelFactory: DashboardViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        viewModelFactory = DashboardViewModelFactory(activity?.applicationContext!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            binding.dashName.text = it.name
            binding.dashUsername.text = it.username
            binding.dashEmail.text = it.email
            binding.dashAssigned.text = it.my_assigned_projects_count.toString()
            binding.dashCompleted.text = it.completed_projects_count.toString()
        })

        viewModel.currencyInfo.observe(viewLifecycleOwner, Observer {
            binding.dashBalance.text = it.symbol
            binding.dashEarnings.text = it.symbol
        })

        viewModel.walletInfo.observe(viewLifecycleOwner, Observer {
            val symbol = binding.dashBalance.text.toString()
            binding.dashBalance.text = symbol.plus(it.balance)
        })

        viewModel.profileInfo.observe(viewLifecycleOwner, Observer { profEntity ->
            Picasso.get().load(BASE_SITE_URL + profEntity.picture).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    binding.dashImg.setCircularBitmap(bitmap!!)
                }

                override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {}

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            })

            binding.profileProgress.max = 7

            viewModel.calculateProfilePercentage().let {
                if (it == 7) {
                    binding.dashProfileComplete.visibility = View.GONE
                } else {
                    binding.profileProgress.progress = it
                }
            }
        })

        binding.finishProfileBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.editProfileActivity))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}