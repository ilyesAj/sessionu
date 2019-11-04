package IHM;

import javax.swing.JFrame;

import org.json.JSONObject;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
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
		frame.setBounds(100, 100, 600, 600);
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
		td.setBounds(167, 63, 79, 26);
		frame.getContentPane().add(td);
		
		JFormattedTextField tp = new JFormattedTextField();
		tp.setText("TP");
		tp.setBounds(247, 63, 79, 26);
		frame.getContentPane().add(tp);
		
		JFormattedTextField cours = new JFormattedTextField();
		cours.setText("Cours");
		cours.setBounds(332, 63, 79, 26);
		frame.getContentPane().add(cours);
		
		JFormattedTextField valeur = new JFormattedTextField();
		valeur.setText("Valeur");
		valeur.setBounds(411, 63, 68, 26);
		frame.getContentPane().add(valeur);
		
		JFormattedTextField idClasse = new JFormattedTextField();
		idClasse.setText("ID");
		idClasse.setBounds(6, 186, 295, 26);
		frame.getContentPane().add(idClasse);
		
		
		JFormattedTextField idCreneau = new JFormattedTextField();
		idCreneau.setText("ID");
		idCreneau.setBounds(6, 214, 295, 26);
		frame.getContentPane().add(idCreneau);
		
		
		JFormattedTextField idUE = new JFormattedTextField();
		idUE.setText("ID");
		idUE.setBounds(6, 243, 295, 26);
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
		idSession.setBounds(6, 157, 295, 26);
		frame.getContentPane().add(idSession);
		
		JTextArea console = new JTextArea();
		console.setEditable(false);
		console.setBounds(6, 342, 588, 230);
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
		dSession.setBounds(436, 157, 158, 29);
		frame.getContentPane().add(dSession);
		
		JButton vSession = new JButton("Afficher session");
		vSession.setBounds(303, 157, 135, 29);
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
		cSession.setBounds(477, 90, 117, 29);
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
		dUE.setBounds(436, 243, 158, 29);
		frame.getContentPane().add(dUE);
		
		JButton vClasse = new JButton("Afficher classe");
		vClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject req = new JSONObject();
				req.put("id", idClasse.getText());
				String rep = sess.getClasse(req.toString());
				if (!rep.equals("erreur"))
				{
					JSONObject view  = new JSONObject(rep);
					String id = view.getString("id");
					int promotion = Integer.parseInt(view.getString("promotion"));
					String filiere = view.getString("filiere");
					console.setText("");
					console.append("ID;Promotion;Filiere \n");
					console.append(""+id+" ; "+ promotion +" ; "+ filiere +"\n");
				}
				else
				{
					console.setText("");
					console.append("Erreur affichage vérfier l'exitence de l'id\n");
				}
					
				
			}
		});
		vClasse.setBounds(303, 186, 135, 29);
		frame.getContentPane().add(vClasse);
		
		JButton vCreneau = new JButton("Afficher creneau");
		vCreneau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject req = new JSONObject();
				req.put("id", idCreneau.getText());
				String rep = sess.getCreneau(req.toString());
				if (!rep.equals("erreur"))
				{
					JSONObject view  = new JSONObject(rep);
					String id = view.getString("id");
					LocalTime debut = LocalTime.parse(view.getString("debut"));
					LocalTime fin = LocalTime.parse(view.getString("fin"));
					LocalDate jour = LocalDate.parse(view.getString("jour"));
					console.setText("");
					console.append("ID;debut;fin;jour \n");
					console.append(""+id+" ; "+ debut +" ; "+ fin +" ; "+jour +"\n");
				}
				else
				{
					console.setText("");
					console.append("Erreur affichage vérfier l'exitence de l'id\n");
				}
				
			}
		});
		vCreneau.setBounds(303, 214, 135, 29);
		frame.getContentPane().add(vCreneau);
		
		JButton vUE = new JButton("Afficher UE");
		vUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject req = new JSONObject();
				req.put("id", idUE.getText());
				String rep = sess.getUE(req.toString());
				if (!rep.equals("erreur"))
				{
					JSONObject view  = new JSONObject(rep);
					String id = view.getString("id");
					String code = view.getString("code");
					String intitule = view.getString("intitule");
					float cours = Float.parseFloat(view.getString("cours"));
					float td = Float.parseFloat(view.getString("td"));
					float tp = Float.parseFloat(view.getString("tp"));
					float valeur = Float.parseFloat(view.getString("valeur"));
					console.setText("");
					console.append("ID;code;intitule;cours;td;tp;valeur \n");
					console.append(""+id+" ; "+ code +" ; "+ intitule +" ; "+cours +" ; "+td+ " ; "+ tp +" ; "+valeur+"\n");
				}
				else
				{
					console.setText("");
					console.append("Erreur affichage vérfier l'exitence de l'id\n");
				}
			}
		});
		vUE.setBounds(303, 243, 135, 29);
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
		dCreneau.setBounds(436, 214, 158, 29);
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
		dClasse.setBounds(436, 186, 158, 29);
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
				String id = UUID.randomUUID().toString();
				JSONObject obj = new JSONObject();
				obj.put("id", id);
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
		cUE.setBounds(477, 63, 117, 29);
		frame.getContentPane().add(cUE);
		
		JButton cCreneau = new JButton("Creer creneau");
		cCreneau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = UUID.randomUUID().toString();
				String deb = debut.getText();
				String fi = fin.getText();
				String jo = jour.getText();
				JSONObject obj = new JSONObject();
				obj.put("id", id);
				obj.put("debut", deb);
				obj.put("fin", fi);
				obj.put("jour", jo);
				//TODO: Test this shit !
				console.setText("");
				JSONObject objRet = new JSONObject(sess.createCreneau(obj.toString()));
				console.append("Creneau créé, id :"+ objRet.getString("id")); 
				
			}
		});
		cCreneau.setBounds(477, 34, 117, 29);
		frame.getContentPane().add(cCreneau);
		
		JButton cClasse = new JButton("Creer classe");
		cClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String promo = promotion.getText();
				String fil = filiere.getText();
				String id = UUID.randomUUID().toString();
				JSONObject obj = new JSONObject();
				obj.put("id", id);
				obj.put("promotion", promo);
				obj.put("filiere", fil);
				console.setText("");
				JSONObject objRet = new JSONObject(sess.createClasse(obj.toString()));
				console.append("Classe créé, id :"+ objRet.getString("id")); 
				
			}
		});
		cClasse.setBounds(477, 6, 117, 29);
		frame.getContentPane().add(cClasse);
		
		JButton lSession = new JButton("Lister sessions");
		lSession.setBounds(6, 281, 127, 29);
		frame.getContentPane().add(lSession);
		
		JButton lClasse = new JButton("Lister classes");
		lClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ls = sess.listClasse();
				JSONObject obj = new JSONObject(ls);
				console.setText("");
				console.append("ID;Promotion;Filiere \n");
				console.append(obj.getString("response"));
				
			}
		});
		lClasse.setBounds(162, 281, 127, 29);
		frame.getContentPane().add(lClasse);
		
		JButton lCreneau = new JButton("Lister creneaux");
		lCreneau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ls = sess.listCreneau();
				JSONObject obj = new JSONObject(ls);
				console.setText("");
				console.append("ID;debut;fin;jour \n");
				console.append(obj.getString("response"));
			}
		});
		lCreneau.setBounds(317, 281, 127, 29);
		frame.getContentPane().add(lCreneau);
		
		JButton lUE = new JButton("Lister UE");
		lUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ls = sess.listEU();
				JSONObject obj = new JSONObject(ls);
				console.setText("");
				console.append("ID;code;intitule;cours;td;tp;valeur \n");
				console.append(obj.getString("response"));
			}
		});
		lUE.setBounds(467, 281, 127, 29);
		frame.getContentPane().add(lUE);
		
		
	}
}
