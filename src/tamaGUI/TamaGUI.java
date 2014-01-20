package tamaGUI;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import tamaDialogs.DialogEngine;
import tamaDialogs.TalkingToTamaEngine;
import tamaSystem.DepressionEngine;
import tamaSystem.GameEngine;
import tamaSystem.HungerEngine;
import tamaSystem.MoneyEngine;
import tamaSystem.ScoreEngine;
import tamaSystem.WinAndEndEngine;

/** TAMA GUI, the game GUI
 * This is the Main game GUI Class,
 * It is build so that it takes arg, from Start GUI
 * and build the button/frame name from it. Aka different levels.
 * 
 * 
 * 
 *
 */

public class TamaGUI extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	//STATIC variables, so everyone can touch them for upgrade/graphic purpose.
	public static JFrame GUIFrame;
	public static JLabel label;
	public static TextArea textArea;
	public static JProgressBar hungerBar;
	public static JProgressBar depressionBar;
	public static JProgressBar moneyBar;
	public static int gameLevel;

	private TextField textInPut;
	private ArrayList <String> buttonNames = new ArrayList<String>();
	private ArrayList <String> tooltips = new ArrayList<String>();
	private ArrayList <String> infoText = new ArrayList<String>();

	private HungerEngine he;
	private DepressionEngine de;
	private DialogEngine di = new DialogEngine();
	private TalkingToTamaEngine tt = new TalkingToTamaEngine();
	private MoneyEngine mo = new MoneyEngine();
	private ScoreEngine se = new ScoreEngine();
	private TamaGUIFace tgf = new TamaGUIFace();

	public TamaGUI(int lvNr, String frameTitle, String tamaName) {
		GameEngine ge = new GameEngine(tamaName);		
		TamaGUI.gameLevel = lvNr;
		buttonNames(lvNr);
		initialize(frameTitle, tamaName);
		di.setDialogLevel(lvNr);
		tt.setDialogLevel(lvNr);

		Thread diaEngine = new Thread(di, "DialogThread");
		Thread monEngine = new Thread(mo, "MoneyThead");
		Thread scoEngine = new Thread(se, "ScoreThread");
		Thread faceEngine = new Thread(tgf, "FaceThread");
		Thread gEngine = new Thread(ge, "GameEngine");

		diaEngine.start();
		monEngine.start();
		scoEngine.start();
		faceEngine.start();
		gEngine.start();
	}

	private void initialize(String frameTitle, String TamaName) {
		he = new HungerEngine();
		de = new DepressionEngine();
		
		GUIFrame = new JFrame();
		GUIFrame.getContentPane().setBackground(Color.WHITE);
		GUIFrame.setResizable(false);
		GUIFrame.setTitle(TamaName + frameTitle);
		GUIFrame.setBounds(100, 100, 669, 366);
		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUIFrame.getContentPane().setLayout(null);

		textArea = new TextArea();
		textArea.setBounds(349, 46, 304, 244);
		GUIFrame.getContentPane().add(textArea);

		//Text field for talking to Tama
		textInPut = new TextField();
		textInPut.setText("Wright something to your <Tama>");
		textInPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tt.dontHaveTimeATMStr();
				textInPut.setText("Wright something to your <Tama>");
			}
		});
		textInPut.setBounds(15, 296, 643, 27);
		GUIFrame.getContentPane().add(textInPut);

		//bar, hunger
		hungerBar = new JProgressBar();
		hungerBar.setStringPainted(true);
		hungerBar.setToolTipText("Hunger Bar");
		hungerBar.setBounds(507, 16, 146, 14);
		GUIFrame.getContentPane().add(hungerBar);

		//bar, Depression
		depressionBar = new JProgressBar ();
		depressionBar.setStringPainted(true);
		depressionBar.setToolTipText("Happiness Bar");
		depressionBar.setBounds(349, 16, 146, 14);
		GUIFrame.getContentPane().add(depressionBar);

		//bar, money
		moneyBar = new JProgressBar();
		moneyBar.setStringPainted(true);
		moneyBar.setToolTipText("Your Money");
		moneyBar.setBounds(191, 16, 146, 14);
		GUIFrame.getContentPane().add(moneyBar);

		//for graphic
		label = new JLabel();
		label.setBounds(142, 46, 201, 244);
		GUIFrame.add(label);	

		//play level 1 button
		final JButton btnPlay1 = new JButton(buttonNames.get(0));
		btnPlay1.setToolTipText(tooltips.get(0));
		btnPlay1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (gameLevel == 1){
					de.happinessGainedLv2();
					textArea.setText(infoText.get(0));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
					}
					textArea.setText(infoText.get(1));
				}
				else if (gameLevel >= 2){
					he.foodDecreases1();
					de.happinessGainedLv2();
					textArea.setText(infoText.get(0));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
					}
					textArea.setText(infoText.get(1));
				}
			}
		});
		btnPlay1.setBounds(15, 47, 115, 29);
		GUIFrame.getContentPane().add(btnPlay1);

		final JButton btnPlay2 = new JButton(buttonNames.get(1));
		btnPlay2.setToolTipText(tooltips.get(1));
		btnPlay2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameLevel == 1){
					de.happinessGainedLv2();
					textArea.setText(infoText.get(2));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
					}
					textArea.setText(infoText.get(3));
				}						

				else if (gameLevel >= 2){
					if (mo.currentMoney >= 3500){
						he.foodDecreases3();
						mo.moneyItem3();
						de.happinessGainedLv2();
						textArea.setText(infoText.get(2));
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e1) {
						}
						textArea.setText(infoText.get(3));
					}						
					else if(mo.currentMoney <= 3500) {
						textArea.setText("You don't have money for it!\n");
					}
				}
			}
		});
		btnPlay2.setBounds(15, 87, 115, 29);
		GUIFrame.getContentPane().add(btnPlay2);

		final JButton btnFood1 = new JButton(buttonNames.get(2));
		btnFood1.setToolTipText(tooltips.get(2));
		btnFood1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameLevel == 1){
					if (mo.currentMoney >= 500){
						he.foodItem1();
						mo.moneyItem1();
						de.happinessGainedLv1();
						textArea.setText(infoText.get(4));
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
						}
						textArea.setText(infoText.get(5));
					}						
					else if(mo.currentMoney <= 500) {
						textArea.setText("You don't have money for it!\n");
					}	
				}
				else if (gameLevel >= 2){
					if (mo.currentMoney >= 500){
						he.foodItem1();
						mo.moneyItem1();
						de.happinessLevel2();
						textArea.setText(infoText.get(4));
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
						}
						textArea.setText(infoText.get(5));
					}						
					else if(mo.currentMoney <= 500) {
						textArea.setText("You don't have money for it!\n");
					}					
				}
			}
		});
		btnFood1.setBounds(15, 172, 115, 29);
		GUIFrame.getContentPane().add(btnFood1);

		final JButton btnFood2 = new JButton(buttonNames.get(3));
		btnFood2.setToolTipText(tooltips.get(3));
		btnFood2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameLevel == 1){
					if (mo.currentMoney >= 2000){
						he.foodItem2();
						mo.moneyItem2();
						de.happinessGainedLv1();
						textArea.setText(infoText.get(4));
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
						}
						textArea.setText(infoText.get(6));
					}				
					else if(mo.currentMoney <= 2000) {
						textArea.setText("You don't have money for it!\n");
					}
				}
				else if (gameLevel >= 2){
					if (mo.currentMoney >= 2000){
						he.foodItem2();
						mo.moneyItem2();
						de.happinessGainedLv1();
						textArea.setText(infoText.get(4));
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
						}
						textArea.setText(infoText.get(6));
					}				
					else if(mo.currentMoney <= 2000) {
						textArea.setText("You don't have money for it!\n");
					}
				}
			}
		});
		btnFood2.setBounds(15, 213, 115, 29);
		GUIFrame.getContentPane().add(btnFood2);

		final JButton btnFood3 = new JButton(buttonNames.get(4));
		btnFood3.setToolTipText(tooltips.get(4));
		btnFood3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameLevel == 1){
					if (mo.currentMoney >= 3500){
						he.foodItem3();
						mo.moneyItem3();
						de.happinessLevel2();
						textArea.setText(infoText.get(4));
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
						}
						textArea.setText(infoText.get(7));
					}
					else if(mo.currentMoney <= 3500) {
						textArea.setText("You don't have money for it!\n");
					}
				}
				else if (gameLevel >= 2){
					if (mo.currentMoney >= 3500){
						he.foodItem3();
						mo.moneyItem3();
						de.happinessLevel2();
						textArea.setText(infoText.get(4));
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
						}
						textArea.setText(infoText.get(7));
					}
					else if(mo.currentMoney <= 3500) {
						textArea.setText("You don't have money for it!\n");
					}
				}
			}
		});
		btnFood3.setBounds(15, 255, 115, 29);
		GUIFrame.getContentPane().add(btnFood3);
		GUIFrame.addMouseListener(this);
	}

	//method that adds strings in to arraylist.
	private void buttonNames(int gameLevel){
		if (gameLevel == 1){
			buttonNames.add("Tickle ");
			buttonNames.add("Give hug");
			buttonNames.add("Candy");
			buttonNames.add("Ice Cream");
			buttonNames.add("Baby food");
			tooltips.add("Tickle the litle Tama, gain happiness");
			tooltips.add("Give Tama a hug, gain happiness");
			tooltips.add("Give Tama candy, gain happiness. Cost 250 Pesoh");
			tooltips.add("Give Tama Ice Cream, gain happiness. Cost 2000 Pesoh");
			tooltips.add("Give Tama baby food, Medium tummy Filler. Cost 3500 Pesoh");
			infoText.add("...hihihi \n ");
			infoText.add("Tama Smiles at you");
			infoText.add("Tama embraces your hug");
			infoText.add("You feel worm and cosy inside.");
			infoText.add("...OmNomNonNOm\n");
			infoText.add("Looks like Tama likes candy");
			infoText.add("Looks like Tama likes ice cream");
			infoText.add("Looks like Tama likes baby food");
		}
		else if (gameLevel == 2){
			buttonNames.add("Play a game");
			buttonNames.add("Watch movie");
			buttonNames.add("Snacks");
			buttonNames.add("Home Meal");
			buttonNames.add("Fancy Restaurant");
			tooltips.add("Play a easy game to make your Tama happy");
			tooltips.add("Go for a movie? Will make Tama extremely happy. Takes 1 Tama hour and cost 3500");
			tooltips.add("Snacks. Small tummy filler. The fat can make the Tama a bit depressed. Cost 500 Pesoh");
			tooltips.add("Home Cooked meal. Medium tummy filler. Cost 2000 Pesos");
			tooltips.add("Eat at a Fancy Resturant. High tummy filler. Cost 3500 Pesos");
			infoText.add("Playing a game \n ");
			infoText.add("...Thannks for playing with me."
					+ "\n...I am a bit tired now \n");
			infoText.add("Out Watching Movie" + "\n...Back in 1 Hour");
			infoText.add("...That was an awesome movie!");
			infoText.add("...OmNomNonNOm\n");
			infoText.add("...I LOVE SNACKS! \n");
			infoText.add("...Home cooked food is the best! ");
			infoText.add("...What a Fancy Restaurant! ");
		}
		else if (gameLevel == 3){
			buttonNames.add("Give money");
			buttonNames.add("Watch movie");
			buttonNames.add("Snacks");
			buttonNames.add("Home Meal");
			buttonNames.add("Fancy Restaurant");
			tooltips.add("Give money to your Tama, gain happiness");
			tooltips.add("Go for a movie? Will make Tama extremely happy. Takes 1 Tama hour and cost 3500");
			tooltips.add("Snacks. Small tummy filler. The fat can make the Tama a bit depressed. Cost 500 Pesoh");
			tooltips.add("Home Cooked meal. Medium tummy filler. Cost 2000 Pesos");
			tooltips.add("Eat at a Fancy Resturant. High tummy filler. Cost 3500 Pesos");
			infoText.add("You hand out money to your Tama.");
			infoText.add("...Thanks for the money" );
			infoText.add("Out Watching Movie" + "\n...Back in 1 Hour");
			infoText.add("...That was an awesome movie!");
			infoText.add("...OmNomNonNOm\n");
			infoText.add("...I LOVE SNACKS! \n");
			infoText.add("...Home cooked food is the best! ");
			infoText.add("...What a Fancy Restaurant! ");
		}
	}

	//Tama gets happiness by clicking in frame
	@Override
	public void mouseClicked(MouseEvent arg0) {
		de.mouseHappiness();
	}
	//player gain money while entering frame
	@Override
	public void mouseEntered(MouseEvent arg0) {
		mo.moneyGain1();
	}
	//tama get depressed when mouse exits the Frame
	@Override
	public void mouseExited(MouseEvent arg0) {
		de.mouseHappinessSinker();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
