package Base_de_Donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Data_Structure.Tag;
import Data_Structure.TagsAnswerers;
import Data_Structure.User;

public class Operation_BDD {
	
	public static Connection Derby_Connexion() {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String URL = "jdbc:derby:BDD_StackOverflow;create=true;upgrade=true";
		try {
			Class.forName(driver);
		}
		catch (Exception e) {
			System.out.println("erreur chargement driver");
			return null;
		}
		try {
			Connection laConnexion = DriverManager.getConnection(URL, "etoile_tse", "etoile_tse");
			return laConnexion;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public static void Derby_DisConnexion(Connection laConnexion) {
		try {
			laConnexion.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void Create_Table_Tags(Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Creer la table_tags echoue!");
		}
		else {
			String requete = "CREATE TABLE PROJET_STACKEXCHANGE.tags (tag_name VARCHAR(20) PRIMARY KEY, nombre INT, has_synonyms BOOLEAN, is_moderator_only BOOLEAN, is_required BOOLEAN)";
			Statement laRequete = null;
			try {
				laRequete = laConnexion.createStatement();
				laRequete.execute(requete);
				System.out.println("Bien creer la table_tags!");
			} 
			catch (SQLException e) {
				if(e.getSQLState().equals("X0Y32")) {
					System.out.println("Table tags already exists.");
				}
				else {
					e.printStackTrace();
				}
			}
			finally {
				try {
					laRequete.close();
					laConnexion.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void Create_Table_Users(Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Creer la table_users echoue!");
		}
		else {
			String requete = "CREATE TABLE PROJET_STACKEXCHANGE.users (user_id INT PRIMARY KEY, reputation INT, user_type VARCHAR(20), accept_rate FLOAT, profile_image VARCHAR(256), display_name VARCHAR(128), link VARCHAR(256))";
			Statement laRequete = null;
			try {
				laRequete = laConnexion.createStatement();
				laRequete.execute(requete);
				//System.out.println("Bien creer la table_tags!");
				laRequete.close();
			} 
			catch (SQLException e) {
				if(e.getSQLState().equals("X0Y32")) {
					System.out.println("Table users already exists.");
				}
				else {
					e.printStackTrace();
				}
			}
			finally {
				try {
					laRequete.close();
					laConnexion.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void Create_Tables_TagsAnswerers (Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Creer la table_TagsAnswerers echoue!");
		}
		else {
			String requete = "CREATE TABLE PROJET_STACKEXCHANGE.tags_answerers (user_id INT NOT NULL, user_name VARCHAR(128), tag_name VARCHAR(20) NOT NULL, post_count INT, score INT, PRIMARY KEY (user_id, tag_name))";
			Statement laRequete = null;
			try {
				laRequete = laConnexion.createStatement();
				laRequete.execute(requete);
				//System.out.println("Bien creer la table_tags_answerers!");
				laRequete.close();
			} 
			catch (SQLException e) {
				if(e.getSQLState().equals("X0Y32")) {
					System.out.println("Table tags_answerers already exists.");
				}
				else {
					e.printStackTrace();
				}
			}
			finally {
				try {
					laRequete.close();
					laConnexion.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void Insert_Into_Table_Tags (Connection laConnexion, Tag tag) {
		if (laConnexion == null) {
			System.out.println("Inserer dans la table_Tags echoue!");
		}
		else {
			String requete = "INSERT INTO PROJET_STACKEXCHANGE.tags VALUES(?, ?, ?, ?, ?)";
			PreparedStatement laRequete = null;
			try {
				laConnexion.setAutoCommit(false);
				laRequete = laConnexion.prepareStatement(requete);
				laRequete.setString(1, tag.getName());
				laRequete.setLong(2, tag.getCount());
				laRequete.setBoolean(3, tag.isHas_synonyms());
				laRequete.setBoolean(4, tag.isIs_moderator_only());
				laRequete.setBoolean(5, tag.isIs_required());
				laRequete.execute();
				laConnexion.commit();
			}
			catch (SQLException e) {
				if(e.getSQLState().equals("23505")) {
					System.out.println("Record " + tag.getName() + " is already exists.");
				}
				else {
					e.printStackTrace();
				}
			}
			finally {
				try {
					laRequete.close();
					laConnexion.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void Insert_Into_Table_Users (Connection laConnexion, User user) {
		if (laConnexion == null) {
			System.out.println("Inserer dans la table_Users echoue!");
		}
		else {
			String requete = "INSERT INTO PROJET_STACKEXCHANGE.users VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement laRequete = null;
			try {
				laConnexion.setAutoCommit(false);
				laRequete = laConnexion.prepareStatement(requete);
				laRequete.setLong(1, user.getUser_id());
				laRequete.setLong(2, user.getReputation());
				laRequete.setString(3, user.getUser_type());
				laRequete.setFloat(4, user.getAccept_rate());
				laRequete.setString(5, user.getProfile_image());
				laRequete.setString(6, user.getDisplay_name());
				laRequete.setString(7, user.getLink());
				laRequete.execute();
				laConnexion.commit();
			}
			catch (SQLException e) {
				if(e.getSQLState().equals("23505")) {
					System.out.println("Record " + user.getDisplay_name() + " is already exists.");
				}
				else {
					e.printStackTrace();
				}
			}
			finally {
				try {
					laRequete.close();
					laConnexion.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void Insert_Into_Table_TagsAnswerers (Connection laConnexion, TagsAnswerers tag_answerers) {
		if (laConnexion == null) {
			System.out.println("Inserer dans la table_TagsAnswerers echoue!");
		}
		else {
			String requete = "INSERT INTO PROJET_STACKEXCHANGE.tags_answerers VALUES(?, ?, ?, ?, ?)";
			PreparedStatement laRequete = null;
			try {
				laConnexion.setAutoCommit(false);
				laRequete = laConnexion.prepareStatement(requete);
				laRequete.setLong(1, tag_answerers.getUser().getUser_id());
				laRequete.setString(2, tag_answerers.getUser().getDisplay_name());
				laRequete.setString(3, tag_answerers.getTag_name());
				laRequete.setInt(4, tag_answerers.getPost_count());
				laRequete.setInt(5, tag_answerers.getScore());
				laRequete.execute();
				laConnexion.commit();
			}
			catch (SQLException e) {
				if(e.getSQLState().equals("23505")) {
					System.out.println("Record is already exists.");
				}
				else {
					e.printStackTrace();
				}
			}
			finally {
				try {
					laRequete.close();
					laConnexion.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void Delete_Table_Tags(Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Delete des contenus de la table_Tags echoue!");
		}
		else {
			Statement laRequete = null;
			try {
				laRequete = laConnexion.createStatement();
				laRequete.execute("DELETE FROM PROJET_STACKEXCHANGE.tags");
				System.out.println("Effacee toutes les records dans la table tags");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					laRequete.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void Delete_Table_Users(Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Delete des contenus de la table_Users echoue!");
		}
		else {
			Statement laRequete = null;
			try {
				laRequete = laConnexion.createStatement();
				laRequete.execute("DELETE FROM PROJET_STACKEXCHANGE.users");
				System.out.println("Effacee toutes les records dans la table users");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					laRequete.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void Delete_Table_TagsAnswerers(Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Delete des contenus de la table_TagsAnswerers echoue!");
		}
		else {
			Statement laRequete = null;
			try {
				laRequete = laConnexion.createStatement();
				laRequete.execute("DELETE FROM PROJET_STACKEXCHANGE.tags_answerers");
				System.out.println("Effacee toutes les records dans la table tags_answerers");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					laRequete.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ArrayList<Tag> Select_Table_Tags(Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Select via la table_Tags echoue!");
			return null;
		}
		else {
			ArrayList<Tag> list_tag = new ArrayList<Tag>();
			try {
				Statement laRequete = laConnexion.createStatement();
				ResultSet leResultat = laRequete.executeQuery("SELECT * FROM PROJET_STACKEXCHANGE.tags");
				while (leResultat.next()) {
					list_tag.add(new Tag(leResultat.getBoolean(3), leResultat.getBoolean(4), leResultat.getBoolean(5), leResultat.getLong(2), leResultat.getString(1)));
				}
				laRequete.close();
				laConnexion.commit();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list_tag;
		}
	}
	
	public static ArrayList<User> Select_Table_Users(Connection laConnexion) {
		if (laConnexion == null) {
			System.out.println("Select via la table_Users echoue!");
			return null;
		}
		else {
			ArrayList<User> list_user = new ArrayList<User>();
			try {
				Statement laRequete = laConnexion.createStatement();
				ResultSet leResultat = laRequete.executeQuery("SELECT * FROM PROJET_STACKEXCHANGE.users");
				while (leResultat.next()) {
					list_user.add(new User(leResultat.getLong(2), leResultat.getLong(1), leResultat.getString(3), leResultat.getFloat(4), leResultat.getString(5), leResultat.getString(6), leResultat.getString(7)));
				}
				laRequete.close();
				laConnexion.commit();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list_user;
		}
	}
	
	public static ArrayList<TagsAnswerers> Select_Table_TagsAnswerers(Connection laConnexion, String para) {
		if (laConnexion == null) {
			System.out.println("Select via la table_Tags echoue!");
			return null;
		}
		else {
			ArrayList<TagsAnswerers> list_tag_answerers = new ArrayList<TagsAnswerers>();
			Statement laRequete = null;
			try {
				String requete = "SELECT * FROM PROJET_STACKEXCHANGE.users AS u JOIN PROJET_STACKEXCHANGE.tags_answerers AS t ON (u.user_id=t.user_id)";
				laRequete = laConnexion.createStatement();
				if(para.equals("1")){
					//tirer par post_count
					requete += "ORDER BY post_count DESC";
				}
				else {
					//tirer par score
					requete += "ORDER BY score DESC";
				}
				ResultSet leResultat = laRequete.executeQuery(requete);
				while (leResultat.next()) {
					User user = new User(leResultat.getLong(2), leResultat.getLong(1), leResultat.getString(3), leResultat.getFloat(4), leResultat.getString(5), leResultat.getString(6),leResultat.getString(7));
					list_tag_answerers.add(new TagsAnswerers(user, leResultat.getString(3), leResultat.getInt(4), leResultat.getInt(5)));
				}
				laRequete.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					laRequete.close();
					laConnexion.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list_tag_answerers;
		}
	}
	
	public static ArrayList<TagsAnswerers> Select_Table_PluralTagsAnswerers(Connection laConnexion, String para) {
		if (laConnexion == null) {
			System.out.println("Select via la table_Tags echoue!");
			return null;
		}
		else {
			ArrayList<TagsAnswerers> list_tag_answerers = new ArrayList<TagsAnswerers>();
			Statement laRequete = null;
			try {
				String requete = "SELECT u.reputation, u.user_id, u.user_type, u.accept_rate, u.profile_image, u.display_name, u.link, t.sum_post_count, t.sum_score "
						+ "FROM PROJET_STACKEXCHANGE.users AS u JOIN "
							+ "(SELECT user_id, SUM(T.post_count) AS sum_post_count, SUM(T.score) AS sum_score "
							+ "FROM PROJET_STACKEXCHANGE.tags_answerers AS T "
							+ "GROUP BY user_id) AS t "
						+ "ON (u.user_id=t.user_id) ";
				laRequete = laConnexion.createStatement();
				if(para.equals("1")){
					//tirer par post_count
					requete += "ORDER BY t.sum_post_count DESC";
				}
				else {
					//tirer par score
					requete += "ORDER BY t.sum_score DESC";
				}
				ResultSet leResultat = laRequete.executeQuery(requete);
				while (leResultat.next()) {
					User user = new User(leResultat.getLong(1), leResultat.getLong(2), leResultat.getString(3), leResultat.getFloat(4), leResultat.getString(5), leResultat.getString(6),leResultat.getString(7));
					list_tag_answerers.add(new TagsAnswerers(user, "A set of tags", leResultat.getInt(8), leResultat.getInt(9)));
				}
				laRequete.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					laRequete.close();
					laConnexion.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list_tag_answerers;
		}
	}
}
