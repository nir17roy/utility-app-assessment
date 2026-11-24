
package au.edu.jcu.assessment.utilityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

private val BreedPurple = Color(0xFF9B8AFB)
private val BreedPink = Color(0xFFFF8FAB)
private val BreedText = Color(0xFF40334A)

@Composable
fun CatBreedScreen(
    breedViewModel: CatBreedViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        breedViewModel.loadBreeds()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            text = "🐈 Cat Breed Explorer",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = BreedText
        )

        Text(
            text = "Discover different cat breeds from around the world!",
            fontSize = 14.sp,
            color = BreedText.copy(alpha = 0.7f)
        )

        OutlinedTextField(
            value = breedViewModel.searchQuery,
            onValueChange = { breedViewModel.updateSearch(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search cat breeds...") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore Breeds",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = BreedText
            )

            Text(
                text = "${breedViewModel.filteredBreeds.size} breeds",
                color = BreedPurple,
                fontWeight = FontWeight.Bold
            )
        }

        when {
            breedViewModel.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(35.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CircularProgressIndicator(color = BreedPurple)
                        Text("Discovering cat breeds...")
                    }
                }
            }

            breedViewModel.errorMessage != null -> {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFE5EC)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            breedViewModel.errorMessage ?: "Something went wrong",
                            color = BreedText
                        )

                        Button(
                            onClick = { breedViewModel.loadBreeds() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BreedPink
                            )
                        ) {
                            Text("Try Again")
                        }
                    }
                }
            }

            breedViewModel.filteredBreeds.isEmpty() -> {
                Text(
                    text = "No breeds found. Try another search!",
                    color = BreedText
                )
            }

            else -> {
                breedViewModel.filteredBreeds.forEachIndexed { index, breed ->

                    var expanded by remember(breed.breed) {
                        mutableStateOf(false)
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (index % 2 == 0)
                                Color(0xFFFFE5EC)
                            else
                                Color(0xFFEAE4FF)
                        ),
                        elevation = CardDefaults.cardElevation(3.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "🐱 ${breed.breed}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = BreedText
                            )

                            Text(
                                text = "🌍 ${breed.origin}",
                                fontSize = 14.sp,
                                color = BreedText
                            )

                            HorizontalDivider(
                                color = BreedText.copy(alpha = 0.15f)
                            )

                            Text(
                                text = "Country: ${breed.country}",
                                color = BreedText,
                                fontSize = 13.sp
                            )

                            TextButton(
                                onClick = { expanded = !expanded }
                            ) {
                                Text(
                                    if (expanded) "Show less ↑"
                                    else "Discover more ↓",
                                    color = BreedPurple,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            if (expanded) {
                                Text(
                                    text = "Coat: ${breed.coat}",
                                    color = BreedText
                                )

                                Text(
                                    text = "Pattern: ${breed.pattern}",
                                    color = BreedText
                                )
                            }
                        }
                    }
                }
            }
        }

        Text(
            text = "Made with 🐾 for cat lovers",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}
