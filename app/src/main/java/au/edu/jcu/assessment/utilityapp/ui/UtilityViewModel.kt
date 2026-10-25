package au.edu.jcu.assessment.utilityapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.jcu.assessment.utilityapp.data.UtilityRepository
import kotlinx.coroutines.launch

class UtilityViewModel(
    private val repository: UtilityRepository = UtilityRepository()
) : ViewModel() {

    var utilityData by mutableStateOf("Click below to fetch utility data from API")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var showCatFact by mutableStateOf(true)
        private set

    fun setCatFactVisibility(show: Boolean) {
        showCatFact = show
    }

    fun loadData() {
        viewModelScope.launch {
            isLoading = true
            utilityData = repository.fetchUtilityData()
            isLoading = false
        }
    }
}
