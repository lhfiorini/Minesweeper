package deviget.minesweeper.model;

import java.util.Hashtable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GameList {
	Hashtable<String, Game> hGames = new Hashtable<String, Game>();

	public void put(String strGameCode, Game gameParam) {
		hGames.put(strGameCode, gameParam);
	}

	public boolean existsGame(String strGameCode) {
		return hGames.containsKey(strGameCode);
	}

	public Game getGame(String strGameCode) {
		return hGames.get(strGameCode);
	}
}
