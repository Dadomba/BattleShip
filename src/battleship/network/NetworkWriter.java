package battleship.network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import battleship.Game;

public class NetworkWriter extends Thread{

	private Socket socket = null;
	public boolean continueWritting = true;
	private BufferedWriter bw = null;
	
	public NetworkWriter(String ip, int port)
	{
		try {
			socket = new Socket(ip, port);
			System.out.println("[DEBUG] Opening socket to "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		if(socket == null)
		{
			System.err.println("Error, wrong initialization of the socket of networkWriter! Thread stopped");
			return;
		}
			
		try {
			//Sending the initial grid to the opponent
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(Game.getInstance().getPlayer());
			
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			send("start:"+Game.getInstance().getRandomStarterPlayer());
			
			while(continueWritting)
			{
				Thread.sleep(500);//on verifie toutes les 500ms si il y'a un message a envoyer
			}
			
			oos.close();
			
			System.out.println("[DEBUG] NetworkWriter stopped");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(String message)
	{
		if(bw == null)
		{
			System.err.println("Error, Wrong initialization of the BufferedWriter of networkWriter ! Cannot send the message : \""+message+"\"");
			return;
		}
		try {
			bw.write(message+"\n");
			bw.flush();
			
			System.out.println("[DEBUG] NetworkWriter send : "+message+" to "+socket.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopWritting()
	{
		continueWritting = false;
	}
}
