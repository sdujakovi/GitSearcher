package com.example.gitsearcher.view

import android.app.Fragment
import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gitsearcher.R
import com.example.gitsearcher.`interface`.IGitRepositoryView
import com.example.gitsearcher.databinding.FragmentItemBinding
import com.example.gitsearcher.databinding.FragmentListBinding
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.presenter.GitRepositoryPresenter


class ListFragment : androidx.fragment.app.Fragment(R.layout.fragment_list), IGitRepositoryView {

    lateinit var  presenter: GitRepositoryPresenter
    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)


        presenter = GitRepositoryPresenter(this, requireContext(), "sdujak")
        presenter.getData()

        Log.d("kreiran", "kreiran")


        binding.button.setOnClickListener(){
            /*val action = ListFragmentDirections.actionListFragmentToItemFragment()
            findNavController().navigate(action)*/
        }
    }

    override fun updateView(result: Any) {
        val data = result as ArrayList<GitRepository>

    }


}