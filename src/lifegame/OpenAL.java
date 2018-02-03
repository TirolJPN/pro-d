package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;


public class OpenAL extends JFrame implements ActionListener{
	private BoardModel model;
	private JRadioButtonMenuItem item;
	private JFrame frame;
	// エラーを考慮して作業用の配列にファイルから書き出す
	private boolean[][] tmpcells;
	private int c=0, r=0;
	private BufferedReader br;
	private String line;
	private String[] elem;

	OpenAL(JRadioButtonMenuItem menuOpen, BoardModel m, JFrame f){
		item = menuOpen;
		model = m;
		frame = f;
		c = model.getCols();
		r = model.getRows();
		tmpcells = new boolean[r][c];
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean flag = true;
		JFileChooser filechooser = new JFileChooser();
	    int selected = filechooser.showOpenDialog(this);
	    if (selected == JFileChooser.APPROVE_OPTION){
	      File file = filechooser.getSelectedFile();
	      /*
	       * 盤面の情報を読み込んでいく
	       * 形式に措定なければエラーとする
	       * それ以降：0,1による盤面の情報
	       * 現在のboardmodelの行数よりもファイルの(行数-1)が少なければまずエラーを出力する
	       * 1行ごとに盤面情報の割り当てを行う，列数が足りない行が存在すれば，その時点でエラー
	       * 多い分は無視する．（現在の盤面の長方形をファイルの盤面情報の左上に合わせるイメージ）
	       */
	      // JOptionPane.showMessageDialog(frame, "Message");
	      try {
	    	  	List<String> buffer = Files.readAllLines(Paths.get(file.getAbsolutePath()));
	    	  	if(model.getRows() > buffer.size()) {
	    	  		JOptionPane.showMessageDialog(frame, "行数が足りません");
	    	  		flag = false;

	    	  	}else {
	    	  		File f = new File(file.getAbsolutePath());
	    			FileReader filereader = new FileReader(f);
	    			br = new BufferedReader(filereader);
	    	  		for(int y=0; y < r; y++) {
	    	  			line = br.readLine();
	    				elem =  line.split(",", 0);
	    				if(elem.length >= c) {
		    	  			for(int x=0; x < c; x++) {
		    	  				if(elem[x].equals("1")) {
		    	  					tmpcells[y][x] = true;
		    	  				}else if(elem[x].equals("0")){
		    	  					tmpcells[y][x] = false;
		    	  				}else {
		    	  					JOptionPane.showMessageDialog(frame, "不正なファイル");
		    	  					flag = false;
		    	  				}
		    	  			}
	    				}else {
	    					JOptionPane.showMessageDialog(frame, "列数が足りません");
	    					flag = false;
	    					break;
	    				}
	    	  		}

	    	  		for(int y=0; y < r; y++) {
	    	  			for(int x=0; x < c; x++) {
	    	  				if((model.isAlive(x, y) != tmpcells[y][x]) && flag) {
	    	  					model.changeCellState(x, y);
	    	  				}
	    	  			}
	    	  		}
	    	  	}
	      } catch (IOException e) {
			// TODO 自動生成された catch ブロック
	    	  	JOptionPane.showMessageDialog(frame, "ファイルが見つかりませんでした");
	      }

	    }
	}
}
