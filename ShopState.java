package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import Entity.PlayerSave;
import TileMap.Background;

public class ShopState extends GameState {
	
	private Background bg;
	private Background bgActive;
	private BufferedImage active;
	private BufferedImage textLine;
	
	private AudioPlayer buy = new AudioPlayer("/SFX/buy.mp3");
	private AudioPlayer nobuy = new AudioPlayer("/SFX/nobuy.mp3");
	
	private int activeX = 96;
	private int activeY = 24;
	
	private static final int fontXStart = 292;
	private int fontX = fontXStart;
			
	private String[] options = {"++ 1 Life [" + coinsLife + " Coins]"
								,"++ 1 Scratch-Damage [" + coinsScratchDamage + " Coins]"
								,"++ 1 Fireball-Damage [" + coinsFireballDamage + " Coins]"
								,"","","","","","","","","","","","",""};
	
	private int currentChoice = 0;
	
	private Font font;
	
	private static final int coinsLife = 8;
	private static final int coinsScratchDamage = 5;
	private static final int coinsFireballDamage = 8;
	
	
	 public ShopState(GameStateManager gsm){
		   this.gsm = gsm;
	
	
		try{
			   bg = new Background("/Backgrounds/shopbg1.gif",1);
			   bgActive = new Background("/Backgrounds/shopbg1Active.gif",1);
			//   bg.setVector(-0.1, 0);
			   font = new Font("Arial", Font.BOLD,10);
			   
			   active = ImageIO.read(getClass().getResourceAsStream("/Tilesets/shop1ActiveSegment.gif"));
			   textLine = ImageIO.read(getClass().getResourceAsStream("/Tilesets/textLine.gif"));
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	 }
     public void init(){
    	 
     }
	public void update() {
		fontX --;
		if(fontX < 96 - options[currentChoice].length() * 5) fontX = 292;
	}

	public void draw(Graphics2D g) {
		g.drawImage(textLine,102,152,null);
		g.drawString(options[currentChoice], fontX, 160);
		bg.draw(g);
		if(currentChoice == 15) bgActive.draw(g);
		g.drawString("-->", 285, 200);
		g.setFont(font);
		
		switch(currentChoice) {
		case 0:	activeX = 96;
				activeY = 24;
				break;
		case 1:	activeX = 136;
				activeY = 24;
				break;
		case 2:	activeX = 176;
				activeY = 24;
				break;
		case 3:	activeX = 216;
				activeY = 24;
				break;
		case 4:	activeX = 256;
				activeY = 24;
				break;
		case 5:	activeX = 96;
				activeY = 64;
				break;
		case 6:	activeX = 136;
				activeY = 64;
				break;
		case 7:	activeX = 176;
				activeY = 64;
				break;
		case 8:	activeX = 216;
				activeY = 64;
				break;
		case 9:	activeX = 256;
				activeY = 64;
				break;
		case 10:activeX = 96;
				activeY = 104;
				break;
		case 11:activeX = 136;
				activeY = 104;
				break;
		case 12:activeX = 176;
				activeY = 104;
				break;
		case 13:activeX = 216;
				activeY = 104;
				break;
		case 14:activeX = 256;
				activeY = 104;
				break;
		case 15:activeX = 500;
				activeY = 500;
				break;
		}
		g.drawImage(active, activeX, activeY,null);
	}

	private void select(){
		if(currentChoice==0) {
			if(PlayerSave.getCoins() >= coinsLife) {
				PlayerSave.setLives(PlayerSave.getLives() + 1);
				PlayerSave.setCoins(PlayerSave.getCoins()-coinsLife);
				buy.player();
			} else nobuy.player();
		}
		if(currentChoice==1) {
			if(PlayerSave.getCoins() >= coinsScratchDamage) {
				PlayerSave.setscratchdamage(PlayerSave.getscratchdamage() + 1);
				PlayerSave.setCoins(PlayerSave.getCoins()-coinsScratchDamage);
				buy.player();
			} else nobuy.player();
		}
		if(currentChoice==2) {
			if(PlayerSave.getCoins() >= coinsFireballDamage) {
				PlayerSave.setfireBallDamage(PlayerSave.getfireBallDamage()+ 1);
				PlayerSave.setCoins(PlayerSave.getCoins()-coinsFireballDamage);
				buy.player();
			} else nobuy.player();
		}
		if(currentChoice==15){
			gsm.setState(1);
		}
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			select();
			fontX = fontXStart;
		}
		if(k == KeyEvent.VK_UP){
			if(currentChoice == 15) currentChoice = 19;
			if(currentChoice <= 4){
				currentChoice += 15;
			}
			currentChoice -= 5;
			fontX = fontXStart;
		}
		if(k == KeyEvent.VK_DOWN){
			if(currentChoice == 14 || currentChoice == 15) currentChoice = 10;
			else if(currentChoice >= 10){
				currentChoice -= 15;
			}
			currentChoice += 5;
			fontX = fontXStart;
		}
		if(k == KeyEvent.VK_LEFT) {
			if(currentChoice == 0) currentChoice = 4;
			else if(currentChoice == 5) currentChoice = 9;
			else if(currentChoice == 10) currentChoice = 14;
			else if(currentChoice == 15) currentChoice = 15;
			else currentChoice--;
			fontX = fontXStart;
		}
		if(k == KeyEvent.VK_RIGHT) {
			if(currentChoice == 4) currentChoice = 0;
			else if(currentChoice == 9) currentChoice = 5;
			else if(currentChoice == 14) currentChoice = 10;
			else if(currentChoice == 15) currentChoice = 15;
			else currentChoice++;
			fontX = fontXStart;
		}
	}

	public void keyReleased(int k) {

		
	}
	
}
