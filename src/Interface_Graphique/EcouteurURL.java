package Interface_Graphique;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

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