package lifegame;

public class BoardModel {
	private int cols;
	private int rows;
	private boolean[][] cells;

	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public void printForDebug() {
		for(int y=0; y < rows; y++) {
			for(int x=0; x < cols; x++) {
				if(cells[y][x] == true) System.out.print('*');
				else System.out.print('.');
			}
			System.out.println("");
		}
	}

	public void changeCellState(int x, int y) {
		// (x, y) で指定されたセルの状態を変更する．
		cells[y][x]=!cells[y][x];
	}



}
