package Data_Structure;

import java.util.HashMap;

public class User {
	long reputation;
	long user_id;
	String user_type;
	float accept_rate;
	String profile_image;
	String display_name;
	String link;
	/**
	 * @param reputation
	 * @param user_id
	 * @param user_type
	 * @param accept_rate
	 * @param profile_image
	 * @param display_name
	 * @param link
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
	public static User MaptoC_User(HashMap<String,Object> user) {
		return new User((long)user.get("reputation"), 
				(long)user.get("user_id"), 
				(String)user.get("user_type"), 
				(float)user.get("accept_rate"), 
				(String)user.get("profile_image"), 
				(String)user.get("display_name"), 
				(String)user.get("link"));
	}
	/*
	public static HashMap<String,Object> C_UsertoMap(User u) {
		HashMap<String, Object> map = {"reputation" : u.getReputation(), "user_id" : u.getUser_id(), "user_type" : u.getUser_type(), "accept_rate" : u.getAccept_rate(), "profile_image" : u.getProfile_image(), "display_name" : u.getDisplay_name()};
	}
	*/
}
