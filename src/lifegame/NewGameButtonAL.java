package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class NewGameButtonAL extends Main implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(new Main());
	}
}
