package Connection_HTTP;

import java.util.Scanner;

public class Requete_Generateur {
	
	public static String Fonction_Tags(int flag){
		String URL = "https://api.stackexchange.com/2.2/tags";
		switch (flag){
			case 0: 
				return URL;
			case 1:
				URL += GetTop_answers();
		}
		return URL;
		//System.out.println(requete);
	}
	
	public static String GetTop_answers(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer le sujet.");
		String sujet = sc.next();
		String partie1 =  "/" + sujet + "/top-answerers/all_time";
		//System.out.println(requete);
		return partie1;
	}
	
	public static String GET_Parameters() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer le nombre de personnes a chercher.");
		String nombre = sc.next();
		String Parameter = "page=1&pagesize=" + nombre + "&site=stackoverflow";
		return Parameter;
	}
}
