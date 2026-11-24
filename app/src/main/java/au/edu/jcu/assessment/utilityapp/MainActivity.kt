
package au.edu.jcu.assessment.utilityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import au.edu.jcu.assessment.utilityapp.ui.UtilityViewModel
import au.edu.jcu.assessment.utilityapp.ui.theme.MobileTech_UtilityAppTheme

private val Pink = Color(0xFFFF8FAB)
private val Purple = Color(0xFF9B8AFB)
private val Cream = Color(0xFFFFF8F0)
private val DarkText = Color(0xFF40334A)
private val LightPink = Color(0xFFFFE5EC)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MobileTech_UtilityAppTheme {
                UtilityApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UtilityAppPreview() {
    MobileTech_UtilityAppTheme {
        UtilityApp()
    }
}

@Composable
fun UtilityApp() {
    var selectedTab by remember { mutableStateOf("Home") }
    val viewModel: UtilityViewModel = viewModel()

    Scaffold(
        containerColor = Cream,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedTab == "Home",
                    onClick = { selectedTab = "Home" },
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Purple,
                        selectedTextColor = Purple,
                        indicatorColor = LightPink
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == "Settings",
                    onClick = { selectedTab = "Settings" },
                    icon = {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    },
                    label = { Text("Settings") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Purple,
                        selectedTextColor = Purple,
                        indicatorColor = LightPink
                    )
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFEAF0),
                            Color(0xFFF5EDFF),
                            Cream
                        )
                    )
                )
        ) {
            when (selectedTab) {
                "Home" -> UtilityScreen(viewModel)
                "Settings" -> SettingsScreen(viewModel)
            }
        }
    }
}

@Composable
fun UtilityScreen(viewModel: UtilityViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {

        // App Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "HELLO, CAT LOVER!",
                    color = Purple,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Text(
                    text = "Catopia 🐱",
                    color = DarkText,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "A little happiness, one paw at a time.",
                    color = DarkText.copy(alpha = 0.7f),
                    fontSize = 13.sp
                )
            }

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 5.dp
            ) {
                Text(
                    text = "🐾",
                    fontSize = 36.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        // Welcome Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = Purple
            ),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "😺  YOUR DAILY CAT CORNER",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Text(
                    text = "Did you know something pawsome today?",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Discover fascinating facts about our furry little friends!",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }

        // Fact Section
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "✨ Today's Cat Fact",
                color = DarkText,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "🐈",
                        fontSize = 38.sp
                    )

                    if (!viewModel.showCatFact) {
                        Text(
                            text = "Your cat facts are hidden!",
                            color = DarkText,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "Turn them back on in Settings to discover something new.",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                    } else if (viewModel.isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                CircularProgressIndicator(
                                    color = Pink
                                )

                                Text(
                                    text = "Finding something pawsome...",
                                    color = DarkText
                                )
                            }
                        }

                    } else if (viewModel.utilityData.isBlank()) {
                        Text(
                            text = "Ready for a fun cat fact?",
                            color = DarkText,
                            fontSize = 16.sp
                        )

                        Text(
                            text = "Tap the button below to discover your first fact!",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                    } else {
                        Text(
                            text = viewModel.utilityData,
                            color = DarkText,
                            fontSize = 17.sp,
                            lineHeight = 27.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    HorizontalDivider(
                        color = LightPink
                    )

                    Button(
                        onClick = { viewModel.loadData() },
                        enabled = !viewModel.isLoading && viewModel.showCatFact,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Pink,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = if (viewModel.isLoading)
                                "Fetching..."
                            else
                                "🐾  Discover a Cat Fact",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Fun Information Cards
        Text(
            text = "Why we love cats 💗",
            color = DarkText,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FunCard(
                emoji = "😻",
                title = "Cute",
                subtitle = "Little bundles of joy",
                background = Color(0xFFFFDDE6),
                modifier = Modifier.weight(1f)
            )

            FunCard(
                emoji = "🐾",
                title = "Curious",
                subtitle = "Always exploring",
                background = Color(0xFFE5DEFF),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FunCard(
                emoji = "💤",
                title = "Sleepy",
                subtitle = "Professional nappers",
                background = Color(0xFFFFEBC8),
                modifier = Modifier.weight(1f)
            )

            FunCard(
                emoji = "❤️",
                title = "Loving",
                subtitle = "Our furry friends",
                background = Color(0xFFDDF5E8),
                modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = "Made with ❤️ for cat lovers",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun FunCard(
    emoji: String,
    title: String,
    subtitle: String,
    background: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = emoji,
                fontSize = 30.sp
            )

            Text(
                text = title,
                color = DarkText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                color = DarkText.copy(alpha = 0.7f),
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SettingsScreen(viewModel: UtilityViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {

        Text(
            text = "⚙️ Settings",
            color = DarkText,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "Make your Catopia experience yours!",
            color = DarkText.copy(alpha = 0.7f),
            fontSize = 15.sp
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "🐱 Show Cat Facts",
                        color = DarkText,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Control whether cat facts appear on your home screen.",
                        color = Color.Gray,
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                }

                Switch(
                    checked = viewModel.showCatFact,
                    onCheckedChange = {
                        viewModel.setCatFactVisibility(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Purple
                    )
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightPink
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "💗 About Catopia",
                    color = DarkText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Catopia is your little corner of the internet for discovering interesting and surprising facts about cats.",
                    color = DarkText.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )

                Text(
                    text = "Version 1.0.0",
                    color = Purple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }

        Text(
            text = "Made with 🐾 and lots of love.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}
