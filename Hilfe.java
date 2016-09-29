package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class Hilfe extends GameState {
     private Background bg;
     private String[] hilfsstring={"Shift gedrückt =>  Laufen"," ","A =>  Nach Links Bewegen"," ","D =>  Nach Rechts Bewegen"," ", "1 =>  Spiel Beenden"," "," Linke Pfeiltaste =>  Gleiten"," ",
    		 "Rechte Pfeiltaste =>  Feuerball"," ", "Pfeiltaste =>  Unten Kratzen"," ", " z => Zurück"};
     private Color titleColor;
     private Font font;
     private Font titleFont;
     
     public Hilfe(GameStateManager gsm){
     this.gsm=gsm;
     try{
    	 bg= new Background("/Backgrounds/menubg.gif",1);
    	
    	 titleColor= new Color(0,0,0);
    	 titleFont = new Font("Arial", Font.PLAIN,28);
    	 font = new Font("Arial", Font.PLAIN,12);
    	 
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
    	 g.drawString("Hilfe", 130, 50);
    	 g.setFont(font);
   	  for(int i= 0; i < hilfsstring.length;i++){
		 
			  g.setColor(Color.BLACK);

		  g.drawString(hilfsstring[i], 60,80+i *10);
	  }
   }

   public void keyPressed(int k){
		if(k == KeyEvent.VK_Z)gsm.setState(0);;
		


   }
   public void keyReleased(int k){
	   
   }
}
