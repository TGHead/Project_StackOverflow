package Interface_Graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

import fonctionalite_Bob.fonctionBob;

public class InterfaceBob {
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Entrez votre numéro d'identifiant ou des mots-clés pour la recherche");
	JButton button1 = new JButton("Rechercher des questions existantes");
	JButton button2 = new JButton("Me proposer des contributeurs à suivre");
	JButton button3 = new JButton("M'indiquer des questions déjà répondues");
	JTextField text = new JTextField(20);
	JEditorPane result = new JEditorPane();

	public InterfaceBob() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JPanel panelLabel = new JPanel();
		JPanel panelText = new JPanel();
		JPanel panelButton1 = new JPanel();
		JPanel panelButton2 = new JPanel();
		JPanel panelButton3 = new JPanel();
		panelLabel.add(label);
		panelText.add(text);
		panelButton1.add(button1);
		panelButton2.add(button2);
		panelButton3.add(button3);
		panel.add(panelLabel);
		panel.add(panelText);
		panel.add(panelButton1);
		panel.add(panelButton2);
		panel.add(panelButton3);
		Ecouteur listen = new Ecouteur();// Ecouteur pour les bouttons
		EcouteurURL listener = new EcouteurURL();// Ecouteur pour les liens
													// hypertext
		result.addHyperlinkListener(listener);
		button1.addActionListener(listen);
		button2.addActionListener(listen);
		button3.addActionListener(listen);
		text.requestFocus();// Focus sur la zone de texte
		result.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
	}

	public class Ecouteur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int cas;
			if (e.getSource() == button1)
				cas = 0;
			else if (e.getSource() == button2)
				cas = 1;
			else
				cas = 2;

			String s = "";
			boolean err = false;
			ArrayList<String[]> tab = new ArrayList<String[]>();
			fonctionBob f = new fonctionBob();
			switch (cas) {
			case 0:

				err = true;
				s = "Pas encore implémenté";
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
				break;

			case 1:

				try {
					tab = f.Bob2(text.getText());
				} catch (IOException e1) {

					e1.printStackTrace();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				if (tab.isEmpty()) {
					err = true;
					s = "<b style=\"color:#FF0000\">Le numéro d'identifiant " + text.getText()
							+ " n'existe pas</b><br>";
				} else {
					if (tab.get(tab.size() - 1)[0] != "") {
						s = "<b style=\"color:#FF0000\">Le(s) tag(s) " + tab.get(tab.size() - 1)[0]
								+ " n'existe(nt) pas</b><br>";
					}

					for (int i = 0; i < tab.size() - 1; i++) {
						s = s + "<a href=\"" + tab.get(i)[1] + "\">" + tab.get(i)[0] + "</a><br><br>";
					}
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

				break;
			case 2:
				System.out.println("#");
				try {
					tab = f.Bob3(text.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (tab.isEmpty()) {
					err = true;
					s = "<b style=\"color:#FF0000\">Le numéro d'identifiant " + text.getText()
							+ " n'existe pas</b><br>";
				} else {

					for (int i = 0; i < tab.get(0).length; i++) {
						s = s + "<a href=\"" + tab.get(1)[i] + "\">" + tab.get(0)[i] + "</a><br>Tags :" + tab.get(2)[i]
								+ "<br><br>";

					}
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

				break;
			default:
				break;
			}
		}
	}
}
