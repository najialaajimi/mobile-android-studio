package com.example.mobileapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileapp.R
import com.example.mobileapp.databinding.ActivityLoginBinding
import com.example.mobileapp.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar

/**
 * Activité de connexion - Point d'entrée de l'application
 * 
 * Gère l'authentification des utilisateurs avec validation des champs
 * et navigation vers l'écran d'accueil après connexion réussie
 */
class LoginActivity : AppCompatActivity() {

    // ViewBinding pour un accès sécurisé aux vues
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialisation du ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuration du bouton de connexion
        setupLoginButton()
    }

    /**
     * Configure le comportement du bouton de connexion
     */
    private fun setupLoginButton() {
        binding.buttonLogin.setOnClickListener {
            // Récupération des valeurs des champs
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            // Validation des champs
            if (validateInputs(email, password)) {
                // Simulation d'une connexion avec animation
                performLogin(email, password)
            }
        }
    }

    /**
     * Valide les champs de saisie
     * 
     * @param email Email ou nom d'utilisateur saisi
     * @param password Mot de passe saisi
     * @return true si les validations passent, false sinon
     */
    private fun validateInputs(email: String, password: String): Boolean {
        var isValid = true

        // Réinitialiser les erreurs
        binding.textInputLayoutEmail.error = null
        binding.textInputLayoutPassword.error = null

        // Validation de l'email
        when {
            TextUtils.isEmpty(email) -> {
                binding.textInputLayoutEmail.error = getString(R.string.login_error_empty_email)
                isValid = false
            }
            !isValidEmail(email) -> {
                binding.textInputLayoutEmail.error = getString(R.string.login_error_invalid_email)
                isValid = false
            }
        }

        // Validation du mot de passe
        if (TextUtils.isEmpty(password)) {
            binding.textInputLayoutPassword.error = getString(R.string.login_error_empty_password)
            isValid = false
        }

        return isValid
    }

    /**
     * Vérifie si l'email est valide
     * 
     * @param email Email à valider
     * @return true si l'email est valide
     */
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.length >= 3
    }

    /**
     * Effectue la connexion avec animation
     * 
     * @param email Email de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     */
    private fun performLogin(email: String, password: String) {
        // Afficher le loader et désactiver le bouton
        showLoading(true)

        // Simulation d'un délai de connexion (remplacer par un vrai appel API)
        binding.root.postDelayed({
            // Masquer le loader
            showLoading(false)

            // Pour cette démo, toute connexion est acceptée
            // Dans une vraie app, vérifier avec un serveur
            onLoginSuccess(email)
        }, 1500) // Délai de 1.5 secondes pour la démo
    }

    /**
     * Gère le succès de la connexion
     * 
     * @param email Email de l'utilisateur connecté
     */
    private fun onLoginSuccess(email: String) {
        // Afficher un message de succès
        Toast.makeText(
            this,
            getString(R.string.login_success),
            Toast.LENGTH_SHORT
        ).show()

        // Optionnel: Sauvegarder l'état de connexion dans SharedPreferences
        saveLoginState(email)

        // Navigation vers l'écran d'accueil
        navigateToHome()
    }

    /**
     * Sauvegarde l'état de connexion (optionnel)
     * 
     * @param email Email de l'utilisateur
     */
    private fun saveLoginState(email: String) {
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        prefs.edit().apply {
            putBoolean("is_logged_in", true)
            putString("user_email", email)
            apply()
        }
    }

    /**
     * Navigue vers l'écran d'accueil
     */
    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        // FLAG_CLEAR_TASK et FLAG_NEW_TASK pour empêcher le retour à l'écran de connexion
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    /**
     * Affiche ou masque l'indicateur de chargement
     * 
     * @param show true pour afficher, false pour masquer
     */
    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.buttonLogin.isEnabled = false
            binding.buttonLogin.text = ""
        } else {
            binding.progressBar.visibility = View.GONE
            binding.buttonLogin.isEnabled = true
            binding.buttonLogin.text = getString(R.string.login_button)
        }
    }

    /**
     * Gère le bouton retour - empêche de fermer l'app facilement
     */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Afficher un message demandant confirmation
        Snackbar.make(
            binding.root,
            "Appuyez à nouveau pour quitter",
            Snackbar.LENGTH_SHORT
        ).show()

        // Permettre de quitter si appuyé deux fois rapidement
        super.onBackPressed()
    }
}
