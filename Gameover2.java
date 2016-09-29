package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class Gameover2 extends GameState {

	private Background bg;
	private int currentChoice= 0;
	private String[] options ={ "Quit", "Alt F4"};
	private Color titleColor;
	private Font font;
	private Font titleFont;
	
   public Gameover2(GameStateManager gsm){
	   this.gsm = gsm;
	   try{
		   bg = new Background("/Backgrounds/GOver.gif",1);
		//   bg.setVector(-0.1, 0);
		   titleColor = new Color(0, 0, 0);
		   titleFont = new Font("Century Gothic", Font.PLAIN,20);
		   font= new Font("Arial", Font.PLAIN,12);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }
   public void init(){
	   
   }
   public void update(){
	   bg.update();
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
	   if(currentChoice == 1){
		   gsm.setState(GameStateManager.MENUSTATE); 
	   }
	   if(currentChoice ==2){
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