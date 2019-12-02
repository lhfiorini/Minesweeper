package deviget.minesweeper.model;

import java.util.Hashtable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GameList {
	private Hashtable<String, Game> games = new Hashtable<String, Game>();

	public void put(String gameCode, Game game) {
		games.put(gameCode, game);
	}

	public boolean existsGame(String gameCode) {
		return games.containsKey(gameCode);
	}

	public Game getGame(String gameCode) {
		return games.get(gameCode);
	}
}
