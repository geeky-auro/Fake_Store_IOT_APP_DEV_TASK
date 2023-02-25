package com.aurosaswat.fakestore

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class FakeStoreViewHolder(view: View):RecyclerView.ViewHolder(view){
//    1st

    val title=view.findViewById<TextView>(R.id.item_title)
    val price=view.findViewById<TextView>(R.id.item_price)
    val description=view.findViewById<TextView>(R.id.item_Description)
    val category=view.findViewById<TextView>(R.id.item_Category)
    val image=view.findViewById<ImageView>(R.id.item_image)
    val rating=view.findViewById<TextView>(R.id.item_Ratings)


    fun bindView(postModel: PostModel){

        title.text=title.text.toString()+postModel.title
        price.text=price.text.toString()+postModel.price.toString()
        description.text=description.text.toString()+postModel.description.toString()
        category.text=category.text.toString()+postModel.category.toString()
        Picasso.get().load(postModel.image)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image,object :Callback{
                override fun onSuccess() {
                    Log.d(".FakeStoreRecycler","Success")
                }
                override fun onError(e: Exception?) {
                    val imageLink="https://plus.unsplash.com/premium_photo-1670462145715-c32d0c91e81b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8c2hvcHBpbmclMjBjYXJ0fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"
                    Picasso.get().load(imageLink).into(image)
                }
            })
        rating.text=rating.text.toString()+postModel.rating?.rate.toString()
    }
}

class FakeStoreRecyclerViewAdapter(private var postModel: MutableList<PostModel>):RecyclerView.Adapter<FakeStoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FakeStoreViewHolder {
        val  view =LayoutInflater.from(parent.context).inflate(R.layout.fake_item,parent,false)
        return FakeStoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: FakeStoreViewHolder, position: Int) {
        return holder.bindView(postModel[position])
    }

    override fun getItemCount(): Int {
        return postModel.size
    }


}