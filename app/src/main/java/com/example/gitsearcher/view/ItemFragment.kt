package com.example.gitsearcher.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.gitsearcher.R
import com.example.gitsearcher.databinding.FragmentItemBinding
import com.example.gitsearcher.databinding.FragmentListBinding
import com.example.gitsearcher.util.LastUpdateCalculater

class ItemFragment : Fragment(R.layout.fragment_item){

    private val args: ItemFragmentArgs by navArgs()

    private lateinit var binding: FragmentItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemBinding.bind(view)


        var repOwner : TextView? = activity?.findViewById(R.id.item_owner_name)
        var repName : TextView? = activity?.findViewById(R.id.item_repository_name)
        var repOwnerImage : ImageView? = activity?.findViewById(R.id.item_owner_image)

        var repNameText = args.gitRepositoryArg.name
        if(repNameText!!.length > 13){
            var stringBuilder = StringBuilder()
            var firstLine = repNameText.subSequence(0,13)
            var secondLine = repNameText.subSequence(14,repNameText.length)
            stringBuilder.append(firstLine).append("\n").append(secondLine)
            repNameText = stringBuilder.toString()
        }

        //Popunjavanje podataka podataka zaglavlja
        repName?.text = repNameText
        repOwner?.text = args.gitRepositoryArg.owner?.login
        Glide.with(requireActivity()).load(args.gitRepositoryArg.owner?.avatarUrl).into(repOwnerImage!!)



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