package Interface_Graphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;

import fonctionalite_Dave.fonction_Dave;

public class ChoixTag {
	JFrame frame = new JFrame("Dave");
	JPanel panelNbSujet = new JPanel();
	JPanel panelNomSujet = new JPanel();
	JPanel panelNbResultat = new JPanel();
	JPanel panelMethode = new JPanel();
	JPanel panelResulat = new JPanel();
	JButton valider = new JButton("Valider");
	JButton valider2 = new JButton("Valider");
	JButton valider3 = new JButton("Valider");
	JButton methode1 = new JButton("Trier par nombre de post");
	JButton methode2 = new JButton("Trier par score obtenu");
	JTextField textField = new JTextField(10);
	JLabel erreur = new JLabel("le nombre de sujet doit être un nombre entier");
	int nbSujet;
	ArrayList<String> sujet_list = new ArrayList<String>();
	String nbResultat;
	String choix;

	public ChoixTag() throws IOException, JSONException {
		panelNbSujet.setLayout(new BoxLayout(panelNbSujet, BoxLayout.PAGE_AXIS));
		panelNomSujet.setLayout(new BoxLayout(panelNomSujet, BoxLayout.PAGE_AXIS));
		panelNbResultat.setLayout(new BoxLayout(panelNbResultat, BoxLayout.PAGE_AXIS));
		panelMethode.setLayout(new BoxLayout(panelMethode, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel("Combien de sujet voulez-vous rechercher ?");
		JPanel panelLabel = new JPanel();
		JPanel panelText = new JPanel();
		JPanel panelButton = new JPanel();
		panelLabel.add(label);
		panelText.add(textField);
		panelButton.add(valider);
		panelNbSujet.add(panelLabel);
		panelNbSujet.add(panelText);
		panelNbSujet.add(panelButton);
		Ecouteur listen = new Ecouteur();
		valider.addActionListener(listen);
		valider2.addActionListener(listen);
		valider3.addActionListener(listen);
		methode1.addActionListener(listen);
		methode2.addActionListener(listen);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panelNbSujet);
		frame.pack();
		frame.setVisible(true);

	}

	public class Ecouteur implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == valider) {
				try {
					nbSujet = Integer.parseInt(textField.getText());
					textField.setText("");
					frame.getContentPane().remove(panelNbSujet);
					JLabel label = new JLabel("Entrez le sujet que vous voulez chercher");
					JPanel panelLabel = new JPanel();
					JPanel panelText = new JPanel();
					JPanel panelButton = new JPanel();
					panelLabel.add(label);
					panelText.add(textField);
					panelButton.add(valider2);
					panelNomSujet.add(panelLabel);
					panelNomSujet.add(panelText);
					panelNomSujet.add(panelButton);
					frame.getContentPane().add(panelNomSujet);
					frame.pack();
					frame.setVisible(true);

				} catch (NumberFormatException nfe) {
					JPanel err = new JPanel();
					err.add(erreur);
					frame.getContentPane().remove(panelNbSujet);
					panelNbSujet.add(err);
					frame.getContentPane().add(panelNbSujet);
					frame.pack();
					frame.setVisible(true);
				}

			}
			if (e.getSource() == valider2) {
				if (textField.getText() != null)
					sujet_list.add(textField.getText());
				textField.setText("");
				if (sujet_list.size() == nbSujet) {
					frame.getContentPane().remove(panelNomSujet);
					JLabel label = new JLabel("Entrez le nombre de resultat à afficher");
					JPanel panelLabel = new JPanel();
					JPanel panelText = new JPanel();
					JPanel panelButton = new JPanel();
					panelLabel.add(label);
					panelText.add(textField);
					panelButton.add(valider3);
					panelNbResultat.add(panelLabel);
					panelNbResultat.add(panelText);
					panelNbResultat.add(panelButton);
					frame.getContentPane().add(panelNbResultat);
					frame.pack();
					frame.setVisible(true);
				}
			}

			if (e.getSource() == valider3) {
				try {
					Integer.parseInt(textField.getText());
					nbResultat = textField.getText();
					textField.setText("");
					frame.getContentPane().remove(panelNbResultat);
					JLabel label = new JLabel("Choisissez la méthode pour trier les résultats");
					JPanel panelLabel = new JPanel();
					JPanel panelButton1 = new JPanel();
					JPanel panelButton2 = new JPanel();
					panelLabel.add(label);
					panelButton1.add(methode1);
					panelButton2.add(methode2);
					panelMethode.add(panelLabel);
					panelMethode.add(panelButton1);
					panelMethode.add(panelButton2);
					frame.getContentPane().add(panelMethode);
					frame.pack();
					frame.setVisible(true);

				} catch (NumberFormatException nfe) {
					JPanel err = new JPanel();
					err.add(erreur);
					frame.getContentPane().remove(panelNbResultat);
					panelNbResultat.add(err);
					frame.getContentPane().add(panelNbResultat);
					frame.pack();
					frame.setVisible(true);
				}

			}
			if (e.getSource() == methode1 || e.getSource() == methode2) {
				if (e.getSource() == methode1)
					choix = "1";
				else
					choix = "2";
				frame.getContentPane().remove(panelMethode);
				fonction_Dave f = new fonction_Dave();
				String u_list = "";
				try {
					u_list = f.resultat(sujet_list, nbResultat, choix);
				} catch (IOException e1) {

					e1.printStackTrace();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				JTextArea list_de_user = new JTextArea(u_list);
				JPanel panel = new JPanel();
				JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);

				panel.add(vbar, BorderLayout.EAST);
				panel.add(list_de_user);
				panelResulat.add(panel);
				frame.getContentPane().add(panelResulat);
				frame.pack();
				frame.setVisible(true);
			}
		}

	}

	public static void main(String[] args) throws IOException, JSONException {
		new ChoixTag();
	}
}
