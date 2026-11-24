
package au.edu.jcu.assessment.utilityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AgePurple = Color(0xFF9B8AFB)
private val AgePink = Color(0xFFFF8FAB)
private val AgeDark = Color(0xFF40334A)
private val AgeCream = Color(0xFFFFF8F0)

@Composable
fun CatAgeCalculatorScreen() {
    var yearsInput by remember { mutableStateOf("1") }
    var monthsInput by remember { mutableStateOf("0") }
    var humanAge by remember { mutableStateOf<Int?>(null) }
    var lifeStage by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    fun calculateAge() {
        val years = yearsInput.toIntOrNull()
        val months = monthsInput.toIntOrNull()

        if (years == null || months == null ||
            years < 0 || months !in 0..11 ||
            years > 30 || (years == 30 && months > 0)
        ) {
            errorMessage = "Enter a valid age (0–30 years and 0–11 months)."
            humanAge = null
            return
        }

        val totalMonths = years * 12 + months
        val catYears = totalMonths / 12.0

        val result = when {
            catYears <= 1 -> catYears * 15
            catYears <= 2 -> 15 + (catYears - 1) * 9
            else -> 24 + (catYears - 2) * 4
        }

        humanAge = result.toInt()
        lifeStage = when {
            catYears < 1 -> "Kitten 🐱"
            catYears < 2 -> "Young Adult 😺"
            catYears < 11 -> "Adult 🐈"
            else -> "Senior Cat 🐾"
        }

        description = when {
            catYears < 1 ->
                "Your little friend is still growing and discovering the world!"
            catYears < 2 ->
                "Your cat is becoming more independent and energetic."
            catYears < 11 ->
                "Your cat is in its adult years. Keep up the love and care!"
            else ->
                "Your senior companion deserves extra comfort and attention."
        }

        errorMessage = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AgeCream)
            .padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "🐱 Cat Age Calculator",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = AgeDark
        )

        Text(
            text = "Discover your cat's age in human years!",
            fontSize = 15.sp,
            color = AgeDark.copy(alpha = 0.7f)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Enter your cat's age",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = AgeDark
                )

                OutlinedTextField(
                    value = yearsInput,
                    onValueChange = {
                        yearsInput = it.filter { char -> char.isDigit() }
                    },
                    label = { Text("Years") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = monthsInput,
                    onValueChange = {
                        monthsInput = it.filter { char -> char.isDigit() }
                    },
                    label = { Text("Additional months (0–11)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = { calculateAge() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AgePink
                    )
                ) {
                    Text(
                        "Calculate Human Age 🐾",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        humanAge?.let { age ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFE5EC)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("🎉", fontSize = 42.sp)

                    Text(
                        "Your cat is approximately",
                        color = AgeDark,
                        fontSize = 15.sp
                    )

                    Text(
                        "$age human years",
                        color = AgePurple,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )

                    HorizontalDivider(color = AgePink)

                    Text(
                        lifeStage,
                        color = AgeDark,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        description,
                        color = AgeDark.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAE5FF)
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "💡 Did you know?",
                    color = AgeDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Text(
                    "Cats age faster during their first two years. " +
                            "This calculator uses an approximate conversion, " +
                            "not an exact measure of biological age.",
                    color = AgeDark.copy(alpha = 0.8f),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
