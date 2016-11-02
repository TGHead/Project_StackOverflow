package fonctionalite_Dave;

import java.io.IOException;
import java.util.Map;
import org.json.JSONException;
import Connection_HTTP.*;
import Data_Structure.*;

public class fonction_Dave {
	public static void main(String[] args) throws IOException, JSONException {
    	System.setProperty("http.proxyHost", "cache.univ-st-etienne.fr");
    	System.setProperty("http.proxyPort", "3128");
    	System.setProperty("http.proxyHost", "cache.univ-st-etienne.fr");
    	System.setProperty("http.proxyPort", "3128");
		String s = HttpRequest.sendGet("https://api.stackexchange.com/2.2/tags/java/top-answerers/all_time", "page=1&pagesize=5&site=stackoverflow");
        Map JSON_Map = JSON_Converter.jsonToMap(s);
		System.out.println(JSON_Map);
	}
}
