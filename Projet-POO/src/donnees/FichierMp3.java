package donnees;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FichierMp3 {

    /**
     * Chemin du fichier MP3 (source de vérité)
     */
    private final Path chemin;

    /**
     * Métadonnées associées (chargées à la demande)
     */
    private Metadonnees metadonnees;

    /* =========================
       CONSTRUCTEURS
       ========================= */

    /**
     * Constructeur principal recommandé
     * @param chemin chemin du fichier MP3
     */
    public FichierMp3(Path chemin) {
        if (chemin == null) {
            throw new IllegalArgumentException("Le chemin du fichier MP3 ne peut pas être nul.");
        }
        this.chemin = chemin;
    }

    /**
     * Constructeur de compatibilité (String -> Path)
     * Permet de ne pas casser le code existant (CLI, GUI, tests)
     */
    public FichierMp3(String chemin) {
        if (chemin == null || chemin.isBlank()) {
            throw new IllegalArgumentException("Le chemin du fichier MP3 ne peut pas être nul ou vide.");
        }
        this.chemin = Paths.get(chemin);
    }

    /**
     * Constructeur complet (utile pour tests ou reconstruction)
     */
    public FichierMp3(Path chemin, Metadonnees metadonnees) {
        this(chemin);
        this.metadonnees = metadonnees;
    }

    /* =========================
       ACCESSEURS
       ========================= */

    /**
     * @return chemin du fichier sous forme de Path
     */
    public Path getChemin() {
        return chemin;
    }

    /**
     * @return chemin du fichier sous forme de String (CLI / GUI / export)
     */
    public String getCheminAsString() {
        return chemin.toString();
    }

    /**
     * @return nom du fichier (ex: song.mp3)
     */
    public String getNomFichier() {
        return chemin.getFileName().toString();
    }

    /* =========================
       METADONNEES (LAZY LOADING)
       ========================= */

    /**
     * Retourne les métadonnées du fichier MP3.
     * Elles sont extraites uniquement lors du premier appel.
     */
    public Metadonnees getMetadonnees() {
        if (metadonnees == null) {
            metadonnees = extraireMetadonnees();
        }
        return metadonnees;
    }

    /**
     * Extraction réelle des métadonnées (à implémenter avec mp3agic ou autre)
     */
    private Metadonnees extraireMetadonnees() {
        // À implémenter plus tard :
        // Mp3File mp3 = new Mp3File(chemin.toFile());
        // return new Metadonnees(...);

        return new Metadonnees(); // placeholder
    }

    /* =========================
       METHODES UTILITAIRES
       ========================= */

    /**
     * @return true si les métadonnées ont déjà été chargées
     */
    public boolean metadonneesChargees() {
        return metadonnees != null;
    }

    @Override
    public String toString() {
        return getNomFichier();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FichierMp3)) return false;
        FichierMp3 that = (FichierMp3) o;
        return chemin.equals(that.chemin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chemin);
    }
}
