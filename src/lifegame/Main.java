package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
    public void run() {
	    	// BoardModel の作成と changeCellState の呼び出しを行う処理をここで実行.
    		BoardModel model = new BoardModel(50, 50);

	    	// next と undo の呼び出しを取り除き，「グライダー」が設置された状態としておく.
	    	// ウィンドウを作成する
    		//引数にString指定でタイトル決定
	    	JFrame frame = new JFrame("Lifegame");
	    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	// ウィンドウ内部を占有する「ベース」パネルを作成する
	    	JPanel base = new JPanel();
	    	frame.setContentPane(base);
	    	base.setPreferredSize(new Dimension(450, 350)); // 希望サイズの指定
	    	frame.setMinimumSize(new Dimension(400, 300)); // 最小サイズの指定

	    	base.setLayout(new BorderLayout()); // base 上に配置するGUI部品のルールを設定
	    	BoardView view = new BoardView(model);
	    	base.add(view, BorderLayout.CENTER); // base の中央に view を配置する

	    	JPanel buttonPanel = new JPanel(); // ボタン用パネルを作成し
	    	base.add(buttonPanel, BorderLayout.SOUTH); // base の下端に配置する
	    	buttonPanel.setLayout(new FlowLayout()); // java.awt.FlowLayout を設定
	    	JButton nextButton = new JButton("Next");
	    	nextButton.addActionListener(new NextButtonActionListener(model));
	    	JButton undoButton = new JButton("Undo");
	    	undoButton.addActionListener(new UndoButtonActionListener(model));

	    	//undoボタンの有効，無効化を管理するboardlistenerを登録する
	    	model.addListener(new UndoButtonAdmin(undoButton));

	    	JButton newGameButton = new JButton("New Game");
	    	newGameButton.addActionListener(new NewGameButtonActionListener());

	    	JButton autoButton = new JButton("Auto");
	    	autoButton.addActionListener(new AutoButtonActionListener(model));
	    	buttonPanel.add(nextButton, BorderLayout.EAST);
	    	buttonPanel.add(undoButton, BorderLayout.EAST);
	    	buttonPanel.add(newGameButton, BorderLayout.EAST);
	    	buttonPanel.add(autoButton, BorderLayout.EAST);

	    	frame.pack(); // ウィンドウに乗せたものの配置を確定する
	    	frame.setVisible(true); // ウィンドウを表示する

    }
}
