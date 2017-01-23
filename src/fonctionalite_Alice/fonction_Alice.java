package fonctionalite_Alice;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import Connection_HTTP.HttpRequest;
import Connection_HTTP.Requete_Generateur;
import Data_Structure.Answer;
import Data_Structure.JSON_Converter;
import Data_Structure.Question;
import Data_Structure.Tag;
import Data_Structure.User;

/**
 * Cette classe realise des fontionnalites d'Alice
 * @author L'Etoile-TSE
 *
 */

public class fonction_Alice {
	
	/**
	 * appel par la class InterfaceAlice pour realiser la fonctionnalite d'Alice3
	 * 
	 * @param id l'identifiant d'utilisateur auquel on s'interesse
	 * @return Une liste des ensembles de string pour l'UI
	 * @throws IOException
	 * @throws JSONException
	 */
	
	public static ArrayList<String []> Alice3 (String id) throws IOException, JSONException{
		ArrayList<String []> tab = new ArrayList<String []>();
		
		
		String json_str_answers = HttpRequest.sendGet(Requete_Generateur.Fonction_Users() + Requete_Generateur.Fonction_Users_Answers(id)
														, "page=1&pagesize=10&order=desc&sort=votes&site=stackoverflow", true);//demande les tops tags ï¿½ stack overflow
		HashMap<String, Object> JSON_Map_answers = JSON_Converter.jsonToMap(json_str_answers);
		ArrayList<Answer> Answers_List = Answer.JSON_ListtoAnswer_List((ArrayList) JSON_Map_answers.get("items"));
		ArrayList<Question> Question_List = new ArrayList<Question>();
		for(Answer ans : Answers_List) {
			String json_str_questions = HttpRequest.sendGet(Requete_Generateur.Fonction_Answers_Questions(ans.getAnswer_id()), Requete_Generateur.GET_Parameters(1, 1), true);
			HashMap<String,Object> JSON_Map_questions = JSON_Converter.jsonToMap(json_str_questions);
			JSON_Map_questions = (HashMap<String, Object>) ((ArrayList)JSON_Map_questions.get("items")).get(0);
			JSON_Map_questions.put("owner", User.MaptoC_User((HashMap<String,Object>)JSON_Map_questions.get("owner")));
			Question_List.add(Question.MaptoC_Question(JSON_Map_questions));
		}
		for(int i=0; i<Question_List.size() - 1;i++) {
			//Question qmax = Question_List.get(i);
			//int max = i;
			for(int j=i+1; j<Question_List.size();j++) {
				if(Question_List.get(i).getScore() < Question_List.get(j).getScore()) {
					Question qt = new Question(Question_List.get(i));
					Question_List.set(i,Question_List.get(j));
					Question_List.set(j, qt);
				}
			}
		}
		String title[] = new String[Question_List.size()];
		String link[] = new String[Question_List.size()];
		String score[] = new String[Question_List.size()];
		//String ans_id[] = new String[Question_List.size()];
		int i = 0;
		for(Question q : Question_List){
			title[i] = q.getTitle();
			link[i] = q.getLink();
			score[i++] = q.getScore() + "";
		}
		tab.clear();
		tab.add(title);
		tab.add(link);
		tab.add(score);
		return tab;
	}
	
	public static ArrayList<String[]> Alice1(String id) throws IOException, JSONException {
		ArrayList<String[]> tab = new ArrayList<String[]>();
		String req;//La suite du programme vise a obtenir les tops tags de l'utilisateur
		req = id + "/top-tags?page=1&pagesize=3&site=stackoverflow";//id = identifiant de l'utilisateur
		String json_str_top_tags = HttpRequest.sendGet("https://api.stackexchange.com/2.2/users/", req, false);//demande les tops tags ï¿½ stack overflow
		HashMap<String, Object> JSON_Map_tags = JSON_Converter.jsonToMap(json_str_top_tags);//les 3 lignes suivantes transforme la reponse de stack overflow en string plus facile ï¿½ utiliser
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
		String titres[] = new String[20];// Je stoque les titre lï¿½
		String liens[] = new String[20];// Je stoque les liens lï¿½
		String tags[] = new String[20];// Je stoque les tags lï¿½
		String nb_quest[] = new String[30];// Pour eviter les doublons
		for (int i = 0; i < 7; i++) {
			titres[i] = Qtitle(questions, i + 1);//on stoque les libellé–Ÿ des questions lï¿½
			// System.out.println("question: ");
			// System.out.println(titres[i]);
			liens[i] = Qlink(questions, i + 1);//on stoque les liens vers les questions lï¿½
			// System.out.println(liens[i]);
			tags[i] = Qtags(questions, i + 1);// on stoque les tags des questions lï¿½
			// System.out.println("tags: ");
			// System.out.println(tags[i]);
			nb_quest[i] = Qnum(questions, i + 1);// on stoque les numeros des questions lï¿½
			// System.out.println("numero : ");
			// System.out.println(nb_quest[i]);
		}
		int sup = 0;
		req = "page=1&pagesize=10&order=desc&sort=activity&tagged=" + tag2 + "&site=stackoverflow";
		questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions/unanswered", req, true);//7 dernieres questions sans reponse avec tag 2
		// System.out.println(questions);
		for (int i = 0; i < 7 + sup; i++) {
			if (doublon(Qnum(questions, i + 1), nb_quest)) {//permet d'é–¢iter les doublons (question ayant deux des tags)
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

	public static String Qtags(String q, int num) {//cette fonction cherche le lien de la liste de tags dans le string de retour de stack overflow q et la renvoie
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("tags", fin);
			fin = q.indexOf("]", debut);
		}
		String res = q.substring(debut + 7, fin);
		return res;
	}

	public static String Qlink(String q, int num) {//cette fonction cherche le lien dans le string q  de retour de stack overflowet et le renvoie
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("link\":\"http://stackoverflow.com/questions", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut + 7, fin);
		return res;
	}

	public static String Qtitle(String q, int num) {//cette fonction cherche le libellé de question dans le string q  de retour de stack overflowet et le renvoie
		int debut = 0;
		int fin = 0;
		for (int i = 0; i < num; i++) {
			debut = q.indexOf("title", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut + 8, fin - 1);
		return res;
	}

	public static String Qnum(String q, int num) {//cette fonction cherche le numero de question dans le string q  de retour de stack overflowet et le retourne
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
