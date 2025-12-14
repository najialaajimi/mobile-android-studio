package com.example.mobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mobileapp.R
import com.example.mobileapp.databinding.ActivityHomeBinding
import com.example.mobileapp.model.Item
import com.example.mobileapp.model.ItemRepository
import com.example.mobileapp.ui.detail.DetailActivity
import com.example.mobileapp.ui.login.LoginActivity

/**
 * Activité d'accueil affichant la liste des éléments
 * 
 * Utilise un RecyclerView avec un adapter personnalisé pour afficher
 * les éléments et permet la navigation vers les détails
 */
class HomeActivity : AppCompatActivity() {

    // ViewBinding pour l'accès aux vues
    private lateinit var binding: ActivityHomeBinding
    
    // Adapter pour le RecyclerView
    private lateinit var adapter: ItemAdapter
    
    // Liste des éléments
    private val items: List<Item> by lazy {
        ItemRepository.getItems()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialisation du ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuration de la toolbar
        setupToolbar()
        
        // Configuration du RecyclerView
        setupRecyclerView()
    }

    /**
     * Configure la toolbar avec le menu
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.home_title)
        }
    }

    /**
     * Configure le RecyclerView avec l'adapter et le layout manager
     */
    private fun setupRecyclerView() {
        // Vérifier si la liste est vide
        if (items.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.textViewEmpty.visibility = View.VISIBLE
            return
        }

        // Créer l'adapter avec le callback de clic
        adapter = ItemAdapter(items) { item ->
            onItemClick(item)
        }

        // Configuration du RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = this@HomeActivity.adapter
            
            // Ajouter un séparateur entre les éléments
            val divider = DividerItemDecoration(
                this@HomeActivity,
                DividerItemDecoration.VERTICAL
            )
            addItemDecoration(divider)
            
            // Animation
            itemAnimator?.apply {
                addDuration = 300
                removeDuration = 300
            }
        }

        // Afficher le RecyclerView et masquer le message vide
        binding.recyclerView.visibility = View.VISIBLE
        binding.textViewEmpty.visibility = View.GONE
    }

    /**
     * Gère le clic sur un élément de la liste
     * 
     * @param item L'élément cliqué
     */
    private fun onItemClick(item: Item) {
        // Naviguer vers la page de détail en passant l'item via Intent
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ITEM, item)
        }
        startActivity(intent)
    }

    /**
     * Crée le menu dans la toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * Gère les actions du menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                showLogoutDialog()
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            R.id.action_settings -> {
                showSettingsDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Affiche un dialog de confirmation de déconnexion
     */
    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.menu_logout))
            .setMessage(getString(R.string.logout_confirm))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                performLogout()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    /**
     * Effectue la déconnexion
     */
    private fun performLogout() {
        // Effacer l'état de connexion
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        prefs.edit().apply {
            putBoolean("is_logged_in", false)
            remove("user_email")
            apply()
        }

        // Retourner à l'écran de connexion
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    /**
     * Affiche le dialog "À propos"
     */
    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.about_title))
            .setMessage(getString(R.string.about_message))
            .setPositiveButton(getString(R.string.about_ok), null)
            .show()
    }

    /**
     * Affiche le dialog des paramètres (implémentation basique)
     */
    private fun showSettingsDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.menu_settings))
            .setMessage("Fonctionnalité en cours de développement")
            .setPositiveButton(getString(R.string.about_ok), null)
            .show()
    }

    /**
     * Gère le bouton retour - affiche un dialog de confirmation
     */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Quitter l'application")
            .setMessage("Voulez-vous vraiment quitter ?")
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }
}
