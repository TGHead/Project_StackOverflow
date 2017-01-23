package Data_Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
* Une class pour decrire un objet answer sur le site Stackoverflow qui represente une reponse ecrite par un utilisateur.
* @author L'Etoile-TSE
*/

public class Answer {
	/**
	* Un objet indiquant l'utilisateur a qui la reponse appartient.
	*/
	User owner;
	/**
	* Un boolean indiquant l'etat d'acceptation.
	*/
	boolean is_accepted;
	/**
	* Un entier indiquant le score que cette reponse a obtenu .
	*/
	long score;
	/**
	* Un entier indiquant l'identifiant de la reponse .
	*/
	int answer_id;
	/**
	* Un entier indiquant l'identifiant de la question qui correspond .
	*/
	int question_id;
	
	/**
	 * Constructeur de la class Answer par defaut.
	 * @param owner Un objet indiquant l'utilisateur a qui la reponse appartient.
	 * @param is_accepted  Un boolean indiquant l'etat d'acceptation.
	 * @param score Un entier indiquant le score que cette reponse a obtenu .
	 * @param answer_id Un entier indiquant l'identifiant de la reponse .
	 * @param question_id Un entier indiquant l'identifiant de la question qui correspond .
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
	 * @param owner le owner qui doit etre set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * @param is_accepted le boolen qui doit etre set
	 */
	public void setIs_accepted(boolean is_accepted) {
		this.is_accepted = is_accepted;
	}
	/**
	 * @param score le score qui doit etre set
	 */
	public void setScore(long score) {
		this.score = score;
	}
	/**
	 * @param answer_id le answer_id qui doit etre set
	 */
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	/**
	 * @param question_id le question_id qui doit etre set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	
	/**
	 * cette methode permet de transformer un map de json_donnee en objet answer
	 * @param answer un HashMap qu'on obtient par le convertisseur de json qui enregistre des informations
	 * @return un objet answer qui correspond.
	 */
	
	public static Answer MaptoC_Answer(HashMap<String,Object> answer) {
		return new Answer(answer.containsKey("owner")?(User)answer.get("owner"):null, 
				answer.containsKey("is_accepted")?(boolean)answer.get("is_accepted"):false, 
				answer.containsKey("score")?Long.valueOf((Integer)answer.get("score")):-1, 
				answer.containsKey("answer_id")?(int)answer.get("answer_id"):-1, 
				answer.containsKey("question_id")?(int)answer.get("question_id"):-1);
	}
	
	/**
	 * cette methode permet de transformer un map de json donnee obtenu par requete de HTTP en une liste d'objets answer
	 * @param json_list un HashMap qu'on obtient par requete de HTTP
	 * @return une liste d'objets answers
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
