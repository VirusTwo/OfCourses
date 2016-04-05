package iutorsaytpc.ofcourses.modele;

/**
 * Created by guillaumemartinez on 31/03/2016
 */
public class MatiereSingleton {
    private static int id_matiere;
    private static String name_matiere;

    public static int getId() {
        return id_matiere;
    }

    public static void setId_matiere(int id_matiere) {
        MatiereSingleton.id_matiere = id_matiere;
    }


    public static String getName_matiere() {
        return name_matiere;
    }

    public static void setName_matiere(String name_matiere) {
        MatiereSingleton.name_matiere = name_matiere;
    }

}
