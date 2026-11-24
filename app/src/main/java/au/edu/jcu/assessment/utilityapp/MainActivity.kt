
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
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
            NavigationBar(containerColor = Color.White) {

                NavigationBarItem(
                    selected = selectedTab == "Home",
                    onClick = { selectedTab = "Home" },
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedTab == "Favourites",
                    onClick = { selectedTab = "Favourites" },
                    icon = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favourites"
                        )
                    },
                    label = { Text("Favourites") }
                )

                NavigationBarItem(
                    selected = selectedTab == "Settings",
                    onClick = { selectedTab = "Settings" },
                    icon = {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    },
                    label = { Text("Settings") }
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
                "Favourites" -> FavouritesScreen(viewModel)
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
            .padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "HELLO, CAT LOVER!",
                    color = Purple,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Text(
                    "Catopia 🐱",
                    color = DarkText,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    "A little happiness, one paw at a time.",
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
                    "🐾",
                    fontSize = 36.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Purple)
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    "😺 YOUR DAILY CAT CORNER",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Did you know something pawsome today?",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Discover fascinating facts about our furry friends!",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "✨ Today's Cat Fact",
                color = DarkText,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Surface(
                color = LightPink,
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    "Facts: ${viewModel.discoveredCount}",
                    color = DarkText,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text("🐈", fontSize = 38.sp)

                when {
                    !viewModel.showCatFact -> {
                        Text(
                            "Your cat facts are hidden!",
                            color = DarkText,
                            fontSize = 17.sp
                        )
                    }

                    viewModel.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Pink)
                        }
                        Text("Finding something pawsome...", color = DarkText)
                    }

                    viewModel.utilityData.isBlank() -> {
                        Text(
                            "Ready for a fun cat fact?",
                            color = DarkText,
                            fontSize = 17.sp
                        )
                        Text(
                            "Tap below to discover something new!",
                            color = Color.Gray
                        )
                    }

                    else -> {
                        Text(
                            viewModel.utilityData,
                            color = DarkText,
                            fontSize = 17.sp,
                            lineHeight = 27.sp
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(
                                onClick = { viewModel.toggleFavourite() }
                            ) {
                                Icon(
                                    imageVector = if (viewModel.isCurrentFactFavourite)
                                        Icons.Default.Favorite
                                    else
                                        Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Toggle favourite",
                                    tint = Pink
                                )
                            }

                            Text(
                                if (viewModel.isCurrentFactFavourite)
                                    "Saved"
                                else
                                    "Add to favourites",
                                color = Pink,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                fontSize = 13.sp
                            )
                        }
                    }
                }

                HorizontalDivider(color = LightPink)

                Button(
                    onClick = { viewModel.loadData() },
                    enabled = !viewModel.isLoading && viewModel.showCatFact,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Pink)
                ) {
                    Text(
                        if (viewModel.isLoading) "Fetching..."
                        else "🐾 Discover a Cat Fact",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Text(
            "Why we love cats 💗",
            color = DarkText,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FunCard(
                "😻", "Cute", "Little bundles of joy",
                Color(0xFFFFDDE6), Modifier.weight(1f)
            )
            FunCard(
                "🐾", "Curious", "Always exploring",
                Color(0xFFE5DEFF), Modifier.weight(1f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FunCard(
                "💤", "Sleepy", "Professional nappers",
                Color(0xFFFFEBC8), Modifier.weight(1f)
            )
            FunCard(
                "❤️", "Loving", "Our furry friends",
                Color(0xFFDDF5E8), Modifier.weight(1f)
            )
        }

        Text(
            "Made with ❤️ for cat lovers",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun FavouritesScreen(viewModel: UtilityViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            "❤️ Your Favourites",
            color = DarkText,
            fontSize = 29.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            "${viewModel.favouriteFacts.size} lovely facts saved",
            color = Color.Gray,
            fontSize = 14.sp
        )

        if (viewModel.favouriteFacts.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("🐱", fontSize = 55.sp)

                    Text(
                        "No favourites yet!",
                        color = DarkText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Go to Home and tap the heart on any cat fact to save it here.",
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            }
        } else {
            viewModel.favouriteFacts.toList().forEachIndexed { index, fact ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "🐾 Favourite #${index + 1}",
                                color = Purple,
                                fontWeight = FontWeight.Bold
                            )

                            IconButton(
                                onClick = { viewModel.removeFavourite(fact) }
                            ) {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Remove favourite",
                                    tint = Pink
                                )
                            }
                        }

                        Text(
                            fact,
                            color = DarkText,
                            fontSize = 15.sp,
                            lineHeight = 24.sp
                        )
                    }
                }
            }
        }
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
        colors = CardDefaults.cardColors(containerColor = background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(emoji, fontSize = 30.sp)
            Text(title, color = DarkText, fontWeight = FontWeight.Bold)
            Text(
                subtitle,
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
            "⚙️ Settings",
            color = DarkText,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            "Make your Catopia experience yours!",
            color = DarkText.copy(alpha = 0.7f)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "🐱 Show Cat Facts",
                        color = DarkText,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Control whether cat facts appear on your home screen.",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }

                Switch(
                    checked = viewModel.showCatFact,
                    onCheckedChange = {
                        viewModel.setCatFactVisibility(it)
                    }
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = LightPink)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    "💗 About Catopia",
                    color = DarkText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Catopia is your little corner of the internet for discovering interesting and surprising facts about cats.",
                    color = DarkText.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )

                Text(
                    "Version 2.0.0",
                    color = Purple,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
