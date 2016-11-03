package IO_operation;

import java.util.Scanner;

public class Input_Keyboard {
	
	public static String Get_String(String indication){
		Scanner sc = new Scanner(System.in);
		//System.out.println("Veuillez entrer un sujet.");
		//System.out.println("Veuillez entrer le nombre de personnes a chercher.");
		System.out.println(indication);
		String str = sc.next();
		//sc.close();
		//System.out.println(requete);
		return str;
	}
}
