package com.example.gitsearcher.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gitsearcher.R
import com.example.gitsearcher.model.data.GitRepository

class RecyclerAdapter(private var repositoryList: List<GitRepository>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val itemImage: ImageView = itemView.findViewById(R.id.image_owner)
            val itemOwner: TextView = itemView.findViewById(R.id.text_view_owner)
            val itemRepository: TextView = itemView.findViewById(R.id.text_view_repository_name)

            init {
                itemView.setOnClickListener { v: View ->
                    val position: Int = absoluteAdapterPosition
                    Toast.makeText(itemView.context, "klik", Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.card_view_gitrepository, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemOwner.text = repositoryList[position].owner?.login
        holder.itemRepository.text = repositoryList[position].name
        holder.itemImage.setImageResource(R.drawable.ic_launcher_foreground)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }
}