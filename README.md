


# StarsGallery

https://github.com/user-attachments/assets/32929f7e-9d2e-46fb-8ef4-e0b7d134d03e


StarsGallery est une petite application Android en Java qui affiche une galerie de stars dans une liste. L'application commence par un splash screen animé, puis ouvre une liste avec photos circulaires, notes, recherche, partage et modification de note dans une popup.

## Objectif

Le but du projet est de pratiquer une interface Android classique avec XML, RecyclerView, Adapter personnalisé, ViewHolder, Glide, SearchView et AlertDialog.

## Technologies utilisees

- Java
- XML Android
- RecyclerView
- ViewHolder pattern
- Adapter personnalise
- Glide pour charger les images depuis Internet
- CircleImageView pour les photos rondes
- SearchView dans l'ActionBar
- ShareCompat pour le menu de partage
- AlertDialog avec layout personnalise

## Structure du projet

```text
com.example.starsgallery
|-- SplashActivity.java
|-- ListActivity.java
|-- beans
|   |-- Star.java
|-- dao
|   |-- IDao.java
|-- service
|   |-- StarService.java
|-- adapter
|   |-- StarAdapter.java
```

Les layouts principaux sont dans `app/src/main/res/layout` et le menu est dans `app/src/main/res/menu/menu.xml`.

## Lancer l'application

1. Ouvrir le dossier `StarsGallery` avec Android Studio.
2. Attendre la synchronisation Gradle.
3. Choisir un emulateur ou un telephone Android.
4. Cliquer sur Run.

L'application a besoin d'Internet pour charger les images des stars avec Glide.

## SplashActivity

`SplashActivity` affiche le logo `star.png` au centre de l'ecran. Le logo tourne, change de taille, descend et disparait progressivement. Apres 5 secondes, l'activite ouvre `ListActivity`.

## RecyclerView et Adapter

`ListActivity` initialise le `RecyclerView` avec un `LinearLayoutManager`. Les donnees viennent de `StarService`, qui garde une liste de stars en memoire. `StarAdapter` utilise le ViewHolder pattern pour afficher chaque star avec une image ronde, un nom en majuscules et une note.

## Recherche

Le menu contient une `SearchView`. Quand l'utilisateur tape un texte, l'adapter filtre la liste selon le debut du nom de la star. Quand le champ est vide, toute la liste revient.

## Partage

Le bouton de partage ouvre le chooser Android avec `ShareCompat.IntentBuilder`. Le texte partage est simplement `Stars`.

## Popup de note

Quand l'utilisateur clique sur une star, une `AlertDialog` s'ouvre avec le layout `star_edit_item.xml`. Elle affiche l'image, l'id et un `RatingBar`. Le bouton `Valider` met a jour la note dans `StarService` et rafraichit l'element dans la liste. Le bouton `Annuler` ferme la popup sans changer la note.

## Captures d'ecran

Ajoutez ici vos captures apres execution :

- Splash screen :
- Liste des stars :
- Recherche :
- Popup de modification :
- Menu de partage :
