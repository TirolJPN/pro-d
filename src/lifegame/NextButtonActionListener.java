package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButtonActionListener implements ActionListener{

	private BoardModel model;

	NextButtonActionListener(BoardModel m){
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.next();
	}
}
