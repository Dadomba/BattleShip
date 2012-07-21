package battleship.network;

import java.io.BufferedWriter;
import java.io.IOException;
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
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try {
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			send("start:"+Game.getInstance().getRandomStarterPlayer());
			while(continueWritting)
			{
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(String message)
	{
		if(bw == null)
			return;
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
