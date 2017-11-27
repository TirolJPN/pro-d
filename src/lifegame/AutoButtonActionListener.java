package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class AutoButtonActionListener
implements ActionListener {
	private BoardModel model;
	AutoButtonActionListener(BoardModel m){
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Timer timer = new Timer();
		timer.schedule(new AutoUpdateModel(model), 0, 50);
	}

}
