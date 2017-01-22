package Interface_Graphique;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONException;

public class StackEtoiles {
	static JFrame frame = new JFrame("StackEtoiles");
	static JPanel panel = new JPanel();
	static JScrollPane jsp = new JScrollPane();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int hauteur;
	static int largeur = 500;
	String listeFonctions[] = { "Dave", "Alice", "Bob" };
	JPanel panelBoxFonctions = new JPanel();
	JComboBox<String> boxFonctions = new JComboBox<String>(listeFonctions);
	InterfaceDave dave = new InterfaceDave();
	InterfaceAlice alice = new InterfaceAlice();
	InterfaceBob bob = new InterfaceBob();

	public StackEtoiles() throws IOException, JSONException {
		hauteur = (int) (screenSize.getHeight() - 100);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panelBoxFonctions.add(boxFonctions);
		panel.add(panelBoxFonctions);
		panel.add(dave.panel);
		EcouteurFonctions lis = new EcouteurFonctions();
		boxFonctions.addActionListener(lis);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(largeur, frame.getHeight());
		frame.setVisible(true);
	}

	public class EcouteurFonctions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			panel.add(panelBoxFonctions);
			int n = boxFonctions.getSelectedIndex();
			switch (n) {
			case 0:
				dave.text.setText("");
				panel.add(dave.panel);
				break;
			case 1:
				panel.add(alice.panel);
				alice.text.setText("");
				break;
			case 2:
				panel.add(bob.panel);
				bob.text.setText("");
				break;
			default:
				break;

			}
			frame.pack();
			frame.setSize(largeur, frame.getHeight());
			frame.setVisible(true);
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		new StackEtoiles();
	}
}
