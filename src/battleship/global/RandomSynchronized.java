package battleship.global;

public class RandomSynchronized {
	
	public static int nextInt(int max)
	{
		return (int) (Math.random()*max);
	}

	public static double nextDouble(double max)
	{
		return (Math.random()*max);
	}
	
	public static float nextFloat(float max)
	{
		return (float) (Math.random()*max);
	}
	
	public static boolean nextBool()
	{
		return (Math.random()/Math.random()) > 1;
	}
}
