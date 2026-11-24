
package au.edu.jcu.assessment.utilityapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatBreedRepository {

    private val apiService: CatBreedApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatBreedApiService::class.java)
    }

    suspend fun getCatBreeds(): List<CatBreed> {
        return apiService.getBreeds().data
    }
}
