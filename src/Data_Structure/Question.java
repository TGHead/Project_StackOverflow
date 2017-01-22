package Data_Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Question {
	String[] tags;
	User owner;
	boolean is_answered;
	long view_count;
	int answer_count;
	long score;
	int question_id;
	String link;
	String title;
	
	
	/**
	 * @param tags
	 * @param owner
	 * @param is_answered
	 * @param view_count
	 * @param answer_count
	 * @param score
	 * @param question_id
	 * @param link
	 * @param title
	 */
	public Question(String[] tags, User owner, boolean is_answered, long view_count, int answer_count, long score,
			int question_id, String link, String title) {
		super();
		this.tags = tags;
		this.owner = owner;
		this.is_answered = is_answered;
		this.view_count = view_count;
		this.answer_count = answer_count;
		this.score = score;
		this.question_id = question_id;
		this.link = link;
		this.title = title;
	}
	
	public Question(Question q) {
		// TODO Auto-generated constructor stub
		this.tags = q.getTags();
		this.owner = q.getOwner();
		this.is_answered = q.isIs_answered();
		this.view_count = q.getView_count();
		this.answer_count = q.getAnswer_count();
		this.score = q.getScore();
		this.question_id = q.getQuestion_id();
		this.link = q.getLink();
		this.title = q.getTitle();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [tags=" + Arrays.toString(tags) + ", owner=" + owner + ", is_answered=" + is_answered
				+ ", view_count=" + view_count + ", answer_count=" + answer_count + ", score=" + score
				+ ", question_id=" + question_id + ", link=" + link + ", title=" + title + "]";
	}

	/**
	 * @return the tags
	 */
	public String[] getTags() {
		return tags;
	}
	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}
	/**
	 * @return the is_answered
	 */
	public boolean isIs_answered() {
		return is_answered;
	}
	/**
	 * @return the view_count
	 */
	public long getView_count() {
		return view_count;
	}
	/**
	 * @return the answer_count
	 */
	public int getAnswer_count() {
		return answer_count;
	}
	/**
	 * @return the score
	 */
	public long getScore() {
		return score;
	}
	/**
	 * @return the question_id
	 */
	public int getQuestion_id() {
		return question_id;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * @param is_answered the is_answered to set
	 */
	public void setIs_answered(boolean is_answered) {
		this.is_answered = is_answered;
	}
	/**
	 * @param view_count the view_count to set
	 */
	public void setView_count(long view_count) {
		this.view_count = view_count;
	}
	/**
	 * @param answer_count the answer_count to set
	 */
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(long score) {
		this.score = score;
	}
	/**
	 * @param question_id the question_id to set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Question MaptoC_Question(HashMap<String,Object> question) {
		return new Question(question.containsKey("tags")?(String [])((ArrayList)question.get("tags")).toArray(new String[((ArrayList)question.get("tags")).size()]):null, 
				question.containsKey("owner")?(User)question.get("owner"):null, 
				question.containsKey("is_answered")?(boolean)question.get("is_answered"):false, 
				question.containsKey("view_count")?Long.valueOf((Integer)question.get("view_count")):-1, 
				question.containsKey("answer_count")?(int)question.get("answer_count"):-1, 
				question.containsKey("score")?Long.valueOf((Integer)question.get("score")):-1, 
				question.containsKey("question_id")?(int)question.get("question_id"):-1,
				question.containsKey("link")?(String)question.get("link"):"UNKNOWN",
				question.containsKey("title")?(String)question.get("title"):"UNKNOWN");
	}
	
	public static ArrayList<Question> JSON_ListtoQuestion_List(ArrayList<HashMap<String, Object>> json_list) {
		ArrayList<Question> question_list = new ArrayList<Question>();
		for(HashMap<String, Object> question : json_list) {
			question.put("owner", User.MaptoC_User((HashMap<String,Object>)question.get("owner")));
			question_list.add(MaptoC_Question(question));
		}
		return question_list;
	}
	
}
