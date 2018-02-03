package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButtonAL implements ActionListener{

	private BoardModel model;

	NextButtonAL(BoardModel m){
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.next();
	}
}
