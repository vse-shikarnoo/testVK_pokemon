package vk.test.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vk.test.pokemon.data.model.AbilityInfo
import vk.test.pokemon.databinding.ItemAbilityListBinding

class AbilitiesListAdapter :
    ListAdapter<AbilityInfo, AbilitiesListAdapter.Holder>(AbilityDiffUtilCallback()) {

    class AbilityDiffUtilCallback : DiffUtil.ItemCallback<AbilityInfo>() {
        override fun areItemsTheSame(oldItem: AbilityInfo, newItem: AbilityInfo): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: AbilityInfo,
            newItem: AbilityInfo
        ): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val binding: ItemAbilityListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(abilityInfo: AbilityInfo) {
            with(binding) {
                nameTv.text = abilityInfo.name.capitalize()
                shortEffectTv.text = abilityInfo.effectEntries[1].shortEffect
                effectTv.text = abilityInfo.effectEntries[1].effect
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemAbilityListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}