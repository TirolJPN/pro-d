package lifegame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class BoardView extends JPanel
implements  MouseListener, MouseMotionListener{
	BoardModel model;
	/*
	 * h:ウィンドウの高さ
	 * w:ウィンドウの幅
	 * s:盤面の最小単位（長さ）
	 * r:行数
	 * c:列数
	 * dh:盤面を表示させる際の高さの余白
	 * dw:盤面を表示させる際の幅の余白
	 */
	private int h;
	private int w;
	private int s=1;
	private int r;
	private int c;
	private int dh;
	private int dw;


	//直前のイベントが起こった盤面上の座標(x, y)を保存しておく
	//eventFlag:直前のイベントがpressedかdraggedであるかを示すフラグ．
	private int eventX = 0;
	private int eventY = 0;
	private boolean eventFlag = false;

	BoardView(BoardModel m){
		model = m;
		this.addMouseListener(this);
	    this.addMouseMotionListener(this);
	}

	@Override
    public void paint(Graphics g) {

		super.paint(g); // JPanel の描画処理(背景塗りつぶし)

		//盤面の線の描画
		w = this.getWidth();
		h = this.getHeight();
		c = model.getCols();
		r = model.getRows();

		setSize();
		dh = (h - s * r) / 2;
		dw = (w - s * c) / 2;

		//盤面の線画
		for(int y=0;y<=r;y++) {
			g.drawLine(dw,dh + y*s,dw + s*c,dh + y*s);
		}
		for(int x=0;x<=c;x++) {
			g.drawLine(dw + x*s, dh,dw + x*s,dh + s*r);
		}

		//セルの塗りつぶし
		for(int y=0;y<r;y++) {
			for(int x=0;x<c;x++) {
				if(model.isAlive(x, y)) {
					g.fillRect(dw + x*s,dh + y*s, s, s);
				}
			}
		}
		repaint();

	}

	private void setSize() {
		//ウィンドウが縦長かつ，盤面が縦長
		//盤面の最小単位はw/c
		if(w<=h && c<=r) {
			s = h / r;
			if(s * c > w) {
				s = w / c;
			}

		//ウィンドウが縦長かつ，盤面が横長
		//盤面の最小単位はw/r
		}else if(w<=h && c>r) {
			s = w/c;

		//ウィンドウが横長かつ，盤面が縦長
		//盤面の最小単位はh/c
		}else if(w>h && c<=r) {
			s = h/r;

		//ウィンドウが横長かつ，盤面が横長
		//盤面の最小単位がh/r
		}else if(w>h && c>r) {
			s = w/c;
			if(s * r > h) {
				s = h / r;
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		eventX = (e.getX() - dw) / s;
    		eventY = (e.getY() - dh) / s;
		eventFlag = false;
    }
    public void mouseEntered(MouseEvent e) {
    		eventX = (e.getX() - dw) / s;
		eventY = (e.getY() - dh) / s;
    		eventFlag = false;
    }
    public void mouseExited(MouseEvent e) {
    		eventX = (e.getX() - dw) / s;
		eventY = (e.getY() - dh) / s;
    		eventFlag = false;
    }
    public void mousePressed(MouseEvent e) {
		eventX = (e.getX() - dw) / s;
		eventY = (e.getY() - dh) / s;
	    	//押された位置が盤面内に収まっているかの確認
	    	if(0 <= eventX && eventX < model.getCols() && 0 <= eventY && eventY < model.getRows() ) {
	    		model.changeCellState(eventX, eventY);
	    	}
	    	eventFlag = true;
    }
    public void mouseReleased(MouseEvent e) {
		eventX = (e.getX() - dw) / s;
		eventY = (e.getY() - dh) / s;
		eventFlag = false;
    }
    public void mouseDragged(MouseEvent e) {
		int x = (e.getX() - dw) / s;
		int y = (e.getY() - dh) / s;
    		if(0 <= x && x < model.getCols() && 0 <= y && y < model.getRows() && (x != eventX || y != eventY || !eventFlag) ) {
	    		model.changeCellState(x, y);
	    	}
    		eventFlag=true;
    		eventX = (e.getX() - dw) / s;
		eventY = (e.getY() - dh) / s;
    }
    public void mouseMoved(MouseEvent e) {
		eventX = (e.getX() - dw) / s;
		eventY = (e.getY() - dh) / s;
    		eventFlag = false;
    }
}