package GameState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import TileMap.Background;
public class Optionstate extends GameState {
	private Background bg;
	private int currentChoice= 0;
	private String[] options ={ "Musik an", "Musik aus","Zurück"};
	private Color titleColor;
	private Font font;
	private Font titleFont;
   public Optionstate(GameStateManager gsm){
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
	  g.drawString("Optionen", 100, 70);
	  g.setFont(font);
	  for(int i= 0; i < options.length;i++){
		  if(i == currentChoice){
			  g.setColor(Color.RED);
		  }else{
			  g.setColor(Color.BLACK);
		  }
		  g.drawString(options[i], 120,100+i *15);
	  }
   }
   private void select(){
	   if(currentChoice == 0){
		   gsm.settrue();
	   }
	   if(currentChoice ==1){
		 gsm.setfalse();
	   }
	   if(currentChoice ==2){      
		   gsm.setState(0);	   
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

