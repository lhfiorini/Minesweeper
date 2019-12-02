package deviget.minesweeper_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

import deviget.minesweeper_client.model.ResponseGrid;

import com.google.gson.Gson;

public class App 
{
	private String server;
	
    public static void main( String[] args ) throws Exception {
        App mainApp = new App();
        mainApp.server = "http://localhost:8080/minesweeper/game";
        
        // define a new game
        String gameCode = mainApp.newGame(1500, 5, 5, 2);
        System.out.println( "the new game code is: " + gameCode);
        mainApp.printGameBoard( mainApp.getGameBoard( gameCode ) );
        
        // reveal several cells
        String gameStatus = mainApp.revealCell( gameCode, 1, 3 );
        System.out.println( "Reveal on 1-3:" );
        mainApp.printGameBoard( mainApp.getGameBoard( gameCode ) );
        gameStatus = mainApp.revealCell( gameCode, 3, 4 );
        System.out.println( "Reveal on 3-4:" );
        mainApp.printGameBoard( mainApp.getGameBoard( gameCode ) );
        gameStatus = mainApp.revealCell( gameCode, 2, 3 );
        System.out.println( "Reveal on 2-4:" );
        mainApp.printGameBoard( mainApp.getGameBoard( gameCode ) );
        gameStatus = mainApp.revealCell( gameCode, 0, 2 );
        System.out.println( "Reveal on 0-2:" );
        mainApp.printGameBoard( mainApp.getGameBoard( gameCode ) );
        gameStatus = mainApp.revealCell( gameCode, 3, 3 );
        System.out.println( "Reveal on 2-3:" );
        mainApp.printGameBoard( mainApp.getGameBoard( gameCode ) );
        
        // flag a cell
        mainApp.flagCell( gameCode, 0, 0 );
        System.out.println( "Flag on 0-0:" );
        mainApp.printGameBoard( mainApp.getGameBoard( gameCode ) );
    }

	private static void printGameBoard( ResponseGrid responseGrid ) {
  
    	System.out.println( "Game Code/Satus: " + responseGrid.gameCode + "/" + responseGrid.gameStatus );
        System.out.println( "Flag Detected: " + responseGrid.flagDetectedAmount + "/" + responseGrid.flagAmount );
        if( responseGrid.startedDate != null )
        	System.out.println( "Time Tracking: " + getTimeTracking(responseGrid.startedDate) );
        else
        	System.out.println( "Time Tracking: " + 0 );

        if( responseGrid.responseGridNodes.size() > 0 ) {
        	String boardCells = "";
        	for( int row = 0; row < responseGrid.responseGridNodes.get(0).cellsLabel.size(); row++ ) {
        		String rowCells = "";
        		for( int col = 0; col < responseGrid.responseGridNodes.size(); col++ ) {
        			if( responseGrid.responseGridNodes.get(col).cellsLabel.get(row).isEmpty() )
        				rowCells += "[     ]" + "\t";
        			else
        				rowCells += responseGrid.responseGridNodes.get(col).cellsLabel.get(row) + "\t";
        		}
        		boardCells += rowCells + "\n";
        	}
        	System.out.println( boardCells );
        }
        else
        	System.out.println( "No board defined." );
        
        System.out.println();
    }
    
    private static String getTimeTracking( Date startedDate ) {
    	Date actualDate = new Date();
 
        //in milliseconds
		long diff = actualDate.getTime() - startedDate.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
        
		return diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.";
    }
    
    private String flagCell(String gameCode, int col, int row) throws Exception {
    	String gameStatus = "";
    	HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("col", col);
		param.put("row", row);

		URL urlServer = new URL(this.server+"/"+ gameCode +"/flag");
		HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
		urlConn.setConnectTimeout(3000);
		urlConn.setRequestMethod("PUT");
		urlConn.setDoOutput(true);
		urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		urlConn.setRequestProperty("charset", "utf-8");
		Gson gson = new Gson();
		String str = gson.toJson(param);
		byte[] bstr = str.getBytes(StandardCharsets.UTF_8);
		int len = bstr.length;
		urlConn.setRequestProperty("Content-Length", String.valueOf(len));
		urlConn.setUseCaches(false);

		try (DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream())) {
			wr.write(bstr);
		}

		if (urlConn.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();
			
			gameStatus = response.toString();
		}
		return gameStatus;
	}
    
    private ResponseGrid getGameBoard(String gameCode) throws Exception {

    	ResponseGrid responseGrid = null;
    	
		URL urlServer = new URL(this.server+"/"+gameCode);
		HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
		urlConn.setConnectTimeout(3000);
		urlConn.setRequestMethod("GET");
		urlConn.setDoOutput(true);
		urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		urlConn.setRequestProperty("charset", "utf-8");
		urlConn.setUseCaches(false);

		if (urlConn.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();

			Gson gson = new Gson();
			responseGrid = gson.fromJson(response.toString(), ResponseGrid.class);			
		}
		return responseGrid;
	}

	private String revealCell(String gameCode, int col, int row) throws Exception {
    	String gameStatus = "";
    	HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("col", col);
		param.put("row", row);

		URL urlServer = new URL(this.server+"/"+gameCode+"/reveal");
		HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
		urlConn.setConnectTimeout(3000);
		urlConn.setRequestMethod("PUT");
		urlConn.setDoOutput(true);
		urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		urlConn.setRequestProperty("charset", "utf-8");
		Gson gson = new Gson();
		String str = gson.toJson(param);
		byte[] bstr = str.getBytes(StandardCharsets.UTF_8);
		int len = bstr.length;
		urlConn.setRequestProperty("Content-Length", String.valueOf(len));
		urlConn.setUseCaches(false);

		try (DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream())) {
			wr.write(bstr);
		}

		if (urlConn.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();
			
			gameStatus = response.toString();
		}
		return gameStatus;
	}

	private String newGame( int userID, int colAmount, int rowAmount, int flagAmount ) throws Exception {
		
    	String gameCode = "";
    	HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userID", userID);
		param.put("colAmount", colAmount);
		param.put("rowAmount", rowAmount);
		param.put("flagAmount", flagAmount);

		URL urlServer = new URL(this.server);
		HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
		urlConn.setConnectTimeout(3000);
		urlConn.setRequestMethod("POST");
		urlConn.setDoOutput(true);
		urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		urlConn.setRequestProperty("charset", "utf-8");
		Gson gson = new Gson();
		String str = gson.toJson(param);
		byte[] bstr = str.getBytes(StandardCharsets.UTF_8);
		int len = bstr.length;
		urlConn.setRequestProperty("Content-Length", String.valueOf(len));
		urlConn.setUseCaches(false);

		try (DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream())) {
			wr.write(bstr);
		}

		if (urlConn.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();
			
			gameCode = response.toString();
		}
		return gameCode;  	
    }
}
