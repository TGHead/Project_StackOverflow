package Interface_Graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

public class ChoixTag {
	JFrame frame = new JFrame("Choix du sujet");
	JButton bouton2 = new JButton("Nombre de réponses ");
	JButton bouton3 = new JButton("Score obtenu");
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel bouton = new JPanel();
	JPanel methode1 = new JPanel();
	JPanel methode2 = new JPanel();
	JPanel t = new JPanel();
	JTextField tags = new JTextField(10);
	JButton bouton1 = new JButton("Valider");
	JPanel texte = new JPanel();
	JLabel text = new JLabel("Choisissez un sujet");

	public ChoixTag() throws IOException, JSONException {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		texte.add(text);
		t.add(tags);
		bouton.add(bouton1);
		panel.add(texte);
		panel.add(t);
		panel.add(bouton);
		Ecouteur listen = new Ecouteur();
		bouton1.addActionListener(listen);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

	}

	public class Ecouteur implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
			methode1.add(bouton2);
			methode2.add(bouton3);
			panel2.add(methode1);
			panel2.add(methode2);
			frame.getContentPane().remove(panel);
			frame.setTitle("Choix de la méthode");
			frame.getContentPane().add(panel2);
			frame.pack();
			frame.setVisible(true);
		}

	}

	public static void main(String[] args) throws IOException, JSONException {
		ChoixTag t = new ChoixTag();
	}
}
