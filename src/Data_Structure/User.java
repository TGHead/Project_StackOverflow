package Data_Structure;

import java.util.HashMap;

/**
* Un class pour decrire un objet d'utilisateur sur la site Stackoverflow qui presente une user sur la site.
* @author L'Etoile-TSE
*/

public class User {
	/**
	 * un entier qui indique la reputation d'utilisateur
	 * 
	 */
	long reputation;
	/**
	 * un eniter indique l'identifiant d'utilisateur
	 */
	long user_id;
	/**
	 * un string indique le type d'utilisateur
	 */
	String user_type;
	/**
	 * un float indique le taux d'success d'utilisateur
	 */
	float accept_rate;
	/**
	 * un lien URL indique l'image de profil d'utilisateur
	 */
	String profile_image;
	/**
	 * un String indique le pseudo d'utilisateur
	 */
	String display_name;
	/**
	 * un lien correspond la page personnel d'utilisateur
	 */
	String link;
	/**
	 * un constructeur pardefaut
	 * @param reputation reputation
	 * @param user_id user_id
	 * @param user_type user_type
	 * @param accept_rate accept_rate
	 * @param profile_image profile_image
	 * @param display_name display_name
	 * @param link link
	 */
	public User(long reputation, long user_id, String user_type, float accept_rate, String profile_image,
			String display_name, String link) {
		super();
		this.reputation = reputation;
		this.user_id = user_id;
		this.user_type = user_type;
		this.accept_rate = accept_rate;
		this.profile_image = profile_image;
		this.display_name = display_name;
		this.link = link;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [reputation=" + reputation + ", user_id=" + user_id + ", user_type=" + user_type + ", accept_rate="
				+ accept_rate + ", profile_image=" + profile_image + ", display_name=" + display_name + ", link=" + link
				+ "]";
	}
	/**
	 * @return the reputation
	 */
	public long getReputation() {
		return reputation;
	}
	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}
	/**
	 * @return the user_type
	 */
	public String getUser_type() {
		return user_type;
	}
	/**
	 * @return the accept_rate
	 */
	public float getAccept_rate() {
		return accept_rate;
	}
	/**
	 * @return the profile_image
	 */
	public String getProfile_image() {
		return profile_image;
	}
	/**
	 * @return the display_name
	 */
	public String getDisplay_name() {
		return display_name;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param reputation the reputation to set
	 */
	public void setReputation(long reputation) {
		this.reputation = reputation;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @param user_type the user_type to set
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	/**
	 * @param accept_rate the accept_rate to set
	 */
	public void setAccept_rate(float accept_rate) {
		this.accept_rate = accept_rate;
	}
	/**
	 * @param profile_image the profile_image to set
	 */
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	/**
	 * @param display_name the display_name to set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * la methode permet de transformer un map de json_donnee au objet user
	 * @param user un HashMap qu'on obtient par le convertisseur de json qui enregistre des information d'utilisateur
	 * @return un objet user a correspond.
	 */
	
	public static User MaptoC_User(HashMap<String,Object> user) {
		return new User(user.containsKey("reputation")?Long.valueOf((Integer)user.get("reputation")):-1, 
				user.containsKey("user_id")?Long.valueOf((Integer)user.get("user_id")):-1, 
				user.containsKey("user_type")?(String)user.get("user_type"):"UNKNOWN", 
				user.containsKey("accept_rate")?Float.valueOf((Integer)user.get("accept_rate")):-1, 
				user.containsKey("profile_image")?(String)user.get("profile_image"):"UNKNOWN", 
				user.containsKey("display_name")?(String)user.get("display_name"):"UNKNOWN", 
				user.containsKey("link")?(String)user.get("link"):"UNKNOWN");
	}
	
}
