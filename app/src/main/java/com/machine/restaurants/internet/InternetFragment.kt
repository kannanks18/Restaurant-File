package com.machine.restaurants.internet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.machine.restaurants.R
import com.machine.restaurants.databinding.FragmentInternetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InternetFragment : Fragment(R.layout.fragment_internet) {
    lateinit var binding: FragmentInternetBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInternetBinding.bind(view)
    }


}