package battleship;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import battleship.frames.Screen;

public class SeparatorMessageQueue extends Thread{

	private LinkedList<SeparatorMessage> queue = new LinkedList<SeparatorMessage>();
	private Screen screen = null;
	
	private long millisecondToWait = 500;
	
	private static SeparatorMessageQueue separatorMessageQueue = null; 
	
	private SeparatorMessageQueue()
	{
		screen = Screen.getInstance();
		start();
	}
	
	public static SeparatorMessageQueue getInstance()
	{
		if(separatorMessageQueue == null)
		{
			separatorMessageQueue = new SeparatorMessageQueue();
		}
		
		return separatorMessageQueue;
	}
	
	public void addMessageToQueue(String message)
	{
		queue.add(new SeparatorMessage(message));
	}
	
	public void addMessageToQueue(String message, Color couleur)
	{
		queue.add(new SeparatorMessage(message,couleur));
	}
	
	public void addMessageToQueue(String message, Font font)
	{
		queue.add(new SeparatorMessage(message,font));
	}
	
	public void addMessageToQueue(String message, long displayTime)
	{
		queue.add(new SeparatorMessage(message,displayTime));
	}
	
	public void addMessageToQueue(String message, Color color, long displayTime)
	{
		queue.add(new SeparatorMessage(message, color, displayTime));
	}
	
	public void addMessageToQueue(String message, Font font, long displayTime)
	{
		queue.add(new SeparatorMessage(message, font, displayTime));
	}
	
	public void addMessageToQueue(String message, Color color, Font font)
	{
		queue.add(new SeparatorMessage(message, color, font));
	}
	
	public void addMessageToQueue(String message, Color color, Font font, long displayTime)
	{
		queue.add(new SeparatorMessage(message,color, font, displayTime));
	}
	
	@Override
	public void run()
	{
		long time = millisecondToWait;
		while(true)
		{
			try
			{
				sleep(time);
				if(queue.size()>0)
				{
					SeparatorMessage s = queue.poll();
					screen.setMessage(s);
					time = s.getDisplayTime();
				}
				else
					time = millisecondToWait;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
