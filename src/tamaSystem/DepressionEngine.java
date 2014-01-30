package tamaSystem;

import java.util.Random;
import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIStart;

/** DEPRESSION ENGINE, 
 * The class that handles depression.
 * Looped a ticker and checkers.
 * Updater for the DepressionBar
 *
 * 
 *
 */
public class DepressionEngine implements Runnable {
	//
	//Note: Incase future upgrade/change the varible is here.
	//
	private int tamaCurrentDepression = 10000;
	private int depressionValue = 20;
	private int mouseHappiness = 20;
	private int mouseHappinessSinker = 20;
	private Random intGenerator = new Random();

	//Depression, Thread sleep timer.
	protected final int depressionBuilderTimeValue = 1000;
	private boolean deathByDepression = false;

	private int gameLevel;
	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}
	
	
	
	public DepressionEngine(){
	}

	//the loop
	@Override
	public void run() {
		while(TamaGUIStart.ALL_THREADS_RUNNING == true){
			depressionWarnings();
			TamaRandomGoodMood();
			TamaRandomDepression();
			depressionBuilder(depressionBuilderTimeValue);
			deathByDepression();
		}
	}

	//Random generate depression for Tama for lv 3
	private void TamaRandomDepression(){
		if(gameLevel == 3){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				happinessLevel1();
				TamaGUI.textArea.setText("Your Tama got depressed"
						+ "\nLost 500 Happiness");
			}
		}
	}

	//Random generate, good mood for Tama
	private void TamaRandomGoodMood(){
		int rndNr = intGenerator.nextInt(30);
		if (rndNr == 5){
			happinessGainedLv0();
			TamaGUI.textArea.setText("Your Tama is in a good mood" 
					+ "\nGained 600 Happiness");
		}
	}

	//Thread Sleeper engine, 
	private void depressionBuilder(int x){
		tamaCurrentDepression -= depressionValue;
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//When Tama reach 0 depression
	// CHANGE SYSTEM
	//
	private void deathByDepression() {
		if (tamaCurrentDepression <= 0){
			deathByDepression = true;
		}
	}

	//warning message if depression reach a low point
	private void depressionWarnings(){
		if (tamaCurrentDepression <= 1000){
			TamaGUI.textArea.setText("...I am so depressed");
		}
	}

	//happiness gain/sink
	public void happinessLevel1(){
		tamaCurrentDepression += -500;
	}
	public void happinessLevel2(){
		tamaCurrentDepression += -1000;
	}
	public void happinessGainedLv0(){
		tamaCurrentDepression += 600;
	}
	public void happinessGainedLv1(){
		tamaCurrentDepression += 1500;
	}
	public void happinessGainedLv2(){
		tamaCurrentDepression += 3000;
	}

	//Tama gain/sink happy when muse is used.
	public void mouseHappiness(){
		tamaCurrentDepression += mouseHappiness;
	}
	public void mouseHappinessSinker(){
		tamaCurrentDepression -= mouseHappinessSinker;
	}

	//Getters and setters
	public boolean isDeathByDepression() {
		return deathByDepression;
	}
	public int getTamaCurrentDepression() {
		return tamaCurrentDepression;
	}
	public void setTamaCurrentDepression(int tamaCurrentDepression) {
		this.tamaCurrentDepression = tamaCurrentDepression;
	}

}