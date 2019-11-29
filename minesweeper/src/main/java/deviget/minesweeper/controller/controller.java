package deviget.minesweeper.controller;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/minesweeper")
@CrossOrigin
public class controller {

	@GetMapping(value = "/helloWord", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getHelloWord() throws ParseException {
		return new ResponseEntity<Object>("Hello Word!!!", HttpStatus.OK);
	}
}
