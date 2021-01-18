package com.sejo.jobs233.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.sejo.jobs233.R
import com.sejo.jobs233.viewmodels.factories.ProjectViewViewModelFactory
import com.sejo.jobs233.viewmodels.projects.ProjectViewViewModel
import kotlinx.android.synthetic.main.activity_project_view.*

private const val ATTACH_REQUEST_CODE = 188

class ProjectViewActivity : AppCompatActivity() {

    private lateinit var viewModel: ProjectViewViewModel
    private lateinit var viewModelFactory: ProjectViewViewModelFactory

    private val prefixes = listOf(
        "Created ",
        "Joined ",
        "Deadline "
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_view)

        setSupportActionBar(view_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val args: ProjectViewActivityArgs by navArgs()

        viewModelFactory = ProjectViewViewModelFactory(args.projectViewID)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectViewViewModel::class.java)

        viewModel.project.observe(this, Observer { project ->
            title = project.title
            if (project.subcategory != null) {
                view_category.text = project.category.name.plus(" > " + project.subcategory.name)
            } else {
                view_category.text = project.category.name
            }
            view_description.text = project.description
            view_skills.text = project.skills
            view_tags.text = project.tags
            view_offer.text = project.currency.symbol.plus(project.budget)
            view_budget.text = project.currency.symbol.plus(project.budget)
            view_client_name.text = project.user.name

            /*Picasso.get().load(BASE_SITE_URL+project.user.profile.picture).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    view_client_pic.setCircularBitmap(bitmap!!)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }
            })*/


        })

        viewModel.created.observe(this, Observer {
            view_created_by.text = prefixes[0].plus("$it by")
        })

        viewModel.joined.observe(this, Observer {
            view_client_joined.text = prefixes[1].plus(it)
        })

        viewModel.deadline.observe(this, Observer {
            view_deadline.text = prefixes[2].plus(it)
        })

        view_attach_btn.setOnClickListener {
            val checkSelfPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
                openFileSelector()
            }

        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFileSelector()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ATTACH_REQUEST_CODE && resultCode == RESULT_OK) {

            Toast.makeText(this, "File ${data?.dataString} Selected", Toast.LENGTH_LONG).show()
        }
    }


    private fun openFileSelector() {
        val intent = Intent("android.intent.action.GET_CONTENT").also {
            it.type = "*/*"
        }
        startActivityForResult(intent, ATTACH_REQUEST_CODE)
    }
}