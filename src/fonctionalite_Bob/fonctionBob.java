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
	public void Bob1(String mots) throws IOException, JSONException {
		String requete = "?page=1&pagesize=10&order=desc&sort=votes&tagged=" + mots + "&site=stackoverflow";
		String resultat = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions", requete, false);
		JSONObject json = new JSONObject(resultat);
		JSONArray arr = json.getJSONArray("items");

		String questions[] = new String[10];
		String liens[] = new String[10];
		String tags[] = new String[10];

		for (int i = 0; i < arr.length(); i++) {
			questions[i] = arr.getJSONObject(i).getString("title");
			liens[i] = arr.getJSONObject(i).getString("link");
			tags[i] = arr.getJSONObject(i).get("tags").toString();
		}
	}

	public void Bob2(String id) throws IOException, JSONException {
		String requete = id + "/top-tags?page=1&pagesize=3&site=stackoverflow";
		String resultat = HttpRequest.sendGet("https://api.stackexchange.com/2.2/users/", requete, false);
		JSONObject json = new JSONObject(resultat);
		JSONArray arr = json.getJSONArray("items");
		ArrayList<String> tags = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++) {
			tags.add(URLEncoder.encode(arr.getJSONObject(i).getString("tag_name"), "UTF-8"));
		}

		fonction_Dave f = new fonction_Dave();
		ArrayList<String[]> tab = new ArrayList<String[]>();

		tab = f.resultat(tags, "10", "1");

	}

	public static void main(String[] args) {
		fonctionBob f = new fonctionBob();
		try {
			// f.Bob1("java;install");
			f.Bob2("1");
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
