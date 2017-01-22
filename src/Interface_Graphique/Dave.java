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
import fonctionalite_Bob.fonctionBob;
import fonctionalite_Dave.fonction_Dave;

public class Dave {

	fonction_Dave f = new fonction_Dave();
	fonction_Alice f2 = new fonction_Alice();
	fonctionBob f3 = new fonctionBob();
	JFrame frame = new JFrame("Dave");
	JLabel labelDave = new JLabel("Entrez le(s) sujet(s) � rechercher");
	JLabel labelAlice = new JLabel("Entrez votre num�ro d'identifiant");
	JLabel labelBob = new JLabel("Entrez votre num�ro d'identifiant ou des mots-cl�s pour la recherche");
	JPanel panelDave = new JPanel();
	JPanel panelAlice = new JPanel();
	JPanel panelBob = new JPanel();
	int numeroFonction = 0;
	int largeurDave, largeurAlice, largeurBob;
	JScrollPane jsp = new JScrollPane();
	JButton buttonDave1 = new JButton("Trier par score obtenu");
	JButton buttonDave2 = new JButton("Trier par nombre de post");
	JButton buttonAlice1 = new JButton("Chercher des nouvelles questions");
	JButton buttonAlice2 = new JButton("Chercher les utilisateurs ayant plus de badges");
	JButton buttonAlice3 = new JButton("Trier les questions auxquelles j'ai r�pondu");
	JButton buttonBob1 = new JButton("Rechercher des questions existantes");
	JButton buttonBob2 = new JButton("Me proposer des contributeurs � suivre");
	JButton buttonBob3 = new JButton("M'indiquer des questions d�j� r�pondues");
	JTextField textDave = new JTextField(20);
	JTextField textAlice = new JTextField(20);
	JTextField textBob = new JTextField(20);
	String listeFonctions[] = { "Dave", "Alice", "Bob" };
	JComboBox<String> boxFonctionsDave = new JComboBox<String>(listeFonctions);
	JComboBox<String> boxFonctionsAlice = new JComboBox<String>(listeFonctions);
	JComboBox<String> boxFonctionsBob = new JComboBox<String>(listeFonctions);
	JEditorPane resultAlice = new JEditorPane();
	JEditorPane resultDave = new JEditorPane();
	JEditorPane resultBob = new JEditorPane();
	boolean res = false;
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String[]> tab = new ArrayList<String[]>();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Dave() throws IOException, JSONException {

		panelBob.setLayout(new BoxLayout(panelBob, BoxLayout.PAGE_AXIS));
		JPanel panel12 = new JPanel();
		JPanel panel13 = new JPanel();
		JPanel panel14 = new JPanel();
		JPanel panel15 = new JPanel();
		JPanel panel16 = new JPanel();
		JPanel panel17 = new JPanel();
		panel12.add(boxFonctionsBob);
		panel13.add(labelBob);
		panel14.add(textBob);
		panel15.add(buttonBob1);
		panel16.add(buttonBob2);
		panel17.add(buttonBob3);
		panelBob.add(panel12);
		panelBob.add(panel13);
		panelBob.add(panel14);
		panelBob.add(panel15);
		panelBob.add(panel16);
		panelBob.add(panel17);
		frame.getContentPane().add(panelBob);
		frame.pack();
		largeurBob = frame.getWidth();

		frame.getContentPane().remove(panelBob);
		panelAlice.setLayout(new BoxLayout(panelAlice, BoxLayout.PAGE_AXIS));
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel panel10 = new JPanel();
		panel5.add(boxFonctionsAlice);
		panel10.add(labelAlice);
		panel6.add(textAlice);
		panel7.add(buttonAlice1);
		panel8.add(buttonAlice2);
		panel9.add(buttonAlice3);
		panelAlice.add(panel5);
		panelAlice.add(panel10);
		panelAlice.add(panel6);
		panelAlice.add(panel7);
		panelAlice.add(panel8);
		panelAlice.add(panel9);
		frame.getContentPane().add(panelAlice);
		frame.pack();
		largeurAlice = frame.getWidth();

		frame.getContentPane().remove(panelAlice);
		panelDave.setLayout(new BoxLayout(panelDave, BoxLayout.PAGE_AXIS));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel11 = new JPanel();
		panel11.add(labelDave);
		panel4.add(boxFonctionsDave);
		panel1.add(textDave);
		panel2.add(buttonDave1);
		panel3.add(buttonDave2);
		panelDave.add(panel4);
		panelDave.add(panel11);
		panelDave.add(panel1);
		panelDave.add(panel2);
		panelDave.add(panel3);
		frame.getContentPane().add(panelDave);
		frame.pack();
		largeurDave = frame.getWidth();

		Ecouteur listen = new Ecouteur();
		EcouteurFonctions lis = new EcouteurFonctions();
		EcouteurURL listener = new EcouteurURL();
		resultAlice.addHyperlinkListener(listener);
		resultDave.addHyperlinkListener(listener);
		resultBob.addHyperlinkListener(listener);
		boxFonctionsAlice.addActionListener(lis);
		boxFonctionsDave.addActionListener(lis);
		boxFonctionsBob.addActionListener(lis);
		buttonDave1.addActionListener(listen);
		buttonDave2.addActionListener(listen);
		buttonAlice1.addActionListener(listen);
		buttonAlice2.addActionListener(listen);
		buttonAlice3.addActionListener(listen);
		buttonBob1.addActionListener(listen);
		buttonBob2.addActionListener(listen);
		buttonBob3.addActionListener(listen);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setVisible(true);
		textDave.requestFocus();
		textAlice.requestFocus();
		textBob.requestFocus();
		resultDave.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		resultAlice.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		resultBob.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
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
			int n = 0;
			switch (numeroFonction) {
			case 0:
				n = boxFonctionsDave.getSelectedIndex();
				break;
			case 1:
				n = boxFonctionsAlice.getSelectedIndex();
				break;
			case 2:
				n = boxFonctionsBob.getSelectedIndex();
				break;

			default:
				break;
			}
			String s = "";
			switch (n) {
			case 0:
				resultDave.setText("");
				String recherche = textDave.getText();
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
				String choix;
				if (e.getSource() == buttonDave1)
					choix = "1";
				else
					choix = "2";
				tab.clear();
				try {
					tab = f.resultat(list, "20", choix);
					res = true;
				} catch (IOException e1) {

					e1.printStackTrace();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

				if (tab.get(tab.size() - 1)[0] != "") {
					s = "<b style=\"color:#FF0000\">Le(s) tag(s) " + tab.get(tab.size() - 1)[0]
							+ " n'existe(nt) pas</b><br>";
				}

				for (int i = 0; i < tab.size() - 1; i++) {
					s = s + "<a href=\"" + tab.get(i)[1] + "\">" + tab.get(i)[0] + "</a><br>Nombre de post : "
							+ tab.get(i)[2] + "<br>Score obtenu : " + tab.get(i)[3] + "<br><br>";
				}

				resultDave.setText(s);
				jsp.setViewportView(resultDave);
				panelDave.add(jsp);
				resultDave.setCaretPosition(0);
				resultDave.setEditable(false);
				frame.getContentPane().add(panelDave);
				frame.setSize(largeurDave, (int) (screenSize.getHeight() - 100));
				if (tab.get(tab.size() - 1) == tab.get(0)) {
					res = false;
					frame.pack();
				}
				frame.setVisible(true);
				break;
			case 1:
				int cas;
				if (e.getSource() == buttonAlice1)
					cas = 0;
				else if (e.getSource() == buttonAlice2)
					cas = 1;
				else
					cas = 2;
				switch (cas) {
				case 0:

					ArrayList<String[]> tab = new ArrayList<String[]>();
					try {
						tab = f2.Alice1(textAlice.getText());
						res = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (tab.isEmpty()) {
						res = false;
						s = "<b style=\"color:#FF0000\">Le num�ro d'identifiant " + textAlice.getText()
								+ " n'existe pas</b><br>";
					} else {

						for (int i = 0; i < tab.get(0).length; i++) {
							s = s + "<a href=\"" + tab.get(1)[i] + "\">" + tab.get(0)[i] + "</a><br>Tags :"
									+ tab.get(2)[i] + "<br><br>";

						}
					}
					resultAlice.setText(s);
					jsp.setViewportView(resultAlice);
					panelAlice.add(jsp);
					resultAlice.setCaretPosition(0);
					resultAlice.setEditable(false);
					frame.getContentPane().add(panelAlice);
					if (res)
						frame.setSize(largeurAlice, (int) (screenSize.getHeight() - 100));
					else
						frame.pack();

					frame.setVisible(true);

					break;

				case 1:
					s = "Pas encore impl�ment�";
					resultAlice.setText(s);
					jsp.setViewportView(resultAlice);
					panelAlice.add(jsp);
					resultAlice.setCaretPosition(0);
					resultAlice.setEditable(false);
					frame.getContentPane().add(panelAlice);
					if (res)
						frame.setSize(largeurAlice, (int) (screenSize.getHeight() - 100));
					else
						frame.pack();

					break;
				case 2:

					
					ArrayList<String[]> tab1 = new ArrayList<String[]>();
					try {
						tab1 = f2.Alice3(textAlice.getText());
						res = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (tab1.isEmpty()) {
						res = false;
						s = "<b style=\"color:#FF0000\">Le num�ro d'identifiant " + textAlice.getText()
								+ " n'existe pas</b><br>";
					} else {

						for (int i = 0; i < tab1.get(0).length; i++) {
							s = s + "<a href=\"" + tab1.get(1)[i] + "\">" + tab1.get(0)[i] + "</a><br>Score :"
									+ tab1.get(2)[i] + "<br><br>";

						}
					}
					
					
					
					//s = "Pas encore impl�ment�";
					resultAlice.setText(s);

					jsp.setViewportView(resultAlice);
					panelAlice.add(jsp);
					resultAlice.setCaretPosition(0);
					resultAlice.setEditable(false);
					frame.getContentPane().add(panelAlice);
					if (res)
						frame.setSize(largeurAlice, (int) (screenSize.getHeight() - 100));
					else
						frame.pack();
					break;

				default:
					break;
				}

				break;

			case 2:
				int button;
				if (e.getSource() == buttonBob1)
					button = 0;
				else if (e.getSource() == buttonBob2)
					button = 1;
				else
					button = 2;

				switch (button) {
				case 0:

					ArrayList<String[]> tab = new ArrayList<String[]>();
					ArrayList<String> liste = new ArrayList<String>();
					String str = textBob.getText();
					String[] strTab = str.split("[,\\ \\;]");
					list.clear();
					str = "";
					for (int i = 0; i < strTab.length; i++) {
						if (!strTab[i].isEmpty())
							try {
								str = str + ";" + URLEncoder.encode(strTab[i], "UTF-8");
							} catch (UnsupportedEncodingException e1) {
								e1.printStackTrace();
							}

					}

					str = str.substring(1);

					try {
						tab = f3.Bob1(str);
						res = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (tab.get(0)[0] == null) {
						res = false;
						s = "<b style=\"color:#FF0000\">Les mots cl�s \"" + textBob.getText()
								+ "\" n'ont pas �t� trouver</b><br>";
					} else if (textBob.getText().isEmpty()) {
						res = false;
						s = "<b style=\"color:#FF0000\">Aucun mots cl�s rentr�</b><br>";
					} else {
						for (int i = 0; i < tab.get(0).length; i++) {
							s = s + "<a href=\"" + tab.get(1)[i] + "\">" + tab.get(0)[i] + "</a><br>Tags :"
									+ tab.get(2)[i] + "<br><br>";

						}
					}
					resultBob.setText(s);
					jsp.setViewportView(resultBob);
					panelBob.add(jsp);
					resultBob.setCaretPosition(0);
					resultBob.setEditable(false);
					frame.getContentPane().add(panelBob);
					if (res)
						frame.setSize(largeurBob, (int) (screenSize.getHeight() - 100));
					else
						frame.pack();

					frame.setVisible(true);
					break;
				case 1:
					ArrayList<String[]> tab2 = new ArrayList<String[]>();
					try {
						tab2 = f3.Bob2(textBob.getText());
						res = true;
					} catch (IOException e1) {

						e1.printStackTrace();
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					if (tab2.isEmpty()) {
						res = false;
						s = "<b style=\"color:#FF0000\">Le num�ro d'identifiant " + textAlice.getText()
								+ " n'existe pas</b><br>";
					} else {
						if (tab2.get(tab2.size() - 1)[0] != "") {
							s = "<b style=\"color:#FF0000\">Le(s) tag(s) " + tab2.get(tab2.size() - 1)[0]
									+ " n'existe(nt) pas</b><br>";
						}

						for (int i = 0; i < tab2.size() - 1; i++) {
							s = s + "<a href=\"" + tab2.get(i)[1] + "\">" + tab2.get(i)[0] + "</a><br><br>";
						}
					}
					resultBob.setText(s);
					jsp.setViewportView(resultBob);
					panelBob.add(jsp);
					resultBob.setCaretPosition(0);
					resultBob.setEditable(false);
					frame.getContentPane().add(panelBob);
					if (res)
						frame.setSize(largeurBob, (int) (screenSize.getHeight() - 100));
					else
						frame.pack();
					if (tab2.get(tab2.size() - 1) == tab2.get(0)) {
						res = false;
						frame.pack();
					}
					frame.setVisible(true);
					break;
				case 2:
					ArrayList<String[]> tab3 = new ArrayList<String[]>();

					try {
						tab3 = f3.Bob3(textBob.getText());
						res = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (tab3.isEmpty()) {
						res = false;
						s = "<b style=\"color:#FF0000\">Le num�ro d'identifiant " + textAlice.getText()
								+ " n'existe pas</b><br>";
					} else {

						for (int i = 0; i < tab3.get(0).length; i++) {
							s = s + "<a href=\"" + tab3.get(1)[i] + "\">" + tab3.get(0)[i] + "</a><br>Tags :"
									+ tab3.get(2)[i] + "<br><br>";

						}
					}

					resultBob.setText(s);
					jsp.setViewportView(resultBob);
					panelBob.add(jsp);
					resultBob.setCaretPosition(0);
					resultBob.setEditable(false);
					frame.getContentPane().add(panelBob);
					if (res)
						frame.setSize(largeurBob, (int) (screenSize.getHeight() - 100));
					else
						frame.pack();
					break;

				default:
					break;
				}
				break;
			default:
				break;
			}
		}
	}

	public class EcouteurFonctions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			res = false;
			switch (numeroFonction) {
			case 0:
				frame.getContentPane().remove(panelDave);
				break;
			case 1:
				frame.getContentPane().remove(panelAlice);
				break;
			case 2:
				frame.getContentPane().remove(panelBob);
				break;
			case 3:
				// frame.getContentPane().remove(panelCharlie);
				break;
			default:
				break;
			}
			textDave.setText("");
			textAlice.setText("");
			textBob.setText("");
			int n = 0;
			switch (numeroFonction) {
			case 0:
				n = boxFonctionsDave.getSelectedIndex();
				resultDave.setText("");
				break;
			case 1:
				n = boxFonctionsAlice.getSelectedIndex();
				resultAlice.setText("");
				break;
			case 2:
				n = boxFonctionsBob.getSelectedIndex();
				resultAlice.setText("");
				break;

			default:
				break;
			}
			switch (n) {
			case 0:
				frame.setTitle("Dave");
				frame.getContentPane().add(panelDave);
				boxFonctionsDave.setSelectedIndex(0);
				if (res)
					frame.setSize(largeurDave, (int) (screenSize.getHeight() - 100));
				else
					frame.pack();
				break;
			case 1:
				frame.setTitle("Alice");
				boxFonctionsAlice.setSelectedIndex(1);
				frame.getContentPane().add(panelAlice);
				if (res)
					frame.setSize(largeurAlice, (int) (screenSize.getHeight() - 100));
				else
					frame.pack();
				break;
			case 2:
				frame.setTitle("Bob");
				boxFonctionsBob.setSelectedIndex(2);
				frame.getContentPane().add(panelBob);
				if (res)
					frame.setSize(largeurBob, (int) (screenSize.getHeight() - 100));
				else
					frame.pack();
				break;
			default:
				break;

			}
			numeroFonction = n;
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		new Dave();
	}
}