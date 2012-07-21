package battleship.network;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import battleship.Game;
import battleship.Grid;
import battleship.SeparatorMessageQueue;
import battleship.global.Coord;

public class NetworkListener extends Thread{
	 
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private BufferedReader br = null;
	public boolean continueListening = true;
	
	public NetworkListener(int port)
	{
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		try {
			socket = serverSocket.accept();
			System.out.println("[DEBUG] Connection from : "+socket.getPort());
			Game.getInstance().connect();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(continueListening)
			{
				String message = br.readLine();
				System.out.println("[DEBUG] NetworkReader read : "+message+" from "+socket.getPort());
				if(message != null)
					traiterMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void traiterMessage(String message)
	{
		String[] params = message.split(":");
		
		if(params[0].equals("start"))
		{
			double opponentRand = Double.parseDouble(params[1]);
			Game.getInstance().decideStarterPlayer(opponentRand);
		}
		else if(params[0].equals("status"))
		{
			String[] infos = params[1].split(",");
			int x = Integer.parseInt(infos[0]);
			int y = Integer.parseInt(infos[1]);
			int status = Integer.parseInt(infos[2]);
			Game.getInstance().getOpponent().getPlayerGrid().setBoxStatus(x,y,status);
			if(status == Grid.MISSED)
				SeparatorMessageQueue.getInstance().addMessageToQueue("Manqué !",Color.BLUE,1500);
			else if(status == Grid.TOUCHED)
				SeparatorMessageQueue.getInstance().addMessageToQueue("Touché !",Color.RED,1500);
		}
		else if(params[0].equals("sink"))
		{
			int shipID = Integer.parseInt(params[1]);
			Game.getInstance().getOpponent().getPlayerGrid().sinkShip(shipID);
			SeparatorMessageQueue.getInstance().addMessageToQueue("Coulé !",Color.GREEN,2500);
			Game.getInstance().canPlay(true);
		}
		else if(params[0].equals("attack"))
		{
			String[] infos = params[1].split(",");
			int x = Integer.parseInt(infos[0]);
			int y = Integer.parseInt(infos[1]);
			Game.getInstance().getPlayer().getPlayerGrid().attack(new Coord(x,y));
		}  
		else if(params[0].equals("EOT"))
		{
			Game.getInstance().canPlay(true);
		}
		else if(params[0].equals("bye"))
			stopListening();
	}

	public void stopListening() {
		continueListening = false;
	}
}
