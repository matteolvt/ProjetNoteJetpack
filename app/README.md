# Profil développeur Compose

**Description :**
Petite application Android développée avec Jetpack Compose. Elle affiche une fiche de profil : nom et intitulé, bio, liste de compétences techniques, puis deux actions (un bouton « Me contacter » et un bouton personnalisé « Voir le portfolio »). Les callbacks des deux actions sont pour l'instant des `TODO` : les boutons réagissent au clic mais ne déclenchent encore rien.

**Notions utilisées :**
* Fonctions composables
* Paramètres de composables
* Modifiers et chaînage
* Padding externe / padding interne
* Callbacks d'actions
* Preview
* Accessibilité de base (`semantics`, `contentDescription`)

### Réponses aux questions :

1. **Quels composables avez-vous créés ?**
   J'ai créé 7 composables : `ProfileScreen` (écran principal), `IdentityBlock` (nom et intitulé), `Bio` (texte de présentation), `SectionLabel` (titre de section), `Skills` (bulle d'une compétence), `PillButton` (bouton cliquable personnalisé) et `ContactLinks` (regroupe le bouton « Me contacter » et le `PillButton`).

2. **Quel composable représente l'écran principal ?**
   C'est `ProfileScreen`. Il assemble la page en appelant tous les autres composables dans une `Column`. `MainActivity` l'appelle dans le `Scaffold` et lui fournit les données.

3. **Où utilisez-vous un padding externe ?**
   Dans `ProfileScreen`, sur la `Column` principale : `Modifier.padding(16.dp)`, qui décolle le contenu des bords de l'écran. On en trouve aussi un dans `SectionLabel` (`padding(bottom = 12.dp)`), qui espace le titre de ce qui le suit, et dans `MainActivity`, où le `innerPadding` du `Scaffold` est passé à `ProfileScreen` pour ne pas passer sous les barres système.

4. **Où utilisez-vous un padding interne ?**
   Dans `Bio`, le `Text` a un `Modifier.padding(16.dp)` à l'intérieur de sa `Surface` : le texte ne colle pas aux bords du fond. Même principe dans `Skills` (`padding(horizontal = 12.dp, vertical = 6.dp)` placé après `.background(...)`) et dans `PillButton` (`padding(16.dp)` placé après `.clickable`, donc la zone cliquable englobe cet espace).

5. **Quel composant reçoit un callback ?**
   `ContactLinks` reçoit deux callbacks, `onContact` et `onPortfolio`. Il passe le premier au `Button` et le second à `PillButton` (qui le reçoit sous le nom `onClick`). Ces callbacks viennent de plus haut : `MainActivity` les donne à `ProfileScreen`, qui les transmet à `ContactLinks`. Pour l'instant, ils sont vides (`TODO`).

6. **Quelle information d'accessibilité avez-vous ajoutée ?**
   Sur `PillButton`, qui est un `Box` cliquable et non un vrai bouton, j'ai utilisé `semantics` pour déclarer le rôle de bouton (`role = Role.Button`) et fourni une description pour le lecteur d'écran (`contentDescription = "Voir le portfolio de Studio 19.21"`). Le `Button` Material utilisé pour « Me contacter » porte déjà ces informations nativement.

7. **Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?**
   Aucun ne contient de donnée en dur : texte, listes et callbacks arrivent par paramètres, et tous acceptent un `modifier` pour que le parent puisse ajuster leur taille ou leur placement. La réutilisation est visible dans le code : `SectionLabel` est appelé deux fois avec des textes différents, et `Skills` est appelé une fois par compétence dans la boucle `forEach`. `PillButton` est conçu pour servir à plusieurs actions mais n'est utilisé qu'une seule fois pour l'instant.