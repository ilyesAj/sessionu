package test;

import Session.SessionImplementation;

public class TestCase {
public static void main(String[] args) {
	SessionImplementation si=new SessionImplementation();
	si.initDatabase();
	
	
	String EU= "{ \"code\":\"1243\",\"intitule\":\"test\",\"cours\":\"1.5\",\"tp\":\"1.5\",\"td\":\"1.5\",\"valeur\":\"1.5\" }"; 
	String resEu=si.createEU(EU);
	System.out.println(resEu);
	
	String creneau="{\"debut\":\"10:20\",\"fin\":\"10:21\",\"jour\":\"2016-10-08\"}";
	String resCr=si.createCreneau(creneau);
	System.out.println(resCr);
	
	String classe="{ \"promotion\":\"1\",\"filiere\":\"INFO\"}";
	String resCl=si.createClasse(classe);
	System.out.println(resCl);
	
	//System.out.println(si.createSession(String JSONEntry));
	//System.out.println(si.changeCreneauSession(String JSONEntry));
	//System.out.println(si.createSessionCreneau(String JSONEntry));
	//System.out.println(si.deleteEU(String JSONEntry));
	//	System.out.println(si.deleteCreneau(String JSONEntry));
	//	System.out.println(si.deleteSession(String JSONEntry));
	//	System.out.println(si.deleteClasse(String JSONEntry));
	

	
	System.out.println(si.deleteEU(resEu.replace("id","UUID")));
	
	
	System.out.println(si.deleteCreneau(resCr.replace("id","UUID")));
	
	
	System.out.println(si.deleteClasse(resCl.replace("id","UUID")));
	
}
}
