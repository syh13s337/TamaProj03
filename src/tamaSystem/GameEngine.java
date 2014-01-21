package tamaSystem;

import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIFace;
import tamaGUI.TamaGUIStart;

/**THE GAME ENGINE
 * 
 * Here is where the game loop and updater is.
 * 
 * 
 *
 */

public class GameEngine implements Runnable {


	private WinAndEndEngine we = new WinAndEndEngine();
	private DepressionEngine de = new DepressionEngine();
	private HungerEngine he = new HungerEngine();
	private TamaGUIFace tgf = new TamaGUIFace();
	private TamaGUI tg = new TamaGUI();
	private MoneyEngine mo = new MoneyEngine();

	private Thread depEngine = new Thread(de, "DepressionThread");
	private Thread winEngine = new Thread(we, "WinThread");
	private Thread hunEngine = new Thread(he, "FoodThread");
	private Thread monEngine = new Thread(mo, "MoneyThead");
	private Thread faceEngine = new Thread(tgf, "FaceThread");

	private String tamaName = "";
	private int gameLevel;

	private void initiater(){
		depEngine.start();
		winEngine.start();
		hunEngine.start();
		monEngine.start();
		faceEngine.start();
	}

	private void deathAndWinChecker(String tamanName){
		if (de.isDeathByDepression() == true){
			we.deathByDepression(tamanName);
			tg.GUIFrame.setVisible(false);
		}
		else if(he.isDeathByHunger() == true){
			we.deathByHunger(tamaName);
			tg.GUIFrame.setVisible(false);
		}
		else if (we.isWin() == true){
			we.winning(tamaName);
			tg.GUIFrame.setVisible(false);
		}
	}

	private void barAndFaceUpdater(){
		//for sad face method,
		tgf.setDepresionValue(de.getTamaCurrentDepression());
		tgf.setHungerValue(he.getTamaCurrentHunger());

		//for bars in TamaGUI
		tg.setDepressionBar(de.getTamaCurrentDepression());	
		tg.setMoneyBar(mo.getCurrentMoney());
		tg.setHungerBar(he.getTamaCurrentHunger());
	}

	private void mouseEventUpdater(){
		for (int i = 0; i < tg.getMoneyMouseCounter(); i++) {
			mo.moneyGain1();
		}
		tg.setMoneyMouseCounter(0);

		for (int i = 0; i < tg.getMouseHappinessSinker(); i++) {
			de.mouseHappinessSinker();
		}
		tg.setMouseHappinessSinker(0);

		for (int i = 0; i < tg.getMouseGainHappiness(); i++) {
			de.mouseHappiness();
		}
		tg.setMouseGainHappiness(0);

	}

	public void startGameGUI(int gameLevel, String frameTitle, String tamaName){
		this.tamaName = tamaName;
		this.gameLevel = gameLevel;
		tgf.setGameLevel(gameLevel);
		we.setGameLevel(gameLevel);
		de.setGameLevel(gameLevel);
		he.setGameLevel(gameLevel);
		mo.setGameLevel(gameLevel);

		tg = new TamaGUI(gameLevel, frameTitle, tamaName);
		tg.GUIFrame.setVisible(true);
	}

	@Override
	public void run() {
		initiater();

		while(TamaGUIStart.ALL_THREADS_RUNNING == true){
			try {
				barAndFaceUpdater();
				deathAndWinChecker(tamaName);
				mouseEventUpdater();


				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	//GETTER name
	public String getTamaName() {
		return tamaName;
	}
	//SETTER name
	public void setTamaName(String tamaName) {
		this.tamaName = tamaName;
	}

}
