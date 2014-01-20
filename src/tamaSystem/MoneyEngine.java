package tamaSystem;

import java.awt.Color;
import java.util.Random;

import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIStart;

/** MONEY ENGINE
 * 	This class is where the money is at!
 * 	It will update the money bar, handle gain/decrease money.
 * 	And of the HardCore mode, the have a chance to steal money. 	
 *
 */


public class MoneyEngine implements Runnable{
	//Current Money
	//Note: In case future upgrade/change the variables is here.
	public int currentMoney = 10000;
	private Random intGenerator = new Random();


	//the Loop
	@Override
	public void run() {
		TamaGUI.moneyBar.setForeground(Color.BLUE);
		
		while(TamaGUIStart.ALL_THREADS_RUNNING == true){
			if(currentMoney <= 0){
				currentMoney = 0;
			}
			TamaGUI.moneyBar.setString(Integer.toString(currentMoney) + " Pesoh");
			moneyBarMethod();
			TamaFoundMoney();
			TamaStealMoney();
			moneyBarUpdaterTimer(1000);

		}
	}

	//Random generate, the Tama going to steal money.
	private void TamaStealMoney(){
		if(TamaGUI.gameLevel == 3){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				moneyItem1();
				TamaGUI.textArea.setText("Your Tama stole money from you"
						+ "\nLost 500 Pesoh");
			}
		}
	}

	//Random generate, Tama finds money and gives it to player.
	private void TamaFoundMoney(){
		if(TamaGUI.gameLevel >= 2){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				moneyGain2();
				TamaGUI.textArea.setText("Your Tama found money and gave it to you"
						+ "\nGained 300 Pesoh");
			}
		}
	}

	//sleep timer
	private void moneyBarUpdaterTimer(int x){
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//MoneyBar
	private void moneyBarMethod() {
		if(currentMoney <= 0){
			TamaGUI.moneyBar.setValue(0);
		}
		else if(currentMoney <= 1000){
			TamaGUI.moneyBar.setValue(10);
		}

		else if(currentMoney <= 2500){
			TamaGUI.moneyBar.setValue(25);
		}

		else if(currentMoney <= 3500){
			TamaGUI.moneyBar.setValue(35);
		}

		else if(currentMoney <= 5000){
			TamaGUI.moneyBar.setValue(50);
		}
		else if (currentMoney <= 7500){
			TamaGUI.moneyBar.setValue(75);
		}
		else if (currentMoney <= 9000){
			TamaGUI.moneyBar.setValue(90);
		}
		else if (currentMoney <= 10000){
			TamaGUI.moneyBar.setValue(10000);
		}
	}

	//Money sinker/gainer item
	public void moneyItem1(){
		currentMoney -= 500;
	}
	public void moneyItem2(){
		currentMoney -= 2000;
	}
	public void moneyItem3(){
		currentMoney -= 3500;
	}
	public void moneyGain1(){
		currentMoney += 10;
	}
	public void moneyGain2(){
		currentMoney += 300;
	}


}
