package battleship;

import battleship.global.Constant;
import battleship.global.RandomSynchronized;
import battleship.network.NetworkListener;
import battleship.network.NetworkWriter;

public class Game extends Thread {

	private static final boolean DEBUG_MODE = true;
	
	private static Game game = null;
	private boolean continueGame = false;
	private Screen screen = null;
	private Player player = null;
	private Player opponent = null;
	private NetworkListener networkListener = null;
	private NetworkWriter networkWriter = null;
	
	private double randomStart = RandomSynchronized.nextDouble(100);
	private boolean canPlay = false;

	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}
	
	public void run()
	{
		continueGame = true;
		
		while(player == null && continueGame){
			try{
				Thread.sleep(500);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(!continueGame)
			return;
		
		networkListener = new NetworkListener(Constant.LISTENING_PORT);
		networkListener.start();
		
		screen = Screen.getInstance();
		SeparatorMessageQueue.getInstance().addMessageToQueue("Waiting for connection");
		screen.setVisible(true);
		
		while(continueGame)
		{
			try {
				Thread.sleep(500);
				refresh();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Screen.getInstance().dispose();
	}
	
	public void quit()
	{
		continueGame  = false;
		if(networkListener != null)
			networkListener.stopListening();
		if(networkWriter != null)
		{
			networkWriter.send("bye");
			networkWriter.stopWritting();
		}
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public Player getOpponent()
	{
		return opponent;
	}

	public void refresh() {
		screen.repaint();
		if(opponent != null && player != null)
		{
			if(!opponent.getPlayerGrid().hasMoreShip())
				screen.showWonMenu();
			else if(!player.getPlayerGrid().hasMoreShip())
				screen.showLostMenu();
		}
	}
	
	public void sendMessage(String message)
	{
		networkWriter.send(message);
	}

	public void connect() {
			connect("localhost", Constant.SENDING_PORT);
	}
	
	public void connect(String hote, int port) {
		if(DEBUG_MODE)
		{
			if(networkWriter == null)
			{
				networkWriter = new NetworkWriter(hote,port);
				networkWriter.start();
			}
		}
		else
		{
			if(port != networkListener.getPort())
			{
				networkListener.stopListening();
				networkListener = new NetworkListener(port);
				networkListener.start();
			}
			if(networkWriter != null)
			{
				networkWriter.stopWritting();
			}
			networkWriter = new NetworkWriter(hote,port);
			networkWriter.start();
		}
	}

	public boolean canPlay() {
		return canPlay;
	}
	
	public void canPlay(boolean state)
	{
		canPlay = state;
		if(state)
			SeparatorMessageQueue.getInstance().addMessageToQueue("Your turn !");
		else
			SeparatorMessageQueue.getInstance().addMessageToQueue("Opponent turn !");
	}

	public double getRandomStarterPlayer()
	{
		return randomStart;
	}
	
	public void decideStarterPlayer(double opponentRand) {
		if (randomStart >opponentRand)
			canPlay(true);
		else
			canPlay(false);
	}

	public void endTurn() {
		canPlay(false);
		sendMessage("EOT");
	}

	public void setPlayer(Player player) {
		this.player = player;
		if(screen != null)
		{
			screen.loadGrids();
			screen.loadPlayersInformationPanel();
		}
	}

	public void addOpponent(Player opponent2) {
		opponent = opponent2;
		screen.loadGrids();
		screen.loadPlayersInformationPanel();
	}
}
