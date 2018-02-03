package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;

public class SaveDialogAL extends JFrame implements ActionListener{

	private BoardModel model;
	private JRadioButtonMenuItem item;

	SaveDialogAL(JRadioButtonMenuItem menuSaveAs, BoardModel m){
		this.item = menuSaveAs;
		this.model = m;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser filechooser = new JFileChooser();
	    int selected = filechooser.showSaveDialog(this);
	    if (selected == JFileChooser.APPROVE_OPTION){
	      File file = filechooser.getSelectedFile();
	      try {
		    	  Writer out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
		    	//盤面情報を0=偽，1=真で保存
		    	  for(int y=0; y<model.getRows(); y++) {
		    		  for(int x=0; x<model.getCols(); x++){
		    			 if(model.isAlive(x, y)) {
		    				 out.write("1,");
		    			 }else {
		    				 out.write("0,");
		    			 }
		    		  }
		    		  out.write("\n");
		    	  }

		    	  out.close();
	      } catch(Exception ex) {
	    	  	ex.printStackTrace();
	      }
	    }
	}
}

