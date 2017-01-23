package Data_Structure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Une class qui presente la relation entre tags et utilisateurs 
 * @author Administrator
 *
 */

public class TagsAnswerers {
	/**
	 * un objet user correspondant
	 */
	User user;
	/**
	 * un string correspondant au nom du tag
	 */
	String tag_name;
	/**
	 * un entier correspondant au nombre de posts par l'user pour ce tag
	 */
	int post_count;
	/**
	 * un entier correspondant au score obtenu par l'user pour ce tag
	 */
	int score;
	/**
	 * un constructeur par defaut
	 * @param user_id l'ID de l'user correspondant
	 * @param user_name le nom de l'user correspondant
	 * @param tag_name un string correspondant au nom du tag
	 * @param post_count un entier correspondant au nombre de posts par l'user pour ce tag
	 * @param score un entier correspondant au score obtenu par l'user pour ce tag
	 */
	public TagsAnswerers(User user, String tag_name, int post_count, int score) {
		super();
		this.user = user;
		this.tag_name = tag_name;
		this.post_count = post_count;
		this.score = score;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}



	/**
	 * @return the tag_name
	 */
	public String getTag_name() {
		return tag_name;
	}



	/**
	 * @return the post_count
	 */
	public int getPost_count() {
		return post_count;
	}



	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}



	/**
	 * @param user le user qui doit etre set
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/**
	 * @param tag_name le tag_name qui doit etre set
	 */
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}



	/**
	 * @param post_count le post_count qui doit etre set
	 */
	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}



	/**
	 * @param score le score qui doit etre set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TagsAnswerers [user=" + user + ", tag_name=" + tag_name + ", post_count=" + post_count + ", score="
				+ score + "]";
	}

	/**
	 * cette methode permet de transformer un map de json_donne en objet tagsanswerers en recherchant sur la BDD
	 * @param tag_answerer un HashMap qu'on obtient grace au convertisseur de json qui enregistre des informations
	 * @return un objet tagsanswerers qui correspond.
	 */
	
	public static TagsAnswerers MaptoC_TagsAnswerers(HashMap<String,Object> tag_answerer, String tag) {
		return new TagsAnswerers(tag_answerer.containsKey("user")?(User)tag_answerer.get("user"):null, 
				tag, 
				tag_answerer.containsKey("post_count")?(int)tag_answerer.get("post_count"):-1, 
				tag_answerer.containsKey("score")?(int)tag_answerer.get("score"):-1);
	}
	
	/**
	 * cette methode permet de transformer un map de json donne obtenu avec la requete de BDD en une liste d'objets tagsanswerers
	 * @param json_list un HashMap qu'on obtient avec la requete de BDD
	 * @return une liste des objets tagsanswerers
	 */
	
	public static ArrayList<TagsAnswerers> JSON_ListtoTagsAnswerers_List(ArrayList<HashMap<String, Object>> json_list, String tag) {
		ArrayList<TagsAnswerers> tag_list = new ArrayList<TagsAnswerers>();
		for(HashMap<String, Object> tag_answerers : json_list) {
			tag_answerers.put("user", User.MaptoC_User((HashMap<String,Object>)tag_answerers.get("user")));
			tag_list.add(MaptoC_TagsAnswerers(tag_answerers, tag));
		}
		return tag_list;
	}
	
}
