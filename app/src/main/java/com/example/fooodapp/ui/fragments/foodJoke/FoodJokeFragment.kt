package com.example.fooodapp.ui.fragments.foodJoke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fooodapp.R
import com.example.fooodapp.databinding.FragmentFoodJokeBinding


class FoodJokeFragment : Fragment() {

    private var _binding: FragmentFoodJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFoodJokeBinding.inflate(inflater, container, false)

        return binding.root
    }

}