package deviget.minesweeper_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class App 
{
	private String strServer;
	
    public static void main( String[] args ) throws Exception {
        App mainApp = new App();
        mainApp.strServer = "http://localhost:8080/minesweeper";
        mainApp.hola();
    }
    
	private String hola() throws Exception {

		String strResult = "";
		try {
			URL urlServer = new URL(this.strServer + "/hola");
			HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
			urlConn.setConnectTimeout(3000); // <- 3Seconds timeout
			urlConn.connect();
			if( urlConn.getResponseCode() == 200 ) {
				// connection ok
				BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				String strInputLine = "";
				while ((strInputLine = in.readLine()) != null) {
					System.out.println(strInputLine);
				}
				in.close();
			}
			else
				throw new Exception( "Connection to server not established" );
				
		} catch (MalformedURLException e1) {
			return strResult;
		}

		return strResult;
	}
	
	/* in case the response is a json
	ajoResult = new ArrayList<JSONObject>();

	// recorro cada registro
	while ((strInputLine = in.readLine()) != null) {
		JSONArray a = (JSONArray) parser.parse(strInputLine);

		// recorro cada campo
		for (Object o : a) {
			JSONObject joElement = (JSONObject) o;
			ajoResult.add(joElement);
		}
	}
	in.close();
	*/
}
