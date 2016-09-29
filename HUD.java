package Entity;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;

public class HUD {
     private Player player;
     private PlayerSave ps;
     private BufferedImage image;
     private BufferedImage image2;
     
     private Font font;
     public HUD(Player p){
    	 player=p;
    	 ps= new PlayerSave();
    	 try{
    		 image =ImageIO.read(getClass().getResourceAsStream("/HUD/hud2.gif"));
    		 image2 =ImageIO.read(getClass().getResourceAsStream("/HUD/time.gif"));
    		 font = new Font("Arial", Font.PLAIN, 14);
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }

     }
	public void draw(Graphics2D g) {
		g.drawImage(image,  0, 10, null);
		g.setFont(font);
		g.drawString(player.getHealth() +"/"+ player.getMaxHealth(),30,25);
		g.drawString(player.getFire()/100 +"/"+ player.getMaxFire()/100,30,45);
		g.drawString(ps.getLives()+"", 30, 65);
		g.drawString(ps.getCoins()+"",30, 85);
		g.drawImage(image2,  235, 4, null);
		g.drawString("/ 1:00",282,15);
		g.setColor(Color.BLACK);
		g.drawString(player.getTimeToString(), 250, 15);

	}
}
