package com.sejo.jobs233.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.sejo.jobs233.R
import com.sejo.jobs233.viewmodels.factories.ProjectBidViewModelFactory
import com.sejo.jobs233.viewmodels.projects.ProjectBidViewModel
import kotlinx.android.synthetic.main.activity_project_bid.*

class ProjectBidActivity : AppCompatActivity() {

    private lateinit var viewModel: ProjectBidViewModel
    private lateinit var viewModelFactory: ProjectBidViewModelFactory

    private val prefixes = listOf(
        "Bids: ",
        "Views: ",
        "Created ",
        "Joined "
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_bid)

        setSupportActionBar(bid_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val args: ProjectBidActivityArgs by navArgs()

        viewModelFactory = ProjectBidViewModelFactory(args.projectBidID)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectBidViewModel::class.java)

        viewModel.project.observe(this, Observer { project ->
            title = project.title

            if (project.subcategory != null) {
                bid_category.text = project.category.name.plus(" > " + project.subcategory.name)
            } else {
                bid_category.text = project.category.name
            }
            bid_description.text = project.description
            bid_skills.text = project.skills
            bid_tags.text = project.tags
            bid_bids_count.text = prefixes[0].plus(project.bids_count.toString())
            bid_views.text = prefixes[1].plus(project.bids_count)
            bid_budget.text = project.currency.symbol.plus(project.budget)
            bid_client_name.text = project.user.name

            //bid_client_pic.setCircularBitmap(viewModel.bitmap.value!!)

        })

        viewModel.created.observe(this, Observer {
            bid_created_by.text = prefixes[2].plus("$it by")
        })

        viewModel.joined.observe(this, Observer {
            bid_joined.text = prefixes[3].plus(it)
        })

    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }
}