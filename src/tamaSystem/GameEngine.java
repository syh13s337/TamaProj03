package tamaSystem;

import tamaDialogs.DialogEngine;
import tamaDialogs.TalkingToTamaEngine;
import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIFace;
import tamaGUI.TamaGUILogIn;
import tamaGUI.TamaGUIStart;

/**THE GAME ENGINE
 * 
 * Here is where the game loop and updater is.
 * 
 * 
 *
 */

public class GameEngine implements Runnable{

	private WinAndEndEngine we = new WinAndEndEngine();
	private DepressionEngine de = new DepressionEngine();
	private HungerEngine he = new HungerEngine();
	private TamaGUI tg;
	private TamaGUIFace tgf;
	private MoneyEngine mo = new MoneyEngine();
	private DialogEngine di = new DialogEngine();
	private TalkingToTamaEngine tt = new TalkingToTamaEngine();
	private ScoreEngine se = new ScoreEngine();
	private TamaGUILogIn tgli = new TamaGUILogIn();
	private GameEngine ge;
	private UserEngine ue;
	//	private MySQLEngine mysql = new MySQLEngine();

	private String tamaName = "";
	private int gameLevel;

	private void initiater(){
		Thread depEngine = new Thread(de, "DepressionThread");
		Thread winEngine = new Thread(we, "WinThread");
		Thread hunEngine = new Thread(he, "FoodThread");
		Thread monEngine = new Thread(mo, "MoneyThead");
		Thread diaEngine = new Thread(di, "DialogThread");
		Thread scoEngine = new Thread(se, "ScoreThread");
		Thread tgfEngine = new Thread(tgf, "TgfThread");

		diaEngine.start();
		scoEngine.start();
		depEngine.start();
		winEngine.start();
		hunEngine.start();
		monEngine.start();
		tgfEngine.start();

	}

	public GameEngine(){

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
	
	public void StartLogIn(){
		TamaGUILogIn login = new TamaGUILogIn();
		login.loginStarter(ge, ue);
	}

	public void startGameGUI(int gameLevel, String frameTitle, String tamaName){
		this.tamaName = tamaName;
		this.gameLevel = gameLevel;
		we.setGameLevel(gameLevel);
		de.setGameLevel(gameLevel);
		he.setGameLevel(gameLevel);
		mo.setGameLevel(gameLevel);

		tg = new TamaGUI(gameLevel, frameTitle, tamaName, he, mo, di, tt, de);
		tgf = new TamaGUIFace(tg, gameLevel, de, he);
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

				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


