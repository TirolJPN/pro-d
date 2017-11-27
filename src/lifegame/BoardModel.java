package lifegame;

import java.util.ArrayList;

public class BoardModel {
	private int cols;
	private int rows;
	private boolean[][] cells;

	private ArrayList<BoardListener> listeners;

	// 盤面の履歴を記録するためのArrayListクラス
	private ArrayList<boolean[][]> history;

	// historyの最後尾を表すインデックス
	private int indexHistory;

	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
		history = new ArrayList<boolean[][]>();
		indexHistory = -1;
		//初期状態もhistoryに記録
		this.addHistory();
	}

	public void addListener(BoardListener listener) {
		listeners.add(listener);
	}

	private void fireUpdate() {
		for (BoardListener listener: listeners) {
			listener.updated(this);
		}
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

		//盤面をhistoryに記録
		this.addHistory();

		//表示
		fireUpdate();
	}

	public void next() {
		// 作業用配列の用意
		// 周りに無駄要素をもつ
		boolean[][] refcells = new boolean[rows+2][cols+2];
		for(int x = 0; x < cols+2; x++) {
			refcells[0][x]=false;
			refcells[rows+1][x]=false;
		}
		for(int y = 0; y < rows+2; y++) {
			refcells[y][0]=false;
			refcells[y][cols+1]=false;
		}

		for(int y=0; y < rows; y++) {
			for(int x=0; x < cols; x++) {
				refcells[y + 1][x + 1] = cells[y][x];
			}
		}


		//　次世代のセルの決定
		for(int y=1; y < rows+1; y++) {
			for(int x=1; x < cols+1; x++) {
				int count = 0;
				for(int dy = y-1; dy < y + 2; dy++) {
					for(int dx = x-1; dx < x + 2; dx++) {
						if(refcells[dy][dx] == true && (dx != x || dy != y)) {
							count++;
						}
					}
				}
				if(refcells[y][x] == true && count!=2 && count != 3) {
						cells[y-1][x-1] = false;
				}else if(refcells[y][x]== false && count == 3) {
						cells[y-1][x-1]=true;
				}
			}
		}
//		盤面をhistoryに記録
		this.addHistory();

		//cellsの更新
		fireUpdate();
	}

	public void undo() {

		//履歴を読み込んでくるための一時的な2次元配列
		boolean[][] tmpcells = history.get(indexHistory - 1);
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols;c++) {
				cells[r][c] = tmpcells[r][c];
			}
		}
		history.remove(indexHistory);
		indexHistory--;
		fireUpdate();
	}

	private void addHistory() {
		//historyに履歴として記録
		//インデックスを更新
		boolean[][] tmpcells = new boolean[rows][cols];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols;c++) {
				tmpcells[r][c] = cells[r][c];
			}
		}
		history.add(tmpcells);
		indexHistory++;
	}

	public boolean isUndoable(){
//		現在の状態数が2以上であれば良い．
		if(indexHistory>0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isAlive(int x, int y) {
		return cells[y][x];
	}

	public int getIndexHistory() {
		return indexHistory;
	}
}
