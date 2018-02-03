package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

public class Main implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
    public void run()  {
	    	// BoardModel の作成と changeCellState の呼び出しを行う処理をここで実行.
    		BoardModel model = new BoardModel(70, 70);
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

	    	//メニューバーの作成？-----------------------------------------------------------------------
	    	JMenuBar bar = new JMenuBar();
	    JMenu menu = new JMenu("File");
	    menu.setMnemonic(KeyEvent.VK_O);

	    ButtonGroup group = new ButtonGroup();
	    JRadioButtonMenuItem menuSaveAs = new JRadioButtonMenuItem("Save As ...");
	    menuSaveAs.addActionListener(new SaveDialogAL(menuSaveAs, model));
	    group.add(menuSaveAs);
	    menu.add(menuSaveAs);

	    JRadioButtonMenuItem menuOpen = new JRadioButtonMenuItem("Open");
	    menuOpen.addActionListener(new OpenAL(menuOpen, model, frame));
	    group.add(menuOpen);
	    menu.add(menuOpen);

	    bar.add(menu);
	    frame.setJMenuBar(bar);
	    	//メニューバーの作成ここまで-----------------------------------------------------------------------

	    	JPanel buttonPanel = new JPanel(); // ボタン用パネルを作成し
	    	base.add(buttonPanel, BorderLayout.SOUTH); // base の下端に配置する
	    	buttonPanel.setLayout(new FlowLayout()); // java.awt.FlowLayout を設定
	    	JButton nextButton = new JButton("Next");
	    	nextButton.addActionListener(new NextButtonAL(model));
	    	JButton undoButton = new JButton("Undo");
	    	undoButton.addActionListener(new UndoButtonAL(model));

	    	//undoボタンの有効，無効化を管理するboardlistenerを登録する
	    	model.addListener(new UndoButtonAdmin(undoButton));

	    	JButton newGameButton = new JButton("New Game");
	    	newGameButton.addActionListener(new NewGameButtonAL());

	    	JButton autoButton = new JButton("Auto");
	    	autoButton.addActionListener(new AutoButtonAL(model));
	    	buttonPanel.add(nextButton, BorderLayout.EAST);
	    	buttonPanel.add(undoButton, BorderLayout.EAST);
	    	buttonPanel.add(autoButton, BorderLayout.EAST);
	    	buttonPanel.add(newGameButton, BorderLayout.EAST);


	    	frame.pack(); // ウィンドウに乗せたものの配置を確定する
	    	frame.setVisible(true); // ウィンドウを表示する

    }
}
