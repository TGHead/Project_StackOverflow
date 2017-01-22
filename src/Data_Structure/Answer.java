package Data_Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
* Un class pour decrire un objet de answer sur la site Stackoverflow qui presente une reponse ecrit par un utilisateur.
* @author L'Etoile-TSE
*/

public class Answer {
	/**
	* Un objet User indique cette reponse appartient de quel utilisateur.
	*/
	User owner;
	/**
	* Un boolean indique l'etat d'accepte.
	*/
	boolean is_accepted;
	/**
	* Un entier indique le score que cette reponse obtient .
	*/
	long score;
	/**
	* Un entier indique l'identifiant de reponse .
	*/
	int answer_id;
	/**
	* Un entier indique l'identifiant de question a correspond .
	*/
	int question_id;
	
	/**
	 * Constructuer de class Answer par defaut.
	 * @param owner owner
	 * @param is_accepted is_accepted
	 * @param score score
	 * @param answer_id answer_id
	 * @param question_id question_id
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
	
	/**
	 * la methode permet de transformer un map de json_donnee au objet answer
	 * @param answer un HashMap qu'on obtient par le convertisseur de json qui enregistre des information
	 * @return un objet answer a correspond.
	 */
	
	public static Answer MaptoC_Answer(HashMap<String,Object> answer) {
		return new Answer(answer.containsKey("owner")?(User)answer.get("owner"):null, 
				answer.containsKey("is_accepted")?(boolean)answer.get("is_accepted"):false, 
				answer.containsKey("score")?Long.valueOf((Integer)answer.get("score")):-1, 
				answer.containsKey("answer_id")?(int)answer.get("answer_id"):-1, 
				answer.containsKey("question_id")?(int)answer.get("question_id"):-1);
	}
	
	/**
	 * la methode permet de transformer un map de json donnee obtenu par la requette de HTTP au une liste des objets answer
	 * @param json_list un HashMap qu'on obtient par la requette de HTTP
	 * @return une liste des objets answers
	 */
	
	public static ArrayList<Answer> JSON_ListtoAnswer_List(ArrayList<HashMap<String, Object>> json_list) {
		ArrayList<Answer> answer_list = new ArrayList<Answer>();
		for(HashMap<String, Object> answer : json_list) {
			answer.put("owner", User.MaptoC_User((HashMap<String,Object>)answer.get("owner")));
			answer_list.add(MaptoC_Answer(answer));
		}
		return answer_list;
	}
}
