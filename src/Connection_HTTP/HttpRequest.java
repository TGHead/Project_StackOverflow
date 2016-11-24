package Connection_HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class HttpRequest {
    /**
     * Envoyer un request GET a un certain URL
     * 
     * @param url
     *            une partie de URL pour appel le API
     * @param param
     *            des parametres details sous le format: name1=value1&name2=value2
     * @return 
     * 			  des resultats que le site retourne
     * @throws IOException 
     */
    public static String sendGet(String url, String param, boolean x) throws IOException {
    	StringBuffer response = new StringBuffer(); 
        try {
            String urlNameString = url + "?" + param;
            if(x){
        		urlNameString = url + "?" + param;
        	}
        	else{
        		urlNameString = url + param;
        	}
            URL realUrl = new URL(urlNameString);
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache.univ-st-etienne.fr", 3128));
            //URLConnection connection = realUrl.openConnection(proxy);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            BufferedReader in = null;
            if(map.get("Content-Encoding").contains("gzip")){
            	in = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
            }
            else if(map.get("Content-Encoding").contains("deflate")) {
            	in = new BufferedReader(new InputStreamReader(new InflaterInputStream(connection.getInputStream())));
            }
            String line;
            while((line=in.readLine())!=null) {
                response.append(line);
                response.append('\r');
            }
        } catch (Exception e) {
            System.out.println("Il y a des problemes en envoyant request GET !" + e);
            e.printStackTrace();
        }
        return response.toString();
    }
}