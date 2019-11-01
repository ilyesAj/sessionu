package IHM;

import javax.swing.JFrame;

import org.json.JSONObject;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Session.SessionImplementation;
import javax.swing.JTextArea;

public class MainWindow {

	public JFrame frame;
	/**
	 * Create the application.
	 */
	public MainWindow(SessionImplementation sess) {

		initialize(sess);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(SessionImplementation sess) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JFormattedTextField promotion = new JFormattedTextField();
		promotion.setText("Promotion");
		promotion.setBounds(6, 6, 79, 26);
		frame.getContentPane().add(promotion);
		
		JFormattedTextField filiere = new JFormattedTextField();
		filiere.setText("Filiere");
		filiere.setBounds(85, 6, 79, 26);
		frame.getContentPane().add(filiere);
		

		
		JFormattedTextField debut = new JFormattedTextField();
		debut.setText("Debut");
		debut.setBounds(6, 34, 79, 26);
		frame.getContentPane().add(debut);
		
		JFormattedTextField fin = new JFormattedTextField();
		fin.setText("Fin");
		fin.setBounds(85, 34, 79, 26);
		frame.getContentPane().add(fin);
		
		JFormattedTextField jour = new JFormattedTextField();
		jour.setText("Jour");
		jour.setBounds(167, 34, 79, 26);
		frame.getContentPane().add(jour);
		
		
		
		JFormattedTextField code = new JFormattedTextField();
		code.setText("Code");
		code.setBounds(6, 63, 79, 26);
		frame.getContentPane().add(code);
		
		JFormattedTextField intitule = new JFormattedTextField();
		intitule.setText("Intitule");
		intitule.setBounds(85, 63, 79, 26);
		frame.getContentPane().add(intitule);
		
		JFormattedTextField td = new JFormattedTextField();
		td.setText("TD");
		td.setBounds(167, 63, 28, 26);
		frame.getContentPane().add(td);
		
		JFormattedTextField tp = new JFormattedTextField();
		tp.setText("TP");
		tp.setBounds(194, 63, 25, 26);
		frame.getContentPane().add(tp);
		
		JFormattedTextField cours = new JFormattedTextField();
		cours.setText("Cours");
		cours.setBounds(218, 63, 57, 26);
		frame.getContentPane().add(cours);
		
		JFormattedTextField valeur = new JFormattedTextField();
		valeur.setText("Valeur");
		valeur.setBounds(277, 63, 52, 26);
		frame.getContentPane().add(valeur);
		
		JFormattedTextField idClasse = new JFormattedTextField();
		idClasse.setText("ID");
		idClasse.setBounds(6, 186, 79, 26);
		frame.getContentPane().add(idClasse);
		
		
		JFormattedTextField idCreneau = new JFormattedTextField();
		idCreneau.setText("ID");
		idCreneau.setBounds(6, 214, 79, 26);
		frame.getContentPane().add(idCreneau);
		
		
		JFormattedTextField idUE = new JFormattedTextField();
		idUE.setText("ID");
		idUE.setBounds(6, 243, 79, 26);
		frame.getContentPane().add(idUE);
		
		
		
		JFormattedTextField iclasse = new JFormattedTextField();
		iclasse.setText("ID Classe");
		iclasse.setBounds(6, 90, 79, 26);
		frame.getContentPane().add(iclasse);
		
		JFormattedTextField iCreneau = new JFormattedTextField();
		iCreneau.setText("ID creneau");
		iCreneau.setBounds(85, 90, 79, 26);
		frame.getContentPane().add(iCreneau);
		
		JFormattedTextField iEU = new JFormattedTextField();
		iEU.setText("ID UE");
		iEU.setBounds(167, 90, 79, 26);
		frame.getContentPane().add(iEU);
		
		
		JFormattedTextField idSession = new JFormattedTextField();
		idSession.setText("ID");
		idSession.setBounds(6, 157, 79, 26);
		frame.getContentPane().add(idSession);
		
		JTextArea console = new JTextArea();
		console.setEditable(false);
		console.setBounds(6, 342, 438, 230);
		frame.getContentPane().add(console);
		
		JButton dSession = new JButton("Supprimer session");
		dSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idSession.getText();
				JSONObject obj = new JSONObject();
				obj.put("UUID", id);
				console.setText("");
				JSONObject objRet = new JSONObject(sess.deleteSession(obj.toString()));
				if (objRet.getString("result").equals("done"))
					console.append("Session supprimée, id :"+ id);
				else
					console.append("Erreur suppression vérfier l'exitence de l'id");
				//TODO: Test me please, belllah :'(
				
			}
		});
		dSession.setBounds(286, 157, 158, 29);
		frame.getContentPane().add(dSession);
		
		JButton vSession = new JButton("Afficher session");
		vSession.setBounds(140, 157, 135, 29);
		frame.getContentPane().add(vSession);
		
		JButton cSession = new JButton("Creer session");
		cSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String icr = iCreneau.getText();
				String icl = iclasse.getText();
				String iue = iEU.getText();
				JSONObject obj = new JSONObject();
				obj.put("UE", iue);
				obj.put("classe", icl);
				obj.put("creneau", icr);
				sess.createSession(obj.toString());
				//TODO: Find a solution for session creation
			}
		});
		cSession.setBounds(327, 90, 117, 29);
		frame.getContentPane().add(cSession);
		
		JButton dUE = new JButton("Supprimer UE");
		dUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idUE.getText();
				JSONObject obj = new JSONObject();
				obj.put("UUID", id);
				console.setText("");
				JSONObject objRet = new JSONObject(sess.deleteEU(obj.toString()));
				if (objRet.getString("result").equals("done"))
					console.append("UE supprimé, id :"+ id);
				else
					console.append("Erreur suppression vérfier l'exitence de l'id");
			}
		});
		dUE.setBounds(286, 243, 158, 29);
		frame.getContentPane().add(dUE);
		
		JButton vClasse = new JButton("Afficher classe");
		vClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		vClasse.setBounds(140, 186, 135, 29);
		frame.getContentPane().add(vClasse);
		
		JButton vCreneau = new JButton("Afficher creneau");
		vCreneau.setBounds(140, 214, 135, 29);
		frame.getContentPane().add(vCreneau);
		
		JButton vUE = new JButton("Afficher UE");
		vUE.setBounds(140, 243, 135, 29);
		frame.getContentPane().add(vUE);
		
		JButton dCreneau = new JButton("Supprimer creneau");
		dCreneau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idCreneau.getText();
				JSONObject obj = new JSONObject();
				obj.put("UUID", id);
				console.setText("");
				JSONObject objRet = new JSONObject(sess.deleteCreneau(obj.toString()));
				if (objRet.getString("result").equals("done"))
					console.append("Creneau supprimé, id :"+ id);
				else
					console.append("Erreur suppression vérfier l'exitence de l'id");
				//TODO: This needs testing (not tested)
			}
		});
		dCreneau.setBounds(286, 214, 158, 29);
		frame.getContentPane().add(dCreneau);
		
		JButton dClasse = new JButton("Supprimer classe");
		dClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idClasse.getText();
				JSONObject obj = new JSONObject();
				obj.put("UUID", id);
				console.setText("");
				JSONObject objRet = new JSONObject(sess.deleteClasse(obj.toString()));
				if (objRet.getString("result").equals("done"))
					console.append("Classe supprimé, id :"+ id);
				else
					console.append("Erreur suppression vérfier l'exitence de l'id");
			}
		});
		dClasse.setBounds(286, 186, 158, 29);
		frame.getContentPane().add(dClasse);
		
		JButton cUE = new JButton("Creer UE");
		cUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String co = code.getText();
				String inti = intitule.getText();
				String cou = cours.getText();
				String TD = td.getText();
				String TP = tp.getText();
				String val = valeur.getText();
				JSONObject obj = new JSONObject();
				obj.put("code", co);
				obj.put("intitule", inti);
				obj.put("cours", cou);
				obj.put("td", TD);
				obj.put("tp", TP);
				obj.put("valeur", val);
				console.setText("");
				JSONObject objRet = new JSONObject(sess.createEU(obj.toString()));
				console.append("UE créé, id :"+ objRet.getString("id")); 
				
			}
		});
		cUE.setBounds(327, 63, 117, 29);
		frame.getContentPane().add(cUE);
		
		JButton cCreneau = new JButton("Creer creneau");
		cCreneau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deb = debut.getText();
				String fi = fin.getText();
				String jo = jour.getText();
				JSONObject obj = new JSONObject();
				obj.put("debut", deb);
				obj.put("fin", fi);
				obj.put("jour", jo);
				//TODO: Test this shit !
				console.setText("");
				JSONObject objRet = new JSONObject(sess.createCreneau(obj.toString()));
				console.append("Creneau créé, id :"+ objRet.getString("id")); 
				
			}
		});
		cCreneau.setBounds(327, 34, 117, 29);
		frame.getContentPane().add(cCreneau);
		
		JButton cClasse = new JButton("Creer classe");
		cClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String promo = promotion.getText();
				String fil = filiere.getText();
				JSONObject obj = new JSONObject();
				obj.put("promotion", promo);
				obj.put("filiere", fil);
				console.setText("");
				JSONObject objRet = new JSONObject(sess.createClasse(obj.toString()));
				console.append("Classe créé, id :"+ objRet.getString("id")); 
				
			}
		});
		cClasse.setBounds(327, 6, 117, 29);
		frame.getContentPane().add(cClasse);
		
		JButton lSession = new JButton("Lister sessions");
		lSession.setBounds(6, 281, 127, 29);
		frame.getContentPane().add(lSession);
		
		JButton lClasse = new JButton("Lister classes");
		lClasse.setBounds(162, 281, 127, 29);
		frame.getContentPane().add(lClasse);
		
		JButton lCreneau = new JButton("Lister creneaux");
		lCreneau.setBounds(317, 281, 127, 29);
		frame.getContentPane().add(lCreneau);
		
		JButton lUE = new JButton("Lister UE");
		lUE.setBounds(162, 311, 127, 29);
		frame.getContentPane().add(lUE);
		
		
	}
}
