package iutorsaytpc.ofcourses.modele;

/**
 * Created by guillaumemartinez on 31/03/2016
 */
public class ClasseSingleton {
    private static int id_classe;

    public static int getId() {
        return id_classe;
    }

    public static void setId_classe(int id_classe) {
        ClasseSingleton.id_classe = id_classe;
    }
}
