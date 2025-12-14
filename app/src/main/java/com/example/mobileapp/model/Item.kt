package com.example.mobileapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Modèle de données représentant un élément de la liste
 * 
 * @property id Identifiant unique de l'élément
 * @property title Titre de l'élément
 * @property description Description détaillée de l'élément
 * @property imageResId ID de la ressource image (optionnel)
 */
@Parcelize
data class Item(
    val id: Int,
    val title: String,
    val description: String,
    val imageResId: Int? = null
) : Parcelable

/**
 * Objet utilitaire pour générer des données de démonstration
 */
object ItemRepository {
    
    /**
     * Génère une liste de 10 éléments de démonstration
     */
    fun getItems(): List<Item> {
        return listOf(
            Item(
                id = 1,
                title = "Smartphone Galaxy X",
                description = "Un smartphone performant avec un écran AMOLED de 6.5 pouces, appareil photo 108MP et batterie longue durée de 5000mAh. Processeur octa-core et 8GB de RAM pour des performances optimales."
            ),
            Item(
                id = 2,
                title = "Ordinateur Portable Pro",
                description = "Ordinateur portable puissant avec processeur Intel i7, 16GB RAM, SSD 512GB. Idéal pour le travail et les créatifs. Écran 15.6 pouces Full HD et design ultra-fin."
            ),
            Item(
                id = 3,
                title = "Casque Audio Sans Fil",
                description = "Casque Bluetooth avec réduction de bruit active, autonomie de 30 heures et son haute qualité. Confortable pour une utilisation prolongée avec coussinets ergonomiques."
            ),
            Item(
                id = 4,
                title = "Montre Connectée Sport",
                description = "Montre connectée avec suivi d'activité, GPS intégré, mesure cardiaque et étanche jusqu'à 50m. Compatible iOS et Android avec notifications intelligentes."
            ),
            Item(
                id = 5,
                title = "Tablette Graphique",
                description = "Tablette pour artistes et designers avec stylet haute précision, surface de travail 10x6 pouces. 8192 niveaux de pression et support multi-touch."
            ),
            Item(
                id = 6,
                title = "Appareil Photo Reflex",
                description = "Appareil photo numérique 24MP avec capteur APS-C, enregistrement vidéo 4K et écran tactile orientable. Parfait pour les photographes passionnés."
            ),
            Item(
                id = 7,
                title = "Console de Jeux Portable",
                description = "Console portable avec écran OLED 7 pouces, stockage extensible et bibliothèque de jeux AAA. Batterie permettant 6 heures de jeu en déplacement."
            ),
            Item(
                id = 8,
                title = "Enceinte Bluetooth Premium",
                description = "Enceinte sans fil avec son 360°, résistante à l'eau IPX7 et autonomie de 24 heures. Basses profondes et aigus cristallins pour une expérience audio immersive."
            ),
            Item(
                id = 9,
                title = "Drone avec Caméra 4K",
                description = "Drone compact avec caméra stabilisée 4K, portée de 2km et temps de vol de 25 minutes. Mode suivi automatique et retour automatique en cas de batterie faible."
            ),
            Item(
                id = 10,
                title = "Clavier Mécanique RGB",
                description = "Clavier gaming mécanique avec switchs Cherry MX, rétroéclairage RGB personnalisable et repose-poignet ergonomique. Anti-ghosting complet et macros programmables."
            )
        )
    }
}
