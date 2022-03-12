package com.machine.restaurants.basket.ui.revieworder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machine.restaurants.basket.local.Order
import com.machine.restaurants.databinding.LayoutmenusBinding
import com.machine.restaurants.databinding.LayoutreviewBinding
import kotlin.collections.ArrayList

class ReviewAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<ReviewAdapter.ReviewxAdapter>(){
    var dataList: ArrayList<Order> = ArrayList()
    var dataListFiltered: ArrayList<Order> = ArrayList()
    var row_index = -1
    var item = -1

    fun setAdapter(list: List<Order>) {
        dataList = list as ArrayList<Order>
        dataListFiltered = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewxAdapter {
        val binding = LayoutreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewxAdapter(binding)

    }

    override fun onBindViewHolder(holder: ReviewxAdapter, position: Int) {
        holder.bindList(dataListFiltered[position],position)
    }

    override fun getItemCount(): Int {
        return dataListFiltered.size
    }


    inner class ReviewxAdapter(private val binding: LayoutreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindList(menuItem: Order?, position: Int) {

            binding.menuItemName.text = "${menuItem?.food?.name}"
            binding.menuItemsauce.text = "${menuItem?.food?.desc}"
            binding.restAmount.text = "₹ ${menuItem?.food?.amount}"
            if (menuItem!!.food.D.contentEquals("Y")){
                binding.tvD.visibility=View.VISIBLE
            }else{
                binding.tvD.visibility=View.INVISIBLE
            }

            if (menuItem!!.food.N.contentEquals("Y")){
                binding.tvN.visibility=View.VISIBLE
            }else{
                binding.tvN.visibility=View.INVISIBLE
            }
            if (menuItem!!.food.quantity>0){
                binding.addBtn.visibility=View.GONE
                binding.linearLayout.visibility=View.VISIBLE
                binding.countText.text=  "${menuItem?.food?.quantity}"
            }else{
                binding.addBtn.visibility=View.VISIBLE
                binding.linearLayout.visibility=View.GONE
                binding.countText.text=  "${menuItem?.food?.quantity}"
            }

            binding.addBtn.setOnClickListener(View.OnClickListener {
                val n = menuItem.food.quantity
                binding.countText.text = (n+1).toString()
                binding.addBtn.visibility=View.GONE
                binding.linearLayout.visibility=View.VISIBLE
                listener.onAddClick(menuItem)
                row_index=position
            })
            binding.incrementCount.setOnClickListener(View.OnClickListener {
                val n = menuItem.food.quantity
                binding.countText.text = (n+1).toString()
                listener.onIncrementClick(menuItem)
                row_index=position
            })
            binding.decrementCount.setOnClickListener(View.OnClickListener {
                val n = menuItem.food.quantity
                if (n==1){
                    binding.addBtn.visibility=View.VISIBLE
                    binding.linearLayout.visibility=View.GONE
                }
                binding.countText.text = (n-1).toString()
                listener.onDecrementClick(menuItem)
                row_index=position
            })
        }

    }

    interface OnItemClickListener {
        public fun onAddClick(menuItem: Order?)
        public fun onIncrementClick(menuItem: Order?)
        public fun onDecrementClick(menuItem: Order?)
    }
}
