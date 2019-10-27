// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.json.*;

/************************************************************/
/**
 * 
 */
public class SessionImplementation implements SessionInterface {

	public void initDatabase() {
		Connection conn = null;
		String url = "jdbc:sqlite:data.db";
		try {

			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		String sql = "CREATE TABLE IF NOT EXISTS Personne(ID TEXT PRIMARY KEY,prenom TEXT,nom TEXT,mail TEXT,status TEXT)";
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("personne table created");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		sql = "CREATE TABLE IF NOT EXISTS UniteEnseignement(ID TEXT PRIMARY KEY,code TEXT,intitule TEXT,cours REAL,td REAL,tp REAL,valeur REAL)";
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("Ue table created");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		sql = "CREATE TABLE IF NOT EXISTS classe(ID TEXT PRIMARY KEY,promotion NUMBER,filiere TEXT)";
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("classe table created");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		sql = "CREATE TABLE IF NOT EXISTS CRENEAU(ID TEXT PRIMARY KEY,debut TEXT,fin TEXT,jour TEXT,classe TEXT,uniteEnseignement TEXT, FOREIGN KEY(classe) REFERENCES classe(id),FOREIGN KEY(uniteEnseignement) REFERENCES UniteEnseignement(id))";
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("patient created");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String createEU(String JSONEntry) {
		// TODO Auto-generated method stub
		String intitule = null;
		float tp = 0;
		float cours = 0;
		float valeur = 0;
		float td = 0;
		String code = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			code = obj.getString("code");
			intitule = obj.getString("intitule");
			cours = Float.parseFloat(obj.getString("cours"));
			td = Float.parseFloat(obj.getString("td"));
			tp = Float.parseFloat(obj.getString("tp"));
			valeur = Float.parseFloat(obj.getString("valeur"));

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: code,intitule,cours,td,tp,valeur");

		}
		String id = UUID.randomUUID().toString();
		UniteEnseignement UE = new UniteEnseignement(id, code, intitule, cours, td, tp, valeur);

		String ret = "{ \"id\": \"" + id + "\"";

		return ret;
	}

	@Override
	public String createCreneau(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createClasse(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSession(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeCreneauSession(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSessionCreneau(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEU(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCreneau(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSession(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteClasse(String JSONEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		SessionImplementation s = new SessionImplementation();
		s.initDatabase();
	}
};
