package vk.test.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vk.test.pokemon.R
import vk.test.pokemon.data.model.Pokemon
import vk.test.pokemon.databinding.ItemPokemonListBinding

class PokemonListAdapter(
    private val onItemClick: (position: Int) -> Unit
) : ListAdapter<Pokemon, PokemonListAdapter.Holder>(PokemonDiffUtilCallback()) {

    class PokemonDiffUtilCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemPokemonListBinding,
        val onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(pokemon: Pokemon) {
            val imagePath = itemView.context.getString(R.string.gif_image, pokemon.id)
            Glide.with(itemView)
                .load(imagePath)
                .error(itemView.context.getDrawable(R.drawable.baseline_error_24))
                .into(binding.imageView)
            binding.nameTextView.text = pokemon.name.capitalize()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
