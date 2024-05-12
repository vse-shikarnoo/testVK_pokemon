package vk.test.pokemon.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import vk.test.pokemon.R
import vk.test.pokemon.databinding.FrPokemonListBinding
import vk.test.pokemon.ui.adapter.PokemonListAdapter
import vk.test.pokemon.utils.PaginationScrollListener
import vk.test.pokemon.utils.autoCleared
import vk.test.pokemon.vm.PokemonViewModel

class PokemonListFragment : Fragment(R.layout.fr_pokemon_list) {

    private val binding: FrPokemonListBinding by viewBinding(FrPokemonListBinding::bind)

    private val viewModel: PokemonViewModel by viewModels()

    private var pokemonListAdapter: PokemonListAdapter by autoCleared()

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observe()

        pagination()

        viewModel.getPokemons()
        binding.retryButton.setOnClickListener {
            viewModel.getPokemons()
        }
    }

    private fun observe() {
        viewModel.pokemonsList.observe(viewLifecycleOwner) {
            Log.d("Test", "observe: getList $it")
            bindState(0)
            pokemonListAdapter.submitList(it)
        }
        viewModel.errorState.observe(viewLifecycleOwner) {
            Log.d("Test", "observe: error $it")
            bindState(1)
        }
        viewModel.loadState.observe(viewLifecycleOwner) {
            Log.d("Test", "observe: loading $it")
            if (it) bindState(2)
        }
    }

    fun initAdapter() {
        pokemonListAdapter = PokemonListAdapter {
            val id = pokemonListAdapter.currentList[it].id
            findNavController().navigate(
                PokemonListFragmentDirections.actionPokemonListFragmentToDetailPokemonFragment(
                    id
                )
            )
        }
        with(binding.list) {
            adapter = pokemonListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    private fun bindState(flag: Int) {
        with(binding) {
            when (flag) {
                0 -> {
                    list.visibility = View.VISIBLE
                    retryButton.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }

                1 -> {
                    list.visibility = View.GONE
                    retryButton.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }

                2 -> {
                    list.visibility = View.GONE
                    retryButton.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun pagination(){
        binding.list.addOnScrollListener(object : PaginationScrollListener(LinearLayoutManager(requireContext())) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                getMoreItems()
            }
        })
    }

    fun getMoreItems() {
        //after fetching your data assuming you have fetched list in your
        // recyclerview adapter assuming your recyclerview adapter is
        //rvAdapter
        isLoading = false

        Log.d("TAG", "getMoreItems: $isLoading")
        viewModel.getPokemons()
    }
}