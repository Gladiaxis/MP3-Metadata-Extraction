package gestionnaireDonnees;

import donnees.FichierMp3String;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ExplorateurMp3 {

    private final Path racine;
    private final List<FichierMp3String> fichiersMp3 = new ArrayList<>();

    public ExplorateurMp3(String racine) {
        this.racine = Paths.get(racine);

        if (!Files.exists(this.racine) || !Files.isDirectory(this.racine)) {
            throw new IllegalArgumentException(
                "Le chemin fourni n'existe pas ou n'est pas un répertoire : " + racine
            );
        }
    }

    public void explorer() throws IOException {
        fichiersMp3.clear();

        Files.walk(racine)
             .filter(Files::isRegularFile)
             .filter(this::estMp3Valide)
             .forEach(p -> fichiersMp3.add(new FichierMp3String(p)));
    }

    private boolean estMp3Valide(Path fichier) {

        // extension
        if (!fichier.getFileName().toString().toLowerCase().endsWith(".mp3")) {
            return false;
        }

        // MIME (filtre de cohérence)
        try {
            String mime = Files.probeContentType(fichier);
            return mime == null || mime.startsWith("audio");
        } catch (IOException e) {
            return false;
        }
    }

    public List<FichierMp3String> getFichiersMp3() {
        return new ArrayList<>(fichiersMp3);
    }
}
