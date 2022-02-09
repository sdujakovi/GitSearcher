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
     * Definiranje događaja na klik elementa RecylcerRiewa (CardView)
     */
    private val cardViewLitener = RecyclerAdapter.OnClickListener{
        val action = ListFragmentDirections.actionListFragmentToItemFragment(it)
        findNavController().navigate(action)
    }

    /***
     * Bindanje na layout fragmenta.
     * Koristi se findViewById za dohvat searchViewa(provjerit postoji li bolji način).
     * Ako već postoje podaci, popunjava se sa postojećim.
     * Instanciranje Presentera za poziv retrofita nakon submitanja unosa.
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
     * Funkcija koji poziva Presenter kako bi predao View-u dohvaćene podatke.
     */
    override fun updateView(result: Any) {
        dataGitRepositorys = result as List<GitRepository>
        fillRecyclerView()
        }

    /***
     * Prikaz podataka kao lista.
     * Funkcija za popunjavanje adaptera i layout managera recyclerviewa.
     */
    private fun fillRecyclerView(){
        binding.recyclerView.adapter = RecyclerAdapter(dataGitRepositorys, cardViewLitener)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}