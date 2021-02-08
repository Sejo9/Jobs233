package com.sejo.jobs233.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.sejo.jobs233.databinding.ActivityProjectBidBinding
import com.sejo.jobs233.viewmodels.factories.ProjectBidViewModelFactory
import com.sejo.jobs233.viewmodels.projects.ProjectBidViewModel

class ProjectBidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectBidBinding

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
        binding = ActivityProjectBidBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.bidToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val args: ProjectBidActivityArgs by navArgs()

        viewModelFactory = ProjectBidViewModelFactory(args.projectBidID)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectBidViewModel::class.java)

        viewModel.project.observe(this, Observer { project ->
            title = project.title

            if (project.subcategory != null) {
                binding.bidCategory.text =
                    project.category.name.plus(" > " + project.subcategory.name)
            } else {
                binding.bidCategory.text = project.category.name
            }
            binding.bidDescription.text = project.description
            binding.bidSkills.text = project.skills
            binding.bidTags.text = project.tags
            binding.bidBidsCount.text = prefixes[0].plus(project.bids_count.toString())
            binding.bidViews.text = prefixes[1].plus(project.bids_count)
            binding.bidBudget.text = project.currency.symbol.plus(project.budget)
            binding.bidClientName.text = project.user.name

            //bid_client_pic.setCircularBitmap(viewModel.bitmap.value!!)

        })

        viewModel.created.observe(this, Observer {
            binding.bidCreatedBy.text = prefixes[2].plus("$it by")
        })

        viewModel.joined.observe(this, Observer {
            binding.bidJoined.text = prefixes[3].plus(it)
        })

    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }
}