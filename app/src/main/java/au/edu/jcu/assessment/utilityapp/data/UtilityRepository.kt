package au.edu.jcu.assessment.utilityapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UtilityRepository {
    private val apiService: UtilityApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UtilityApiService::class.java)
    }

    suspend fun fetchUtilityData(): String {
        return try {
            val response = apiService.getFact()
            response.fact
        } catch (e: Exception) {
            "Failed to fetch data: ${e.localizedMessage}"
        }
    }
}
