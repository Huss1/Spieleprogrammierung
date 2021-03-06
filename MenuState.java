package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class MenuState extends GameState {
	private Background bg;
	private int currentChoice= 0;
	private String[] options ={ "Start", "Steuerung","Optionen","Alt F4",};
	private Color titleColor;
	private Font font;
	private Font titleFont;
   public MenuState(GameStateManager gsm){
	   this.gsm = gsm;
	   try{
		   bg = new Background("/Backgrounds/menubg.gif",1);
		   bg.setVector(-0.1, 0);
		   titleColor = new Color(128, 0, 0);
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
	  g.drawString("1337 Game", 80, 70);
	  g.setFont(font);
	  for(int i= 0; i < options.length;i++){
		  if(i == currentChoice){
			  g.setColor(Color.BLACK);
		  }else{
			  g.setColor(Color.WHITE);
		  }
		  g.drawString(options[i], 155,140+i *15);
	  }
   }
   private void select(){
	   if(currentChoice == 0){
		   gsm.setState(GameStateManager.LEVEL1STATE);
	   }
	   if(currentChoice ==1){
		  gsm.setState(GameStateManager.HILFE);
	   }
	   if(currentChoice == 2){
		   gsm.setState(4);
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
