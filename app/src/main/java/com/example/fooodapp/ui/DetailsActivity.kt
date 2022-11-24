package com.example.fooodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.example.fooodapp.R
import com.example.fooodapp.adapters.PagerAdapter
import com.example.fooodapp.databinding.ActivityDetailsBinding
import com.example.fooodapp.ui.fragments.ingredients.IngredientsFragment
import com.example.fooodapp.ui.fragments.instructions.InstructionsFragment
import com.example.fooodapp.ui.fragments.overview.OverviewFragment
import com.example.fooodapp.ui.fragments.recipes.RecipesFragmentDirections
import com.example.fooodapp.util.Constants

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val args by navArgs<DetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragment = ArrayList<Fragment>()
        fragment.add(OverviewFragment())
        fragment.add(IngredientsFragment())
        fragment.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(Constants.RECIPE_RESULT_KEY, args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragment,
            titles,
            supportFragmentManager
        )
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}