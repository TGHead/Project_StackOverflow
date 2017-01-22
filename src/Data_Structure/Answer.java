package Data_Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Answer {
	User owner;
	boolean is_accepted;
	long score;
	int answer_id;
	int question_id;
	
	/**
	 * @param owner
	 * @param is_accepted
	 * @param score
	 * @param answer_id
	 * @param question_id
	 */
	public Answer(User owner, boolean is_accepted, long score, int answer_id, int question_id) {
		super();
		this.owner = owner;
		this.is_accepted = is_accepted;
		this.score = score;
		this.answer_id = answer_id;
		this.question_id = question_id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [owner=" + owner + ", is_accepted=" + is_accepted + ", score=" + score + ", answer_id="
				+ answer_id + ", question_id=" + question_id + "]";
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}
	/**
	 * @return the is_accepted
	 */
	public boolean isIs_accepted() {
		return is_accepted;
	}
	/**
	 * @return the score
	 */
	public long getScore() {
		return score;
	}
	/**
	 * @return the answer_id
	 */
	public int getAnswer_id() {
		return answer_id;
	}
	/**
	 * @return the question_id
	 */
	public int getQuestion_id() {
		return question_id;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * @param is_accepted the is_accepted to set
	 */
	public void setIs_accepted(boolean is_accepted) {
		this.is_accepted = is_accepted;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(long score) {
		this.score = score;
	}
	/**
	 * @param answer_id the answer_id to set
	 */
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	/**
	 * @param question_id the question_id to set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	
	public static Answer MaptoC_Answer(HashMap<String,Object> answer) {
		return new Answer(answer.containsKey("owner")?(User)answer.get("owner"):null, 
				answer.containsKey("is_accepted")?(boolean)answer.get("is_accepted"):false, 
				answer.containsKey("score")?Long.valueOf((Integer)answer.get("score")):-1, 
				answer.containsKey("answer_id")?(int)answer.get("answer_id"):-1, 
				answer.containsKey("question_id")?(int)answer.get("question_id"):-1);
	}
	
	public static ArrayList<Answer> JSON_ListtoAnswer_List(ArrayList<HashMap<String, Object>> json_list) {
		ArrayList<Answer> answer_list = new ArrayList<Answer>();
		for(HashMap<String, Object> answer : json_list) {
			answer.put("owner", User.MaptoC_User((HashMap<String,Object>)answer.get("owner")));
			answer_list.add(MaptoC_Answer(answer));
		}
		return answer_list;
	}
}
