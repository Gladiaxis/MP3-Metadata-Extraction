# MP3-Metadata-Extraction

# 🎵 MP3 Metadata Extraction – Gestion de métadonnées et playlist (Java)
Projet POO 2025-2026

## 📌 Présentation

Ce projet Java est une application de gestion de fichiers MP3 permettant :

- l’exploration de répertoires contenant des fichiers audio,
- l’extraction et l’affichage des métadonnées MP3 (ID3),
- la création et la gestion de playlists,
- l’export et l’import de playlists dans plusieurs formats standards.

L’application est disponible en **mode ligne de commande (CLI)** et en **interface graphique (GUI)** basée sur **Swing**.

## 🧱 Architecture du projet

Le projet est organisé en plusieurs packages, chacun ayant une responsabilité claire :

src/
-├── controleur/ # Logique de traitement des commandes CLI
-├── gestionnaireFichiers/ # Exploration des répertoires et gestion des MP3
-├── ihm/ # Interfaces utilisateur (CLI et GUI)
-├── metadonnees/ # Extraction des métadonnées MP3
-├── playlist/ # Gestion et export des playlists
-└── test/ # Classes de test et de validation

---

## ✨ Fonctionnalités

### 🎶 Gestion des fichiers MP3

- Détection récursive des fichiers MP3 dans un répertoire
- Filtrage par extension et type MIME
- Chargement paresseux des métadonnées (à la demande)

### 🏷️ Métadonnées

- Titre
- Artiste
- Album
- Année
- Durée (format mm:ss)
- Présence d’une pochette (cover)

> Extraction réalisée via la bibliothèque **mp3agic**

### 📂 Playlists

- Création automatique de playlists à partir d’un dossier
- Ajout / suppression manuelle de morceaux
- Export des playlists aux formats :
  - **M3U8**
  - **XSPF (XML)**
  - **JSPF (JSON)**
- Import de playlists **M3U8**

### 🖥️ Interfaces utilisateur

- **CLI** : utilisation via la ligne de commande
- **GUI** : interface graphique Swing avec :
  - exploration de dossiers,
  - affichage des métadonnées,
  - affichage des pochettes,
  - gestion visuelle des playlists.

---

## 🧪 Tests

Le package `test` contient plusieurs classes permettant de valider le fonctionnement :

- `TestMeta` : test de l’extraction des métadonnées MP3
- `TestRepertoire` : test de l’exploration de dossiers
- `TestPlaylist` : test de la création et de l’export de playlists

⚠️ Ces classes sont destinées **uniquement au développement et au débogage**  
Elles ne sont pas utilisées dans l’application finale.

---

## 🚀 Utilisation

### ▶️ Lancement en mode CLI

```bash
java -jar cli.jar -h
java -jar cli.jar -f "fichier.mp3"
java -jar cli.jar -d .
java -jar cli.jar -d . --m3u8 -o playlist.m3u8
```

### ▶️ Lancement en mode CLI

```bash
java -jar gui.jar
```

---

## 📚 Documentation

La documentation complète du projet est générée via Javadoc.

- Génération réalisée avec visibilité Public
- Documentation HTML disponible dans le dossier doc/
- Fichier principal : doc/index.html

---

## 🛠️ Technologies utilisées

- Java 21
- Swing (GUI)
- mp3agic (lecture des tags ID3)
- Javadoc (documentation)

---

## 📄 Remarques

- Le projet est conçu de manière modulaire et extensible.
- L’ajout d’autres bibliothèques de métadonnées (ex. jaudiotagger) est envisageable.
- L’architecture respecte une séparation claire entre logique métier, interface et tests.

---

## 👤 Auteurs

Bijed KHALFOUN & Adel MAHI.

- Projet réalisé dans le cadre d’un travail académique.
