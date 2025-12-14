# Guide d'Utilisation de l'Application Mobile

## 📖 Introduction

Cette application mobile Android est une démonstration complète d'une architecture MVVM avec Kotlin et Material Design 3. Elle implémente un flux complet de connexion, affichage de liste, et détails.

## 🔑 Connexion (LoginActivity)

### Validation des Champs

L'application valide les champs suivants :

1. **Email/Nom d'utilisateur** :
   - Ne peut pas être vide
   - Doit être un email valide OU au moins 3 caractères
   - Affiche une erreur en temps réel si invalide

2. **Mot de passe** :
   - Ne peut pas être vide
   - Masqué par défaut avec option de basculement
   - Affiche une erreur si vide

### Connexion Réussie

Pour cette démonstration, **toute combinaison email/mot de passe non vide est acceptée**.

Dans une application réelle, vous devriez :
- Appeler une API backend
- Vérifier les credentials avec un serveur
- Gérer les erreurs réseau
- Implémenter OAuth ou JWT

### Fonctionnalités de l'Écran de Connexion

- **Animation de chargement** : Spinner circulaire pendant la connexion
- **Bouton désactivé** : Pendant le processus de connexion
- **Toast de succès** : Message de confirmation
- **SharedPreferences** : Sauvegarde de l'état de connexion
- **Navigation automatique** : Vers l'écran d'accueil après connexion

## 🏠 Page d'Accueil (HomeActivity)

### RecyclerView

L'écran d'accueil affiche une liste de 10 éléments de démonstration :

- **Affichage** : Cards Material Design avec titre, description, et ID
- **Scroll** : Défilement vertical fluide
- **Séparateurs** : Entre chaque élément
- **Animation** : Au clic sur les éléments

### Navigation

Cliquer sur un élément :
- Ouvre l'écran de détail
- Passe les données via Intent avec Parcelable
- Animation de transition

### Menu

Accessible via l'icône ⋮ en haut à droite :

1. **Paramètres** : Fonctionnalité en développement
2. **À propos** : Informations sur l'application
3. **Déconnexion** :
   - Affiche un dialog de confirmation
   - Efface les SharedPreferences
   - Retourne à l'écran de connexion

### Bouton Retour

Appuyer sur le bouton retour :
- Affiche un dialog de confirmation
- Permet de quitter l'application

## 📄 Page de Détail (DetailActivity)

### Affichage des Données

L'écran de détail affiche :
- **Titre** : Nom complet de l'élément
- **ID** : Identifiant unique
- **Description** : Description complète de l'élément

### Navigation Retour

Deux options pour revenir :
1. **Icône de navigation** : En haut à gauche dans la toolbar
2. **Bouton "Retour"** : En bas de l'écran

## 🎨 Design et Thème

### Palette de Couleurs Material Design 3

- **Primaire** : Violet (#6750A4)
- **Secondaire** : Gris-violet (#625B71)
- **Tertiaire** : Rose (#7D5260)
- **Fond** : Blanc cassé (#FFFBFE)
- **Surface** : Cartes et composants

### Composants Material

- **TextInputLayout** : Champs de texte avec animations
- **MaterialButton** : Boutons avec ripple effect
- **MaterialCard** : Cards pour la liste
- **MaterialToolbar** : Barre d'outils
- **CircularProgressIndicator** : Indicateur de chargement

## 🔧 Architecture et Code

### Pattern MVVM (Prêt à l'emploi)

Bien que l'application actuelle n'utilise pas de ViewModel, elle est structurée pour l'ajouter facilement :

```kotlin
// Exemple de ViewModel pour LoginActivity
class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState
    
    fun login(email: String, password: String) {
        // Logique de connexion
    }
}
```

### ViewBinding

Toutes les activités utilisent ViewBinding pour :
- Accès type-safe aux vues
- Pas de `findViewById()`
- Détection d'erreurs à la compilation

### Parcelable

Le modèle `Item` implémente `Parcelable` avec `@Parcelize` pour :
- Passage efficace entre activités
- Sérialisation automatique
- Performance optimale

## 📱 Tests et Débogage

### Logs

L'application peut être étendue avec des logs :

```kotlin
import android.util.Log

private const val TAG = "LoginActivity"
Log.d(TAG, "User logged in: $email")
```

### SharedPreferences

Vérifier les données sauvegardées :

```kotlin
val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
val isLoggedIn = prefs.getBoolean("is_logged_in", false)
val userEmail = prefs.getString("user_email", "")
```

## 🚀 Extensibilité

### Ajouter Plus d'Éléments

Modifier `ItemRepository.kt` :

```kotlin
fun getItems(): List<Item> {
    return listOf(
        // ... éléments existants
        Item(
            id = 11,
            title = "Nouvel élément",
            description = "Description du nouvel élément"
        )
    )
}
```

### Ajouter une Image

1. Ajouter l'image dans `res/drawable/`
2. Modifier le modèle pour utiliser l'image :

```kotlin
Item(
    id = 1,
    title = "Titre",
    description = "Description",
    imageResId = R.drawable.mon_image
)
```

### Connexion à une API

Ajouter Retrofit dans `build.gradle.kts` :

```kotlin
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
```

Créer un service API :

```kotlin
interface ApiService {
    @POST("login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse
}
```

## 🔐 Sécurité

### Bonnes Pratiques

- ✅ Validation des entrées utilisateur
- ✅ Mot de passe masqué par défaut
- ⚠️ **À faire** : Chiffrer les SharedPreferences
- ⚠️ **À faire** : Utiliser HTTPS pour les API
- ⚠️ **À faire** : Implémenter l'authentification réelle

### Recommandations pour la Production

1. **Chiffrement** : Utiliser EncryptedSharedPreferences
2. **Authentification** : OAuth 2.0 ou JWT
3. **HTTPS** : Forcer les connexions sécurisées
4. **ProGuard** : Activer l'obfuscation du code
5. **Certificat SSL Pinning** : Pour les API critiques

## 📊 Performances

### Optimisations Actuelles

- RecyclerView avec ViewHolder pattern
- ViewBinding (pas de reflection)
- Parcelable (plus rapide que Serializable)
- Material Components optimisés

### Optimisations Possibles

- Pagination de la liste
- Chargement asynchrone des images (Glide/Coil)
- Cache des données
- Préchargement des écrans

## 🐛 Problèmes Connus et Solutions

### BuildConfig Non Trouvé

Si vous rencontrez `BuildConfig not found` :
- Synchroniser Gradle
- Nettoyer le projet : `./gradlew clean`
- Rebuild : `./gradlew build`

### ViewBinding Non Activé

Vérifier dans `build.gradle.kts` :
```kotlin
buildFeatures {
    viewBinding = true
}
```

## 📚 Ressources Supplémentaires

- [Guide Android officiel](https://developer.android.com)
- [Material Design 3](https://m3.material.io)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [AndroidX Documentation](https://developer.android.com/jetpack/androidx)

## 💡 Idées d'Améliorations

1. **Mode Sombre** : Ajouter le support du thème sombre
2. **Base de Données** : Room pour persister les données
3. **Recherche** : Ajouter une barre de recherche dans la liste
4. **Filtres** : Filtrer les éléments par catégorie
5. **Favoris** : Marquer des éléments comme favoris
6. **Partage** : Partager un élément via Intent
7. **Images** : Ajouter des vraies images aux éléments
8. **Animations** : Transitions entre activités
9. **Notifications** : Push notifications
10. **Langue** : Support multilingue (EN/FR)
