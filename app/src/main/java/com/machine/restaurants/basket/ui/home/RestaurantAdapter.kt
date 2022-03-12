package com.machine.restaurants.basket.ui.home
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.machine.restaurants.R
import com.machine.restaurants.databinding.LayoutrestaurantsBinding
import com.machine.restaurants.basket.ui.home.model.Restaurant
import kotlin.collections.ArrayList

class RestaurantAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RestaurantAdapter.RestAdapter>(),
    Filterable {
    var dataList: ArrayList<Restaurant> = ArrayList()
    var dataListFiltered: ArrayList<Restaurant> = ArrayList()
    var row_index = -1

    fun setAdapter( list: List<Restaurant> ) {
        dataList = list as ArrayList<Restaurant>
        dataListFiltered = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestAdapter {
        val binding = LayoutrestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestAdapter(binding)

    }

    override fun onBindViewHolder(holder: RestAdapter, position: Int) {
        holder.bindList(dataListFiltered[position],position)
    }

    override fun getItemCount(): Int {
        return dataListFiltered.size
    }


    inner class RestAdapter(private val binding: LayoutrestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindList(restaurant: Restaurant?, position: Int) {
            binding.restName.text = "${restaurant?.name}"

            if (restaurant?.id==1){
                binding.restImage.setImageDrawable(binding.restImage.context.resources.getDrawable(R.drawable.one))
            }else if (restaurant?.id==2){
                binding.restImage.setImageDrawable(binding.restImage.context.resources.getDrawable(R.drawable.two))
            }else if (restaurant?.id==3){
                binding.restImage.setImageDrawable(binding.restImage.context.resources.getDrawable(R.drawable.three))
            }

            binding.restCard.setOnClickListener(View.OnClickListener {
                listener.onItemClick(restaurant)
                row_index=position
            })
        }

    }

    interface OnItemClickListener {
        public fun onItemClick(restaurant: Restaurant?)
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) dataListFiltered = dataList else {
                    val filteredList = ArrayList<Restaurant>()
                    dataList
                        .filter {
                            (it.name!!.contains(constraint!!))

                        }
                        .forEach { filteredList.add(it) }
                    dataListFiltered = filteredList

                }
                return FilterResults().apply { values = dataListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                dataListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Restaurant>
                notifyDataSetChanged()
            }
        }
    }
//     Search Filter
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence): FilterResults? {
//                val charString = charSequence.toString()
//                if (charString.isEmpty()) {
//                    datumList = datumList as ArrayList<Employee>
//                } else {
//                    val filteredList: MutableList<Employee> = ArrayList()
//                    for (employee in datumList) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or email match
//                        val name = employee.name ?: ""
//                        val email = employee.email ?: ""
//                        if (name.toLowerCase(Locale.getDefault()).contains(charString.toLowerCase(
//                                Locale.getDefault()))
//                            || email.toLowerCase(Locale.getDefault()).contains(charSequence)) {
//                            filteredList.add(employee)
//                        }
//                    }
//                    datumList = filteredList as ArrayList<Employee>
//                }
//                val filterResults = FilterResults()
//                filterResults.values = datumList
//                return filterResults
//            }
//
//            override fun publishResults(charSequence: CharSequence?, results: FilterResults) {
//                datumList = if (results?.values == null)
//                    ArrayList()
//                else
//                    results.values as ArrayList<Employee>
//                notifyDataSetChanged()
////                if ((filterResults.values as ArrayList<Employee>).size!=null || (filterResults.values as ArrayList<Employee>).size>0) {
////                    datumList = (filterResults.values as? ArrayList<Employee>)!!
////                    notifyDataSetChanged()
////                }
//
//            }
//        }
//    }
}
