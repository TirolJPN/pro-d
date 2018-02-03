package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class AutoButtonAL
implements ActionListener {
	private BoardModel model;
	// ボタンが２度押されても更新速度が早くならないようにするためのフラグ
	private boolean flag;
	private Timer timer;
	AutoButtonAL(BoardModel m){
		model = m;
		flag = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(flag) {
			timer = new Timer();
			timer.schedule(new AutoUpdateModel(model), 0, 500);
			flag = false;
		}else {
			timer.cancel();
			flag = true;
		}

	}

}
