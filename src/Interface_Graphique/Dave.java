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

import fonctionalite_Dave.fonction_Dave;

public class Dave {

	fonction_Dave f = new fonction_Dave();
	JFrame frame = new JFrame("Dave");
	JLabel label = new JLabel("Entrez le(s) sujet(s) à rechercher");
	JPanel panel = new JPanel();
	JScrollPane jsp = new JScrollPane();
	JButton button = new JButton("OK");
	JTextField text = new JTextField(20);
	String s[] = { "Trier par score obtenu", "Trier par nombre de post" };
	JComboBox<String> box = new JComboBox<String>(s);
	JEditorPane result = new JEditorPane();
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String[]> tab = new ArrayList<String[]>();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
		EcouteurURL listener = new EcouteurURL();
		result.addHyperlinkListener(listener);

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
			if (tab.get(tab.size() - 1) == tab.get(0))
				frame.pack();

			frame.setVisible(true);
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		new Dave();

	}
}