package battleship;

public class Grid {
	public static final int FREE = 0;
	public static final int BUSY = 1;
	public static final int MISSED = 2;
	public static final int TOUCHED = 3;
	public static final int SINK = 4;

	public int[][] caseStatus = new int[10][10];

	public Grid() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				caseStatus[i][j] = FREE;
	}

	public int getCaseStatus(int i, int j) {
		return caseStatus[i][j];
	}

	public void setCaseStatus(int i, int j, int valeur) {
		if (valeur > -1 && valeur < 5)
			caseStatus[i][j] = valeur;
	}
}
