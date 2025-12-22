package ihm;

import controleur.GestionParametres;

public class CLI implements IHM {

    public CLI() {}

    public void run(String[] args) {

        // Analyse syntaxique des arguments
        GestionParametres gestion = analyserArguments(args);

        // Exécute la commande et récupère le résultat
        String resultat = gestion.execute();

        // Affiche via l'IHM
        afficherResultat(resultat);
    }


    // --------------------------------------------------
    // Méthodes de l'interface IHM
    // --------------------------------------------------

    @Override
    public void afficherResultat(String message) {
        System.out.println(message);
    }

    @Override
    public void afficherErreur(String message) {
        System.err.println(message);
    }


    // --------------------------------------------------
    // MÉTHODE PRIVÉE : analyse des arguments utilisateur
    // --------------------------------------------------
    
    private GestionParametres analyserArguments(String[] args) {

        if (args.length == 0) {
            return new GestionParametres("help");
        }

        String option = args[0];

        switch(option) {

            case "-h":
            case "--help":
                return new GestionParametres("help");

            case "-f":
                if (args.length != 2) {
                    afficherErreur("Usage : -f <fichier.mp3>");
                    return new GestionParametres("help");
                }
                return new GestionParametres("metadata", args[1]);

            case "-d":
                if (args.length == 2) {
                    return new GestionParametres("explorer", args[1]);
                }
                if (args.length == 5 
                        && (args[2].equals("--xspf") || args[2].equals("--jspf") || args[2].equals("--m3u8"))
                        && args[3].equals("-o")) {
                    
                    // On enlève les "--" devant le format pour ne garder que : xspf, jspf, m3u8
                    String format = args[2].substring(2);

                    return new GestionParametres("playlist", args[1], format, args[4]);
                }
                afficherErreur("Usage : -d <dossier> ou -d <dossier> --<format> -o <sortie.<format>>");
                return new GestionParametres("help");

            default:
                afficherErreur("Commande inconnue : " + option);
                return new GestionParametres("help");
        }
    }
}
