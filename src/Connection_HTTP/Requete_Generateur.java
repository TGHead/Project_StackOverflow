package Connection_HTTP;

public class Requete_Generateur {
	
	public static String Fonction_Tags() {
		return "https://api.stackexchange.com/2.2/tags/";
		//System.out.println(requete);
	}
	
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
	
	public static String Fonction_Users() {
		return "https://api.stackexchange.com/2.2/users";
	}
	
	public static String Fonction_Users_TopTags(String user_id) {
		return Fonction_Users() + user_id + "/top-tags";
	}
	
	public static String GET_Parameters(String page,String pagesize) {
		String Parameter = "page=" + page + "&pagesize=" + pagesize + "&site=stackoverflow";
		return Parameter;
	}
	
	public static String GET_Parameters(int page, int pagesize) {
		String Parameter = "page=" + page + "&pagesize=" + pagesize + "&site=stackoverflow";
		return Parameter;
	}
}
