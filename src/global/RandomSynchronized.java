package global;

import java.util.Random;

public class RandomSynchronized {
	private static Random rand = new Random();
	
	public static int nextInt(int max)
	{
		return rand.nextInt(max);
	}

	public static double nextDouble(double max)
	{
		return rand.nextDouble()%max;
	}
	
	public static float nextFloat(float max)
	{
		return rand.nextFloat()%max;
	}
	
	public static boolean nextBool()
	{
		return rand.nextBoolean();
	}
}
