package iutorsaytpc.ofcourses.bd;

import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import iutorsaytpc.ofcourses.MainActivity;
import iutorsaytpc.ofcourses.modele.EnseignantSingleton;
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

	private ResultSet requeteSelect(String requete, Connection co, int type) {
		ResultSet res=null;
		try {
			Statement st;
			if(type==0) st=co.createStatement();
			else st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			res=st.executeQuery(requete);
		}
		catch(SQLException e) {
			System.out.println("Problème lors de l'execution de la requête " + requete);
			e.printStackTrace();
		}

		return res;
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

	private static void deconnexion(Connection co) {
		try {
			co.close();
			System.out.println("Connexion fermée.");
		} catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion.");
			e.printStackTrace();
		}
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
        if (co == null) return null;

        try {
            cst=co.prepareCall(" { call getCours(?, ?) } ");
            cst.setInt(1, EnseignantSingleton.getId());
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();
            resSet=((OracleCallableStatement)cst).getCursor(2);
            while(resSet.next()) {
                Date dateDebut = resSet.getDate(1);
                Date dateFin = resSet.getDate(2);
                String matiere = resSet.getString(3);
                String classe = resSet.getString(4);
                String salle = resSet.getString(5);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateDebut);
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                int heureDebut = dateDebut.getHours();
                int heureFin = dateFin.getHours();

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
        
        return res;
    }

    private static String getCode(int day, int heureDebut, int heureFin) {
        String res = "";

        switch (day) {
            case 0 :
                res += "lun";
                break;
            case 1 :
                res += "mar";
                break;
            case 2 :
                res += "mer";
                break;
            case 3 :
                res += "jeu";
                break;
            case 4 :
                res += "ven";
                break;
        }

        if(heureDebut >= 0 && heureDebut <= 9) res += "0";
        res += heureDebut;

        if(heureFin >= 0 && heureFin <= 9) res += "0";
        res += heureFin;

        return res;
    }

    //

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
        if (co == null) return null;

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
}

