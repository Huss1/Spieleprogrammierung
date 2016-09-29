package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Entity.Animation;
import Entity.Item;

public class Herz extends Item {

	private BufferedImage[] sprites;

	public Herz(TileMap tm) {
		super(tm);
		status=true;

		width = 7;
		height = 7;
        facingRight=true;
		try {
			BufferedImage bi = ImageIO.read(getClass().getResourceAsStream(
					"/Herz/Herz.gif"));
			sprites = new BufferedImage[1];
			sprites[0] = bi;
		} catch (Exception e) {

		}
          animation= new Animation();
          animation.setFrames(sprites);
         animation.setDelay(300);
          right=true;
          facingRight=true;
	}

public void update(){
    


}
public void draw(Graphics2D g){
	setMapPosition();
	super.draw(g);
}
}
