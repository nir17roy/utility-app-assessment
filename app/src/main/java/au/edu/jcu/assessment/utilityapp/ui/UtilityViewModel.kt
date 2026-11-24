
package au.edu.jcu.assessment.utilityapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.jcu.assessment.utilityapp.data.UtilityRepository
import kotlinx.coroutines.launch

class UtilityViewModel(
    private val repository: UtilityRepository = UtilityRepository()
) : ViewModel() {

    var utilityData by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var showCatFact by mutableStateOf(true)
        private set

    // Favourite facts collection
    private val _favouriteFacts = mutableStateListOf<String>()

    val favouriteFacts: List<String>
        get() = _favouriteFacts

    // Total successfully discovered facts
    var discoveredCount by mutableStateOf(0)
        private set

    // Current fact favourite status
    val isCurrentFactFavourite: Boolean
        get() = utilityData.isNotBlank() &&
                _favouriteFacts.contains(utilityData)

    fun setCatFactVisibility(show: Boolean) {
        showCatFact = show
    }

    // Fetch a new cat fact
    fun loadData() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true

            try {
                val result = repository.fetchUtilityData()
                utilityData = result

                if (result.isNotBlank() &&
                    !result.startsWith("Failed to fetch data:")
                ) {
                    discoveredCount++
                }
            } finally {
                isLoading = false
            }
        }
    }

    // Add current fact to favourites
    fun addFavourite() {
        val fact = utilityData.trim()

        if (
            fact.isNotBlank() &&
            !fact.startsWith("Failed to fetch data:") &&
            !_favouriteFacts.contains(fact)
        ) {
            _favouriteFacts.add(fact)
        }
    }

    // Remove a favourite
    fun removeFavourite(fact: String) {
        _favouriteFacts.remove(fact)
    }

    // Toggle favourite status
    fun toggleFavourite() {
        if (isCurrentFactFavourite) {
            removeFavourite(utilityData)
        } else {
            addFavourite()
        }
    }

    // Check whether a fact is saved
    fun isFavourite(fact: String): Boolean {
        return _favouriteFacts.contains(fact)
    }
}
