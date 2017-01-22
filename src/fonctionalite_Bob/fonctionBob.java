package fonctionalite_Bob;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Connection_HTTP.HttpRequest;
import Data_Structure.JSON_Converter;
import fonctionalite_Dave.fonction_Dave;

public class fonctionBob {
	public ArrayList<String[]> Bob1(String mots) throws IOException, JSONException {

		ArrayList<String[]> tab = new ArrayList<String[]>();
		String requete = "?order=desc&sort=relevance&title=" + mots + "&site=stackoverflow";
		String resultat = HttpRequest.sendGet("https://api.stackexchange.com/2.2/similar", requete, false);
		JSONObject json = new JSONObject(resultat);
		JSONArray arr = json.getJSONArray("items");

		String questions[] = new String[10];
		String liens[] = new String[10];
		String tags[] = new String[10];

		for (int i = 0; i < 10; i++) {
			if (arr.getJSONObject(i).getString("title") == null)
				return tab;
			questions[i] = arr.getJSONObject(i).getString("title");
			liens[i] = arr.getJSONObject(i).getString("link");
			tags[i] = arr.getJSONObject(i).get("tags").toString();
		}

		tab.clear();
		tab.add(questions);
		tab.add(liens);
		tab.add(tags);
		return tab;
	}

	public ArrayList<String[]> Bob2(String id) throws IOException, JSONException {
		ArrayList<String[]> tab = new ArrayList<String[]>();
		String requete = id + "/top-tags?page=1&pagesize=3&site=stackoverflow";
		String resultat = HttpRequest.sendGet("https://api.stackexchange.com/2.2/users/", requete, false);
		JSONObject json = new JSONObject(resultat);
		JSONArray arr = json.getJSONArray("items");
		ArrayList<String> tags = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++) {
			tags.add(URLEncoder.encode(arr.getJSONObject(i).getString("tag_name"), "UTF-8"));
		}

		if (tags.isEmpty())
			return tab;
		fonction_Dave f = new fonction_Dave();

		tab = f.Dave(tags, "10", "1");
		return tab;

	}

	public ArrayList<String[]> Bob3(String id) throws IOException, JSONException {
		ArrayList<String[]> tab = new ArrayList<String[]>();
		String req;// La suite du programme vise a obtenir les tops tags de
					// l'utilisateur
		req = id + "/top-tags?page=1&pagesize=3&site=stackoverflow";// id =
																	// identifiant
																	// de
																	// l'utilisateur
		String json_str_top_tags = HttpRequest.sendGet("https://api.stackexchange.com/2.2/users/", req, false);// demande
																												// les
																												// tops
																												// tags
																												// �
																												// stack
																												// overflow
		HashMap<String, Object> JSON_Map_tags = JSON_Converter.jsonToMap(json_str_top_tags);// les
																							// 3
																							// lignes
																							// suivantes
																							// transforme
																							// la
																							// reponse
																							// de
																							// stack
																							// overflow
																							// en
																							// string
																							// plus
																							// facile
																							// �
																							// utiliser
		// System.out.println(JSON_Map_tags);
		Object it = JSON_Map_tags.get("items");
		String temp = it.toString();

		if (temp == "[]")
			return tab;

		int debut = temp.indexOf("tag_name");// localise l'emplacement du debut
												// du nom du premier tag
		int fin = temp.indexOf(" ", debut);// localise l'emplacement de la fin
											// du nom du premier tag
		String tag1 = temp.substring(debut + 9, fin - 1);// decoupe le premier
															// tag
		// System.out.println(tag1);
		debut = temp.indexOf("tag_name", fin);
		fin = temp.indexOf(" ", debut);
		String tag2 = temp.substring(debut + 9, fin - 1);// pareil pour le tag 2
		// System.out.println(tag2);
		debut = temp.indexOf("tag_name", fin);
		fin = temp.indexOf(" ", debut);
		String tag3 = temp.substring(debut + 9, fin - 1);// pareil pour le tag 3
		// System.out.println(tag3);
		tag1 = URLEncoder.encode(tag1, "UTF-8");// encodage des tags pour les
												// requetes aupres de stack
												// overflow
		tag2 = URLEncoder.encode(tag2, "UTF-8");
		tag3 = URLEncoder.encode(tag3, "UTF-8");
		req = "page=1&pagesize=50&order=desc&sort=activity&tagged=" + tag1 + "&site=stackoverflow";
		String questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions", req, true);// requete
																											// des
																											// 50
																											// dernieres
																											// questions
																											// ayant
																											// le
																											// tag
																											// 1.
																											// on
																											// en
																											// prend
																											// autant
																											// car
																											// on
																											// ne
																											// sais
																											// pas
																											// lesquelles
																											// ont
																											// deja
																											// des
																											// reponses
		// System.out.println(questions);

		String titres[] = new String[20];// Je stoque les titre l�
		String liens[] = new String[20];// Je stoque les liens l�
		String tags[] = new String[20];// Je stoque les tags l�
		int pos = 0;
		int nb = 0;
		String bidule = new String("true");// sers � verifier si la question a
											// deja des reponses

		for (int i = 0; i < 50; i++) {
			pos = questions.indexOf("is_answered", pos + 1);// localise le
															// marqueur
															// indiquant la
															// presence de
															// reponses
			// System.out.println(questions.substring(pos+13, pos+17));
			// System.out.println(questions.substring(pos+13,
			// pos+17).equals(bidule));
			if (questions.substring(pos + 13, pos + 17).equals(bidule)) {// verifie
																			// si
																			// la
																			// question
																			// a
																			// deja
																			// des
																			// reponses
				titres[nb] = Qtitle(questions, nb + 1);// on stoque les libell閟
														// des questions l�
				// System.out.println("question: ");
				// System.out.println(titres[nb]);
				liens[nb] = Qlink(questions, nb + 1);// on stoque les liens vers
														// les questions l�
				// System.out.println(liens[nb]);
				tags[nb] = Qtags(questions, nb + 1);// on stoque les tags des
													// questions l�
				// System.out.println("tags: ");
				// System.out.println(tags[nb]);
				nb++;
			}
			if (nb == 7)
				break;// lorsque l'on a obtenu 7 questions, on arr阾e
		}
		req = "page=1&pagesize=50&order=desc&sort=activity&tagged=" + tag2 + "&site=stackoverflow";
		questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions", req, true);// m阭e
																									// chose
																									// avec
																									// le
																									// tag
																									// 2
		nb = 0;
		pos = 0;
		for (int i = 0; i < 50; i++) {
			pos = questions.indexOf("is_answered", pos + 1);
			// System.out.println(questions.substring(pos+13, pos+17));
			// System.out.println(pos);
			if (questions.substring(pos + 13, pos + 17).equals(bidule)) {
				titres[nb + 7] = Qtitle(questions, nb + 1);
				// System.out.println("question: ");
				// System.out.println(titres[nb]);
				liens[nb + 7] = Qlink(questions, nb + 1);
				// System.out.println(liens[nb]);
				tags[nb + 7] = Qtags(questions, nb + 1);
				// System.out.println("tags: ");
				// System.out.println(tags[nb]);
				nb++;
			}
			if (nb == 7)
				break;
		}
		req = "page=1&pagesize=50&order=desc&sort=activity&tagged=" + tag3 + "&site=stackoverflow";
		questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions", req, true);// m阭e
																									// chose
																									// avec
																									// le
																									// tag
																									// 3
		nb = 0;
		pos = 0;
		for (int i = 0; i < 50; i++) {
			pos = questions.indexOf("is_answered", pos + 1);
			// System.out.println(questions.substring(pos+13, pos+17));
			if (questions.substring(pos + 13, pos + 17).equals(bidule)) {
				titres[nb + 14] = Qtitle(questions, nb + 1);
				// System.out.println("question: ");
				// System.out.println(titres[nb]);
				liens[nb + 14] = Qlink(questions, nb + 1);
				// System.out.println(liens[nb]);
				tags[nb + 14] = Qtags(questions, nb + 1);
				// System.out.println("tags: ");
				// System.out.println(tags[nb]);
				nb++;
			}
			if (nb == 6)
				break;
		}
		tab.clear();// on retourne les r閟ultats
		tab.add(titres);
		tab.add(liens);
		tab.add(tags);
		return (tab);
		/*
		 * for (int i = 0; i < 20; i++) { System.out.println(i + 1);
		 * System.out.println(titres[i]); System.out.println(liens[i]);
		 * System.out.println(tags[i]); }
		 */
	}

	public static String Qtags(String q, int num) {// cette fonction cherche la
													// num鑝e liste de tags dans
													// le string de retour de
													// stack overflow q et la
													// retourne
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("tags", fin);
			fin = q.indexOf("]", debut);
		}
		String res = q.substring(debut + 7, fin);
		return res;
	}

	public static String Qlink(String q, int num) {// cette fonction cherche le
													// num鑝e lien dans le
													// string q de retour de
													// stack overflowet la
													// retourne
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("link\":\"http://stackoverflow.com/questions", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut + 7, fin);
		return res;
	}

	public static String Qtitle(String q, int num) {// cette fonction cherche le
													// num鑝e libell� de
													// question dans le string q
													// de retour de stack
													// overflowet la retourne
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("title", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut + 8, fin - 1);
		return res;
	}

	// test
	/*
	 * public static void main(String[] args) throws IOException, JSONException
	 * { fonctionBob x = new fonctionBob(); x.Bob1("12345"); }
	 */
}
