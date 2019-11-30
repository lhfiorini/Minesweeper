package deviget.minesweeper.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deviget.minesweeper.model.Game;
import deviget.minesweeper.model.GameList;
import deviget.minesweeper.model.RequestParam;
import deviget.minesweeper.model.ResponseGrid;

@RestController
@RequestMapping(value = "/minesweeper")
@CrossOrigin
public class controller {

	@Autowired GameList sharedGameList;
	
	@PostMapping(value = "/newGame", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> postNewGame(@RequestBody RequestParam reqParam) throws ParseException {

		// check if the parameters are ok
		if( reqParam.getFlagAmount() <= (reqParam.getColAmount() * reqParam.getRowAmount()) ) {
		
			// add the new game to the list, if this game already exists it will be replaced for the new one
			Game auxGame = new Game( reqParam.getUserID(), reqParam.getColAmount(), reqParam.getRowAmount(), reqParam.getFlagAmount() );
			sharedGameList.put( auxGame.getGameCode(), auxGame );
			return new ResponseEntity<Object>(auxGame.getGameCode(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/getGame", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseGrid> getGameBoard(@RequestBody RequestParam reqParam) throws ParseException {

		// check if the parameters are ok
		if( sharedGameList.existsGame( reqParam.getGameCode() ) ) {
			Game auxGame = sharedGameList.getGame( reqParam.getGameCode() );
			return new ResponseEntity<ResponseGrid>(auxGame.getGame(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<ResponseGrid>(new ResponseGrid(), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/onClic", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> onClic(@RequestBody RequestParam reqParam) throws ParseException {

		// check if the parameters are ok
		if( sharedGameList.existsGame( reqParam.getGameCode() ) ) {
			Game auxGame = sharedGameList.getGame( reqParam.getGameCode() );
			auxGame.onClic(reqParam.getCol(), reqParam.getRow());
			return new ResponseEntity<Object>(auxGame.getGameStatus(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/onRightButton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> onRightButton(@RequestBody RequestParam reqParam) throws ParseException {

		// check if the parameters are ok
		if( sharedGameList.existsGame( reqParam.getGameCode() ) ) {
			Game auxGame = sharedGameList.getGame( reqParam.getGameCode() );
			auxGame.onRightButton(reqParam.getCol(), reqParam.getRow());
			return new ResponseEntity<Object>(auxGame.getGameStatus(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
	}
}
