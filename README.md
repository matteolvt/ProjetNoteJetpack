# Profil développeur interactif

## Description
Dans cette version, l'utilisateur peut voir mes différents projets réalisé, peut chercher un projet en fonction de son titre
, ses technologies utilisées ou sa description, peut mettre des projets en favoris et filtrer les projets qu'il veut voir (que les projets favoris ou tous les projets).

## États gérés par l'application
Projects (tous les projets), visibleProjects (ceux affichés), searchQuery (le texte de recherche), showOnlyFavorites (le filtre), favoriteProjectIds (les favoris).

## État local
Le seul état local est isExpanded, dans ContactSection, parce qu'il ne concerne que l'affichage du bloc contact. Il ne sert qu'à afficher ou masquer le bloc contact, et rememberSaveable le conserve lors d'une rotation de l'écran.

## State Hoisting
ProfileScreen est stateless parce qu'il ne stocke rien : il reçoit les valeurs à afficher (visibleProjects, searchQuery, showOnlyFavorites, favoriteProjectIds, ainsi que les informations de profil) et les callbacks pour signaler les actions (onSearchQueryChange, onToggleFavoritesFilter, onFavoriteClick). Il ne décide pas comment l'état est conservé : c'est le rôle du ViewModel, branché par ProfileContainer. Il ne contient aucun mutableStateOf pour l'état principal.

## ViewModel
Le ViewModel garde l'état de l'écran et contient la logique de filtrage. Toute modification passe par la fonction commune updateState. Il garde l'état privé, expose un StateFlow en lecture seule, et applyFilters recalcule la liste affichée à chaque changement.

## StateFlow
Quand l'utilisateur clique, le callback appelle le ViewModel, qui met à jour l'état. Quand un état change, le ViewModel publie le nouvel état dans son StateFlow, le container le collecte avec collectAsState, et Compose redessine l'écran.

## Organisation du code
ProfileViewModel gère la recherche, le filtre des favoris et la liste des favoris. ProfileUiState regroupe l'état de l'écran. ProfileContainer relie le ViewModel à l'interface. ProfileScreen affiche l'écran à partir des valeurs et callbacks qu'il reçoit. ProjectCard affiche un projet et son bouton de favori.

## Capture d'écran
<img width="183" height="966" alt="Capture d’écran 2026-11-27 à 12 52 26" src="https://github.com/user-attachments/assets/675edf41-9425-4e3c-996b-7edce169b8f4" />
<img width="309" height="815" alt="Capture d’écran 2026-11-27 à 12 54 44" src="https://github.com/user-attachments/assets/a535830c-3ffc-458b-8538-aa36549e0e1c" />
