package com.machine.restaurants.basket.ui.revieworder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.machine.restaurants.R
import com.machine.restaurants.basket.local.Order
import com.machine.restaurants.databinding.FragmentReviewOrderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewOrderFragment : Fragment(R.layout.fragment_review_order),
    ReviewAdapter.OnItemClickListener {
    val list: MutableList<Order> = ArrayList()
    val firstList: MutableList<Order> = ArrayList()
    private lateinit var reviewAdapter: ReviewAdapter
    private val viewModel: ReviewViewModel by viewModels()
    var rating = ""
    var timing = ""
    var mobile = ""
    var totalCount = 0
    var totalPrice = 0
    var viewmore = 0
    lateinit var binding: FragmentReviewOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReviewOrderBinding.bind(view)
        setUpRecyclerView()

        observeOrders()
        observeOrderItems()
        binding.viewMore.setOnClickListener(View.OnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                reviewAdapter.setAdapter(list!!)
                reviewAdapter.notifyDataSetChanged()
            }
            binding.viewMore.visibility = View.GONE
            viewmore=1
        })
        binding.backButton.setOnClickListener(View.OnClickListener {
            findNavController().navigateUp()
        })
    }


    override fun onAddClick(menuItem: Order?) {
        menuItem!!.food.quantity += 1
        totalCount += 1
        menuItem!!.count += 1
        menuItem!!.total = ((menuItem!!.total).toInt() + (menuItem?.food?.amount).toInt())
        viewModel.addToBasket(menuItem)

    }

    override fun onIncrementClick(menuItem: Order?) {
        menuItem!!.food.quantity += 1
        menuItem!!.count += 1
        menuItem!!.total = ((menuItem!!.total).toInt() + (menuItem?.food?.amount).toInt())
        totalCount += 1
        viewModel.addToBasket(menuItem)
    }

    override fun onDecrementClick(menuItem: Order?) {
        menuItem!!.food.quantity -= 1
        totalCount -= 1
        menuItem!!.count -= 1
        menuItem!!.total = ((menuItem!!.total).toInt() - (menuItem?.food?.amount).toInt())
        viewModel.addToBasket(menuItem)

    }

    private fun setUpRecyclerView() {
        reviewAdapter = ReviewAdapter(this)
        binding.listRest.apply {
            setHasFixedSize(true)
            adapter = reviewAdapter
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

        }
    }

    private fun observeData(response: List<Order>?) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                list.clear()
                for (i in 0 until response!!.size) {
                    if (response[i].food.quantity > 0) {
                        list.add(response[i])
                    } else {

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.invokeOnCompletion {
            viewLifecycleOwner.lifecycleScope.launch {
                if (list.size > 2) {
                    firstList.clear()
                    for (i in 0..1) {
                        firstList.add(list[i])
                    }
                }
            }.invokeOnCompletion {
                viewLifecycleOwner.lifecycleScope.launch {
                    if (list.size > 2 && viewmore == 0) {
                        binding.viewMore.visibility=View.VISIBLE
                        reviewAdapter.setAdapter(firstList!!)
                        reviewAdapter.notifyDataSetChanged()
                    } else {
                        reviewAdapter.setAdapter(list!!)
                        reviewAdapter.notifyDataSetChanged()

                    }
                }
            }
        }

    }

    private fun observeOrderItems() {
        viewModel.totalCount.asLiveData().observe(viewLifecycleOwner) {
            totalCount = it
            if (totalCount==0){
                Toast.makeText(requireContext(),"Your Cart is Empty Add Something", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }
        viewModel.totalPrice.asLiveData().observe(viewLifecycleOwner) {
            totalPrice = it
            binding.restAmount.text = "₹ $totalPrice"
        }
    }

}