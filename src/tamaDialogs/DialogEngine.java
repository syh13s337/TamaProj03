package tamaDialogs;

import java.util.ArrayList;
import java.util.Random;





import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIStart;

/** TAMA DIALOGS CLASS
 * This class will generate dialogs for Tama to randomly say.
 * It does it by, adding strings to a ArrayList. 
 * Using a random int generator to give .get(randomInt) from list for dialogs.
 * And its looped and in the loop it got a random thread sleeper time, 
 * so the dialogs appear on different time.
 *  
 *  Future plans:
 *  Make more random dialogs,
 *  Connect Dialogs with hunger/depression/money sinks/gain, Like, gain money cuz your Tama found Treasure.
 *  Make a Emo Version for level 3, when the depression hits below 50%
 *  
 *  
 */

public class DialogEngine implements Runnable {

	//Set Max millisec on the random timer.
	private final int setRandomTimerGenerator = 50000;

	private String dialogString = "Let the Adventure Begin!";
	private ArrayList<String> dialogList0 = new ArrayList<String>();
	private ArrayList<String> dialogList1 = new ArrayList<String>();
	private ArrayList<String> dialogList2 = new ArrayList<String>();
	private ArrayList<String> dialogList3 = new ArrayList<String>();


	private Random intGenerator = new Random();
	private int randomNumber;
	private int randomTimerCount;

	//sets Dialog level
	private int dialogLevel;

	public DialogEngine(){	
	}

	private void dialogLister(){
		dialogListLevel0();
		dialogListLevel1();
		dialogListLevel2();
		dialogListLevel3();	
	}

	// Just randome smilies, NOT in use at the moment.
	private void dialogListLevel0(){
		dialogList0.add("/(-.-)...");
		dialogList0.add("/('_')...");
		dialogList0.add("( '_')...");
		dialogList0.add("('_' )...");
		dialogList0.add("('-'*)...");
		dialogList0.add("(*'-')...");
		dialogList0.add("( -.-)...");
		dialogList0.add("(-.- )...");
	}

	// dialogs + add funktion on Level 1 (Easy) Baby Mode
	private void dialogListLevel1(){
		dialogList1.add("grrrlll.......");
		dialogList1.add("hu?!?!?.......");
		dialogList1.add("WHAAA WHAAA!..");
		dialogList1.add("Hehehe........");
		dialogList1.add("phuuu.........");
		dialogList1.add("buuh!.........");
		dialogList1.add("Bah Bah.......");
		dialogList1.add("Gah Gah.......");
		dialogList1.add("bluh Gah......");
		dialogList1.add("pfff..........");
		dialogList1.add("hmmmf.........");
		dialogList1.add("Daah..........");
	}

	// dialogs + add funktion on Level 2 (Normal) Kid Mode
	private void dialogListLevel2(){
		dialogList2.add("Haaj");
		dialogList2.add("I should cal a friend");
		dialogList2.add("I wana Make a song");
		dialogList2.add("So cold in here");
		dialogList2.add("What should i do?");
		dialogList2.add("I read on the internet that the world is round");
		dialogList2.add("phuuuue");
		dialogList2.add("Woow! so much rain outisde");
		dialogList2.add("I love Red");
		dialogList2.add("I should learn Karate");
		dialogList2.add("Can you tel me a story?");
		dialogList2.add("");
		dialogList2.add("");
	}

	// dialogs + add funktion on Level 3 (Hard) Young Adult Mode
	//Emo version if depression is low enough
	private void dialogListLevel3(){
		dialogList3.add("YOLO");
		dialogList3.add("I HATE SCHOOL");
		dialogList3.add("I wana buy new cloth ");
		dialogList3.add("I look so ugly");
		dialogList3.add("I got girl friend! ");
		dialogList3.add("I wounder how other countrys are");
		dialogList3.add("Sing* Sing* Sing* Sing*");
		dialogList3.add("Why is the internet so slooooow!");
		dialogList3.add("I love my new EyePhone!");
		dialogList3.add("How can some people love TwatLight?");
	}

	// sets dialog level.
	public void setDialogLevel(int x){
		this.dialogLevel = dialogLevel + x;		
	}

	//timer and dialogs
	public void timerAndDialogs() throws InterruptedException{

		TamaGUI.textArea.setText(dialogString + "\n");
		Thread.sleep(3000);

		while(TamaGUIStart.ALL_THREADS_RUNNING == true){
			randomTimerCount = intGenerator.nextInt(setRandomTimerGenerator);

			if (dialogLevel == 1){
				TamaGUI.textArea.setText(getRandomeDialogsLevel1() + "\n");
			}
			
			else if (dialogLevel == 2){
				TamaGUI.textArea.setText(getRandomeDialogsLevel2() + "\n");

			}				
			else if (dialogLevel == 3){
				TamaGUI.textArea.setText(getRandomDialogsLevel3() + "\n");
			}
			
			Thread.sleep(randomTimerCount);
		}
	}

	//random dialogs lv 1
	private String getRandomeDialogsLevel1(){
		String getRandomeDialogsLevel1;
		randomNumber = intGenerator.nextInt(dialogList1.size());
		getRandomeDialogsLevel1 = "..." + dialogList1.get(randomNumber);
		return getRandomeDialogsLevel1;
	}

	//randome dialogs lv 2
	private String getRandomeDialogsLevel2(){
		String getRandomeDialogsLevel2;
		randomNumber = intGenerator.nextInt(dialogList2.size());
		getRandomeDialogsLevel2 = "..." + dialogList2.get(randomNumber);
		return getRandomeDialogsLevel2;
	}

	//randome dialogs lv 3
	private String getRandomDialogsLevel3(){
		String getRandomeDialogsLevel3;
		randomNumber = intGenerator.nextInt(dialogList3.size());
		getRandomeDialogsLevel3 = "..." + dialogList3.get(randomNumber);
		return getRandomeDialogsLevel3;
	}

	@Override
	public void run() {
		try {
			dialogLister();
			timerAndDialogs();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}