package battleship.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import battleship.Game;
import battleship.Grid;
import battleship.Player;
import battleship.SeparatorMessageQueue;
import battleship.global.Constant;
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
			System.out.println("[DEBUG] ServerSocket opened on "+serverSocket.getLocalPort());
			serverSocket.setSoTimeout(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		while(continueListening && socket == null)
		{
			try
			{
				socket = serverSocket.accept();
			}
			catch(IOException ex)
			{
				System.out.println("[DEBUG] Waiting for connection on port "+serverSocket.getLocalPort());					
			}
		}
		
		if(!continueListening)
			return;
		
		System.out.println("[DEBUG] Connection from : "+socket.getPort());
		Game.getInstance().connect();
		
		try{
			//Reading the opponent initial grid
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Player opponent = (Player) ois.readObject();
			Game.getInstance().addOpponent(opponent);
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		while(continueListening)
		{
			try{
				String message = br.readLine();
				System.out.println("[DEBUG] NetworkReader read : "+message+" from "+socket.getPort());
				if(message != null)
					traiterMessage(message);
			} catch (IOException e) {
				System.out.println("Opponent is now disconnected !");
				stopListening();
			}
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
				SeparatorMessageQueue.getInstance().addMessageToQueue("Missed !",Constant.MISSED_BOX,1500);
			else if(status == Grid.TOUCHED)
				SeparatorMessageQueue.getInstance().addMessageToQueue("Touched !",Constant.TOUCHED_BOX,1500);
		}
		else if(params[0].equals("hurt"))
		{
			int index = Integer.parseInt(params[1]);
			Game.getInstance().getOpponent().getPlayerGrid().getShipList().get(index).hurt();
		}
		else if(params[0].equals("sink"))
		{
			int shipID = Integer.parseInt(params[1]);
			Game.getInstance().getOpponent().getPlayerGrid().sinkShip(shipID);
			SeparatorMessageQueue.getInstance().addMessageToQueue("Sunk !",Constant.SUNK_BOX,2500);
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

	public int getPort() {
		return serverSocket.getLocalPort();
	}
}
