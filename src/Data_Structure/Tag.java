package Data_Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Une class pour decrire un objet tag sur le site Stackoverflow
* @author L'Etoile-TSE
*/

public class Tag {
	/**
	 * Un boolean indiquant si le tag a des synonymes
	 */
	boolean has_synonyms;
	/**
	 * Un boolean indiquant l'etat de moderator_only.
	 */
	boolean is_moderator_only;
	/**
	 * Un boolean indiquant l'etat is_required.
	 */
	boolean is_required;
	/**
	 * Un entier indiquant le nombre count.
	 */
	long count;
	/**
	 * Un string indiquant le nom du tag
	 */
	String name;
	/**
	 * Un constructeur par defaut
	 * @param has_synonyms
	 * @param is_moderator_only
	 * @param is_required
	 * @param count
	 * @param name
	 */
	public Tag(boolean has_synonyms, boolean is_moderator_only, boolean is_required, long count, String name) {
		super();
		this.has_synonyms = has_synonyms;
		this.is_moderator_only = is_moderator_only;
		this.is_required = is_required;
		this.count = count;
		this.name = name;
	}
	/**
	 * @return has_synonyms
	 */
	public boolean isHas_synonyms() {
		return has_synonyms;
	}
	/**
	 * @return is_moderator_only
	 */
	public boolean isIs_moderator_only() {
		return is_moderator_only;
	}
	/**
	 * @return is_required
	 */
	public boolean isIs_required() {
		return is_required;
	}
	/**
	 * @return count
	 */
	public long getCount() {
		return count;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param has_synonyms le has_synonyms qui va etre set
	 */
	public void setHas_synonyms(boolean has_synonyms) {
		this.has_synonyms = has_synonyms;
	}
	/**
	 * @param is_moderator_only le is_moderator_only qui va etre set
	 */
	public void setIs_moderator_only(boolean is_moderator_only) {
		this.is_moderator_only = is_moderator_only;
	}
	/**
	 * @param is_required le is_required qui va etre set
	 */
	public void setIs_required(boolean is_required) {
		this.is_required = is_required;
	}
	/**
	 * @param count le count qui va etre set
	 */
	public void setCount(long count) {
		this.count = count;
	}
	/**
	 * @param name le name qui va etre set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tag [has_synonyms=" + has_synonyms + ", is_moderator_only=" + is_moderator_only + ", is_required="
				+ is_required + ", count=" + count + ", name=" + name + "]" + "\n";
	}
	
	/**
	 * cette methode permet de transformer un map de json_donnee en objet tag
	 * @param tag un HashMap qu'on obtient grace au convertisseur de json qui enregistre les informations
	 * @return un objet tag qui correspond.
	 */
	
	public static Tag MaptoC_Tag(HashMap<String,Object> tag) {
		return new Tag(tag.containsKey("has_synonyms")?(boolean)tag.get("has_synonyms"):false, 
				tag.containsKey("is_moderator_only")?(boolean)tag.get("is_moderator_only"):false, 
				tag.containsKey("is_required")?(boolean)tag.get("is_required"):false, 
				tag.containsKey("count")?Long.valueOf((Integer)tag.get("count")):-1, 
				tag.containsKey("name")?(String)tag.get("name"):"UNKNOWN");
	}
	
	/**
	 * cette methode permet de transformer un map de json donnee obtenu avec la requette HTTP en une liste d'objets tag
	 * @param json_list un HashMap qu'on obtient avec la requette HTTP
	 * @return une liste d'objets tags
	 */
	
	public static ArrayList<Tag> JSON_ListtoTag_List(ArrayList<HashMap<String, Object>> json_list) {
		ArrayList<Tag> tag_list = new ArrayList<Tag>();
		for(HashMap<String, Object> tag : json_list) {
			tag_list.add(MaptoC_Tag(tag));
		}
		return tag_list;
	}
}
