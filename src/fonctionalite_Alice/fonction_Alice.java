package fonctionalite_Alice;

import java.io.IOException;
import java.lang.System;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONException;

import Connection_HTTP.HttpRequest;
import Data_Structure.JSON_Converter;
import Data_Structure.Tag;

public class fonction_Alice {
	public static void main(String[] args) throws IOException, JSONException {
		Scanner sc = new Scanner(System.in);
		String id;
		System.out.println("Veuillez entrer votre id.");
		id = sc.next();
		String req;
		req = id + "/top-tags?page=1&pagesize=3&site=stackoverflow";
		String json_str_top_tags = HttpRequest.sendGet("https://api.stackexchange.com/2.2/users/", req, false);
		HashMap<String, Object> JSON_Map_tags = JSON_Converter.jsonToMap(json_str_top_tags);
		Object it = JSON_Map_tags.get("items");
		String temp = it.toString();
		int debut = temp.indexOf("tag_name");
		int fin = temp.indexOf(" ", debut);
		String tag1 = temp.substring(debut+9, fin);
		debut = temp.indexOf("tag_name", fin);
		fin = temp.indexOf(" ", debut);
		String tag2 = temp.substring(debut+9, fin);
		debut = temp.indexOf("tag_name", fin);
		fin = temp.indexOf(" ", debut);
		String tag3 = temp.substring(debut+9, fin);
		req = "page=1&pagesize=7&order=desc&sort=activity&tagged=" + tag1 + "&site=stackoverflow";
		String questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions/unanswered", req, true);
		System.out.println(questions);
		String titres[] = new String[20];//Je stoque les titre là
		String liens[] = new String[20];//Je stoque les liens là
		String tags[] = new String[20];//Je stoque les tags là
		for(int i = 0; i<7; i++){
			titres[i] = Qtitle(questions, i+1);
			System.out.println("question: ");
			System.out.println(titres[i]);
			liens[i] = Qlink(questions, i+1);
			System.out.println(liens[i]);
			tags[i] = Qtags(questions, i+1);
			System.out.println("tags: ");
			System.out.println(tags[i]);
		}
		req = "page=1&pagesize=7&order=desc&sort=activity&tagged=" + tag2 + "&site=stackoverflow";
		questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions/unanswered", req, true);
		System.out.println(questions);
		for(int i = 0; i<7; i++){
			titres[i+7] = Qtitle(questions, i+1);
			System.out.println("question: ");
			System.out.println(titres[i+7]);
			liens[i+7] = Qlink(questions, i+1);
			System.out.println(liens[i+7]);
			tags[i+7] = Qtags(questions, i+1);
			System.out.println("tags: ");
			System.out.println(tags[i+7]);
		}
		req = "page=1&pagesize=7&order=desc&sort=activity&tagged=" + tag3 + "&site=stackoverflow";
		questions = HttpRequest.sendGet("https://api.stackexchange.com/2.2/questions/unanswered", req, true);
		System.out.println(questions);
		for(int i = 0; i<6; i++){
			titres[i+14] = Qtitle(questions, i+1);
			System.out.println("question: ");
			System.out.println(titres[i+14]);
			liens[i+14] = Qlink(questions, i+1);
			System.out.println(liens[i+14]);
			tags[i+14] = Qtags(questions, i+1);
			System.out.println("tags: ");
			System.out.println(tags[i+14]);
		}
	}
	public static String Qtags(String q, int num){
		int debut = 0;
		int fin = 0;
		for(int i=0; i<num; i++){
			debut = q.indexOf("tags", fin);
			fin = q.indexOf("]", debut);
		}
		String res = q.substring(debut+7, fin);
		return res;
	}
	public static String Qlink(String q, int num){
		int debut = 0;
		int fin = 0;
		for(int i=0; i<num; i++){
			debut = q.indexOf("link", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut+6, fin);
		return res;
	}
	public static String Qtitle(String q, int num){
		int debut = 0;
		int fin = 0;
		for(int i=0; i<num; i++){
			debut = q.indexOf("title", fin);
			fin = q.indexOf("}", debut);
		}
		String res = q.substring(debut+8, fin-1);
		return res;
	}
}
