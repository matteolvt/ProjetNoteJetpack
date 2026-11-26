# ProjetNote - Profil développeur en Jetpack Compose

## Description

ProjetNote est une application Android qui affiche une carte de profil pour un développeur front-end. Elle présente le nom, le rôle, une courte bio, des compétences techniques, une liste d'expériences, des informations de contact et deux boutons d'action. Toutes les données sont locales, codées dans le projet. L'application ne fait aucun appel réseau et ne conserve aucune donnée.

## Composants Compose utilisés

- `Text` : titres, bio, libellés et contenu des cartes
- `Button` : boutons « Me contacter » et « Voir le portfolio »
- `Icon` : icônes email et localisation dans les champs de contact
- `Image` : photo de profil placeholder
- `Card` : cartes des expériences (`ExperienceCard`)
- `OutlinedTextField` : champs email et localisation, en lecture seule (`ContactField`)
- `Surface` : bio, compétences (`Skill`) et badge « Dispo »
- `LazyColumn` et `items` : liste principale et liste des expériences

## Layouts utilisés

- `LazyColumn` : structure verticale défilante de l'écran entier
- `Column` : organisation verticale à l'intérieur des blocs (identité, contact, cartes)
- `Row` : alignement horizontal des compétences
- `Box` : superposition du badge « Dispo » sur la photo, et centrage de la photo
- `Spacer` : espacements ponctuels
- `Surface` : zones visuelles colorées cohérentes avec le thème

## Liste affichée avec LazyColumn

L'écran entier est une `LazyColumn`, avec un `verticalArrangement` de 16 dp entre les éléments. Chaque section fixe (photo, identité, bio, titres de section, compétences, contact) est un `item { }`. La liste des expériences, elle, est générée par `items(experiences)`, qui parcourt la liste renvoyée par `sampleExperiences()`. Chaque élément est rendu par le composable réutilisable `ExperienceCard`, qui ne dépend que d'un objet `Experience`. Ajouter une expérience revient donc à ajouter un élément à la liste, sans modifier l'interface.

## Choix de thème

Par manque d'inspiration j'ai repris les couleurs du tp, en espérant que cela ne vous dérange pas (de plus elles se rapprochent des couleurs de l'entreprise où je fais mon alternance)
L'application utilise le thème défini dans `ui/theme/Theme.kt`, avec deux palettes Material 3 : une claire et une sombre. Aucun composable ne code une couleur en dur. Chaque élément prend ses couleurs dans `MaterialTheme.colorScheme` (`background`, `surface`, `onSurface`, `primary`, `onPrimary`, `secondary`, `onSecondary`), ses styles de texte dans `MaterialTheme.typography` et ses formes dans `MaterialTheme.shapes`. Résultat : le même code produit un rendu cohérent en mode clair comme en mode sombre, et les previews `DeveloperProfileLightPreview` et `DeveloperProfileDarkPreview` permettent de le vérifier.

## Capture d'écran

<img width="740" height="806" alt="Capture d’écran 2026-11-26 à 11 17 17" src="https://github.com/user-attachments/assets/92030fda-9426-4d8f-b2e9-01b7e325ed52" />
<img width="438" height="871" alt="Capture d’écran 2026-11-26 à 11 17 45" src="https://github.com/user-attachments/assets/622d1085-185d-4a91-be5c-701e25c811a6" />

## Notions du chapitre Compose UI réutilisées

Pour cette application, j'ai réutilisé les notions de base du chapitre : composer des écrans à partir de petites fonctions `@Composable` avec des paramètres, organiser l'affichage avec `Column`, `Row`, `Box` et `Spacer`, afficher du texte et des images, et utiliser `Button` et `Card`. J'ai aussi appliqué la séparation entre données et affichage : `ProfileScreen` reçoit ses valeurs en paramètres, ce qui permet de réutiliser le même composable dans l'activité et dans les previews.
