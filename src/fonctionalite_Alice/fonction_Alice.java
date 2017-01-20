package fonctionalite_Alice;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import Connection_HTTP.HttpRequest;
import Data_Structure.JSON_Converter;

public class fonction_Alice {
	public static ArrayList<String[]> Alice1(String id) throws IOException, JSONException {
		ArrayList<String[]> tab = new ArrayList<String[]>();
		String req;//La suite du programme vise a obtenir les tops tags de l'utilisateur
		req = id + "/top-tags?page=1&pagesize=3&site=stackoverflow";//id = identifiant de l'utilisateur
		String json_str_top_tags = HttpRequest.sendGet("https://api.stackexchange.com/2.2/users/", req, false);//demande les tops tags à stack overflow
		HashMap<String, Object> JSON_Map_tags = JSON_Converter.jsonToMap(json_str_top_tags);//les 3 lignes suivantes transforme la reponse de stack overflow en string plus facile à utiliser
		Object it = JSON_Map_tags.get("items");
		String temp = it.toString();

		if (temp == "[]")
			return tab;

		int debut = temp.indexOf("tag_name");//localise l'emplacement du debut du nom du premier tag
		int fin = temp.indexOf(" ", debut);//localise l'emplacement de la fin du nom du premier tag
		String tag1 = temp.substring(debut + 9, fin);//decoupe le premier tag
		debut = temp.indexOf("tag_name", fin);
		fin = temp.indexOf(" ", debut);
		String tag2 = temp.substring(debut + 9, fin);//pareil pour le tag 2
		debut = temp.indexOf("tag_name", fin);
		fin = temp.indexOf(" ", debut);
		String tag3 = temp.substring(debut + 9, fin);//pareil pour le tag 3
		tag1 = URLEncoder.encode(tag1, "UTF-8");//encodage des tags pour les requetes aupres de stack overflow
		tag2 = URLEncoder.encode(tag2, "UTF-8");
		tag3 = URLEncoder.encode(tag3, "UTF-8");
		req = "page=1&pagesize=7&order=desc&sort=activity&tagged=" + tag1 + "&site=stackoverflow";
		String questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions/unanswered", req, true);//requete des 7 dernieres questions sans reponses ayant le tag 1
		// System.out.println(questions);
		String titres[] = new String[20];// Je stoque les titre là
		String liens[] = new String[20];// Je stoque les liens là
		String tags[] = new String[20];// Je stoque les tags là
		String nb_quest[] = new String[30];// Pour eviter les doublons
		for (int i = 0; i < 7; i++) {
			titres[i] = Qtitle(questions, i + 1);//on stoque les libellés des questions là
			// System.out.println("question: ");
			// System.out.println(titres[i]);
			liens[i] = Qlink(questions, i + 1);//on stoque les liens vers les questions là
			// System.out.println(liens[i]);
			tags[i] = Qtags(questions, i + 1);// on stoque les tags des questions là
			// System.out.println("tags: ");
			// System.out.println(tags[i]);
			nb_quest[i] = Qnum(questions, i + 1);// on stoque les numeros des questions là
			// System.out.println("numero : ");
			// System.out.println(nb_quest[i]);
		}
		int sup = 0;
		req = "page=1&pagesize=10&order=desc&sort=activity&tagged=" + tag2 + "&site=stackoverflow";
		questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions/unanswered", req, true);//7 dernieres questions sans reponse avec tag 2
		// System.out.println(questions);
		for (int i = 0; i < 7 + sup; i++) {
			if (doublon(Qnum(questions, i + 1), nb_quest)) {//permet d'éviter les doublons (question ayant deux des tags)
				sup++;
			} else {
				titres[i + 7] = Qtitle(questions, i + 1);
				// System.out.println("question: ");
				// System.out.println(titres[i + 7]);
				liens[i + 7] = Qlink(questions, i + 1);
				// System.out.println(liens[i + 7]);
				tags[i + 7] = Qtags(questions, i + 1);
				// System.out.println("tags: ");
				// System.out.println(tags[i + 7]);
				nb_quest[i + 7] = Qnum(questions, i + 1);
				// System.out.println("numero : ");
				// System.out.println(nb_quest[i]);
			}
		}
		req = "page=1&pagesize=7&order=desc&sort=activity&tagged=" + tag3 + "&site=stackoverflow";
		questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions/unanswered", req, true);//pareil ici
		// System.out.println(questions);
		for (int i = 0; i < 6; i++) {
			if (doublon(Qnum(questions, i + 1), nb_quest)) {
				sup++;
			} else {
				titres[i + 14] = Qtitle(questions, i + 1);
				// System.out.println("question: ");
				// System.out.println(titres[i + 14]);
				liens[i + 14] = Qlink(questions, i + 1);
				// System.out.println(liens[i + 14]);
				tags[i + 14] = Qtags(questions, i + 1);
				// System.out.println("tags: ");
				// System.out.println(tags[i + 14]);
				nb_quest[i + 14] = Qnum(questions, i + 1);
				// System.out.println("numero : ");
				// System.out.println(nb_quest[i]);
			}
		}
		tab.clear();//on retourne les résultats
		tab.add(titres);
		tab.add(liens);
		tab.add(tags);
		return (tab);

	}

	public static String Qtags(String q, int num) {//cette fonction cherche la numème liste de tags dans le string de retour de stack overflow q et la retourne
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("tags", fin);
			fin = q.indexOf("]", debut);
		}
		String res = q.substring(debut + 7, fin);
		return res;
	}

	public static String Qlink(String q, int num) {//cette fonction cherche le numème lien dans le string q  de retour de stack overflowet la retourne
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("link\":\"http://stackoverflow.com/questions", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut + 7, fin);
		return res;
	}

	public static String Qtitle(String q, int num) {//cette fonction cherche le numème libellé de question dans le string q  de retour de stack overflowet la retourne
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("title", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut + 8, fin - 1);
		return res;
	}

	public static String Qnum(String q, int num) {//cette fonction cherche le numème numero de question dans le string q  de retour de stack overflowet la retourne
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("question_id", fin);
			fin = q.indexOf(",", debut);
		}
		String res = q.substring(debut + 13, fin);
		return res;
	}

	public static boolean doublon(String nq, String[] qprec) {//vérifie que le string nq (numero question) est absent du tableau de string qprec (questions precedentes).
		for (int i = 0; i < qprec.length; i++) {//retourne true s'il est present et false sinon
			if (qprec[i] == nq) {
				return true;
			}
		}
		return false;
	}
	/*
	 * public static void main(String[] args) throws IOException, JSONException{
	 * fonction_Alice x = new fonction_Alice(); x.Alice1("12345"); }
	 */
}
