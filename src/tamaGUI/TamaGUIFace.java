package tamaGUI;

import java.util.ArrayList;
import javax.swing.ImageIcon;


/** TAMA GUI FACE CLASS, is not real GUI. 
 * This Class gives a animation to player. 
 * Depending on the Level it gives different faces.
 * 
 * 
 * Image made by Arild in Paint!
 * Try sue me now!
 * 
 * funkar inte....242014
 * TESTA PROTECTED/ARV system
 * 
 *
 */

public class TamaGUIFace extends TamaGUI {

	private static final long serialVersionUID = 1L;

	private ArrayList <ImageIcon> faces;
	private TamaGUI tg;
	private int gameLevel;
	private ImageIcon label = new ImageIcon();

	public TamaGUIFace(TamaGUI tg, int gameLevel){
		loadpics();
		this.tg = tg;
		this.gameLevel = gameLevel;
	}

	//gives back an animation of Tama, Int x and Int y for sad face animation
	// make a better system for sad face..
	public void tamaAnimation(int depresionValue, int hungerValue) {
		int x = 0;
		int y = 7;

		try {
			Thread.sleep(3000);
			if (x >= 7 || y >= 9){
				x = 0;
				y = 7;
			}
			if (depresionValue <= 3000 || hungerValue <= 3000 ){
				label = faces.get(y);
				y++;
			}else{
				TamaGUI.label.setIcon(faces.get(x));
				x++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		tg.labelUpdater(label);
	}

	//load pics, depending on game level
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

}

