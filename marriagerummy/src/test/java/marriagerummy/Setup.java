package marriagerummy;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;

public class Setup {

	public static void main(String[] args) throws Exception {
		 String lobbyName = "Beginners Lobby";
		 RummyArena.getInstance();
		 GameLobby lobby = GameLobby.createGameLobby(lobbyName);
		 Game game = lobby.createGame("7Card Begineers", GameListConstants.GAMELIST_SEVENCARD_TYPE);
		 
		 displayRummyArena();
		 
		 lobby.createGame("7Card Intermediate", GameListConstants.GAMELIST_SEVENCARD_TYPE);
		 lobby.createGame("7Card Advanced", GameListConstants.GAMELIST_SEVENCARD_TYPE);
		 displayRummyArena();
		 lobby.createGame("7Card Experts", GameListConstants.GAMELIST_SEVENCARD_TYPE);
		 
		 displayRummyArena();
		

	}
	
	private static void displayRummyArena()
	{
		 RummyArena rum = RummyArena.getInstance();
		 System.out.println("Lobby size : " + rum.getGameLobbyList().size());
		 for(GameLobby curlobby : rum.getGameLobbyList())
		 {
			 System.out.println("cur lobbyname : " + curlobby.getLobbyName());
			 for(Game curgame : curlobby.getSevencard_gamelist().getGamelist())
			 {
				 if(curgame instanceof SevenCardRummy)
				 {
					 SevenCardRummy cardrummy = (SevenCardRummy) curgame;
					 System.out.println(cardrummy.getGameInstanceId() + " - " + cardrummy.getGameName());
				 }
			 }
		 }
	}

}
