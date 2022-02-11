package com.example.gitsearcher.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.gitsearcher.R
import com.example.gitsearcher.databinding.FragmentItemBinding
import com.example.gitsearcher.util.LastUpdateCalculater

/**
 * Concrete item displaying class.
 *
 * This class binds the fragment_item to its logic, which is
 * showing some details of the clicked element in the previous fragment.
 *
 * @property args provided safeArgs from the previous fragment.
 */
class ItemFragment : Fragment(R.layout.fragment_item){

    private val args: ItemFragmentArgs by navArgs()

    private lateinit var binding: FragmentItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemBinding.bind(view)

        var repOwner : TextView? = activity?.findViewById(R.id.item_owner_name)
        var repName : TextView? = activity?.findViewById(R.id.item_repository_name)
        var repOwnerImage : ImageView? = activity?.findViewById(R.id.item_owner_image)

        /***
         * Filling up data in toolbar
         * If the text of GitRepository name is larger than 13,
         * display in 2 lines
         */
        var repNameText = args.gitRepositoryArg.name
        if(repNameText!!.length > 13){
            var stringBuilder = StringBuilder()
            var firstLine = repNameText.subSequence(0,13)
            var secondLine = repNameText.subSequence(14,repNameText.length)
            stringBuilder.append(firstLine).append("\n").append(secondLine)
            repNameText = stringBuilder.toString()
        }
        repName?.text = repNameText
        repOwner?.text = args.gitRepositoryArg.owner?.login
        Glide.with(requireActivity()).load(args.gitRepositoryArg.owner?.avatarUrl).into(repOwnerImage!!)

        fillBody()
    }

    /***
     * Fill up body of fragment method
     *
     * Method for filling up the body data.
     * Binds description and last update data
     * to concrete views.
     */
    private fun fillBody(){
        if(args.gitRepositoryArg.description.toString() == "null"){
            binding.textViewDescriptionItem.text = getText(R.string.description_text)
        }else{
            binding.textViewDescriptionItem.text = args.gitRepositoryArg.description.toString()
        }
        binding.textViewUpdateItem.text = LastUpdateCalculater.CalculateTimeExtended(
            args.gitRepositoryArg.updatedAt.toString()
        )
    }
}