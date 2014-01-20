package tamaSystem;

import java.awt.Color;
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
	public static int tamaCurrentDepression;
	private int depressionValue = 20;
	private int mouseHappiness = 50;
	private int mouseHappinessSinker = 10;
	private Random intGenerator = new Random();

	//Depression, Thread sleep timer.
	protected final int depressionBuilderTimeValue = 1000;
	
	private boolean deathByDepression = false;

	public DepressionEngine(){
	}

	//the loop
	@Override
	public void run() {
		tamaCurrentDepression = 10000;

		while(TamaGUIStart.ALL_THREADS_RUNNING == true){
			depressionBarMethod();
			depressionWarnings();
			TamaRandomGoodMood();
			TamaRandomDepression();
			depressionBuilder(depressionBuilderTimeValue);
			deathByDepression();
		}
	}

	//Random generate depression for Tama for lv 3
	private void TamaRandomDepression(){
		if(TamaGUI.gameLevel == 3){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				happinessLevel1();
				TamaGUI.textArea.setText("Your Tama got depressed"
						+ "\nLost 500 Happiness");
			}
		}
	}

	//Random generate, good mood for tama
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

	//HungerBar method/Checker updater. 
	private void depressionBarMethod(){
		TamaGUI.depressionBar.setString("Happiness: " + Integer.toString(tamaCurrentDepression));
		if (tamaCurrentDepression >= 9000){
			TamaGUI.depressionBar.setValue(100);
			TamaGUI.depressionBar.setForeground(new Color(0, 128, 0));
		}
		else if (tamaCurrentDepression >= 7500){
			TamaGUI.depressionBar.setForeground(new Color(0, 128, 0));
			TamaGUI.depressionBar.setValue(85);
		}
		else if (tamaCurrentDepression >= 5000){
			TamaGUI.depressionBar.setForeground(new Color(0, 128, 0));
			TamaGUI.depressionBar.setValue(65);
		}
		else if(tamaCurrentDepression >= 4000){
			TamaGUI.depressionBar.setForeground(Color.ORANGE);
			TamaGUI.depressionBar.setValue(50);
		}
		else if(tamaCurrentDepression >= 3500){
			TamaGUI.depressionBar.setForeground(Color.ORANGE);
			TamaGUI.depressionBar.setValue(35);
		}
		else if(tamaCurrentDepression >= 3000){
			TamaGUI.depressionBar.setForeground(Color.ORANGE);
			TamaGUI.depressionBar.setValue(25);
		}
		else if(tamaCurrentDepression >= 2500){
			TamaGUI.depressionBar.setForeground(Color.PINK);
			TamaGUI.depressionBar.setValue(20);
		}
		else if(tamaCurrentDepression >= 2000){
			TamaGUI.depressionBar.setForeground(Color.RED);
			TamaGUI.depressionBar.setValue(10);
		}
		else if(tamaCurrentDepression >= 1500){
			TamaGUI.depressionBar.setForeground(Color.PINK);
			TamaGUI.depressionBar.setValue(10);
		}
		else if(tamaCurrentDepression >= 1000){
			TamaGUI.depressionBar.setForeground(Color.RED);
			TamaGUI.depressionBar.setValue(10);
		}
	}

	//When Tama reach 0 depression
	//
	// CHANGE SYSTEM
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

	public boolean isDeathByDepression() {
		return deathByDepression;
	}

}