// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package Session;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.json.*;



/************************************************************/
/**
 * 
 */
public class SessionImplementation implements SessionInterface {

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
			
		}catch(JSONException e) {
			System.out.println("Unexpected json file, should be: code,intitule,cours,td,tp,valeur");
			
		}
		String id = UUID.randomUUID().toString();
		UniteEnseignement UE = new UniteEnseignement(id, code, intitule, cours, td, tp, valeur);

		String ret = "{ \"id\": \""+id+"\" }";
		
		return ret;
	}

	@Override
	public String createCreneau(String JSONEntry) {
		// TODO Auto-generated method stub
		//init
		String id =UUID.randomUUID().toString();
		LocalTime debut =LocalTime.of(0, 0, 0);
		LocalTime fin=LocalTime.of(0, 0, 0);;
		LocalDate jour=LocalDate.of(0, 0, 0);
		//parse
		JSONObject obj = new JSONObject(JSONEntry);
		debut =LocalTime.parse(obj.getString("debut"));
		fin=LocalTime.parse(obj.getString("fin"));
		jour=LocalDate.parse(obj.getString("jour"));
		//create json
		Creneau C = new Creneau(id, debut, fin, jour);
		String ret = "{ \"id\": \""+id+"\"";
		return ret;
	}

	@Override
	public String createClasse(String JSONEntry) {
		// TODO Auto-generated method stub
	
		int promotion = 0;
		String filiere = null; 
		
		JSONObject obj = new JSONObject(JSONEntry);
		
		try {
			promotion = Integer.parseInt(obj.getString("promotion"));
			filiere = obj.getString("filiere");
		}catch(JSONException e) {
			
			System.out.println("Unexpected json file, should be: code,intitule,cours,td,tp,valeur");
			
		}
		String id = UUID.randomUUID().toString();
		Classe classe= new Classe(id,promotion,filiere);

		String ret = "{ \"id\": \""+id+"\"}";
			
		return ret;	

		
		
		
		
	}

	@Override
	public String createSession(String JSONEntry) {
		// TODO Auto-generated method stub
		String UE = null ;
		String classe = null;
		String creneau = null;
		JSONObject obj = new JSONObject(JSONEntry);
		try {

			UE = obj.getString("UE");
			classe = obj.getString("classe");
			creneau = obj.getString("creneau");
			
			
		}catch(JSONException e) {
			System.out.println("Unexpected json file, should be: UE,classe,creneau");
			
		}
		String id = UUID.randomUUID().toString();

		String ret = "{ \"id\": \""+id+"\"}";
		
		return ret;
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

	
};
