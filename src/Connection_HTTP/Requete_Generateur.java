package Connection_HTTP;

import java.util.Scanner;

public class Requete_Generateur {
	
	public static String Fonction_Tags() {
		return "https://api.stackexchange.com/2.2/tags";
		//System.out.println(requete);
	}
	
	public static String Fonction_Tags(int flag, String para){
		String URL = "https://api.stackexchange.com/2.2/tags";
		switch (flag){
			case 1:
				URL += "/" + para + "/top-answerers/all_time";
		}
		return URL;
		//System.out.println(requete);
	}
	
	public static String GET_Parameters(String para) {
		String Parameter = "page=1&pagesize=" + para + "&site=stackoverflow";
		return Parameter;
	}
	
	public static String GET_Parameters(int para) {
		String Parameter = "page=1&pagesize=" + para + "&site=stackoverflow";
		return Parameter;
	}
}
