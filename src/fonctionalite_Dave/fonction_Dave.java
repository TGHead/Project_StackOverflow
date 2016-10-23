package fonctionalite_Dave;

import java.io.IOException;

import Connection_HTTP.*;

public class fonction_Dave {
	public static void main(String[] args) throws IOException {
		String s = HttpRequest.sendGet("https://api.stackexchange.com/2.2/tags/java/top-answerers/all_time", "page=1&pagesize=5&site=stackoverflow");
        //String s = MessageGZIP.uncompressToString(b);
		System.out.println(s);
	}
}
