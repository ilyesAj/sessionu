package test;

import Session.Classe;
import Session.Creneau;
import Session.SessionImplementation;
import Session.UniteEnseignement;

public class TestCase {
	public static void main(String[] args) throws ClassNotFoundException {
		SessionImplementation si = new SessionImplementation();
		si.initDatabase();
		System.out.println(Classe.getAll());
		System.out.println(Creneau.getAll());
		System.out.println(UniteEnseignement.getAll());
		String EU = "{ \"code\":\"1243\",\"intitule\":\"test\",\"cours\":\"1.5\",\"tp\":\"1.5\",\"td\":\"1.5\",\"valeur\":\"1.5\" }";
		String resEu = si.createEU(EU);
		System.out.println(resEu);

		String creneau = "{\"debut\":\"10:20\",\"fin\":\"10:21\",\"jour\":\"2016-10-08\"}";
		String resCr = si.createCreneau(creneau);
		System.out.println(resCr);

		String classe = "{ \"promotion\":\"1\",\"filiere\":\"INFO\"}";
		String resCl = si.createClasse(classe);
		System.out.println(resCl);

		String session = "{ " + resEu.replace("id", "UE").substring(1, resEu.length() - 1) + ","
				+ resCr.replace("id", "creneau").substring(1, resCr.length() + 4) + ","
				+ resCl.replace("id", "classe").substring(1, resCl.length() + 3) + "}";

		String crSres = si.createSession(session);
		
		System.out.println(si.changeCreneauSession(crSres));
		System.out.println(si.createSessionCreneau(crSres));

		String dlSres = si.createSession(session);
		System.out.println(dlSres);

		System.out.println(si.deleteEU(resEu.replace("id", "UUID")));

		System.out.println(si.deleteCreneau(resCr.replace("id", "UUID")));

		System.out.println(si.deleteClasse(resCl.replace("id", "UUID")));
		
	}
}
