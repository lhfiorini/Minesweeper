package deviget.minesweeper_client.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.google.gson.Gson;

import deviget.minesweeper_client.model.ResponseGrid;

public class MinesweeperAPIClient {

	private String server;

	// builder
	public MinesweeperAPIClient(String server) {
		this.server = server;
	}

	// functions
	public String newGame( int userID, int colAmount, int rowAmount, int flagAmount ) throws Exception {
		
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

		DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream());
		wr.write(bstr);
		

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

	public String revealCell(String gameCode, int col, int row) throws Exception {

		String gameStatus = "";
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("col", col);
		param.put("row", row);

		URL urlServer = new URL(this.server + "/" + gameCode + "/reveal");
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

	public ResponseGrid getGameBoard(String gameCode) throws Exception {

		ResponseGrid responseGrid = null;

		URL urlServer = new URL(this.server + "/" + gameCode);
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

	public String flagCell(String gameCode, int col, int row) throws Exception {

		String gameStatus = "";
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("col", col);
		param.put("row", row);

		URL urlServer = new URL(this.server + "/" + gameCode + "/flag");
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
}
