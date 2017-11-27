package lifegame;

import java.util.TimerTask;

public class AutoUpdateModel extends TimerTask {
	private BoardModel model;

	AutoUpdateModel(BoardModel m){
		model = m;
	}

	@Override
	public void run() {
		model.next();
	}

}
