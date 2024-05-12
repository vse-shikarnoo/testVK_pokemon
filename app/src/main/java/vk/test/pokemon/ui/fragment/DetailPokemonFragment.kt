package vk.test.pokemon.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import vk.test.pokemon.R
import vk.test.pokemon.data.model.PokemonInfo
import vk.test.pokemon.databinding.FrDetailPokemonBinding
import vk.test.pokemon.ui.adapter.AbilitiesListAdapter
import vk.test.pokemon.utils.autoCleared
import vk.test.pokemon.utils.loadUrl
import vk.test.pokemon.vm.DetailPokemonViewModel


class DetailPokemonFragment : Fragment(R.layout.fr_detail_pokemon) {

    private val binding: FrDetailPokemonBinding by viewBinding(FrDetailPokemonBinding::bind)

    private val viewModel: DetailPokemonViewModel by viewModels()

    private val args: DetailPokemonFragmentArgs by navArgs()

    private var abilityListAdapter: AbilitiesListAdapter by autoCleared()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        initAbilityAdapter()

        viewModel.getDetailPokemon(args.id)
    }

    private fun initAbilityAdapter() {
        abilityListAdapter = AbilitiesListAdapter()

        with(binding.rvAbility){
            adapter = abilityListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    private fun observe() {
        viewModel.detailPokemon.observe(viewLifecycleOwner) {
            bindInfo(it)
            viewModel.getAbilitiesInfo(it.abilities)
        }
        viewModel.abilities.observe(viewLifecycleOwner){
            abilityListAdapter.submitList(it)
        }
    }

    private fun bindInfo(pokemonInfo: PokemonInfo) {
        with(binding){
            //val gifImagePath = context!!.getString(R.string.gif_image, args.id)
            val svgImagePath= context!!.getString(R.string.svg_image, args.id)
//            Glide.with(view!!)
//                .load(gifImagePath)
//                .error(context!!.getDrawable(R.drawable.baseline_error_24))
//                .into(gifIv)
            imageView.loadUrl(svgImagePath)
            nameTv.text = pokemonInfo.name.capitalize()
            pokemonInfo.stats.forEach {
                when(it.stat.name){
                    "hp" -> {
                        hpView.imageView.setImageResource(it.stat.img)
                        hpView.statTv.text = it.baseStat.toString()
                    }

                    "attack" -> {
                        attackView.imageView.setImageResource(it.stat.img)
                        attackView.statTv.text = it.baseStat.toString()
                    }

                    "defense" -> {
                        defView.imageView.setImageResource(it.stat.img)
                        defView.statTv.text = it.baseStat.toString()
                    }

                    "speed" -> {
                        speedView.imageView.setImageResource(it.stat.img)
                        speedView.statTv.text = it.baseStat.toString()
                    }
                    else -> {

                    }
                }
            }
        }
    }


}