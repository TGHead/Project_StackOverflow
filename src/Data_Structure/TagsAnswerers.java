package Data_Structure;

import java.util.ArrayList;
import java.util.HashMap;

public class TagsAnswerers {
	Long user_id;
	String user_name;
	String tag_name;
	int post_count;
	int score;
	/**
	 * @param user_id
	 * @param user_name
	 * @param tag_name
	 * @param post_count
	 * @param score
	 */
	public TagsAnswerers(Long user_id, String user_name, String tag_name, int post_count, int score) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.tag_name = tag_name;
		this.post_count = post_count;
		this.score = score;
	}
	/**
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
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
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @param tag_name the tag_name to set
	 */
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	/**
	 * @param post_count the post_count to set
	 */
	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TagsAnswerers [user_id=" + user_id + ", user_name=" + user_name + ", tag_name=" + tag_name
				+ ", post_count=" + post_count + ", score=" + score + "]\n";
	}
	
	public static TagsAnswerers MaptoC_TagsAnswerers(HashMap<String,Object> tag_answerer, String tag) {
		return new TagsAnswerers(tag_answerer.containsKey("user")?(long)((User) (tag_answerer.get("user"))).getUser_id():-1, 
				tag_answerer.containsKey("user")?(String)((User) tag_answerer.get("user")).getDisplay_name():"UNKNOWN", 
				tag, 
				tag_answerer.containsKey("post_count")?(int)tag_answerer.get("post_count"):-1, 
				tag_answerer.containsKey("score")?(int)tag_answerer.get("score"):-1);
	}
	
	public static ArrayList<TagsAnswerers> JSON_ListtoTagsAnswerers_List(ArrayList<HashMap<String, Object>> json_list, String tag) {
		ArrayList<TagsAnswerers> tag_list = new ArrayList<TagsAnswerers>();
		for(HashMap<String, Object> tag_answerers : json_list) {
			tag_answerers.put("user", User.MaptoC_User((HashMap<String,Object>)tag_answerers.get("user")));
			tag_list.add(MaptoC_TagsAnswerers(tag_answerers, tag));
		}
		return tag_list;
	}
	
}
