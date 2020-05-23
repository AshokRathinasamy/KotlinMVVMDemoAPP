package com.mvvm.csquare.dashboard

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.csquare.data.source.model.DataUserList

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<DataUserList>?){
    items?.let {
        (listView.adapter as UsersAdapter).submitList(items)
    }
}

@BindingAdapter("loadImage")
fun setLoadImage(imageView: ImageView, imgUrl: String ){
    Glide.with(imageView.context).load(imgUrl).into(imageView)
}