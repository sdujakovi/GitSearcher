package com.example.gitsearcher.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitsearcher.R
import com.example.gitsearcher.databinding.FragmentListBinding
import com.example.gitsearcher.interfaces.IGitRepositoryView
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
 * @property isLoading value for checking if progress bar has to show up
 * @property totalCount sum of items gained by a retrofit call
 * @property page current page loaded from retrofit call
 */
class ListFragment : androidx.fragment.app.Fragment(R.layout.fragment_list), IGitRepositoryView {

    private var dataGitRepositories: List<GitRepository> = mutableListOf<GitRepository>()
    private var  presenter = GitRepositoryPresenter(this@ListFragment)
    private lateinit var layoutManager : LinearLayoutManager
    private  lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var binding: FragmentListBinding
    private var isLoading = false
    private var totalCount = 0
    private var page = 1

    /***
     * On view created method.
     *
     * Binds the fragment to its layout.
     * Creating layoutManager and adapter for recyclerview
     * If data already exist fill up with existing data.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        var seachText = String()
        var searchView : SearchView? = activity?.findViewById(R.id.search_view)

        recyclerAdapter = RecyclerAdapter(dataGitRepositories, cardViewListener)
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = recyclerAdapter
        binding.recyclerView.layoutManager = layoutManager

        if(!dataGitRepositories.isEmpty()){
            loadData()
        }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                page = 1
                dataGitRepositories = mutableListOf<GitRepository>()
                seachText = query.toString()
                binding.progressBar.visibility = View.VISIBLE
                presenter.getData(searchText = query.toString(), page = page.toString())
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.scrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(
                v: NestedScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight){
                    if(!(totalCount <= 10 * page)){
                        page++
                        binding.progressBar.visibility = View.VISIBLE
                        presenter.getData(searchText = seachText, page = page.toString())
                    }
                }
            }
        })
    }

    //Define onClick event for CardView elements.
    private val cardViewListener = RecyclerAdapter.OnClickListener{
        val action = ListFragmentDirections.actionListFragmentToItemFragment(it)
        findNavController().navigate(action)
    }

    //Method called by presenter component for handover of obtained data.
    override fun updateView(result: Any, count: Int) {
        if(dataGitRepositories.isEmpty()){
            dataGitRepositories = result as List<GitRepository>
        }else{
            dataGitRepositories += result as List<GitRepository>
            isLoading = false
        }
        totalCount = count
        loadData()
        }

    //Metgod called everytime when recyclerview needs to be fild up
    fun loadData(){
        binding.recyclerView.adapter = RecyclerAdapter(dataGitRepositories, cardViewListener)
        binding.progressBar.visibility = View.GONE
    }
}