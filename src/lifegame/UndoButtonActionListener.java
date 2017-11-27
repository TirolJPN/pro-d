package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButtonActionListener implements ActionListener{

	private BoardModel model;

	UndoButtonActionListener(BoardModel m){
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.undo();
	}
}
