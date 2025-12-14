package com.example.mobileapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.databinding.ItemListLayoutBinding
import com.example.mobileapp.model.Item

/**
 * Adapter pour le RecyclerView affichant la liste d'éléments
 * 
 * @property items Liste des éléments à afficher
 * @property onItemClick Callback appelé lors du clic sur un élément
 */
class ItemAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**
     * ViewHolder pour chaque élément de la liste
     * 
     * @property binding ViewBinding de l'item layout
     */
    inner class ItemViewHolder(private val binding: ItemListLayoutBinding) : 
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Lie les données d'un item aux vues
         * 
         * @param item L'élément à afficher
         */
        fun bind(item: Item) {
            // Définir les textes
            binding.textViewTitle.text = item.title
            binding.textViewDescription.text = item.description
            binding.textViewId.text = "ID: ${item.id}"

            // Définir l'image si disponible
            item.imageResId?.let {
                binding.imageViewItem.setImageResource(it)
            }

            // Gérer le clic sur l'élément
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    /**
     * Crée un nouveau ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    /**
     * Lie les données à un ViewHolder existant
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    /**
     * Retourne le nombre total d'éléments
     */
    override fun getItemCount(): Int = items.size
}
