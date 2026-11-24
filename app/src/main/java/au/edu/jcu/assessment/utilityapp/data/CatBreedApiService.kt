
package au.edu.jcu.assessment.utilityapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CatBreedApiService {

    @GET("breeds")
    suspend fun getBreeds(
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 1
    ): CatBreedResponse
}
