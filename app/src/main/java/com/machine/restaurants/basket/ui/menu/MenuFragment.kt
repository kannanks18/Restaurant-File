package com.machine.restaurants.basket.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.machine.restaurants.R
import com.machine.restaurants.basket.local.MenuList
import com.machine.restaurants.basket.local.Order
import com.machine.restaurants.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu),
    MenuAdapter.OnItemClickListener {
    private lateinit var menuList: MenuList
    private lateinit var menuAdapter: MenuAdapter
    lateinit var binding: FragmentMenuBinding
    private val args: MenuFragmentArgs by navArgs()
    private val viewModel: MenuViewModel by viewModels()


    var rating =""
    var timing =""
    var mobile =""
    var totalCount = 0
    var totalPrice = 0
    var dataCount = 0
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)
        binding.resName.text = "${args.rest.name}"
        rating = "${args.rest.rating}"
        timing = "${args.rest.operating_hours}"
        mobile = "${args.rest.contact}"
        dataCount = args.dataCount
        binding.desc.text = "$rating | $timing"
        binding.desc1.text = " Reach us at : $mobile"
        setUpRecyclerView()

        if (dataCount>0){
            observeOrderItems()
        }
        observeOrders()
        binding.backButton.setOnClickListener(View.OnClickListener {
            findNavController().navigateUp()
        })
        binding.constraintLayout.setOnClickListener(View.OnClickListener {
            if (totalCount>0) {
                findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToReviewOrderFragment())
            }else{
                Toast.makeText(requireContext(),"Please Add Any Item",Toast.LENGTH_LONG).show()

            }
            })
    }

    private fun observeData(response: List<Order>?) {

        menuAdapter.setAdapter(response!!)
        menuAdapter.notifyDataSetChanged()

    }

    override fun onAddClick(menuItem: Order?) {
        menuItem!!.food.quantity += 1
        totalCount += 1
        menuItem!!.count += 1
        menuItem!!.total= ((menuItem!!.total).toInt() + (menuItem?.food?.amount).toInt())
        binding.itemCount.text= " ($totalCount Items)"
        viewModel.addToBasket(menuItem)

    }

    override fun onIncrementClick(menuItem: Order?) {
        menuItem!!.food.quantity += 1
        menuItem!!.count += 1
        menuItem!!.total= ((menuItem!!.total).toInt() + (menuItem?.food?.amount).toInt())
        totalCount += 1
        binding.itemCount.text= " ($totalCount Items)"
        viewModel.addToBasket(menuItem)
    }

    override fun onDecrementClick(menuItem: Order?) {
        menuItem!!.food.quantity -= 1
        totalCount -= 1
        menuItem!!.count -= 1
        menuItem!!.total= ((menuItem!!.total).toInt() - (menuItem?.food?.amount).toInt())
        binding.itemCount.text= " ($totalCount Items)"
        viewModel.addToBasket(menuItem)

    }
    private fun setUpRecyclerView() {
        menuAdapter = MenuAdapter(this)
        binding.listRest.apply {
            setHasFixedSize(true)
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun observeOrders() {
        viewModel.orderList.asLiveData().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty()) {
//                    basketLayout.isVisible = false
//                    if (!orderPlaced)
//                        emptyBasketLayout.isVisible = true
                } else {
                    observeData(it)
                }
            }

        }}
        private fun observeOrderItems() {
        viewModel.totalCount.asLiveData().observe(viewLifecycleOwner) {
            totalCount = it
            binding.itemCount.text= " ($totalCount Items)"
        }
        viewModel.totalPrice.asLiveData().observe(viewLifecycleOwner) {
            totalPrice = it
        }
    }

}