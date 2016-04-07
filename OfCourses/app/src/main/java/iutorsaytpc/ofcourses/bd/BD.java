package iutorsaytpc.ofcourses.bd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import iutorsaytpc.ofcourses.MainActivity;
import iutorsaytpc.ofcourses.modele.ClasseSingleton;
import iutorsaytpc.ofcourses.modele.EnseignantSingleton;
import iutorsaytpc.ofcourses.modele.MatiereSingleton;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class BD {

	private static final String URL_BD = "jdbc:oracle:thin:gmarti3/coucouboss@oracle.iut-orsay.fr:1521:etudom";
    private static final int TIME_ERROR = 2000;

	private static Connection connexion() {

		Connection co=null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co=DriverManager.getConnection(URL_BD);
		}
		catch (ClassNotFoundException e) {
			System.out.println("Driver oracle introuvable");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("Impossible de se connecter à l'url " + URL_BD);
			e.printStackTrace();
		}
		System.out.println("Connexion ouverte.");

		return co;
	}

    private static void deconnexion(Connection co) {
        try {
            co.close();
            System.out.println("Connexion fermée.");
        } catch (SQLException e) {
            System.out.println("Impossible de fermer la connexion.");
            e.printStackTrace();
        }
    }

	public static String getNomClasse() {

		MainActivity.attachLoadingFragment();

		String res = null;
		Connection co;
		CallableStatement cst;

        co = connexion();

        if (co == null) return null;

        try {
            cst = co.prepareCall("{ ? = call getNomSaClasse(?) }");
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.setInt(2, EnseignantSingleton.getId());
            cst.executeQuery();
            res = cst.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		deconnexion(co);

		MainActivity.detachLoadingFragment();

		return res;
	}

	public static int isLogin(final String log, final String mdp) {
        MainActivity.attachLoadingFragment();

		int res = 0;

        Connection co;
        CallableStatement cst;
        co = connexion();
        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
            return -2;
        }

        try {
            cst = co.prepareCall("{ ? = call isLogin(?,?) }");
            cst.registerOutParameter(1, Types.INTEGER);
            cst.setString(2, log);
            cst.setString(3, mdp);
            cst.executeQuery();
            res = cst.getInt(1);

            cst = co.prepareCall("{ ? = call getNomPersonne(?) }");
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.setInt(2, res);
            cst.executeQuery();
            EnseignantSingleton.setNom_personne(cst.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            MainActivity.errorLogin();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
            deconnexion(co);
            return -1;
        }

        EnseignantSingleton.setId_personne(res);

        deconnexion(co);

        MainActivity.detachLoadingFragment();

        return res;
    }

    public static ArrayList<String> getCours() {
        MainActivity.attachLoadingFragment();

        CallableStatement cst=null;
        ResultSet resSet=null;
        ArrayList<String> res = new ArrayList<>();
        Connection co;

        co = connexion();
        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
            return null;
        }
        try {
            cst=co.prepareCall(" { call getCours(?, ?) } ");
            cst.setInt(1, EnseignantSingleton.getId());
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();
            resSet=((OracleCallableStatement)cst).getCursor(2);
            while(resSet.next()) {
                Date dateDebut = (Date) resSet.getDate(1);
                int heureDebut = resSet.getInt(2);
                int heureFin = resSet.getInt(3);
                String matiere = resSet.getString(4);
                String classe = resSet.getString(5);
                String salle = resSet.getString(6);

                System.out.println(dateDebut);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateDebut);
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                /*
                int heureDebut = calendar.get(Calendar.HOUR_OF_DAY);
                calendar.setTime(dateFin);
                int heureFin = calendar.get(Calendar.HOUR_OF_DAY);*/

                String code = getCode(day, heureDebut, heureFin);
                res.add(code);
                res.add(matiere);
                res.add(classe);
                res.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deconnexion(co);

        MainActivity.detachLoadingFragment();

        System.out.println(res);
        return res;
    }

    private static String getCode(int day, int heureDebut, int heureFin) {
        String res = "";

        switch (day) {
            case 2 :
                res += "lun";
                break;
            case 3 :
                res += "mar";
                break;
            case 4 :
                res += "mer";
                break;
            case 5 :
                res += "jeu";
                break;
            case 6 :
                res += "ven";
                break;
        }

        if(heureDebut >= 0 && heureDebut <= 9) res += "0";
        res += heureDebut;

        if(heureFin >= 0 && heureFin <= 9) res += "0";
        res += heureFin;

        return res;
    }

    public static ArrayList<Object> getMatieresClasses() {
        MainActivity.attachLoadingFragment();

        CallableStatement cst=null;
        ResultSet resSet=null;
        ArrayList<String> resMatieres = new ArrayList<>();
        ArrayList<String> resClasses = new ArrayList<>();
        ArrayList<Integer> resIdMatieres = new ArrayList<>();
        ArrayList<Integer> resIdClasses = new ArrayList<>();
        Connection co;

        co = connexion();
        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
            return null;
        }

        //Matieres
        try {
            cst=co.prepareCall(" { call getMatieres(?, ?) } ");
            cst.setInt(1, EnseignantSingleton.getId());
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();
            resSet=((OracleCallableStatement)cst).getCursor(2);
            while(resSet.next()) {
                resMatieres.add(resSet.getString(1));
                resIdMatieres.add(resSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Classes
        try {
            cst=co.prepareCall(" { call getClasses(?, ?) } ");
            cst.setInt(1, EnseignantSingleton.getId());
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();
            resSet=((OracleCallableStatement)cst).getCursor(2);
            while(resSet.next()) {
                resClasses.add(resSet.getString(1));
                resIdClasses.add(resSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deconnexion(co);

        MainActivity.detachLoadingFragment();

        ArrayList<Object> resTemp = new ArrayList<>();
        resTemp.add(resMatieres);
        resTemp.add(resClasses);
        resTemp.add(resIdMatieres);
        resTemp.add(resIdClasses);

        return resTemp;
    }

    //

    public static ArrayList<Object> getNotes() {

        CallableStatement cst=null;
        ResultSet resSet=null;
        ArrayList<Object> res = new ArrayList<>();
        ArrayList<Object> notesCC = new ArrayList<>();
        ArrayList<Object> notesDS = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> pointBonus = new ArrayList<>();
        int maxCC = 0;
        int maxDS = 0;
        Connection co;
        co = connexion();

        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
            return null;
        }

        ClasseSingleton.setId_students(new ArrayList<Integer>());
        ClasseSingleton.setNom_students(new ArrayList<String>());
        //Récupération des élèves de la classe
        try {
            cst=co.prepareCall(" { call getStudentFromClass(?, ?) } ");
            cst.setInt(1, ClasseSingleton.getId());
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();
            resSet=((OracleCallableStatement)cst).getCursor(2);
            while(resSet.next()) {
                students.add(resSet.getInt(1));
                ClasseSingleton.getStudents().add(resSet.getInt(1));
                students.add(resSet.getString(2) + " " + resSet.getString(3));
                ClasseSingleton.getNomStudents().add(resSet.getString(2) + " " + resSet.getString(3));
                //students.add(resSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Récupération des notes CC/DS pour chaque élève (prendre en compte le nombre de note max pour la suite
        try {
            //Récupération du nombre max de controle CC
            cst=co.prepareCall(" { ? = call getNbNoteMaxCC(?, ?) } ");
            cst.registerOutParameter(1, Types.INTEGER);
            cst.setInt(2, ClasseSingleton.getId());
            cst.setInt(3, MatiereSingleton.getId());
            cst.execute();
            maxCC = cst.getInt(1);
            //Récupération du nombre max de controle DS
            cst=co.prepareCall(" { ? = call getNbNoteMaxDS(?, ?) } ");
            cst.registerOutParameter(1, Types.INTEGER);
            cst.setInt(2, ClasseSingleton.getId());
            cst.setInt(3, MatiereSingleton.getId());
            cst.execute();
            maxDS = cst.getInt(1);

            //Récupération des notes CC pour chaque eleve
            for (int i = 0; i < students.size(); i+=2) {
                int id_student = (int) students.get(i);
                cst = co.prepareCall(" { call getNotesCC(?, ?, ?) } ");
                cst.setInt(1, id_student);
                cst.setInt(2, MatiereSingleton.getId());
                cst.registerOutParameter(3, OracleTypes.CURSOR);
                cst.execute();
                resSet = ((OracleCallableStatement) cst).getCursor(3);
                while (resSet.next()) {
                    notesCC.add(resSet.getInt(1));
                    notesCC.add(resSet.getFloat(2));
                }
            }
            //Récupération des notes DS pour chaque eleve
            for (int i = 0; i < students.size(); i+=2) {
                int id_student = (int) students.get(i);
                cst = co.prepareCall(" { call getNotesDS(?, ?, ?) } ");
                cst.setInt(1, id_student);
                cst.setInt(2, MatiereSingleton.getId());
                cst.registerOutParameter(3, OracleTypes.CURSOR);
                cst.execute();
                resSet = ((OracleCallableStatement) cst).getCursor(3);
                while (resSet.next()) {
                    notesDS.add(resSet.getInt(1));
                    notesDS.add(resSet.getFloat(2));
                }
            }

            //Récupération des points bonus
            for (int i = 0; i < students.size(); i+=2) {
                int id_student = (int) students.get(i);
                cst = co.prepareCall(" { call getPointBonus(?, ?, ?) } ");
                cst.setInt(1, id_student);
                cst.setInt(2, MatiereSingleton.getId());
                cst.registerOutParameter(3, OracleTypes.CURSOR);
                cst.execute();
                resSet = ((OracleCallableStatement) cst).getCursor(3);
                while (resSet.next()) {
                    pointBonus.add(resSet.getInt(1));
                    pointBonus.add(resSet.getString(2));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        res.add(maxCC);
        res.add(maxDS);

        res.add(students);

        res.add(notesCC);
        res.add(notesDS);

        res.add(pointBonus);

        deconnexion(co);

        return res;
    }

    public static void setNote(int id_note, float note) {

        Connection co;
        CallableStatement cst;

        co = connexion();

        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
        }

        try {
            cst = co.prepareCall("{ call setNote(?, ?) }");
            cst.setInt(1, id_note);
            cst.setFloat(2, note);
            cst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deconnexion(co);

    }

    public static void addCC() {
        MainActivity.attachLoadingFragment();

        Connection co;
        CallableStatement cst;

        co = connexion();

        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
        }

        try {
            cst = co.prepareCall("{ call addCC(?, ?) }");
            cst.setInt(1, ClasseSingleton.getId());
            cst.setInt(2, MatiereSingleton.getId());
            cst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deconnexion(co);

        MainActivity.detachLoadingFragment();
    }

    public static void addDS() {
        MainActivity.attachLoadingFragment();

        Connection co;
        CallableStatement cst;

        co = connexion();

        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
        }

        try {
            cst = co.prepareCall("{ call addDS(?, ?) }");
            cst.setInt(1, ClasseSingleton.getId());
            cst.setInt(2, MatiereSingleton.getId());
            cst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deconnexion(co);

        MainActivity.detachLoadingFragment();
    }

    public static void setPointBonus(int id_personne, int point, String description) {
        MainActivity.attachLoadingFragment();

        Connection co;
        CallableStatement cst;

        co = connexion();

        if (co == null) {
            MainActivity.errorConnexion();
            try {
                Thread.sleep(TIME_ERROR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity.detachLoadingFragment();
            MainActivity.resetLoading();
        }

        try {
            cst = co.prepareCall("{ call setPointBonus(?, ?, ?, ?) }");
            cst.setInt(1, id_personne);
            cst.setInt(2, MatiereSingleton.getId());
            cst.setString(3, description);
            cst.setInt(4, MatiereSingleton.getId());
            cst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deconnexion(co);

        MainActivity.detachLoadingFragment();
    }
}

