package tamaStart;

/** Just a start Class
 * 
 *  Made by Arild Oderman, Java13
 *  *  Yes even the png. PAINT proh!
 * 
 */


import tamaGUI.TamaGUIStart;

public class TamaStartClass {

	public static void main(String[] args) {
		gameStarter();
	}

	private static void gameStarter(){
		TamaGUIStart tsg = new TamaGUIStart();
		tsg.frmTamav.setVisible(true);
	}
}
