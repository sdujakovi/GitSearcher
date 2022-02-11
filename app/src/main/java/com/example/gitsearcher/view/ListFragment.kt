package com.example.gitsearcher.view

import android.os.Bundle
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

    private var dataGitRepositorys: List<GitRepository> = mutableListOf<GitRepository>()
    lateinit var  presenter: GitRepositoryPresenter
    private lateinit var binding: FragmentListBinding

    /***
     * Defining onClick event for CardView elements.
     */
    private val cardViewLitener = RecyclerAdapter.OnClickListener{
        val action = ListFragmentDirections.actionListFragmentToItemFragment(it)
        findNavController().navigate(action)
    }

    /***
     * Binding to fragment layout.
     * Use of findViewById for binding on SearchView.
     * If data already exist fill up with existing data.
     * Instantiation of presenter for retrofit call after submit SearchView.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        var searchView : SearchView? = activity?.findViewById(R.id.search_view)

        if(!dataGitRepositorys.isEmpty()){
            fillRecyclerView()
        }

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

    /***
     * Method called by presenter component for handover of obtained data.
     */
    override fun updateView(result: Any) {
        dataGitRepositorys = result as List<GitRepository>
        fillRecyclerView()
        }

    /***
     * Method for filling up RecyclerView adapter and layout manager.
     */
    private fun fillRecyclerView(){
        binding.recyclerView.adapter = RecyclerAdapter(dataGitRepositorys, cardViewLitener)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}