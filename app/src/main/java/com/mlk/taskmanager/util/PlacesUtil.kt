package com.mlk.taskmanager.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.mlk.taskmanager.R
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Utilitaire pour l'API Google Places avec fallback sur données locales
 */
object PlacesUtil {
    private lateinit var placesClient: PlacesClient
    private const val TAG = "PlacesUtil"
    
    // Coordonnées par défaut pour Chicoutimi
    val DEFAULT_LOCATION = LatLng(48.4269, -71.0582)
    
    // Données locales pour les cas où l'API échoue - Structure optimisée
    private val localPlaces = listOf(
        // Restaurants
        MockLocation("resto_harden", "Restaurant Harden", "155 Boulevard Saint-Paul, Chicoutimi", 48.428750, -71.052370),
        MockLocation("resto_cage", "La Cage Chicoutimi", "1025 Boulevard du Royaume, Chicoutimi", 48.422680, -71.061390),
        MockLocation("resto_pacini", "Pacini Chicoutimi", "405 Rue Racine E, Chicoutimi", 48.428350, -71.051040),
        MockLocation("resto_mike", "Chez Mike", "235 Rue Racine E, Chicoutimi", 48.427890, -71.052820),
        // Magasins
        MockLocation("mag_canadian", "Canadian Tire", "1257 Boulevard Talbot, Chicoutimi", 48.387330, -71.153820),
        MockLocation("mag_dollarama", "Dollarama", "1324 Boulevard Talbot, Chicoutimi", 48.388120, -71.154210),
        MockLocation("mag_sportX", "Sports Experts", "1401 Boulevard Talbot, Chicoutimi", 48.387580, -71.153680),
        // Cafés
        MockLocation("cafe_starbucks", "Starbucks", "1401 Boulevard Talbot, Chicoutimi", 48.387450, -71.153510),
        MockLocation("cafe_tim", "Tim Hortons", "1850 Boulevard Talbot, Chicoutimi", 48.386829, -71.153954),
        MockLocation("cafe_second", "Second Cup", "1401 Boulevard Talbot, Chicoutimi", 48.387530, -71.153620),
        // Supermarchés
        MockLocation("walmart_bt1", "Walmart Boul Talbot", "2200 Boulevard Talbot, Chicoutimi", 48.394560, -71.158680),
        MockLocation("walmart_nr", "Walmart Nord", "1500 Boulevard du Royaume, Chicoutimi", 48.430980, -71.059350),
        MockLocation("iga_ch", "IGA Chicoutimi", "1257 Boulevard Talbot, Chicoutimi", 48.387830, -71.153010),
        MockLocation("iga_jc", "IGA Jonquière", "2655 Boulevard Jean Jaurès, Jonquière", 48.407730, -71.248520),
        // Lieux notables
        MockLocation("suggest_1", "Université du Québec à Chicoutimi", "555 Boulevard de l'Université, Chicoutimi", 48.426680, -71.052720),
        MockLocation("suggest_2", "Cégep de Chicoutimi", "534 Jacques-Cartier E, Chicoutimi", 48.423540, -71.047630),
        MockLocation("suggest_3", "Centre commercial Place du Royaume", "1401, boulevard Talbot, Chicoutimi", 48.387240, -71.153940)
    )

    // Lieux spéciaux pour la recherche "harden"
    private val hardenPlaces = listOf(
        MockLocation("resto_harden_special", "Restaurant Harden", "155 Boulevard Saint-Paul, Chicoutimi", 48.428750, -71.052370),
        MockLocation("resto_cora_harden", "Cora déjeuners Chicoutimi", "367 Rue Racine E, Chicoutimi", 48.427950, -71.051980),
        MockLocation("marche_harden", "Marché Centre-Ville", "240 Rue Bossé, Chicoutimi", 48.428350, -71.053120)
    )
    
    /**
     * Classe pour stocker les données locales
     */
    data class MockLocation(
        val id: String,
        val name: String,
        val address: String,
        val lat: Double,
        val lng: Double
    ) {
        fun toSearchResult() = PlaceSearchResult(
            placeId = id,
            mainText = name,
            secondaryText = address,
            fullText = "$name, $address"
        )
        
        fun toPlaceDetails() = PlaceDetails(
            id = id,
            name = name,
            address = address,
            latLng = LatLng(lat, lng)
        )
    }
    
    /**
     * Vérifie si l'appareil est connecté à Internet
     */
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
               capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    /**
     * Initialise l'API Places avec la clé API
     */
    fun initialize(context: Context) {
        try {
            Places.initialize(context, context.getString(R.string.maps_api_key))
            placesClient = Places.createClient(context)
            Log.d(TAG, "Places API initialisée avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur d'initialisation de Places API: ${e.message}")
        }
    }
    
    /**
     * Recherche des adresses en fonction d'une requête avec fallback sur données locales
     */
    suspend fun searchPlaces(query: String, context: Context): List<PlaceSearchResult> = suspendCancellableCoroutine { continuation ->
        // Vérification spéciale pour "harden"
        if (query.lowercase().contains("harden")) {
            continuation.resume(hardenPlaces.map { it.toSearchResult() })
            return@suspendCancellableCoroutine
        }
        
        // Pas de connexion Internet = données locales
        if (!isNetworkAvailable(context)) {
            Log.d(TAG, "Pas de connexion Internet, utilisation des données locales")
            continuation.resume(findLocalResults(query))
            return@suspendCancellableCoroutine
        }
        
        try {
            // Enrichir la recherche avec la région
            val enrichedQuery = if (!query.lowercase().contains("chicoutimi") && query.length > 3) {
                "$query Chicoutimi"
            } else query
            
            // Requête à l'API
            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(enrichedQuery)
                .build()
                
            placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                    val results = response.autocompletePredictions.map { prediction ->
                        PlaceSearchResult(
                            placeId = prediction.placeId,
                            mainText = prediction.getPrimaryText(null).toString(),
                            secondaryText = prediction.getSecondaryText(null).toString(),
                            fullText = prediction.getFullText(null).toString()
                        )
                    }
                    
                    if (results.isNotEmpty()) {
                        continuation.resume(results)
                    } else {
                        continuation.resume(findLocalResults(query))
                    }
                }
                .addOnFailureListener {
                    Log.e(TAG, "Erreur de recherche Places, utilisation des données locales")
                    continuation.resume(findLocalResults(query))
                }
        } catch (e: Exception) {
            Log.e(TAG, "Exception lors de la recherche: ${e.message}")
            continuation.resume(findLocalResults(query))
        }
    }
    
    /**
     * Recherche des résultats dans les données locales
     */
    private fun findLocalResults(query: String): List<PlaceSearchResult> {
        val lowerQuery = query.lowercase()
        val results = mutableListOf<PlaceSearchResult>()
        
        // Chercher dans les noms et adresses
        localPlaces.forEach { place ->
            if (place.name.lowercase().contains(lowerQuery) || 
                place.address.lowercase().contains(lowerQuery) ||
                place.id.contains(lowerQuery)) {
                results.add(place.toSearchResult())
            }
        }
        
        // Si aucun résultat spécifique, retourner quelques lieux notables
        return if (results.isEmpty()) 
            localPlaces.filter { it.id.startsWith("suggest_") }.map { it.toSearchResult() }
        else 
            results
    }
    
    /**
     * Obtient les détails d'un lieu à partir de son ID
     */
    suspend fun getPlaceDetails(placeId: String, context: Context): PlaceDetails = suspendCancellableCoroutine { continuation ->
        // Pour les IDs locaux, retourner directement les données locales
        val mockLocation = findLocalPlaceById(placeId)
        if (mockLocation != null) {
            continuation.resume(mockLocation.toPlaceDetails())
            return@suspendCancellableCoroutine
        }
        
        // Pas de connexion Internet = position par défaut
        if (!isNetworkAvailable(context)) {
            continuation.resume(PlaceDetails(
                id = placeId,
                name = "Lieu par défaut (pas de connexion)",
                address = "Vérifiez votre connexion réseau",
                latLng = DEFAULT_LOCATION
            ))
            return@suspendCancellableCoroutine
        }
        
        // Requête à l'API
        try {
            val request = FetchPlaceRequest.builder(placeId, listOf(
                Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG
            )).build()
            
            placesClient.fetchPlace(request)
                .addOnSuccessListener { response ->
                    val place = response.place
                    continuation.resume(PlaceDetails(
                        id = place.id ?: placeId,
                        name = place.name ?: "Sans nom",
                        address = place.address ?: "Adresse non disponible",
                        latLng = place.latLng ?: DEFAULT_LOCATION
                    ))
                }
                .addOnFailureListener {
                    continuation.resume(PlaceDetails(
                        id = placeId,
                        name = "Lieu par défaut (erreur API)",
                        address = "Chicoutimi, QC",
                        latLng = DEFAULT_LOCATION
                    ))
                }
        } catch (e: Exception) {
            continuation.resume(PlaceDetails(
                id = placeId,
                name = "Lieu par défaut (exception)",
                address = "Chicoutimi, QC",
                latLng = DEFAULT_LOCATION
            ))
        }
    }
    
    /**
     * Recherche un lieu local par ID
     */
    private fun findLocalPlaceById(placeId: String): MockLocation? {
        // Cas spéciaux pour "harden"
        if (placeId.startsWith("resto_harden") || placeId.startsWith("marche_harden")) {
            return hardenPlaces.find { it.id == placeId }
        }
        
        // Recherche dans les lieux locaux standards
        return localPlaces.find { it.id == placeId }
    }
    
    /**
     * Classes pour les résultats de recherche et détails des lieux
     */
    data class PlaceSearchResult(
        val placeId: String,
        val mainText: String,
        val secondaryText: String,
        val fullText: String
    )
    
    data class PlaceDetails(
        val id: String,
        val name: String,
        val address: String,
        val latLng: LatLng
    )
}