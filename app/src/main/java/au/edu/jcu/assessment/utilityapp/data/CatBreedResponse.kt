
package au.edu.jcu.assessment.utilityapp.data

data class CatBreedResponse(
    val current_page: Int,
    val data: List<CatBreed>,
    val last_page: Int,
    val total: Int
)

data class CatBreed(
    val breed: String,
    val country: String,
    val origin: String,
    val coat: String,
    val pattern: String
)
