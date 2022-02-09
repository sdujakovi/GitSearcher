package com.example.gitsearcher.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitsearcher.R
import com.example.gitsearcher.interfaces.IGitRepositoryView
import com.example.gitsearcher.databinding.FragmentListBinding
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.presenter.GitRepositoryPresenter
import com.example.gitsearcher.util.RecyclerAdapter


class ListFragment : androidx.fragment.app.Fragment(R.layout.fragment_list), IGitRepositoryView {

    lateinit var  presenter: GitRepositoryPresenter
    private lateinit var binding: FragmentListBinding

    private val cardViewLitener = RecyclerAdapter.OnClickListener{
        val action = ListFragmentDirections.actionListFragmentToItemFragment()
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        var searchView : SearchView? = activity?.findViewById(R.id.search_view)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter = GitRepositoryPresenter(this@ListFragment, requireContext(), query.toString())
                presenter.getData()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun updateView(result: Any) {
        binding.recyclerView.adapter = RecyclerAdapter(result as List<GitRepository>, cardViewLitener)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}