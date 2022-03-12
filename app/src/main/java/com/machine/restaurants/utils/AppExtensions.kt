package com.machine.restaurants.utils
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.machine.restaurants.R
import java.io.IOException

fun ImageView.loadProfile(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder_user)
        .apply(RequestOptions.circleCropTransform())
        .circleCrop()
        .into(this)
}
fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}