package tamaSystem;

import java.text.DecimalFormat;

import tamaGUI.TamaGUIEnd;
import tamaGUI.TamaGUIStart;

/** SCORE ENGINE CLASS
 * This class will do:
 *	1. count score
 * 	2. Show score
 * 
 */
public class ScoreEngine implements Runnable {
	public static int theScore;
	private double scoreCounter;
	private double hAlive;
	private double dAlive;
	private double mAlive;
	private double yAlive;

	private DecimalFormat df = new DecimalFormat("0.00");  

	public ScoreEngine(){
	}

	public void totalAliveTime(){
		hAlive = scoreCounter/6;
		dAlive = scoreCounter/144;
		mAlive = scoreCounter/4320;
		yAlive = scoreCounter/51840;

		TamaGUIEnd.textEndInfo.append("Total Alive Time, in Tama Time: \n");
		TamaGUIEnd.textEndInfo.append("Houres Alive: " + df.format(hAlive) + "\n");
		TamaGUIEnd.textEndInfo.append("Days Alive: " + df.format(dAlive) + "\n");
		TamaGUIEnd.textEndInfo.append("Month Alive: " + df.format(mAlive) + "\n");
		TamaGUIEnd.textEndInfo.append("Years Alive: " + df.format(yAlive) + "\n");
		TamaGUIEnd.textEndInfo.append("\nyour score is: " + (scoreCounter) +"\n");
	}

	@Override
	public void run() {
		scoreCounter = 0;
		int z = 1;
		while(z == 1){
			if (TamaGUIStart.ALL_THREADS_RUNNING == false){
				totalAliveTime();
				z = 0;
				break;
			}
			else if (TamaGUIStart.ALL_THREADS_RUNNING == true){
				try {
					this.scoreCounter += 10;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}






