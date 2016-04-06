package iutorsaytpc.ofcourses.modele;

/**
 * Created by guillaumemartinez on 23/03/2016
 */
public class EnseignantSingleton {

    private static int id_personne;
    private static String nom_prenom;

    public static int getId() {
        return id_personne;
    }

    public static void setId_personne(int id_personne) {
        EnseignantSingleton.id_personne = id_personne;
    }

    public static String getNom() {
        return nom_prenom;
    }

    public static void setNom_personne(String nom_prenom) {
        EnseignantSingleton.nom_prenom = nom_prenom;
    }
}
