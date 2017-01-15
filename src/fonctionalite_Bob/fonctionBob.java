package fonctionalite_Bob;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Connection_HTTP.HttpRequest;
import fonctionalite_Dave.fonction_Dave;

public class fonctionBob {
	public ArrayList<String[]> Bob1(String mots) throws IOException, JSONException {

		ArrayList<String[]> tab = new ArrayList<String[]>();
		String requete = "?page=1&pagesize=10&order=desc&sort=votes&tagged=" + mots + "&site=stackoverflow";
		String resultat = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions", requete, false);
		JSONObject json = new JSONObject(resultat);
		JSONArray arr = json.getJSONArray("items");

		String questions[] = new String[10];
		String liens[] = new String[10];
		String tags[] = new String[10];

		for (int i = 0; i < arr.length(); i++) {
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

		tab = f.resultat(tags, "10", "1");
		return tab;

	}

}
