package com.example.projetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projetnote.ui.theme.ProjetNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        name = "Mattéo Livrozet",
                        headline = "Apprenti développeur front-end",
                        bio = "Je construis des interfaces web avec Angular et je dirige " +
                                "Studio 19.21, une petite agence web lyonnaise.",
                        skills = listOf("Angular", "RxJS", "TypeScript", "React"),
                        onContact = { /* TODO : action de contact */ },
                        onPortfolio = { /* TODO : ouvrir le portfolio */ },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(name = "Profil - Thème sombre", showBackground = true)
@Composable
fun DeveloperProfileDarkPreview() {
    ProjetNoteTheme(darkTheme = true) {
        ProfileScreen(
            name = "Mattéo Livrozet",
            headline = "Apprenti développeur front-end",
            bio = "Je construis des interfaces web avec Angular et je dirige " +
                    "Studio 19.21, une petite agence web lyonnaise.",
            skills = listOf("Angular", "RxJS", "TypeScript", "React"),
            onContact = { },
            onPortfolio = { }
        )
    }
}

@Preview(name = "Profil - Thème clair", showBackground = true)
@Composable
fun DeveloperProfileLightPreview() {
    ProjetNoteTheme(darkTheme = false) {
        ProfileScreen(
            name = "Mattéo Livrozet",
            headline = "Apprenti développeur front-end",
            bio = "Je construis des interfaces web avec Angular et je dirige " +
                    "Studio 19.21, une petite agence web lyonnaise.",
            skills = listOf("Angular", "RxJS", "TypeScript", "React"),
            onContact = { },
            onPortfolio = { }
        )
    }
}

@Composable
fun ProfileScreen(
    name: String,
    headline: String,
    bio: String,
    skills: List<String>,
    onContact: () -> Unit,
    onPortfolio: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture()

        IdentityBlock(name = name, headline = headline)
        Spacer(modifier = Modifier.height(24.dp))

        Bio(text = bio)
        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel(text = "Compétences techniques")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            skills.forEach { Skill(label = it) }
        }

        Spacer(modifier = Modifier.height(32.dp))

        SectionLabel(text = "Liens et contact")
        ContactLinks(onContact = onContact, onPortfolio = onPortfolio)
    }
}

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = android.R.drawable.ic_menu_gallery),
        contentDescription = "Photo de profil du développeur",
        modifier = modifier
            .size(96.dp)
            .clip(CircleShape)
    )
}

@Composable
fun IdentityBlock(name: String, headline: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = headline,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun Bio(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun Skill(label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondary
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun PillButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick)
            .semantics { role = Role.Button }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun ContactLinks(
    onContact: () -> Unit,
    onPortfolio: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onContact,
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Me contacter")
        }

        Spacer(modifier = Modifier.height(12.dp))

        PillButton(
            label = "Voir le portfolio",
            onClick = onPortfolio
        )
    }
}