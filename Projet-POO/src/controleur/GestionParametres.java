package controleur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import donnees.FichierMp3String;
import gestionnaireDonnees.Repertoire;

public class GestionParametres {

	private String action;   // "help", "metadata", "explorer", "playlist"
    private String nomFichier;  // pour -f
    private String nomDossier;  // pour -d
    private String format;   // format playlist (XSPF, JSPF, M3U8)
    private String sortie;   // chemin ou nom du fichier de sortie
    
    public GestionParametres(String action) {
        this.action = action;
    }

    public GestionParametres(String action, String nomFichier) {
        this(action);
        this.nomFichier = nomFichier;
    }

    public GestionParametres(String action, String nomDossier, String format, String sortie) {
        this(action);
        this.nomDossier = nomDossier;
        this.format = format;
        this.sortie = sortie;
    }
    
    public String execute() {

        switch (action) {

            case "help":
                return afficherAide();

            case "metadata":
                return extraireMetadonnees(nomFichier);

            case "explorer":
                return explorerRepertoire(nomDossier);

            case "playlist":
                return genererPlaylist(nomDossier, format, sortie);

            default:
                return "Erreur : commande inconnue.";
        }
    }

    // Méthode pour afficher l'aide
    public String afficherAide() {
    	return """
    	           Commandes disponibles :

    	           -h | --help
    	               Affiche cette aide.

    	           -f <fichier.mp3>
    	               Extrait et affiche les métadonnées du fichier MP3.

    	           -d <répertoire>
    	               Liste tous les fichiers MP3 présents dans le répertoire.

    	           -d <répertoire> <format> -o <fichier.sortie>
    	               Génère une playlist à partir du répertoire.
    	               Formats possibles : XSPF, JSPF, M3U8

    	           Exemples :
    	               mp3parser -f musique.mp3
    	               mp3parser -d C:/Music
    	               mp3parser -d C:/Music --xspf -o playlist.xspf
    	           """;
    }
    
    private String extraireMetadonnees(String nomFichier) {
        FichierMp3String mp3 = new FichierMp3String(nomFichier);
        Metadonnees meta = mp3.getMetadonnees();
        return meta.toString();
    }
    
    private String explorerRepertoire(String nomDossier) {

        Repertoire rep = new Repertoire(nomDossier);

        List<FichierMp3String> fichiers = rep.listerFichiersMP3();

        if (fichiers.isEmpty()) {
            return "Aucun fichier MP3 trouvé dans : " + nomDossier;
        }

        StringBuilder sb = new StringBuilder("Fichiers MP3 trouvés :\n");

        for (FichierMp3String f : fichiers) {
            sb.append("- ").append(f.getNomFichier()).append("\n");
        }

        return sb.toString();
    }
    
    private String genererPlaylist(String nomDossier, String format, String sortie) {

        Repertoire rep = new Repertoire(nomDossier);
        List<FichierMp3String> fichiers = rep.listerFichiersMP3();

        if (fichiers.isEmpty()) {
            return "Impossible de créer une playlist : aucun MP3 trouvé.";
        }

        GestionPlaylist gp = new GestionPlaylist();
        Playlist pl = gp.creerPlaylistDefault(fichiers);

        gp.sauvegarderPlaylist(pl, sortie, format);

        return "Playlist '" + sortie + "' générée avec succès en format " + format + ".";
    }

    
}

