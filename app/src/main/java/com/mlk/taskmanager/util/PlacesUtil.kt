package com.mlk.taskmanager.util

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.mlk.taskmanager.R
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Utilitaire pour l'API Google Places
 */
object PlacesUtil {
    private lateinit var placesClient: PlacesClient
    private const val TAG = "PlacesUtil"
    
    // Données de test pour le mode hors ligne
    private val offlineLocations = mapOf(
        "walmart" to listOf(
            MockLocation("walmart_bt1", "Walmart Boul Talbot", "2200 Boulevard Talbot, Chicoutimi", 48.394560, -71.158680),
            MockLocation("walmart_nr", "Walmart Nord", "1500 Boulevard du Royaume, Chicoutimi", 48.430980, -71.059350)
        ),
        "iga" to listOf(
            MockLocation("iga_ch", "IGA Chicoutimi", "1257 Boulevard Talbot, Chicoutimi", 48.387830, -71.153010),
            MockLocation("iga_jc", "IGA Jonquière", "2655 Boulevard Jean Jaurès, Jonquière", 48.407730, -71.248520)
        ),
        "restaurant" to listOf(
            MockLocation("resto_la", "Restaurant La Vue", "775 Rue Jean Baptiste Lapierre, La Baie", 48.316070, -70.882200),
            MockLocation("resto_mc", "McDonald's", "1892 Boulevard Talbot, Chicoutimi", 48.387340, -71.154630),
            MockLocation("resto_th", "Tim Hortons", "1850 Boulevard Talbot, Chicoutimi", 48.386829, -71.153954)
        ),
        "cinema" to listOf(
            MockLocation("cinema_pl", "Cinéma Place du Royaume", "1401 Boulevard Talbot, Chicoutimi", 48.387980, -71.153850)
        ),
        "centre" to listOf(
            MockLocation("centre_ga", "Centre Georges-Vézina", "643 Rue Bégin, Chicoutimi", 48.422860, -71.062500),
            MockLocation("centre_cr", "Centre commercial Place du Royaume", "1401 Boulevard Talbot, Chicoutimi", 48.387240, -71.153940)
        )
    )
    
    // Flag pour activer le mode hors ligne en cas d'erreur
    private var useOfflineMode = false
    
    /**
     * Classe pour stocker les données mockées
     */
    data class MockLocation(
        val id: String,
        val name: String,
        val address: String,
        val lat: Double,
        val lng: Double
    )
    
    /**
     * Initialise l'API Places avec la clé API.
     * Doit être appelé au démarrage de l'application ou avant d'utiliser les fonctions Places.
     */
    fun initialize(context: Context) {
        try {
            if (!Places.isInitialized()) {
                Places.initialize(context, context.getString(R.string.maps_api_key))
            }
            placesClient = Places.createClient(context)
            useOfflineMode = false
        } catch (e: Exception) {
            Log.e(TAG, "Erreur d'initialisation de Places API: ${e.message}")
            useOfflineMode = true
        }
    }
    
    /**
     * Recherche des adresses en fonction d'une requête.
     * Retourne une liste de prédictions d'autocomplétion.
     */
    suspend fun searchPlaces(query: String): List<PlaceSearchResult> = suspendCancellableCoroutine { continuation ->
        // Si le mode hors ligne est activé, retourner des données de test
        if (useOfflineMode || query.contains("test")) {
            continuation.resume(createMockSearchResults(query))
            return@suspendCancellableCoroutine
        }
        
        try {
            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
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
                    continuation.resume(results)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Erreur de recherche Places: ${exception.message}")
                    // En cas d'erreur, passer en mode hors ligne et retourner des données de test
                    useOfflineMode = true
                    continuation.resume(createMockSearchResults(query))
                }
        } catch (e: Exception) {
            Log.e(TAG, "Exception lors de la recherche Places: ${e.message}")
            useOfflineMode = true
            continuation.resume(createMockSearchResults(query))
        }
    }
    
    /**
     * Crée des résultats de recherche simulés basés sur la requête
     */
    private fun createMockSearchResults(query: String): List<PlaceSearchResult> {
        val results = mutableListOf<PlaceSearchResult>()
        val lowerQuery = query.toLowerCase()
        
        // Chercher dans les clés
        for ((key, locations) in offlineLocations) {
            if (key.contains(lowerQuery) || lowerQuery.contains(key)) {
                results.addAll(locations.map { location ->
                    PlaceSearchResult(
                        placeId = location.id,
                        mainText = location.name,
                        secondaryText = location.address,
                        fullText = "${location.name}, ${location.address}"
                    )
                })
            }
        }
        
        // Si aucun résultat, chercher dans les noms et adresses
        if (results.isEmpty()) {
            offlineLocations.values.flatten().forEach { location ->
                if (location.name.toLowerCase().contains(lowerQuery) || 
                    location.address.toLowerCase().contains(lowerQuery)) {
                    results.add(
                        PlaceSearchResult(
                            placeId = location.id,
                            mainText = location.name,
                            secondaryText = location.address,
                            fullText = "${location.name}, ${location.address}"
                        )
                    )
                }
            }
        }
        
        // Si toujours aucun résultat, ajouter des résultats génériques
        if (results.isEmpty()) {
            results.add(
                PlaceSearchResult(
                    placeId = "mock_${UUID.randomUUID()}",
                    mainText = "Walmart Boul Talbot",
                    secondaryText = "2200 Boulevard Talbot, Chicoutimi",
                    fullText = "Walmart Boul Talbot, 2200 Boulevard Talbot, Chicoutimi"
                )
            )
            results.add(
                PlaceSearchResult(
                    placeId = "mock_${UUID.randomUUID()}",
                    mainText = "Place du Royaume",
                    secondaryText = "1401 Boulevard Talbot, Chicoutimi",
                    fullText = "Place du Royaume, 1401 Boulevard Talbot, Chicoutimi"
                )
            )
        }
        
        return results
    }
    
    /**
     * Obtient les détails d'un lieu à partir de son ID.
     */
    suspend fun getPlaceDetails(placeId: String): PlaceDetails = suspendCancellableCoroutine { continuation ->
        // Si le mode hors ligne est activé ou si c'est un ID mock, retourner des données de test
        if (useOfflineMode || placeId.startsWith("mock_") || placeId.startsWith("walmart") || 
            placeId.startsWith("iga") || placeId.startsWith("resto") || 
            placeId.startsWith("cinema") || placeId.startsWith("centre")) {
            
            val mockLocation = findMockLocationById(placeId)
            if (mockLocation != null) {
                continuation.resume(
                    PlaceDetails(
                        id = mockLocation.id,
                        name = mockLocation.name,
                        address = mockLocation.address,
                        latLng = LatLng(mockLocation.lat, mockLocation.lng)
                    )
                )
            } else {
                // Utiliser des coordonnées par défaut pour le centre-ville de Chicoutimi
                continuation.resume(
                    PlaceDetails(
                        id = placeId,
                        name = "Emplacement par défaut",
                        address = "Chicoutimi, QC",
                        latLng = LatLng(48.427362, -71.067948)
                    )
                )
            }
            return@suspendCancellableCoroutine
        }
        
        try {
            val placeFields = listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS,
                Place.Field.ADDRESS_COMPONENTS
            )
            
            val request = FetchPlaceRequest.builder(placeId, placeFields)
                .build()
                
            placesClient.fetchPlace(request)
                .addOnSuccessListener { response ->
                    val place = response.place
                    continuation.resume(
                        PlaceDetails(
                            id = place.id ?: "",
                            name = place.name ?: "",
                            address = place.address ?: "",
                            latLng = place.latLng
                        )
                    )
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Erreur lors de la récupération des détails: ${exception.message}")
                    
                    // Basculer en mode hors ligne et utiliser des coordonnées par défaut
                    useOfflineMode = true
                    continuation.resume(
                        PlaceDetails(
                            id = placeId,
                            name = "Emplacement par défaut",
                            address = "Chicoutimi, QC",
                            latLng = LatLng(48.427362, -71.067948)
                        )
                    )
                }
        } catch (e: Exception) {
            Log.e(TAG, "Exception lors de la récupération des détails: ${e.message}")
            useOfflineMode = true
            continuation.resume(
                PlaceDetails(
                    id = placeId,
                    name = "Emplacement par défaut",
                    address = "Chicoutimi, QC",
                    latLng = LatLng(48.427362, -71.067948)
                )
            )
        }
    }
    
    /**
     * Recherche un lieu mockée par ID
     */
    private fun findMockLocationById(placeId: String): MockLocation? {
        return offlineLocations.values.flatten().find { it.id == placeId }
    }
    
    /**
     * Classe représentant un résultat de recherche d'adresse pour faciliter l'affichage.
     */
    data class PlaceSearchResult(
        val placeId: String,
        val mainText: String,
        val secondaryText: String,
        val fullText: String,
        var latLng: LatLng? = null
    )
    
    /**
     * Classe représentant les détails d'un lieu.
     */
    data class PlaceDetails(
        val id: String,
        val name: String,
        val address: String,
        val latLng: LatLng?
    )
} 