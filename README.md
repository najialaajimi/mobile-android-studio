# Mobile Android Studio - Application Android Complète

Application Android mobile développée avec Kotlin et Material Design 3.

## 📱 Fonctionnalités

### 1. Interface de Connexion (LoginActivity)
- Validation des champs email et mot de passe
- Animation de chargement lors de la connexion
- Navigation automatique vers l'écran d'accueil
- Design moderne avec Material Design 3

### 2. Page d'Accueil (HomeActivity)
- Liste de 10 éléments avec RecyclerView
- Adapter personnalisé pour afficher les éléments
- Menu avec options : Déconnexion, À propos, Paramètres
- Navigation vers les détails au clic sur un élément

### 3. Page de Détail (DetailActivity)
- Affichage complet des informations de l'élément
- Navigation depuis la liste via Intent
- Bouton retour dans la toolbar et en bas de page

## 🏗️ Architecture

- **Langage**: Kotlin 1.9.20
- **SDK minimum**: 24 (Android 7.0)
- **SDK cible**: 34
- **Build Tools**: Gradle 8.2 avec Kotlin DSL
- **Design**: Material Design 3

## 📂 Structure du Projet

```
app/src/main/
├── java/com/example/mobileapp/
│   ├── ui/
│   │   ├── login/
│   │   │   └── LoginActivity.kt          # Écran de connexion
│   │   ├── home/
│   │   │   ├── HomeActivity.kt           # Écran d'accueil avec liste
│   │   │   └── ItemAdapter.kt            # Adapter pour RecyclerView
│   │   └── detail/
│   │       └── DetailActivity.kt         # Écran de détails
│   └── model/
│       └── Item.kt                       # Modèle de données (data class)
├── res/
│   ├── layout/
│   │   ├── activity_login.xml            # Layout connexion
│   │   ├── activity_home.xml             # Layout accueil
│   │   ├── activity_detail.xml           # Layout détails
│   │   └── item_list_layout.xml          # Layout élément de liste
│   ├── menu/
│   │   └── main_menu.xml                 # Menu de l'application
│   ├── values/
│   │   ├── strings.xml                   # Textes en français
│   │   ├── colors.xml                    # Palette Material Design 3
│   │   └── themes.xml                    # Thème de l'application
│   └── mipmap-*/                         # Icônes de l'application
└── AndroidManifest.xml                   # Manifest de l'application
```

## 🚀 Compilation et Exécution

### Prérequis
- Android Studio Arctic Fox ou supérieur
- JDK 8 ou supérieur
- Android SDK 34
- Émulateur Android ou appareil physique

### Installation
1. Cloner le dépôt
```bash
git clone https://github.com/najialaajimi/mobile-android-studio.git
cd mobile-android-studio
```

2. Ouvrir le projet dans Android Studio

3. Synchroniser Gradle
```bash
./gradlew sync
```

4. Compiler l'application
```bash
./gradlew assembleDebug
```

5. Lancer sur un émulateur ou appareil
```bash
./gradlew installDebug
```

## 📦 Dépendances

- **AndroidX Core**: 1.12.0
- **AppCompat**: 1.6.1
- **Material Design**: 1.11.0
- **ConstraintLayout**: 2.1.4
- **RecyclerView**: 1.3.2
- **CardView**: 1.0.0
- **Lifecycle (ViewModel & LiveData)**: 2.7.0

## 🎨 Design

L'application utilise Material Design 3 avec :
- Palette de couleurs personnalisée (violet primaire)
- Composants Material (TextField, Button, Card, etc.)
- Animations et transitions fluides
- Support du mode clair (extensible au mode sombre)

## 📝 Données de Démonstration

L'application affiche 10 éléments de démonstration :
1. Smartphone Galaxy X
2. Ordinateur Portable Pro
3. Casque Audio Sans Fil
4. Montre Connectée Sport
5. Tablette Graphique
6. Appareil Photo Reflex
7. Console de Jeux Portable
8. Enceinte Bluetooth Premium
9. Drone avec Caméra 4K
10. Clavier Mécanique RGB

## 🔐 Navigation

- **LoginActivity** → **HomeActivity** : Après connexion réussie
- **HomeActivity** → **DetailActivity** : Clic sur un élément
- **Menu** → **LoginActivity** : Option "Déconnexion"

## ✨ Fonctionnalités Avancées

- Validation des entrées utilisateur
- Sauvegarde de l'état de connexion (SharedPreferences)
- Gestion du cycle de vie Android
- ViewBinding pour un accès sécurisé aux vues
- Parcelable pour le passage de données entre activités

## 📄 Licence

Ce projet est développé dans un cadre éducatif.

## 👨‍💻 Auteur

Développé avec ❤️ en Kotlin