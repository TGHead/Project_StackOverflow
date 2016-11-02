package fonctionalite_Dave;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

import Connection_HTTP.*;
import Data_Structure.*;
import Interface_Graphique.*;

public class fonction_Dave {
	public static void main(String[] args) throws IOException, JSONException {
		
		String s = HttpRequest.sendGet(Requete_Generateur.Fonction_Tags(1),Requete_Generateur.GET_Parameters());
		
        Map JSON_Map = JSON_Converter.jsonToMap(s);
        
        List<User> ulist = JSON_Converter.User_fromJMap(JSON_Map);
		System.out.println(ulist);
		//resultat_Frame f = new resultat_Frame("Fonction_Dave",ulist.toString());
	}
}
