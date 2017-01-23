package Data_Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
* Une classe pour decrire un objet correspondant a une question ecrite par un utilisateur sur le site Stackoverflow.
* @author L'Etoile-TSE
*/

public class Question {
	/**
	 * Un ensemble de tags correspondant a la question
	 */
	String[] tags;
	/**
	* Un objet User indiquant a quel utilisateur cette question appartient
	*/
	User owner;
	/**
	 * Un boolean indiquant l'etat de la reponse.
	 */
	boolean is_answered;
	/**
	 * Un entier indiquant le nombre de vues.
	 */
	long view_count;
	/**
	 * Un entier indiquant le nombre de reponses.
	 */
	int answer_count;
	/**
	 * Un entier indiquant le score que cette reponse a obtenu.
	 */
	long score;
	/**
	 * Un entier indiquant l'identifiant de la question.
	 */
	int question_id;
	/**
	 * Un String qui est l'URL correspondant a la question.
	 */
	String link;
	/**
	 * Un String qui est le titre correspondant a la question.
	 */
	String title;
	
	
	/**
	 * Un constructeur par defaut
	 * @param tags Un ensemble de tags correspondant a la question
	 * @param owner Un objet User indiquant a quel utilisateur cette question appartient
	 * @param is_answered Un boolean indiquant l'etat de la reponse.
	 * @param view_count Un entier indiquant le nombre de vues.
	 * @param answer_count Un entier indiquant le nombre de reponses.
	 * @param score Un entier indiquant le score que cette reponse a obtenu.
	 * @param question_id Un entier indiquant l'identifiant de la question.
	 * @param link Un String qui est l'URL correspondant a la question.
	 * @param title Un String qui est le titre correspondant a la question.
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
	
	/**
	 * Un constructeur qui est initialise par un autre objet
	 * @param q un objet Question
	 */
	
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
	 * @return Un ensemble de tags correspondant a la question
	 */
	public String[] getTags() {
		return tags;
	}
	/**
	 * @return Un objet User indiquant a quel utilisateur cette question appartient
	 */
	public User getOwner() {
		return owner;
	}
	/**
	 * @return Un boolean indiquant l'etat de la reponse.
	 */
	public boolean isIs_answered() {
		return is_answered;
	}
	/**
	 * @return the Un entier indiquant le nombre de vues.
	 */
	public long getView_count() {
		return view_count;
	}
	/**
	 * @return Un entier indiquant le nombre de reponses.
	 */
	public int getAnswer_count() {
		return answer_count;
	}
	/**
	 * @return Un entier indiquant le score que cette reponse a obtenu.
	 */
	public long getScore() {
		return score;
	}
	/**
	 * @return Un entier indiquant l'identifiant de la question.
	 */
	public int getQuestion_id() {
		return question_id;
	}
	/**
	 * @return the Un String qui est l'URL correspondant a la question.
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @return Un String qui est le titre correspondant a la question.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param tags les tags qui seront set
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	/**
	 * @param owner le owner qui sera set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * @param is_answered le is_answered qui sera set
	 */
	public void setIs_answered(boolean is_answered) {
		this.is_answered = is_answered;
	}
	/**
	 * @param view_count le view_count qui sera set
	 */
	public void setView_count(long view_count) {
		this.view_count = view_count;
	}
	/**
	 * @param answer_count le answer_count qui sera set
	 */
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
	/**
	 * @param score le score qui sera set
	 */
	public void setScore(long score) {
		this.score = score;
	}
	/**
	 * @param question_id le question_id qui sera set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	/**
	 * @param link le link qui sera set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @param title le title qui sera set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * cette methode permet de transformer un map de json_donnee en objet question
	 * @param question un HashMap qu'on obtient par le convertisseur de json qui enregistre des informations
	 * @return l'objet question qui correspond.
	 */
	
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
	
	/**
	 * la methode permet de transformer un map de json donnee obtenu par la requete de HTTP en une liste d'objet question
	 * @param json_list un HashMap qu'on obtient par la requete HTTP
	 * @return une liste d'objet question
	 */
	
	public static ArrayList<Question> JSON_ListtoQuestion_List(ArrayList<HashMap<String, Object>> json_list) {
		ArrayList<Question> question_list = new ArrayList<Question>();
		for(HashMap<String, Object> question : json_list) {
			question.put("owner", User.MaptoC_User((HashMap<String,Object>)question.get("owner")));
			question_list.add(MaptoC_Question(question));
		}
		return question_list;
	}
	
}
