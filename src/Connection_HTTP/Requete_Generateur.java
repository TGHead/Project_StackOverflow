package Connection_HTTP;


/**
* Un class pour prendre quelques methodes differentes pour generer des requetes pour envoyer au serveur <br/>
* Les requetes correspondent des formats de L'API de la site.
* @author L'Etoile-TSE
*/

public class Requete_Generateur {
	
	/**
	* renvoyer la tete de URL pour L'API sur tags
	* @return Un string constitue la tete de URL pour L'API sur tags
	*/
	
	public static String Fonction_Tags() {
		return "https://api.stackexchange.com/2.2/tags/";
		//System.out.println(requete);
	}
	
	/**
	* renvoyer l'option top-answerers sous L'API de tags
	* @param flag un entier pour indiquer la duration qu'on prend en compte :<br/> 1-->all_time; 2-->month
	* @param tag le nom de tag qu'on interesse.
	* @return Un string constitue une partie de URL pour L'API sur tags de l'option : top-answerers
	*/
	
	public static String Fonction_Tags_TopAnswerers(int flag, String tag){
		String URL = Fonction_Tags();
		switch (flag){
			case 1:
				URL += tag + "/top-answerers/all_time";break;
			case 2:
				URL += tag + "/top-answerers/month";break;
		}
		return URL;
		//System.out.println(requete);
	}
	
	/**
	* renvoyer la tete de URL pour L'API sur users
	* @return Un string constitue la tete de URL pour L'API sur users
	*/
	
	public static String Fonction_Users() {
		return "https://api.stackexchange.com/2.2/users/";
	}
	
	/**
	* renvoyer l'option top-tags sous L'API de users
	* @param user_id l'identifiant d'utilisateur qu'on interesse.
	* @return Un string constitue une partie de URL pour L'API sur users de l'option : top-tags
	*/
	
	public static String Fonction_Users_TopTags(String user_id) {
		return user_id + "/top-tags";
	}
	
	/**
	* renvoyer l'option answers sous L'API de users
	* @param user_id l'identifiant d'utilisateur qu'on interesse.
	* @return Un string constitue une partie de URL pour L'API sur users de l'option : answers
	*/
	
	public static String Fonction_Users_Answers(String user_id) {
		return user_id + "/answers";
	}
	
	/**
	* renvoyer l'option questions sous L'API de answers
	* @param ans_id l'identifiant d'answer qu'on interesse.
	* @return Un string constitue une entiere de URL pour L'API sur answers de l'option : questions
	*/
	
	public static String Fonction_Answers_Questions(int ans_id) {
		return "https://api.stackexchange.com/2.2/answers/" + ans_id + "/questions";
	}
	
	/**
	* generer des parametres de GET sous L'API.
	* @param page le nombre de page qu'on demande en format de String.
	* @param pagesize le nombre des enregistements par chaque page en format de String.
	* @return Un string constitue le parametre de URL pour L'API.
	*/
	
	public static String GET_Parameters(String page,String pagesize) {
		String Parameter = "page=" + page + "&pagesize=" + pagesize + "&site=stackoverflow";
		return Parameter;
	}
	
	/**
	* generer des parametres de GET sous L'API.
	* @param page le nombre de page qu'on demande en format d'entier.
	* @param pagesize le nombre des enregistements par chaque page en format d'entier.
	* @return Un string constitue le parametre de URL pour L'API.
	*/
	
	public static String GET_Parameters(int page, int pagesize) {
		String Parameter = "page=" + page + "&pagesize=" + pagesize + "&site=stackoverflow";
		return Parameter;
	}
}
