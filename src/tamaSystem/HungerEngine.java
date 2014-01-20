package tamaSystem;

import java.awt.Color;
import java.util.Random;

import tamaGUI.TamaGUIEnd;
import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIStart;

/**HUNGER ENGINE CLASS
 * This class works with energy/food/hunger
 * It starts with X amount of energy, and every sec it ticks away a bit of the energy.
 * This class also update the graphic on HungerBar and have methods for gain/sink functions.
 * 
 * 
 * 
 *
 */

public class HungerEngine implements Runnable  {

	//
	//Note: In case future upgrade/change the varible is here.
	//
	public static int tamaCurrentHunger = 10000;
	private int hungerValue = 30;
	//HungerBuilder, Thread sleep timer.
	private final int hungerBuilderTimeValue = 1000;
	//Food/energi Value:
	private final int foodItem1 = 500;
	private final int foodItem2 = 3000;
	private final int foodItem3 = 4500;
	private final int foodDeacreses1 = -500;
	private final int foodDeacreses2 = -1500;
	private final int foodDeacreses3 = -3000;
	private Random intGenerator = new Random();

	private boolean deathByHunger = false;

	public HungerEngine(){
	}

	//The loop
	@Override
	public void run() {
		while(TamaGUIStart.ALL_THREADS_RUNNING == true){
			hungerBarMethod();
			hungerWarnings();
			TamaEatsAtFriend();
			hungerBuilder(hungerBuilderTimeValue);
			dieByHunger();
		}
	}

	//Random generate, Tama eats at a friend
	private void TamaEatsAtFriend(){
		if(TamaGUI.gameLevel >= 2){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				foodItem1();
				TamaGUI.textArea.setText("Your Tama ate at a friends house"
						+ "\nGained 500 Energy");
			}
		}
	}

	//HungerBar method/Checker updater
	private void hungerBarMethod(){
		TamaGUI.hungerBar.setString("Hunger: " + Integer.toString(tamaCurrentHunger));
		if(tamaCurrentHunger >= 9000){
			TamaGUI.hungerBar.setValue(100);
			TamaGUI.hungerBar.setForeground(new Color(0, 128, 0));
		}		
		else if (tamaCurrentHunger >= 8500){
			TamaGUI.hungerBar.setValue(85);
			TamaGUI.hungerBar.setForeground(new Color(0, 128, 0));
		}	
		else if (tamaCurrentHunger >= 7500){
			TamaGUI.hungerBar.setValue(75);
			TamaGUI.hungerBar.setForeground(new Color(0, 128, 0));
		}
		else if (tamaCurrentHunger >= 5000){
			TamaGUI.hungerBar.setForeground(Color.ORANGE);
			TamaGUI.hungerBar.setValue(60);
		}		
		else if(tamaCurrentHunger >= 4000){
			TamaGUI.hungerBar.setForeground(Color.ORANGE);
			TamaGUI.hungerBar.setValue(50);
		}
		else if(tamaCurrentHunger >= 2500){
			TamaGUI.hungerBar.setForeground(Color.ORANGE);
			TamaGUI.hungerBar.setValue(30);
		}		
		else if(tamaCurrentHunger >= 2500){
			TamaGUI.hungerBar.setForeground(Color.ORANGE);
			TamaGUI.hungerBar.setValue(30);
		}		
		else if(tamaCurrentHunger >= 2000){
			TamaGUI.hungerBar.setForeground(Color.RED);
			TamaGUI.hungerBar.setValue(20);
		}	
		else if(tamaCurrentHunger >= 1500){
			TamaGUI.hungerBar.setForeground(Color.RED);
			TamaGUI.hungerBar.setValue(10);
		}
		else if(tamaCurrentHunger >= 1000){
			TamaGUI.hungerBar.setForeground(Color.PINK);
			TamaGUI.hungerBar.setValue(10);
		}		
		else if (tamaCurrentHunger >= 500){
			TamaGUI.hungerBar.setForeground(Color.RED);
			TamaGUI.hungerBar.setValue(10);
		}
		else if (tamaCurrentHunger >= 500){
			TamaGUI.hungerBar.setForeground(Color.PINK);
			TamaGUI.hungerBar.setValue(10);
		}

	}

	//Thread Sleeper engine, 
	private void hungerBuilder(int x){
		tamaCurrentHunger -= hungerValue;
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Get hunger level/meter
	public String getTamaCurrentHunger(){
		String getTamaCurrentHunger = null;

		if(tamaCurrentHunger >= 9000){
			getTamaCurrentHunger = "...My Tummy is full";
		}		
		else if (tamaCurrentHunger >= 8500){
			getTamaCurrentHunger = "...My Tummy is full";
		}	
		else if (tamaCurrentHunger >= 7500){
			getTamaCurrentHunger = "...I am good at the moment";
		}
		else if (tamaCurrentHunger >= 5000){
			getTamaCurrentHunger = "...I am good at the moment";
		}		
		else if(tamaCurrentHunger >= 4000){
			getTamaCurrentHunger = "...I am starting to get hungry";
		}
		else if(tamaCurrentHunger >= 2500){
			getTamaCurrentHunger = "...I am starting to get hungry";
		}		
		else if(tamaCurrentHunger >= 2500){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}		
		else if(tamaCurrentHunger >= 2000){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}	
		else if(tamaCurrentHunger >= 1500){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}		
		else if (tamaCurrentHunger >= 500){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}	
		return getTamaCurrentHunger;
	}

	//CHANGE SYSTEM
	//
	//Method for when the Tama dies by hunger
	private void dieByHunger(){
		if (tamaCurrentHunger <= 0){
			deathByHunger = true;
		}
	}

	//Gives hunger warnings for the user
	private void hungerWarnings(){
		if (tamaCurrentHunger <= 1000){
			TamaGUI.textArea.setText("...I am so hungry");
		}
	}

	//Food items
	public void foodItem1(){	
		tamaCurrentHunger += foodItem1;
	}
	public void foodItem2(){	
		tamaCurrentHunger += foodItem2;
	}
	public void foodItem3(){	
		tamaCurrentHunger += foodItem3;
	}

	//lower the energy
	public void foodDecreases1(){
		tamaCurrentHunger += foodDeacreses1;
	}
	public void foodDecreases2(){
		tamaCurrentHunger += foodDeacreses2;
	}
	public void foodDecreases3(){
		tamaCurrentHunger += foodDeacreses3;
	}
	
	public boolean isDeathByHunger() {
		return deathByHunger;
	}

}
