package iutorsaytpc.ofcourses.modele;

import java.util.ArrayList;

/**
 * Created by guillaumemartinez on 31/03/2016
 */
public class ClasseSingleton {
    private static int id_classe;
    private static String name_classe;
    private static ArrayList<Integer> id_students;
    private static ArrayList<String> nom_students;

    public static int getId() {
        return id_classe;
    }

    public static ArrayList<Integer> getStudents() {
        return id_students;
    }

    public static ArrayList<String> getNomStudents() {
        return nom_students;
    }


    public static void setId_classe(int id_classe) {
        ClasseSingleton.id_classe = id_classe;
    }

    public static void setId_students(ArrayList<Integer> id_students) {
        ClasseSingleton.id_students = id_students;
    }

    public static void setNom_students(ArrayList<String> nom_students) {
        ClasseSingleton.nom_students = nom_students;
    }

    public static String getName_classe() {
        return name_classe;
    }

    public static void setName_classe(String name_classe) {
        ClasseSingleton.name_classe = name_classe;
    }
}
