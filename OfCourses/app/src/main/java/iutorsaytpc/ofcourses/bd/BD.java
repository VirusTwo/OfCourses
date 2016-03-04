package iutorsaytpc.ofcourses.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	
	public static Connection connexion(String url) {
		Connection co=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co=DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e) {
			System.out.println("Driver oracle introuvable");
			e.printStackTrace();
			System.exit(1);
		}
		catch(SQLException e) {
			System.out.println("Impossible de se connecter à l'url "+url);
			e.printStackTrace();
			System.exit(1);
		}
		return co;
	}
	
	public static ResultSet requete(String requete, Connection co, int type) {
		ResultSet res=null;
		try {
			Statement st;
			if(type==0) st=co.createStatement();
			else st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			res=st.executeQuery(requete);
		}
		catch(SQLException e) {
			System.out.println("Problème lors de l'execution de la requête "+requete);
			e.printStackTrace();
		}
		return res;
	}
	
	public static void deconnexion(Connection co) {
		try {
			co.close();
			System.out.println("Connexion fermée.");
		} catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion.");
			e.printStackTrace();
		}
	}
}
