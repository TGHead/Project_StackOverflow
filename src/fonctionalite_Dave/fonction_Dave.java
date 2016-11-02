package fonctionalite_Dave;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONException;
import Connection_HTTP.*;
import Data_Structure.*;

public class fonction_Dave {
	public static void main(String[] args) throws IOException, JSONException {
		
		String s = HttpRequest.sendGet(Requete_Generateur.Fonction_Tags(1),Requete_Generateur.GET_Parameters());
		
        Map JSON_Map = JSON_Converter.jsonToMap(s);
		System.out.println(JSON_Map);
		
	}
}
