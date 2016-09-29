package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import TileMap.TileMap;
import Entity.Animation;
import Entity.Enemy;

public class Spinne extends Enemy{
	
	private BufferedImage[] sprites;
	
public Spinne(TileMap tm){
		super(tm);
		
		moveSpeed =0.8;
		maxSpeed =0.8;
		fallSpeed = 0.4;
		maxFallSpeed= 10.0;
		width=30;
		height=30;
		cwidth=20;
		cheight=20;
		
		health =maxHealth= 10;
		damage=2;
		try{
			BufferedImage spritesheet =ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/arachnik.gif"));
			sprites = new BufferedImage[1];
			for(int i=0; i<sprites.length;i++){
				sprites[i]=spritesheet.getSubimage(i *width, 0, width, height);
			}
		}catch(Exception e){
			
		}
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		down=true;
		up=false;
		facingDown=true;
		
		
		
	}
private void getNextPosition(){
	//System.out.println(currRow+""+currCol);
	if(up){
	
		dy-= moveSpeed;
		if(dy<-maxSpeed){
			dy= -maxSpeed;
		}
	}
	else if(down){
		dy+=moveSpeed;
		if(dy>maxSpeed){
			dy= maxSpeed;
		}
	}
//	if(falling){
//		dy += fallSpeed;
//		
//		
//	}
}
public void update(){
	getNextPosition();
	checkTileMapCollision();
	setPosition(xtemp, ytemp);
	
	if(flinching){
		long elapsed =(System.nanoTime()-flinchTime)/1000000;
		if(elapsed>400){
			flinching = false;
		}
	}
	if(down&& dy==0.0){
		down= false;
		up=true;
//		facingRight= false;
	}else if(up){
		down=true;
		up=false;
//		facingRight=true;
	}
	animation.update();
}
public void draw(Graphics2D g){
	//if(notOnScreen()) return;
	setMapPosition();
	super.draw(g);
}
}