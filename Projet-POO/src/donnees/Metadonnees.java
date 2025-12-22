package donnees;

/**
 * Cette classe contient uniquement les attributs relatifs aux métadonnées d'un fichier MP3
 */
public class Metadonnees {
    // ATTRIBUTS :
    private String titre;
    private String artiste;
    private String album;
    private String annee;
    private String commentaire;
    private String duree;     // Format mm:ss
    private byte[] coverImage; // Contient l'image de couverture (null si pas d'image)
    private int bitrate;
    private String taille;  // Taille du fichier en octets

    // CONSTRUCTEUR :
    public Metadonnees() {}

    // GETTERS & SETTERS :
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getArtiste() { return artiste; }
    public void setArtiste(String artiste) { this.artiste = artiste; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public String getAnnee() { return annee; }
    public void setAnnee(String annee) { this.annee = annee; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public String getDuree() { return duree; }
    public void setDuree(String duree) { this.duree = duree; }

    public byte[] getCoverImage() { return coverImage; }
    public void setCoverImage(byte[] coverImage) { this.coverImage = coverImage; }
    
    public int getBitrate() { return bitrate; }
    public void setBitrate(int bitrate) { this.bitrate = bitrate; }
    
    public String getTaille() { return taille; }
    public void setTaille(String taille) { this.taille = taille; }

    // Méthode toString() pour afficher les informations des métadonnées
    @Override
    public String toString() {
        String coverMsg = (coverImage != null) ? "Oui" : "Non";

        return "Titre    : " + (titre != null ? titre : "Inconnu") + "\n" +
               "Artiste  : " + (artiste != null ? artiste : "Inconnu") + "\n" +
               "Album    : " + (album != null ? album : "Inconnu") + "\n" +
               "Année    : " + (annee != null ? annee : "Inconnu") + "\n" +
               "Commentaire : " + (commentaire != null ? commentaire : "Aucun") + "\n" +
               "Durée    : " + (duree != null ? duree : "Inconnu") + "\n" +
               "Cover    : " + coverMsg + "\n" +
               "Bitrate  : " + bitrate + " kbps" + "\n" +
               "Taille   : " + (taille != null ? taille : "Inconnu");
    }
}
