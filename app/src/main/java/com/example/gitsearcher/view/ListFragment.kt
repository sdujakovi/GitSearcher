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

/**
 * List of items displaying class.
 *
 * This class binds the fragment_list to its logic, which is
 * showing the list of all obtained repositories.
 *
 * @property dataGitRepositories list of all repositories obtained from the api call.
 * @property presenter used for obtaining data.
 */
class ListFragment : androidx.fragment.app.Fragment(R.layout.fragment_list), IGitRepositoryView {

    private var dataGitRepositories: List<GitRepository> = mutableListOf<GitRepository>()
    lateinit var  presenter: GitRepositoryPresenter
    private lateinit var binding: FragmentListBinding

    /***
     * On view created method.
     *
     * Binds the fragment to its layout.
     * Use of findViewById for binding on SearchView.
     * If data already exist fill up with existing data.
     * Instantiation of presenter for retrofit call after submit SearchView.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        var searchView : SearchView? = activity?.findViewById(R.id.search_view)

        if(!dataGitRepositories.isEmpty()){
            fillRecyclerView()
        }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter = GitRepositoryPresenter(this@ListFragment, query.toString())
                presenter.getData()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    //Define onClick event for CardView elements.
    private val cardViewLitener = RecyclerAdapter.OnClickListener{
        val action = ListFragmentDirections.actionListFragmentToItemFragment(it)
        findNavController().navigate(action)
    }

    //Method called by presenter component for handover of obtained data.
    override fun updateView(result: Any) {
        dataGitRepositories = result as List<GitRepository>
        fillRecyclerView()
        }

    //Method for filling up RecyclerView adapter and layout manager.
    private fun fillRecyclerView(){
        binding.recyclerView.adapter = RecyclerAdapter(dataGitRepositories, cardViewLitener)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}