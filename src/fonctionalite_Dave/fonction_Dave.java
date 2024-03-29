package fonctionalite_Dave;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import Base_de_Donnees.Operation_BDD;
import Connection_HTTP.HttpRequest;
import Connection_HTTP.Requete_Generateur;
import Data_Structure.JSON_Converter;
import Data_Structure.Tag;
import Data_Structure.TagsAnswerers;

/**
 * Ce class realise des fontionnalites de Dave
 * @author L'Etoile-TSE
 *
 */

public class fonction_Dave {
	
	/**
	 * rappel par l'UI pour realiser des fonctionnalites de Dave
	 * 
	 * @param sujet_list tag saisi par l'UI
	 * @param nombre le nombre d'utilisateurs a chercher
	 * @param choix le choix indique l'ordre de resultat
	 * @see Select_Table_PluralTagsAnswerers
	 * @return Une liste d'ensembles de string pour l'UI
	 * @throws IOException
	 * @throws JSONException
	 */

	public static ArrayList<String[]> Dave(ArrayList<String> sujet_list, String nombre, String choix)
			throws IOException, JSONException {

		String wrongTags = "";

		String json_str_tags = HttpRequest.sendGet(Requete_Generateur.Fonction_Tags(),
				Requete_Generateur.GET_Parameters(1, 20), true);

		HashMap<String, Object> JSON_Map_tags = JSON_Converter.jsonToMap(json_str_tags);

		ArrayList<Tag> Tags_List = Tag.JSON_ListtoTag_List((ArrayList) JSON_Map_tags.get("items"));

		Connection Conn_BDD = Operation_BDD.Derby_Connexion();

		Operation_BDD.Create_Table_Tags(Conn_BDD);

		Operation_BDD.Delete_Table_Tags(Conn_BDD);

		for (Tag tag : Tags_List) {
			Operation_BDD.Insert_Into_Table_Tags(Conn_BDD, tag);
		}

		ArrayList<Tag> Tags_List_New = Operation_BDD.Select_Table_Tags(Conn_BDD);

		// System.out.println(Tags_List_New);

		// Operation_BDD.Derby_DisConnexion(Conn_BDD);
		/*
		 * String nb_sujet = Input_Keyboard.
		 * Get_String("Veuillez saisir le nombre des sujets vous voulez chercher:"
		 * );
		 * 
		 * ArrayList<String> sujet_list = new ArrayList<String>();
		 * 
		 * for (int i = 0; i < Integer.parseInt(nb_sujet); i++) {
		 * sujet_list.add(Input_Keyboard.Get_String("Veuillez entrer un sujet:")
		 * ); }
		 */
		// String sujet = Input_Keyboard.Get_String("Veuillez entrer un
		// sujet.");

		// String nombre = Input_Keyboard.Get_String("Veuillez entrer le nombre
		// de personnes a chercher.");

		Operation_BDD.Create_Table_Users(Conn_BDD);

		Operation_BDD.Delete_Table_Users(Conn_BDD);

		Operation_BDD.Create_Tables_TagsAnswerers(Conn_BDD);

		Operation_BDD.Delete_Table_TagsAnswerers(Conn_BDD);

		for (String sujet : sujet_list) {

			String json_str_tags_answerers = HttpRequest.sendGet(
					Requete_Generateur.Fonction_Tags_TopAnswerers(1, sujet),
					Requete_Generateur.GET_Parameters("1", nombre), true);

			HashMap<String, Object> JSON_Map_tags_answerers = JSON_Converter.jsonToMap(json_str_tags_answerers);
			ArrayList<TagsAnswerers> TagsAnswerers_List = TagsAnswerers
					.JSON_ListtoTagsAnswerers_List((ArrayList) JSON_Map_tags_answerers.get("items"), sujet);

			if (TagsAnswerers_List.isEmpty()) {
				wrongTags = wrongTags + sujet + ",";
			}
			for (TagsAnswerers tag_answerers : TagsAnswerers_List) {
				Operation_BDD.Insert_Into_Table_Users(Conn_BDD, tag_answerers.getUser());
				Operation_BDD.Insert_Into_Table_TagsAnswerers(Conn_BDD, tag_answerers);
			}
		}
		/*
		 * String choix = Input_Keyboard.Get_String(
		 * "Veuillez choisir la methode pour trier:\n1. Par le nombre de poser des reponses\n2. Par des scores qu'il est recu"
		 * );
		 */
		// Operation_BDD.Derby_DisConnexion(Conn_BDD);

		ArrayList<TagsAnswerers> TagsAnswerers_List_new = Operation_BDD.Select_Table_PluralTagsAnswerers(Conn_BDD,
				choix);

		// System.out.println(TagsAnswerers_List_new);
		ArrayList<String[]> tab = new ArrayList<String[]>();
		tab.clear();
		for (TagsAnswerers tag_answerers : TagsAnswerers_List_new) {
			String[] str = { "", "", "", "" };

			str[0] = tag_answerers.getUser().getDisplay_name();
			str[1] = tag_answerers.getUser().getLink();
			str[2] = Integer.toString(tag_answerers.getPost_count());
			str[3] = Integer.toString(tag_answerers.getScore());
			tab.add(str);
			/*
			 * System.out.println("*****************************************");
			 * System.out.println("User_pseudo:" +
			 * tag_answerers.getUser().getDisplay_name());
			 * System.out.println("User_Link:" +
			 * tag_answerers.getUser().getLink());
			 * System.out.println("Tags_name:" + sujet_list);
			 * System.out.println("User_post_count (total):" +
			 * tag_answerers.getPost_count());
			 * System.out.println("User_score (total):" +
			 * tag_answerers.getScore());
			 */
		}
		String[] str = { wrongTags, "", "", "" };
		tab.add(str);
		Operation_BDD.Derby_DisConnexion(Conn_BDD);

		return tab;
		// resultat_Frame f = new resultat_Frame("Fonction_Dave",result);
	}
}
