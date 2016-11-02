package Interface_Graphique;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class resultat_Frame extends JFrame {
	
	JTextArea list_de_user;
	
	public resultat_Frame (String str, String u_list) {
		super(str);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		list_de_user = new JTextArea(u_list);
		contentPane.add(list_de_user,BorderLayout.CENTER);
		setSize(400,300);
		setVisible(true);
	}
}
