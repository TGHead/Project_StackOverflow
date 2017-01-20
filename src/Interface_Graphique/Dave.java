package Interface_Graphique;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.json.JSONException;

import fonctionalite_Alice.fonction_Alice;
import fonctionalite_Dave.fonction_Dave;

public class Dave {

	fonction_Dave f = new fonction_Dave();
	fonction_Alice f2 = new fonction_Alice();
	JFrame frame = new JFrame("Dave");
	JLabel label = new JLabel("Entrez le(s) sujet(s) � rechercher");
	JPanel panel = new JPanel();
	JScrollPane jsp = new JScrollPane();
	JButton button = new JButton("OK");
	JTextField text = new JTextField(20);
	String listeDave[] = { "Trier par score obtenu", "Trier par nombre de post" };
	String listeAlice[] = { "Chercher des nouvelles questions", "Chercher les utilisateurs ayant plus de badges",
			"Trier les questions auxquelles j'ai r閜ondu" };
	String listeFonctions[] = { "Fonctionnalit閟 de Dave", "Fonctionnalit閟 d'Alice" };
	JComboBox<String> box = new JComboBox<String>(listeDave);
	JComboBox<String> boxFonctions = new JComboBox<String>(listeFonctions);
	JEditorPane result = new JEditorPane();
	boolean res = false;
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String[]> tab = new ArrayList<String[]>();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Dave() throws IOException, JSONException {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		panel4.add(boxFonctions);
		panel1.add(text);
		panel2.add(box);
		panel2.add(button);
		panel3.add(label);
		panel.add(panel4);
		panel.add(panel3);
		panel.add(panel1);
		panel.add(panel2);

		Ecouteur listen = new Ecouteur();
		EcouteurFonctions lis = new EcouteurFonctions();
		EcouteurURL listener = new EcouteurURL();
		result.addHyperlinkListener(listener);
		boxFonctions.addActionListener(lis);
		button.addActionListener(listen);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		result.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));

	}

	public class EcouteurURL implements HyperlinkListener {
		@Override
		public void hyperlinkUpdate(HyperlinkEvent e) {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	public class Ecouteur implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int n = boxFonctions.getSelectedIndex();
			switch (n) {
			case 0:
				result.setText("");
				String recherche = text.getText();
				String[] temp = recherche.split("[,\\ \\;]");
				list.clear();
				for (int i = 0; i < temp.length; i++) {
					if (!temp[i].isEmpty())
						try {
							list.add(URLEncoder.encode(temp[i], "UTF-8"));
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
				}

				String choix = Integer.toString(box.getSelectedIndex());
				tab.clear();
				try {
					tab = f.resultat(list, "20", choix);
					res = true;
				} catch (IOException e1) {

					e1.printStackTrace();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

				String s = "";
				if (tab.get(tab.size() - 1)[0] != "") {
					s = "<b style=\"color:#FF0000\">Le(s) tag(s) " + tab.get(tab.size() - 1)[0]
							+ " n'existe(nt) pas</b><br>";
				}

				for (int i = 0; i < tab.size() - 1; i++) {
					s = s + "<a href=\"" + tab.get(i)[1] + "\">" + tab.get(i)[0] + "</a><br>Nombre de post : "
							+ tab.get(i)[2] + "<br>Score obtenu : " + tab.get(i)[3] + "<br><br>";
				}

				result.setText(s);
				frame.getContentPane().remove(panel);
				jsp.setViewportView(result);
				panel.add(jsp);
				result.setCaretPosition(0);
				result.setEditable(false);
				frame.getContentPane().add(panel);
				frame.setSize(251, (int) (screenSize.getHeight() - 100));
				if (tab.get(tab.size() - 1) == tab.get(0)) {
					res = false;
					frame.pack();
				}
				frame.setVisible(true);
				break;
			case 1:
				ArrayList<String[]> tab = new ArrayList<String[]>();
				try {
					tab = f2.Alice1(text.getText());
					res = true;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String string = "";

				if (tab.isEmpty()) {
					res = false;
					string = "<b style=\"color:#FF0000\">Le num閞o d'identifiant " + text.getText()
							+ " n'existe pas</b><br>";
				} else {

					for (int i = 0; i < tab.get(0).length; i++) {
						string = string + "<a href=\"" + tab.get(1)[i] + "\">" + tab.get(0)[i] + "</a><br>Tags :"
								+ tab.get(2)[i] + "<br><br>";

					}
				}
				result.setText(string);
				frame.getContentPane().remove(panel);
				jsp.setViewportView(result);
				panel.add(jsp);
				result.setCaretPosition(0);
				result.setEditable(false);
				frame.getContentPane().add(panel);
				if (res)
					frame.setSize(373, (int) (screenSize.getHeight() - 100));
				else
					frame.pack();

				frame.setVisible(true);
				break;

			default:
				break;
			}
		}
	}

	public class EcouteurFonctions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int n = boxFonctions.getSelectedIndex();
			switch (n) {
			case 0:
				frame.setTitle("Dave");
				label.setText("Entrez le(s) sujet(s) � rechercher");
				box.setModel(new JComboBox(listeDave).getModel());
				if (res)
					frame.setSize(251, (int) (screenSize.getHeight() - 100));
				else
					frame.pack();
				break;
			case 1:
				frame.setTitle("Alice");
				label.setText("Entrez votre num閞o d'identifiant");
				box.setModel(new JComboBox(listeAlice).getModel());
				if (res)
					frame.setSize(373, (int) (screenSize.getHeight() - 100));
				else
					frame.pack();
				break;
			default:
				break;
			}

		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		new Dave();
	}
}