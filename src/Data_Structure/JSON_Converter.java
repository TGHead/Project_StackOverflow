package Data_Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Un class pour convertiser un json string au HashMap. 
 * @author L'Etoile-TSE
 *
 */
public class JSON_Converter {
	/**
	 * la methode essentiel pour convertiser.
	 * @param jsonString unstring en format de json
	 * @return HashMap correspond des donnees json
	 * @throws JSONException
	 */
	public static HashMap<String, Object> jsonToMap(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		
	    HashMap<String, Object> retMap = new HashMap<String, Object>();

	    if(jsonObject != JSONObject.NULL) {
	        retMap = toMap(jsonObject);
	    }
	    return retMap;
	}
	
	/**
	 * Utiliser un objet intermediaire pour constuire le HashMap
	 * @param object un objet de JSONObject intermediaire
	 * @return le HashMap correspond des donnee json
	 * @throws JSONException
	 */

	public static HashMap<String, Object> toMap(JSONObject object) throws JSONException {
	    HashMap<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	
	/**
	 * Utiliser un objet intermediaire pour constuire la liste
	 * @param array un objet de JSONArray intermediaire
	 * @return la liste correspond des donnee json
	 * @throws JSONException
	 */

	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
	
	/**
	 * Convertiser Un Map de json donnees au une liste d'user.
	 * @param JSON_Map le json donnee 
	 * @returnune liste d'user
	 */
	
	public static List<User> User_fromJMap(Map<String, Object> JSON_Map){
		List<User> u_list = new ArrayList<User>();
		ArrayList<HashMap<String, Object>> items = (ArrayList)JSON_Map.get("items");
		for(HashMap<String,Object> user : items) {
			u_list.add(User.MaptoC_User((HashMap<String,Object>)user.get("user")));
		}
		return u_list;
	}
	
}
