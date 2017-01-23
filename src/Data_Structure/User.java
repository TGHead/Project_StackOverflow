package Data_Structure;

import java.util.HashMap;

/**
* Un class pour decrire un objet d'utilisateur sur le site Stackoverflow.
* @author L'Etoile-TSE
*/

public class User {
	/**
	 * un entier qui indique la reputation de l'utilisateur
	 * 
	 */
	long reputation;
	/**
	 * un entier qui indique l'identifiant de l'utilisateur
	 */
	long user_id;
	/**
	 * un string qui indique le type d'utilisateur
	 */
	String user_type;
	/**
	 * un float qui indique le taux de succes de l'utilisateur
	 */
	float accept_rate;
	/**
	 * un lien URL qui correspond a l'image de profil de l'utilisateur
	 */
	String profile_image;
	/**
	 * un String qui indique le pseudo de l'utilisateur
	 */
	String display_name;
	/**
	 * un lien qui correspond a la page personnelle de l'utilisateur
	 */
	String link;
	/**
	 * un constructeur par defaut
	 * @param reputation un entier qui indique la reputation de l'utilisateur
	 * @param user_id un entier qui indique l'identifiant de l'utilisateur
	 * @param user_type un string qui indique le type d'utilisateur
	 * @param accept_rate un float qui indique le taux de succes de l'utilisateur
	 * @param profile_image un lien URL qui correspond a l'image de profil de l'utilisateur
	 * @param display_name un String qui indique le pseudo de l'utilisateur
	 * @param link un lien qui correspond a la page personnelle de l'utilisateur
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
	 * @param reputation la reputation qui va etre set
	 */
	public void setReputation(long reputation) {
		this.reputation = reputation;
	}
	/**
	 * @param user_id le user_id qui va etre set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @param user_type le user_type qui va etre set
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	/**
	 * @param accept_rate le accept_rate qui va etre set
	 */
	public void setAccept_rate(float accept_rate) {
		this.accept_rate = accept_rate;
	}
	/**
	 * @param profile_image la profile_image qui va etre set
	 */
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	/**
	 * @param display_name le display_name qui va etre set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @param link le link qui va etre set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * la methode permet de transformer un map de json_donnee en objet user
	 * @param user un HashMap qu'on obtient avec le convertisseur de json qui enregistre des informations d'utilisateur
	 * @return un objet user qui correspond.
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
