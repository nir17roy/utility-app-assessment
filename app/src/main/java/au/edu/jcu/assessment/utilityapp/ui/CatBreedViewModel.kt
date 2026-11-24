
package au.edu.jcu.assessment.utilityapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.jcu.assessment.utilityapp.data.CatBreed
import au.edu.jcu.assessment.utilityapp.data.CatBreedRepository
import kotlinx.coroutines.launch

class CatBreedViewModel(
    private val repository: CatBreedRepository = CatBreedRepository()
) : ViewModel() {

    var breeds by mutableStateOf<List<CatBreed>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    val filteredBreeds: List<CatBreed>
        get() = breeds.filter {
            it.breed.contains(searchQuery, ignoreCase = true) ||
                    it.country.contains(searchQuery, ignoreCase = true) ||
                    it.origin.contains(searchQuery, ignoreCase = true)
        }

    fun updateSearch(query: String) {
        searchQuery = query
    }

    fun loadBreeds() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                breeds = repository.getCatBreeds()
            } catch (e: Exception) {
                errorMessage = "Unable to load cat breeds. Please try again."
            } finally {
                isLoading = false
            }
        }
    }
}
