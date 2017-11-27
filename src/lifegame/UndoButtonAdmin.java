package lifegame;

import javax.swing.JButton;

public class UndoButtonAdmin implements BoardListener{
	private JButton b;

	UndoButtonAdmin(JButton button){
		this.b = button;
		b.setEnabled(false);
	}

	public void updated(BoardModel m) {
		if(m.getIndexHistory() < 1) {
			b.setEnabled(false);
		}else {
			b.setEnabled(true);
		}
	}
}
