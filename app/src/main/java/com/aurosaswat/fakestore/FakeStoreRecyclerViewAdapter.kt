package com.aurosaswat.fakestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FakeStoreViewHolder(view: View):RecyclerView.ViewHolder(view){
//    1st
    val title=view.findViewById<TextView>(R.id.item_title)
    val price=view.findViewById<TextView>(R.id.item_price)
    val description=view.findViewById<TextView>(R.id.item_Description)
    val category=view.findViewById<TextView>(R.id.item_Category)
    val image=view.findViewById<ImageView>(R.id.item_image)
    val rating=view.findViewById<TextView>(R.id.item_Ratings)

    fun bindView(postModel: PostModel){
        title.text=postModel.title
        price.text=postModel.price.toString()
        description.text=postModel.description.toString()
        category.text=postModel.category.toString()
        Picasso.get().load(postModel.image)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image)
        rating.text=postModel.rating?.rate.toString()
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