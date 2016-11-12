package fonctionalite_Dave;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import Base_de_Donnees.Operation_BDD;
import Connection_HTTP.HttpRequest;
import Connection_HTTP.Requete_Generateur;
import Data_Structure.JSON_Converter;
import Data_Structure.Tag;
import Data_Structure.TagsAnswerers;
import IO_operation.Input_Keyboard;

public class fonction_Dave {

	public static String Tri_resultat(String choix, Map json_map) throws Exception {
		String Str = "";
		ArrayList<Map<String, Object>> sortList = (ArrayList) json_map.get("items");
		if (choix.equals("1")) {
			for (int i = 0; i < sortList.size(); i++) {
				for (int j = i + 1; j < sortList.size(); j++) {
					if ((Integer) sortList.get(i).get("post_count") < (Integer) sortList.get(j).get("post_count")) {
						Map<String, Object> temp = sortList.get(i);
						sortList.set(i, sortList.get(j));
						sortList.set(j, temp);
					}
				}
			}
		} else if (choix.equals("2")) {
			for (int i = 0; i < sortList.size(); i++) {
				for (int j = i + 1; j < sortList.size(); j++) {
					if ((Integer) sortList.get(i).get("score") < (Integer) sortList.get(j).get("score")) {
						Map<String, Object> temp = sortList.get(i);
						sortList.set(i, sortList.get(j));
						sortList.set(j, temp);
					}
				}
			}
		} else {
			// throw new Exception("parametre illegale!");
		}
		for (int i = 0; i < sortList.size(); i++) {
			Str += (i + 1) + ". Name: " + ((Map) sortList.get(i).get("user")).get("display_name") + "\n"
					+ " post_count: " + sortList.get(i).get("post_count") + "\n" + " score: "
					+ sortList.get(i).get("score") + "\n" + " link: " + ((Map) sortList.get(i).get("user")).get("link")
					+ "\n";
		}
		return Str;
	}

	public static void main(String[] args) throws IOException, JSONException {

		String json_str_tags = HttpRequest.sendGet(Requete_Generateur.Fonction_Tags(),
				Requete_Generateur.GET_Parameters(20));

		HashMap<String, Object> JSON_Map_tags = JSON_Converter.jsonToMap(json_str_tags);

		ArrayList<Tag> Tags_List = Tag.JSON_ListtoTag_List((ArrayList) JSON_Map_tags.get("items"));

		Connection Conn_BDD = Operation_BDD.Derby_Connexion();

		Operation_BDD.Create_Table_Tags(Conn_BDD);

		for (Tag tag : Tags_List) {
			Operation_BDD.Insert_Into_Table_Tags(Conn_BDD, tag);
		}

		ArrayList<Tag> Tags_List_New = Operation_BDD.Select_Table_Tags(Conn_BDD);

		System.out.println(Tags_List_New);

		// Operation_BDD.Derby_DisConnexion(Conn_BDD);

		String sujet = Input_Keyboard.Get_String("Veuillez entrer un sujet.");

		String nombre = Input_Keyboard.Get_String("Veuillez entrer le nombre de personnes a chercher.");
		
		String json_str_tags_answerers = HttpRequest.sendGet(Requete_Generateur.Fonction_Tags(1,sujet),Requete_Generateur.GET_Parameters(nombre));
		
        HashMap<String, Object> JSON_Map_tags_answerers = JSON_Converter.jsonToMap(json_str_tags_answerers);
        
        ArrayList<TagsAnswerers> TagsAnswerers_List = TagsAnswerers.JSON_ListtoTagsAnswerers_List((ArrayList)JSON_Map_tags_answerers.get("items"), sujet);
        
        Operation_BDD.Create_Tables_TagsAnswerers(Conn_BDD);
        
        for(TagsAnswerers tag_answerers : TagsAnswerers_List) {
			Operation_BDD.Insert_Into_Table_TagsAnswerers(Conn_BDD, tag_answerers);
		}

		String choix = Input_Keyboard.Get_String(
				"Veuillez choisir la methode pour trier:\n1. Par le nombre de poser des reponses\n2. Par des scores qu'il est recu\n");

		ArrayList<TagsAnswerers> TagsAnswerers_List_new = Operation_BDD.Select_Table_TagsAnswerers(Conn_BDD, choix);
		/*
		 * String result = null; try { result = Tri_resultat(choix,JSON_Map); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// List<User> ulist = JSON_Converter.User_fromJMap(JSON_Map);
		System.out.println(TagsAnswerers_List_new);
		// resultat_Frame f = new resultat_Frame("Fonction_Dave",result);
	}
}
