package Interface_Graphique;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
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

import fonctionalite_Dave.fonction_Dave;

public class Dave {

	fonction_Dave f = new fonction_Dave();
	JFrame frame = new JFrame("Dave");
	JLabel label = new JLabel("Entrez le(s) sujet(s) à rechercher");
	JPanel panel = new JPanel();
	JButton button = new JButton("OK");
	JTextField text = new JTextField(20);
	String s[] = { "Trier par nombre de post", "Trier par score obtenu" };
	JComboBox<String> box = new JComboBox<String>(s);
	JEditorPane result = new JEditorPane();
	JScrollPane jsp = new JScrollPane();

	public Dave() throws IOException, JSONException {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel1.add(text);
		panel2.add(box);
		panel2.add(button);
		panel3.add(label);
		panel.add(panel3);
		panel.add(panel1);
		panel.add(panel2);
		Ecouteur listen = new Ecouteur();

		result.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		button.addActionListener(listen);
		frame.getContentPane().add(panel);
		frame.setAutoRequestFocus(true);
		frame.setLocation(100, 0);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public class Ecouteur implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			result.setText("");
			String recherche = text.getText();
			String[] temp = recherche.split("[,\\ \\;]");
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < temp.length; i++) {
				if (!temp[i].isEmpty())
					list.add(temp[i]);
			}

			String choix = Integer.toString(box.getSelectedIndex());

			ArrayList<String[]> tab = new ArrayList<String[]>();
			try {
				tab = f.resultat(list, "20", choix);
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

			result.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
			result.setText(s);
			jsp.setViewportView(result);
			panel.add(jsp);
			result.setCaretPosition(0);
			result.setEditable(false);
			frame.getContentPane().remove(panel);
			frame.getContentPane().add(panel);
			frame.pack();
			frame.setVisible(true);

		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		new Dave();

	}
}
