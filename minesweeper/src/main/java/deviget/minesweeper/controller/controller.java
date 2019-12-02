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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deviget.minesweeper.model.Game;
import deviget.minesweeper.model.GameList;
import deviget.minesweeper.model.RequestParam;
import deviget.minesweeper.model.ResponseGrid;

@RestController
@RequestMapping(value = "/minesweeper")
@CrossOrigin
public class controller {

	@Autowired GameList gameList;
	
	@PostMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> postNewGame(@RequestBody RequestParam reqParam) throws ParseException {

		// check if the parameters are ok
		if( reqParam.getFlagAmount() <= (reqParam.getColAmount() * reqParam.getRowAmount()) ) {
		
			// add the new game to the list, if this game already exists it will be replaced for the new one
			Game game = new Game( reqParam.getUserID(), reqParam.getColAmount(), reqParam.getRowAmount(), reqParam.getFlagAmount() );
			gameList.put( game.getGameCode(), game );
			return new ResponseEntity<Object>(game.getGameCode(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/game/{gameCode}", method = RequestMethod.GET)
	public ResponseEntity<ResponseGrid> getGame(@PathVariable String gameCode) throws ParseException {

		// check if the parameters are ok
		if( gameList.existsGame( gameCode ) ) {
			Game game = gameList.getGame( gameCode );
			return new ResponseEntity<ResponseGrid>(game.getGame(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<ResponseGrid>(new ResponseGrid(), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/game/{gameCode}/reveal", method = RequestMethod.PUT)
	public ResponseEntity<Object> putGameReveal(@PathVariable String gameCode, @RequestBody RequestParam reqParam) throws ParseException {
		// check if the parameters are ok
		if( gameList.existsGame( gameCode ) ) {
			Game game = gameList.getGame( gameCode );
			game.reveal(reqParam.getCol(), reqParam.getRow());
			return new ResponseEntity<Object>(game.getGameStatus(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/game/{gameCode}/flag", method = RequestMethod.PUT)
	public ResponseEntity<Object> putGameFlag(@PathVariable String gameCode, @RequestBody RequestParam reqParam) throws ParseException {

		// check if the parameters are ok
		if( gameList.existsGame( gameCode ) ) {
			Game game = gameList.getGame( gameCode );
			game.flag(reqParam.getCol(), reqParam.getRow());
			return new ResponseEntity<Object>(game.getGameStatus(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
	}
}
