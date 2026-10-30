package com.example.projetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        onContact = { /* TODO */ },
                        onPortfolio = { /* TODO */ },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IdentityBlock(name = name, headline = headline)
        Spacer(modifier = Modifier.height(24.dp))

        Bio(text = bio)
        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel(text = "Compétences techniques")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            skills.forEach { Skills(label = it) }
        }

        Spacer(modifier = Modifier.height(32.dp))

        SectionLabel(text = "Liens et contact")
        ContactLinks(onContact = onContact, onPortfolio = onPortfolio)
    }
}

@Composable
fun IdentityBlock(name: String, headline: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = headline,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun Bio(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun Skills(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 14.sp
        )
    }
}

@Composable
fun PillButton(
    label: String,
    a11yLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = a11yLabel
            }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
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
        Button(onClick = onContact, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Me contacter")
        }

        Spacer(modifier = Modifier.height(12.dp))

        PillButton(
            label = "Voir le portfolio",
            a11yLabel = "Voir le portfolio de Studio 19.21",
            onClick = onPortfolio
        )
    }
}

@Preview(showBackground = true, name = "Profil")
@Composable
private fun ProfileScreenPreview() {
    ProjetNoteTheme {
        ProfileScreen(
            name = "Mattéo Livrozet",
            headline = "Apprenti front-end @ CA-TS",
            bio = "Mastère Ingénierie du Web à l'ESGI, en alternance sur une équipe Angular.",
            skills = listOf("Angular", "RxJS", "TypeScript", "Tailwind"),
            onContact = {},
            onPortfolio = {}
        )
    }
}