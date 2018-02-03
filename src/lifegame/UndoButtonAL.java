package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButtonAL implements ActionListener{

	private BoardModel model;

	UndoButtonAL(BoardModel m){
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.undo();
	}
}
