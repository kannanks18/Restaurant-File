package com.machine.restaurants.basket.ui.home
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.machine.restaurants.R
import com.machine.restaurants.basket.local.MenuList
import com.machine.restaurants.databinding.FragmentHomeBinding
import com.machine.restaurants.basket.ui.home.model.Restaurant
import com.machine.restaurants.basket.ui.home.model.RestaurantResponse
import com.machine.restaurants.utils.getJsonDataFromAsset
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    RestaurantAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var restaurantResponse: RestaurantResponse
    lateinit var restaurant: Restaurant
    private lateinit var restAdapter: RestaurantAdapter
    private val viewModel: HomeViewModel by viewModels()
    var totalCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setUpRecyclerView()
        val jsonFileString = getJsonDataFromAsset(requireContext(), "restaurant.json")
        Log.d("okhttp iii", jsonFileString!!)
        val gson = Gson()

        var restaurantResponse = gson.fromJson(jsonFileString, RestaurantResponse::class.java)
        Log.d("okhttp iii", jsonFileString!!)
        observeData(restaurantResponse)
        observeAdd()

    }

    private fun observeAdd() {

        viewModel.invoke.asLiveData().observe(viewLifecycleOwner) { count ->
            if (count == 0) {
                findNavController().navigate( HomeFragmentDirections.actionHomeFragmentToMenuFragment(restaurant!!, totalCount))
            } else {
            }

        }
        viewModel.totalItems.asLiveData().observe(viewLifecycleOwner) { itemCount ->
            totalCount = itemCount
        }
    }

    private fun observeData(response: RestaurantResponse?) {
        restAdapter.setAdapter(response!!.restaurants)
        restAdapter.notifyDataSetChanged()
    }


    private fun showLoading(show: Boolean) {
        binding.apply {
            homeLayout.isVisible = !show
        }
    }

    override fun onItemClick(res: Restaurant?) {
        if (totalCount>0){
            findNavController().navigate( HomeFragmentDirections.actionHomeFragmentToMenuFragment(res!!, totalCount))
        }else{
        val jsonFileString = getJsonDataFromAsset(requireContext(), "menu.json")
        Log.d("okhttp iii", jsonFileString!!)
        val gson = Gson()
        restaurant = res!!
        var menu = gson.fromJson(jsonFileString, MenuList::class.java)
        Log.d("okhttp iii", jsonFileString!!)
        viewModel.addToMenu(menu!!)}
    }


    private fun setUpRecyclerView() {
        restAdapter = RestaurantAdapter(this)
        binding.listRest.apply {
            setHasFixedSize(true)
            adapter = restAdapter
            layoutManager = LinearLayoutManager(requireContext())
//            empAdapter.filter.filter("")
        }
    }


}