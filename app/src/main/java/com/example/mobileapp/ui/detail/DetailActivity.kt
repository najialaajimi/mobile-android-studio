package com.example.mobileapp.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileapp.R
import com.example.mobileapp.databinding.ActivityDetailBinding
import com.example.mobileapp.model.Item

/**
 * Activité de détail affichant les informations complètes d'un élément
 * 
 * Reçoit les données de l'élément via Intent et les affiche
 */
class DetailActivity : AppCompatActivity() {

    // ViewBinding pour l'accès aux vues
    private lateinit var binding: ActivityDetailBinding
    
    // L'élément à afficher
    private var item: Item? = null

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialisation du ViewBinding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Récupération de l'élément depuis l'Intent (API 33+)
        item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ITEM, Item::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ITEM)
        }

        // Configuration de la toolbar
        setupToolbar()
        
        // Affichage des données
        displayItemDetails()
        
        // Configuration du bouton retour
        setupBackButton()
        
        // Configuration du gestionnaire de retour moderne
        setupBackPressedHandler()
    }

    /**
     * Configure la toolbar avec le bouton de retour
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        
        // Gérer le clic sur le bouton de navigation
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    /**
     * Affiche les détails de l'élément
     */
    private fun displayItemDetails() {
        item?.let { currentItem ->
            // Afficher le titre
            binding.textViewTitle.text = currentItem.title
            
            // Afficher l'ID
            binding.textViewId.text = currentItem.id.toString()
            
            // Afficher la description
            binding.textViewDescription.text = currentItem.description
            
            // Afficher l'image si disponible
            currentItem.imageResId?.let { imageRes ->
                // Si une ressource image est disponible, on peut l'afficher
                // Pour l'instant, on utilise l'icône par défaut
            }
        } ?: run {
            // Si aucun élément n'est trouvé, afficher un message d'erreur
            binding.textViewTitle.text = getString(R.string.error_title)
            binding.textViewId.text = getString(R.string.error_na)
            binding.textViewDescription.text = getString(R.string.error_no_data)
        }
    }

    /**
     * Configure le bouton de retour
     */
    private fun setupBackButton() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    /**
     * Configure le gestionnaire de retour moderne
     */
    private fun setupBackPressedHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Animation de transition optionnelle
                finish()
            }
        })
    }

    /**
     * Gère le bouton retour dans la toolbar
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
