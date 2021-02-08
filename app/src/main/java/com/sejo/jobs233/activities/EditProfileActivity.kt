package com.sejo.jobs233.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sejo.jobs233.databinding.ActivityEditProfileBinding
import com.sejo.jobs233.viewmodels.factories.EditProfileViewModelFactory
import com.sejo.jobs233.viewmodels.profile.EditProfileViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel: EditProfileViewModel
    private lateinit var viewModelFactory: EditProfileViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        setSupportActionBar(binding.toolbar)
        title = "Edit Profile"



        viewModelFactory = EditProfileViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)


        viewModel.userInfo.observe(this, {
            binding.firstNameLayout.editText?.setText(it.first_name)
            binding.lastNameLayout.editText?.setText(it.last_name)
        })

        viewModel.profileInfo.observe(this, {
            binding.titleLayout.editText?.setText(it.title)
            binding.phoneNumberLayout.editText?.setText(it.phone_number)

            when (it.gender) {
                "M" -> binding.editGender.setSelection(0)
                "F" -> binding.editGender.setSelection(1)
                else -> binding.editGender.setSelection(2)
            }

            binding.bioLayout.editText?.setText(it.bio)
            binding.countryLayout.editText?.setText(it.country)
            binding.cityLayout.editText?.setText(it.city)
            binding.addressLayout.editText?.setText(it.address)

            Picasso.get().load(it.picture).into(binding.profilePic)
            Picasso.get().load(it.cover_image).into(binding.coverPic)
        })


        binding.updateBtn.setOnClickListener {
            binding.updateBtn.visibility = View.GONE
            binding.updateProgress.visibility = View.VISIBLE

            val firstName = binding.firstNameLayout.editText?.text.toString()
            val lastName = binding.lastNameLayout.editText?.text.toString()
            val title = binding.titleLayout.editText?.text.toString()
            val phoneNumber = binding.phoneNumberLayout.editText?.text.toString()
            val gender = binding.editGender.selectedItem as String
            val bio = binding.bioLayout.editText?.text.toString()
            val country = binding.countryLayout.editText?.text.toString()
            val city = binding.cityLayout.editText?.text.toString()
            val address = binding.addressLayout.editText?.text.toString()

            lifecycleScope.launch {
                val response = viewModel.updateProfile(
                    firstName,
                    lastName,
                    title,
                    phoneNumber,
                    gender,
                    bio,
                    country,
                    city,
                    address
                )

                if (response) {
                    Toast.makeText(
                        this@EditProfileActivity,
                        "Profile Updated Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.updateBtn.visibility = View.VISIBLE
                    binding.updateProgress.visibility = View.GONE
                } else {
                    binding.updateBtn.visibility = View.VISIBLE
                    binding.updateProgress.visibility = View.GONE
                }

            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}