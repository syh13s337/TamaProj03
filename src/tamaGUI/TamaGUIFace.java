package tamaGUI;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import tamaSystem.DepressionEngine;
import tamaSystem.HungerEngine;

/** TAMA GUI FACE CLASS, is not real GUI. 
 * This Class gives a animation to player. 
 * Depending on the Level it gives different faces.
 * 
 * 
 * Image made by Arild in Paint!
 * Try sue me now!
 *
 */

public class TamaGUIFace extends JComponent implements Runnable {

	private static final long serialVersionUID = 1L;

	private ArrayList <ImageIcon> faces;
	private int depresionValue;
	private int hungerValue;
	private int gameLevel;
	public int getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}

	//The Loop, with depression and hunger checker for sad face.
	@Override
	public void run() {
		loadpics();
		int x = 0;
		int y = 7;

		while(TamaGUIStart.ALL_THREADS_RUNNING == true){
			try {
				if (x >= 7 || y >= 9){
					x = 0;
					y = 7;
				}
				if (depresionValue <= 3000 || hungerValue <= 3000 ){
					TamaGUI.label.setIcon(faces.get(y));
					y++;
				}else{
					TamaGUI.label.setIcon(faces.get(x));
					x++;
				}
				Thread.sleep(3000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public TamaGUIFace(){
	}

	//load pics
	private void loadpics(){
		faces = new ArrayList <ImageIcon>();

		if (gameLevel == 1) {
			faces.add(new ImageIcon("image/Baby/b2.png"));
			faces.add(new ImageIcon("image/Baby/b3.png"));
			faces.add(new ImageIcon("image/Baby/b4.png"));
			faces.add(new ImageIcon("image/Baby/b3.png"));
			faces.add(new ImageIcon("image/Baby/bo1.png"));
			faces.add(new ImageIcon("image/Baby/bo2.png"));
			faces.add(new ImageIcon("image/Baby/bo3.png"));
			faces.add(new ImageIcon("image/Baby/bs1.png"));
			faces.add(new ImageIcon("image/Baby/bs2.png"));
			faces.add(new ImageIcon("image/Baby/bs3.png"));
		}
		else if (gameLevel == 2) {
			faces.add(new ImageIcon("image/Kid/b2.png"));
			faces.add(new ImageIcon("image/Kid/b3.png"));
			faces.add(new ImageIcon("image/Kid/b4.png"));
			faces.add(new ImageIcon("image/Kid/b3.png"));
			faces.add(new ImageIcon("image/Kid/bo1.png"));
			faces.add(new ImageIcon("image/Kid/bo2.png"));
			faces.add(new ImageIcon("image/Kid/bo3.png"));
			faces.add(new ImageIcon("image/Kid/bs1.png"));
			faces.add(new ImageIcon("image/Kid/bs2.png"));
			faces.add(new ImageIcon("image/Kid/bs3.png"));
		}
		else if (gameLevel == 3) {
			faces.add(new ImageIcon("image/YA/b2.png"));
			faces.add(new ImageIcon("image/YA/b3.png"));
			faces.add(new ImageIcon("image/YA/b4.png"));
			faces.add(new ImageIcon("image/YA/b3.png"));
			faces.add(new ImageIcon("image/YA/bo1.png"));
			faces.add(new ImageIcon("image/YA/bo2.png"));
			faces.add(new ImageIcon("image/YA/bo3.png"));
			faces.add(new ImageIcon("image/YA/bs1.png"));
			faces.add(new ImageIcon("image/YA/bs2.png"));
			faces.add(new ImageIcon("image/YA/bs3.png"));
		}
	}

	//GETTER hunger
	public int getHungerValue() {
		return hungerValue;
	}
	//SET Hunger
	public void setHungerValue(int hungerValue) {
		this.hungerValue = hungerValue;
	}

	//GETTER depression
	public int getDepressionValue() {
		return depresionValue;
	}
	//SETER depression
	public void setDepresionValue(int depresionValue) {
		this.depresionValue = depresionValue;
	}
}

