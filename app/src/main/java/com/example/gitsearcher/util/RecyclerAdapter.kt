package com.example.gitsearcher.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitsearcher.R
import com.example.gitsearcher.model.data.GitRepository


/**
 * Adapter class for RecyclerView.
 *
 * This class implements the adaptation of the provided data to a RecyclerView.
 * It implements the binding of data and onClick listener to RecyclerView items.
 * Inheritance of  RecyclerView.Adapter<>().
 *
 * @param repositoryList list of all obtained repositories.
 * @param onClickListener listener for for every item in list.
 */
class RecyclerAdapter(private var repositoryList: List<GitRepository>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    /***
     * ViewHolder class for items.
     *
     * This class binds and holds references for CardView elements of RecyclerView.
     * TODO - Ask for better way than findViewById.
     */
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val itemImage: ImageView = itemView.findViewById(R.id.image_owner)
            val itemOwner: TextView = itemView.findViewById(R.id.text_view_owner)
            val itemRepository: TextView = itemView.findViewById(R.id.text_view_repository_name)
            val itemTime: TextView = itemView.findViewById(R.id.text_view_last_update)

            fun bind(
                gitRepository: GitRepository,
                onClickListener: OnClickListener
            ){
                itemOwner.text = gitRepository.owner?.login
                itemRepository.text = TextChecker(gitRepository.name)
                itemTime.text = LastUpdateCalculater.CalculateTime(gitRepository.updatedAt)
                Glide.with(itemView.context).load(gitRepository.owner?.avatarUrl).into(itemImage)
                itemView.setOnClickListener {
                    onClickListener.onClick(gitRepository)
                }
            }
        }

    /***
     * Size of text checker.
     *
     * Method implements checking of text length,
     * if larger than 17 char, return first 14 chars and "...".
     *
     * @param text provided text for check.
     * @return text for that can be displayed to the user.
     */
    private fun TextChecker(text: String?): CharSequence? {
        if (text!!.length > 17){
            val stringBuilder = StringBuilder()
            stringBuilder.append(text.subSequence(0,14)).append(". . .")

            return stringBuilder
        }
        return text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.card_view_gitrepository, parent, false)
        return ViewHolder(v)
    }

    /***
     * Bind ViewHolder method.
     *
     * This method binds data and click listener to CardView.
     *
     * @param holder provided ViewHolder.
     * @param position the position of the binding element in list.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = repositoryList[position]
        holder.bind(data, onClickListener)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    class OnClickListener(val clickListener: (gitRepository: GitRepository) -> Unit) {
        fun onClick(gitRepository: GitRepository) = clickListener(gitRepository)
    }
}