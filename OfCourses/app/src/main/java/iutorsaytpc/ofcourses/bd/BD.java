package iutorsaytpc.ofcourses.bd;

import android.os.AsyncTask;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.concurrent.ExecutionException;

import iutorsaytpc.ofcourses.MainActivity;

public class BD {

    private static final String URL_BD = "jdbc:oracle:thin:gmarti3/coucouboss@oracle.iut-orsay.fr:1521:etudom";

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

    public static String getNomClasse(final int id_personne) {

        MainActivity.attachLoadingFragment();

        String res = null;
        Connection co;
        CallableStatement cst;

        co = connexion();
        if (co == null) return null;

        try {
            cst = co.prepareCall("{ ? = call getNomSaClasse(?) }");
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.setInt(2, id_personne);
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
}
