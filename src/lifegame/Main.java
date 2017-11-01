package lifegame;

public class Main {
	public static void main(String[] args) {
		BoardModel model = new BoardModel(10, 10);
		model.printForDebug();
		model.changeCellState(1, 1);
		model.printForDebug();
		model.changeCellState(2, 2);
		model.printForDebug();
		model.changeCellState(0, 3);
		model.printForDebug();
		model.changeCellState(1, 3);
		model.printForDebug();
		model.changeCellState(2, 3);
		model.printForDebug();
		model.changeCellState(4, 4);
		model.printForDebug();
		model.changeCellState(4, 4);
		model.printForDebug();
	}
}
