package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.PlayerSave;
import TileMap.Background;

public class Gameover extends GameState {

	private Background bg;
	private int currentChoice= 0;
	private String[] options ={ "Continue","Shop", "Quit", "Alt F4"};
	private Color titleColor;
	private Font font;
	public int counter=0;
	private Font titleFont;
	
   public Gameover(GameStateManager gsm){
	   this.gsm = gsm;
	   try{
		   bg = new Background("/Backgrounds/GOverAnimated.gif",1);
		//   bg.setVector(-0.1, 0);
		   titleColor = new Color(0, 0, 0);
		   titleFont = new Font("Century Gothic", Font.PLAIN,20);
		   font= new Font("Arial", Font.PLAIN,12);
		   PlayerSave.setLives(PlayerSave.getLives()-1);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }
   public void init(){
	   
   }
   public void update(){
	   bg.update();
	   counter++;
   }
   
   public void draw(Graphics2D g){
	  bg.draw(g); 
	  g.setColor(titleColor);
	  g.setFont(titleFont);
	  g.setFont(font);
	  for(int i= 0; i < options.length;i++){
		  if(i == currentChoice){
			  g.setColor(Color.RED);
		  }else{
			  g.setColor(Color.BLACK);
		  }
		  g.drawString(options[i], 20,100+i *15);
	  }
   }
   private void select(){
	   if(currentChoice == 0&& PlayerSave.getLives()>=0){
		   gsm.setState(GameStateManager.LEVEL1STATE);
		   
	   }
	   if(currentChoice ==1){
		  gsm.setState(GameStateManager.SHOPSTATE);
	   }
	   if(currentChoice ==2){
		  gsm.setState(GameStateManager.MENUSTATE);
	   }
	   if(currentChoice ==3){
		  System.exit(0);
	   }
   }
   public void keyPressed(int k){
	   if(k == KeyEvent.VK_ENTER){
		   select();
	   }
	   if(k == KeyEvent.VK_UP){
		   currentChoice--;
		   if(currentChoice== -1){
			   currentChoice= options.length-1;
		   }
	   }
	   if(k == KeyEvent.VK_DOWN){
		   currentChoice++;
		   if(currentChoice == options.length){
			   currentChoice = 0;
		   }
	   }
   }
   public void keyReleased(int k){
	   
   }
}
