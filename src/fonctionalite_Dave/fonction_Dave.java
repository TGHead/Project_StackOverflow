package fonctionalite_Dave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.spi.TransactionalWriter;

import org.json.JSONException;

import Connection_HTTP.*;
import Data_Structure.*;
import IO_operation.Input_Keyboard;
import Interface_Graphique.*;

public class fonction_Dave {
	
	public static String Tri_resultat(String choix, Map json_map) throws Exception{
		String Str = null;
		ArrayList<Map<String,Object>> sortList = (ArrayList)json_map.get("items");
		if (choix.equals("1")){
			for(int i = 0; i < sortList.size(); i++){  
			    for(int j = i + 1; j < sortList.size(); j++){  
			    	if((Integer)sortList.get(i).get("post_count") < (Integer)sortList.get(j).get("post_count")){  
			    		Map<String, Object> temp = sortList.get(i);  
			    		sortList.set(i, sortList.get(j));  
			    		sortList.set(j, temp);  
			    	}  
			    }  
			}  
		}
		if (choix.equals("2")) {
			for(int i = 0; i < sortList.size(); i++){  
			    for(int j = i + 1; j < sortList.size(); j++){  
			    	if((Integer)sortList.get(i).get("score") < (Integer)sortList.get(j).get("score")){  
			    		Map<String, Object> temp = sortList.get(i);  
			    		sortList.set(i, sortList.get(j));  
			    		sortList.set(j, temp);  
			    	}  
			    }  
			}  
		}
		else {
			//throw new Exception("parametre illegale!");
		}
		for(int i = 0; i < sortList.size(); i++){
			Str += i + ". Name: " + ((Map) sortList.get(i).get("user")).get("display_name") + "\n" 
				+ " post_count: " + sortList.get(i).get("post_count") + "\n" 
				+ " score: " + sortList.get(i).get("score") + "\n"
				+ " link: " + ((Map) sortList.get(i).get("user")).get("link") + "\n";
		}
		return Str;
	}
	
	public static void main(String[] args) throws IOException, JSONException {
		
		String sujet = Input_Keyboard.Get_String("Veuillez entrer un sujet.");
		
		String nombre = Input_Keyboard.Get_String("Veuillez entrer le nombre de personnes a chercher.");
		
		String json_str = HttpRequest.sendGet(Requete_Generateur.Fonction_Tags(1,sujet),Requete_Generateur.GET_Parameters(nombre));
		
        Map JSON_Map = JSON_Converter.jsonToMap(json_str);
        
        String choix = Input_Keyboard.Get_String("Veuillez choisir la methode pour trier:\n1. Par le nombre de poser des reponses\n2. Par des scores qu'il est recu\n");
        
        String result = null;
		try {
			result = Tri_resultat(choix,JSON_Map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //List<User> ulist = JSON_Converter.User_fromJMap(JSON_Map);
		System.out.println(result);
		resultat_Frame f = new resultat_Frame("Fonction_Dave",result);
	}
}
