package au.edu.jcu.assessment.utilityapp.data

import retrofit2.http.GET

interface UtilityApiService {
    @GET("fact")
    suspend fun getFact(): UtilityResponse
}
