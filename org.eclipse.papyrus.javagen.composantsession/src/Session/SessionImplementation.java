
// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

/************************************************************/
/**
 * 
 */
public class SessionImplementation implements SessionInterface {

	public void initDatabase() throws ClassNotFoundException {
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
			System.out.println("CRENEAU table created");
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
		String id = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {
			id = obj.getString("id");
			code = obj.getString("code");
			intitule = obj.getString("intitule");
			cours = Float.parseFloat(obj.getString("cours"));
			td = Float.parseFloat(obj.getString("td"));
			tp = Float.parseFloat(obj.getString("tp"));
			valeur = Float.parseFloat(obj.getString("valeur"));

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: code,intitule,cours,td,tp,valeur");

		}
		UniteEnseignement UE = new UniteEnseignement(id, code, intitule, cours, td, tp, valeur);

		UE.save();

		String ret = "{ \"id\": \"" + id + "\"}";

		return ret;
	}

	@Override
	public String createCreneau(String JSONEntry) {
		// TODO Auto-generated method stub
		// init
		String id = null;
		LocalTime debut = LocalTime.now();
		LocalTime fin = LocalTime.now();
		LocalDate jour = LocalDate.now();
		// parse
		JSONObject obj = new JSONObject(JSONEntry);
		id = obj.getString("id");
		debut = LocalTime.parse(obj.getString("debut"));
		fin = LocalTime.parse(obj.getString("fin"));

		jour = LocalDate.parse(obj.getString("jour"));
		// create json
		Creneau creneau = new Creneau(id, debut, fin, jour);
		String ret = "{ \"id\": \"" + id + "\"}";
		creneau.save();
		return ret;

	}

	@Override
	public String createClasse(String JSONEntry) {
		// TODO Auto-generated method stub

		int promotion = 0;
		String filiere = null;
		String id = null;
		JSONObject obj = new JSONObject(JSONEntry);

		try {
			id = obj.getString("id");
			promotion = Integer.parseInt(obj.getString("promotion"));
			filiere = obj.getString("filiere");
		} catch (JSONException e) {

			System.out.println("Unexpected json file, should be: promotion,filiere");

		}
		
		Classe classe = new Classe(id, promotion, filiere);

		String ret = "{ \"id\": \"" + id + "\"}";
		classe.save();
		return ret;

	}

	@Override
	public String createSession(String JSONEntry) {
		// TODO Auto-generated method stub
		String UE = null;
		String classe = null;
		String creneau = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			UE = obj.getString("UE");
			classe = obj.getString("classe");
			creneau = obj.getString("creneau");

			UniteEnseignement ue = UniteEnseignement.getById(UE);
			Classe objClasse = Classe.getById(classe);
			Creneau cr = Creneau.getById(creneau);

			cr.setUniteEnseignement(ue);
			cr.setClasse(objClasse);
			cr.update();

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: UE,classe,creneau");

		}
		// String id = UUID.randomUUID().toString();

		// String ret = "{ \"id\": \"" + id + "\"}";
		return "{ \"result\": \"done\"}";
		// return ret;
	}

	@Override
	public String changeCreneauSession(String JSONEntry) {

		// TODO Auto-generated method stub
		String UE = null;
		String classe = null;
		String creneau = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			classe = obj.getString("classe");
			creneau = obj.getString("creneau");

			Classe objClasse = Classe.getById(classe);
			Creneau cr = Creneau.getById(creneau);

			cr.setClasse(objClasse);
			cr.update();

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: UE,classe,creneau");

		}
		// String id = UUID.randomUUID().toString();

		// String ret = "{ \"id\": \"" + id + "\"}";
		return "{ \"result\": \"done\"}";
		// return ret;

	}

	@Override
	public String createSessionCreneau(String JSONEntry) {
		// TODO Auto-generated method stub
		String UE = null;
		String classe = null;
		String creneau = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			classe = obj.getString("classe");
			creneau = obj.getString("creneau");

			Classe objClasse = Classe.getById(classe);
			Creneau cr = Creneau.getById(creneau);

			cr.setClasse(objClasse);
			cr.update();

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: UE,classe,creneau");

		}
		// String id = UUID.randomUUID().toString();

		// String ret = "{ \"id\": \"" + id + "\"}";
		return "{ \"result\": \"done\"}";
		// return ret;
	}

	@Override
	public String deleteEU(String JSONEntry) {
		// TODO Auto-generated method stub
		String UUID = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			UUID = obj.getString("UUID");

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: UUID");

		}
		try {
			UniteEnseignement.getById(UUID).delete();
			return "{ \"result\": \"done\" ,  \"type\": \"EU\" , \"UUID\": \"" + UUID + "\"  }";

		} catch (Exception e) {
			return "{ \"result\": \"error\" ,  \"type\": \"EU inexistant\"   }";

		}
	}

	@Override
	public String deleteCreneau(String JSONEntry) {
		// TODO Auto-generated method stub
		String UUID = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			UUID = obj.getString("UUID");

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: UUID");

		}
		try {
			Creneau.getById(UUID).delete();
			return "{ \"result\": \"done\" ,  \"type\": \"creneau\" , \"UUID\": \"" + UUID + "\"  }";
		} catch (Exception e) {
			return "{ \"result\": \"error\" ,  \"type\": \"creneau inexistant\"   }";

		}
	}

	@Override
	public String deleteSession(String JSONEntry) {
		// TODO Auto-generated method stub
		String UE = null;
		String classe = null;
		String creneau = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			UE = obj.getString("UE");
			classe = obj.getString("classe");
			creneau = obj.getString("creneau");

			UniteEnseignement ue = UniteEnseignement.getById(UE);
			Classe objClasse = Classe.getById(classe);
			Creneau cr = Creneau.getById(creneau);

			cr.setUniteEnseignement(null);
			cr.setClasse(null);
			cr.update();

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: UE,classe,creneau");

		}
		// String id = UUID.randomUUID().toString();

		// String ret = "{ \"id\": \"" + id + "\"}";
		return "{ \"result\": \"done\"}";
		// return ret;
	}

	@Override
	public String deleteClasse(String JSONEntry) {
		// TODO Auto-generated method stub
		String UUID = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			UUID = obj.getString("UUID");

		} catch (JSONException e) {
			System.out.println("Unexpected json file, should be: UUID");

		}
		try {
			Classe.getById(UUID).delete();
			return "{ \"result\": \"done\" ,  \"type\": \"classe\" , \"UUID\": \"" + UUID + "\"  }";
		} catch (Exception e) {
			return "{ \"result\": \"error\" ,  \"type\": \"classe inexistante\"   }";

		}
	}

	@Override
	public String getClasse(String JSONEntry) {
		// TODO This should return JSON
		JSONObject obj = new JSONObject(JSONEntry);
		Classe x = Classe.getById(obj.getString("id"));
		if (x != null)
		{	
			int promo = x.getPromotion();
			String fil = x.getFiliere();
			obj.put("promotion", String.valueOf(promo));
			obj.put("filiere", fil);
			return obj.toString() ;
		}
		else
			return "erreur"; 
		
	}

	@Override
	public String getSession(String JSONEntry) {
		// TODO This should return JSON
		JSONObject obj = new JSONObject(JSONEntry);
		//TODO we need to find a way to show sessions (not yet implemented)
		return " " ;
	}

	@Override
	public String getCreneau(String JSONEntry) {
		// TODO This should return JSON
		JSONObject obj = new JSONObject(JSONEntry);
		Creneau x = Creneau.getById(obj.getString("id"));
		if (x != null)
		{
			LocalTime deb = x.getDebut();
			LocalTime fi = x.getFin();
			LocalDate jo = x.getJour();
			obj.put("debut", deb.toString());
			obj.put("fin",fi.toString());
			obj.put("jour", jo.toString());
			return obj.toString() ;
		}
		else
			return "erreur"; 
			
	}

	@Override
	public String getUE(String JSONEntry) {
		// TODO This should return JSON
		JSONObject obj = new JSONObject(JSONEntry);
		UniteEnseignement x = UniteEnseignement.getById(obj.getString("id"));
		if (x != null)
		{
			obj.put("code", x.getCode());
			obj.put("intitule", x.getIntitule());
			obj.put("cours", String.valueOf(x.getCours()));
			obj.put("td", String.valueOf(x.getTd()));
			obj.put("tp", String.valueOf(x.getTp()));
			obj.put("valeur", String.valueOf(x.getValeur()));
			return obj.toString() ;
		}
		else
			return "erreur"; 
		
		
	}

	@Override
	public String listClasse() {
		// TODO Auto-generated method stub
		List <Classe>  x = Classe.getAll();
		String resp = "";
		for (int i= 0 ; i < x.size();i++)
		{
			resp = resp + x.get(i).toString();
		}
		JSONObject obj = new JSONObject();
		obj.put("response", resp);
		
		return obj.toString();
	}

	@Override
	public String listSession() {
		// TODO Auto-generated method stub
		return null;
		// TODO list all Sessions
	}

	@Override
	public String listCreneau() {
		// TODO Auto-generated method stub
		List <Creneau>  x = Creneau.getAll();
		String resp = "";
		for (int i= 0 ; i < x.size();i++)
		{
			resp = resp + x.get(i).toString();
		}
		JSONObject obj = new JSONObject();
		obj.put("response", resp);
		
		return obj.toString();
	}

	@Override
	public String listEU() {
		// TODO Auto-generated method stub
		List <UniteEnseignement>  x = UniteEnseignement.getAll();
		String resp = "";
		for (int i= 0 ; i < x.size();i++)
		{
			resp = resp + x.get(i).toString();
		}
		JSONObject obj = new JSONObject();
		obj.put("response", resp);
		
		return obj.toString();
	}
	
	//TODO methods that returns all of everything.

	

};
