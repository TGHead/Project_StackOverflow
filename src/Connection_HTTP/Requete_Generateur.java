package Connection_HTTP;


/**
* Une classe  utilisant des methodes differentes pour generer des requetes a envoyer au serveur <br/>
* Les requetes correspondent au format API du site.
* @author L'Etoile-TSE
*/

public class Requete_Generateur {
	
	/**
	* renvoyer la partie de l'URL qui correspond aux tags pour L'API
	* @return la partie de l'URL qui correspond aux tags pour L'API
	*/
	
	public static String Fonction_Tags() {
		return "https://api.stackexchange.com/2.2/tags/";
		//System.out.println(requete);
	}
	
	/**
	* renvoyer l'URL de l'API qui correspond aux top answerers pour un tag et une periode temporelle precis
	* @param flag un entier pour indiquer la duree qu'on prend en compte :<br/> 1-->all_time; 2-->month
	* @param tag le nom de tag qui nous interesse.
	* @return Un string qui est l'URL de l'API qui correspond aux top answerers pour un tag et une periode temporelle precis
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
	* renvoyer la partie de l'URL qui correspond aux users pour L'API
	* @return Un string qui est la partie de l'URL qui correspond aux users pour L'API
	*/
	
	public static String Fonction_Users() {
		return "https://api.stackexchange.com/2.2/users/";
	}
	
	/**
	* renvoyer une partie de l'URL de l'API qui correspond aux top tags pour un utilisateur donne
	* @param user_id l'identifiant d'utilisateur qui nous interesse.
	* @return Un string qui est une partie de l'URL de l'API qui correspond aux top tags pour un utilisateur donne
	*/
	
	public static String Fonction_Users_TopTags(String user_id) {
		return user_id + "/top-tags";
	}
	
	/**
	* renvoyer une partie de l'URL de l'API qui correspond aux reponses laisse par un utilisateur donne
	* @param user_id l'identifiant d'utilisateur qui nous interesse.
	* @return Un string qui est une partie de l'URL de l'API qui correspond aux reponses laisse par un utilisateur donne
	*/
	
	public static String Fonction_Users_Answers(String user_id) {
		return user_id + "/answers";
	}
	
	/**
	* renvoyer l'option questions de L'API pour les answers
	* @param ans_id l'identifiant d'answer qui nous interesse.
	* @return Un string qui est l'URL entiere de l'API sur answers pour l'option : questions
	*/
	
	public static String Fonction_Answers_Questions(int ans_id) {
		return "https://api.stackexchange.com/2.2/answers/" + ans_id + "/questions";
	}
	
	/**
	* generer des parametres de GET pour L'API, avec des parametres d'entree au format String
	* @param page le nombre de pages qu'on demande au format String.
	* @param pagesize le nombre d'enregistements pour chaque page au format String.
	* @return Un string constituant le parametre de URL pour L'API.
	*/
	
	public static String GET_Parameters(String page,String pagesize) {
		String Parameter = "page=" + page + "&pagesize=" + pagesize + "&site=stackoverflow";
		return Parameter;
	}
	
	/**
	* generer des parametres de GET pour L'API, avec des parametres d'entree au format entier
	* @param page le nombre de page qu'on demande au format d'entier.
	* @param pagesize le nombre d'enregistements par chaque page au format d'entier.
	* @return Un string constitue le parametre de URL pour L'API.
	*/
	
	public static String GET_Parameters(int page, int pagesize) {
		String Parameter = "page=" + page + "&pagesize=" + pagesize + "&site=stackoverflow";
		return Parameter;
	}
}
