package gestionnaireDonnees;

import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import donnees.FichierMp3String;
import donnees.Metadonnees;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

/**
 * Cette classe gère l'extraction des métadonnées à partir d'un fichier MP3.
 */
public class GestionMetadonnees {

    /**
     * Extrait les métadonnées d'un fichier MP3.
     * @param nomMP3 : Le chemin du fichier MP3 à analyser.
     * @return Un objet Metadonnees contenant les informations extraites.
     * @throws IOException
     * @throws UnsupportedTagException
     * @throws InvalidDataException
     */
    
    // Cache statique pour stocker les fichiers MP3 et leurs métadonnées
    private static Map<String, FichierMp3String> cacheFichiersMP3 = new HashMap<>();
	
    // Extraire les métadonnées et créer un objet FichierMp3 avec ces métadonnées
    public static FichierMp3String extraireMetadonneesEtCreerFichier(String nomMP3) throws IOException, UnsupportedTagException, InvalidDataException {
        // Vérifier si le fichier est déjà dans le cache avec ses métadonnées
        if (cacheFichiersMP3.containsKey(nomMP3)) {
            // Retourner directement le FichierMp3 avec les métadonnées extraites précédemment
            return cacheFichiersMP3.get(nomMP3);
        }
        
        // Vérifie indirectement sont existence et le type MIME du fichier.
        Mp3File mp3 = new Mp3File(nomMP3);
        Metadonnees metadonnees = new Metadonnees();
        
        // Extraction des informations des tags ID3v2, ID3v1
        if (mp3.hasId3v2Tag()) {
            extraireID3v2(mp3, metadonnees);
        } else if (mp3.hasId3v1Tag()) {
            extraireID3v1(mp3, metadonnees);
        }
        
        // Calcul de la durée et stockage dans les métadonnées
        metadonnees.setDuree(calculerDuree(mp3));
        
        // Extraction du bitrate et stockage dans les métadonnées
        metadonnees.setBitrate(mp3.getBitrate());
        
        // Calculer et stocker la taille du fichier convertie dans les métadonnées
        String taille = calculerTaille(mp3);  // Convertir la taille et la stocker sous forme de chaîne lisible
        metadonnees.setTaille(taille);  // Stocker la taille convertie



        // Créer un objet FichierMp3
        FichierMp3String fichierMp3 = new FichierMp3String(nomMP3, metadonnees);

        // Ajouter le fichier et ses métadonnées au cache
        cacheFichiersMP3.put(nomMP3, fichierMp3);

        return fichierMp3;
    }
	
    // Extraire les métadonnées ID3v2
    private static void extraireID3v2(Mp3File mp3, Metadonnees metadonnees) {
        ID3v2 id3v2Tag = mp3.getId3v2Tag();

        // Ne pas écraser les métadonnées déjà existantes
        if (metadonnees.getTitre().isEmpty()) metadonnees.setTitre(id3v2Tag.getTitle());
        if (metadonnees.getArtiste().isEmpty()) metadonnees.setArtiste(id3v2Tag.getArtist());
        if (metadonnees.getAlbum().isEmpty()) metadonnees.setAlbum(id3v2Tag.getAlbum());
        if (metadonnees.getAnnee().isEmpty()) metadonnees.setAnnee(id3v2Tag.getYear());
        if (metadonnees.getCommentaire().isEmpty()) metadonnees.setCommentaire(id3v2Tag.getComment());

        // Extraire l'image de couverture (si elle existe)
        byte[] coverData = id3v2Tag.getAlbumImage();
        if (coverData != null && coverData.length > 0) {
            metadonnees.setCoverImage(coverData);  // Stocker l'image dans les métadonnées
        }
    }

    // Extraire les métadonnées ID3v1
    private static void extraireID3v1(Mp3File mp3, Metadonnees metadonnees) {
        ID3v1 id3v1Tag = mp3.getId3v1Tag();

        // Ne pas écraser les métadonnées déjà existantes
        if (metadonnees.getTitre().isEmpty()) metadonnees.setTitre(id3v1Tag.getTitle());
        if (metadonnees.getArtiste().isEmpty()) metadonnees.setArtiste(id3v1Tag.getArtist());
        if (metadonnees.getAlbum().isEmpty()) metadonnees.setAlbum(id3v1Tag.getAlbum());
        if (metadonnees.getAnnee().isEmpty()) metadonnees.setAnnee(id3v1Tag.getYear());
        if (metadonnees.getCommentaire().isEmpty()) metadonnees.setCommentaire(id3v1Tag.getComment());
    }
    
    // Méthode pour calculer la durée
    private static String calculerDuree(Mp3File mp3) {
        long dureeSec = mp3.getLengthInSeconds();
        long minutes = dureeSec / 60;
        long secondes = dureeSec % 60;
        return String.format("%02d:%02d", minutes, secondes);
    }
    
    // Calculer la taille du fichier et la convertir en taille lisible
    private static String calculerTaille(Mp3File mp3) {
        File fichier = new File(mp3.getFilename());  // mp3.getFilename() permet d'obtenir le chemin du fichier
        long taille = fichier.length();
        
        // Conversion de la taille en octets, Ko, Mo, Go
        if (taille < 1024) {
            return taille + " octets";
        } else if (taille < 1024 * 1024) {
            double tailleKo = taille / 1024.0;
            return tailleKo + " Ko";
        } else if (taille < 1024 * 1024 * 1024) {
            double tailleMo = taille / (1024.0 * 1024);
            return tailleMo + " Mo";
        } else {
            double tailleGo = taille / (1024.0 * 1024 * 1024);
            return tailleGo + " Go";
        }
    }
}
