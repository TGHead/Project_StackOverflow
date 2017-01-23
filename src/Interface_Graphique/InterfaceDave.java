package Interface_Graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

import fonctionalite_Dave.fonction_Dave;

/**
 * Element d'affichage des users stories de Dave
 * 
 * @author Etoile-TSE
 */
public class InterfaceDave {
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Entrez le(s) sujet(s) à rechercher");
	JButton button1 = new JButton("Trier par score obtenu");
	JButton button2 = new JButton("Trier par nombre de post");
	JTextField text = new JTextField(20);
	JEditorPane result = new JEditorPane();

	// Constructeur
	/**
	 * 
	 * @throws IOException
	 * @throws JSONException
	 */
	public InterfaceDave() throws IOException, JSONException {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JPanel panelLabel = new JPanel();
		JPanel panelText = new JPanel();
		JPanel panelButton1 = new JPanel();
		JPanel panelButton2 = new JPanel();
		panelLabel.add(label);
		panelText.add(text);
		panelButton1.add(button1);
		panelButton2.add(button2);
		panel.add(panelLabel);
		panel.add(panelText);
		panel.add(panelButton1);
		panel.add(panelButton2);

		Ecouteur listen = new Ecouteur();// Ecouteur pour les bouttons
		EcouteurURL listener = new EcouteurURL();// Ecouteur pour les liens
													// hypertext
		result.addHyperlinkListener(listener);
		button1.addActionListener(listen);
		button2.addActionListener(listen);
		text.requestFocus();// Focus sur la zone de texte
		result.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));

	}

	/**
	 * Ecouteur permettant une action lors de l'appui sur un bouton
	 * 
	 * @author Etoile-TSE
	 */
	public class Ecouteur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = "";
			boolean err = false;
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String[]> tab = new ArrayList<String[]>();
			fonction_Dave f = new fonction_Dave();
			result.setText("");
			String recherche = text.getText();
			String[] temp = recherche.split("[,\\ \\;]");
			for (int i = 0; i < temp.length; i++) {
				if (!temp[i].isEmpty())
					try {
						list.add(URLEncoder.encode(temp[i], "UTF-8"));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
			}
			String choix;
			if (e.getSource() == button1)
				choix = "2";
			else
				choix = "1";
			try {
				tab = f.Dave(list, "20", choix);
			} catch (IOException e1) {

				e1.printStackTrace();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

			if (tab.get(tab.size() - 1)[0] != "") {
				err = true;
				s = "<b style=\"color:#FF0000\">Le(s) tag(s) " + tab.get(tab.size() - 1)[0]
						+ " n'existe(nt) pas</b><br>";
			}

			for (int i = 0; i < tab.size() - 1; i++) {
				s = s + "<a href=\"" + tab.get(i)[1] + "\">" + tab.get(i)[0] + "</a><br>Nombre de post : "
						+ tab.get(i)[2] + "<br>Score obtenu : " + tab.get(i)[3] + "<br><br>";
			}

			result.setText(s);
			StackEtoiles.jsp.setViewportView(result);
			result.setCaretPosition(0);
			result.setEditable(false);
			StackEtoiles.panel.add(StackEtoiles.jsp);
			if (err) {
				StackEtoiles.frame.pack();
				StackEtoiles.frame.setSize(StackEtoiles.largeur, StackEtoiles.frame.getHeight());
			} else {
				StackEtoiles.frame.setSize(StackEtoiles.largeur, StackEtoiles.hauteur);
			}
			StackEtoiles.frame.setVisible(true);
		}

	}

}
