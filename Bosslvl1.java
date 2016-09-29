package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;


import TileMap.TileMap;
import Audio.AudioPlayer;
import Entity.Animation;
import Entity.Enemy;


public class Bosslvl1 extends Enemy {

	private BufferedImage[] bf;
	private int help=0;
	private long starttime;
	private long elapseds;
	private double px;
	private double py;
    private boolean boo;
	public Bosslvl1(TileMap tm,boolean trueiffacingright){
		super(tm);	
		
	moveSpeed =0.04;
	maxSpeed =0.04;
	fallSpeed = 0.2;
	maxFallSpeed= 10.0;
	width=39;
	height=41;
	cwidth=20;
	cheight=20;
	
    maxHealth=10000;
	health = 25;
	maxHealth= 47;
	damage=2;
	try{
		BufferedImage zero =ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Bossstart.gif"));

    bf = new BufferedImage[1];
	bf[0]= zero;
	}catch(Exception e){
		
	}
	animation = new Animation();
	animation.setFrames(bf);
	animation.setDelay(300);
	right=true;
	left=false;
	up=false;
	down=false;
    px= this.getx();
    py=this.gety();

	
}

private void getNextPosition(){


	if (getx()>=2920 && getx()<3170&& gety()==200){
		dx=dx+moveSpeed;
	}
	if(gety()<=200 &&gety()>=40&& getx()==3170 ){
		dy=dy-moveSpeed;
	}
	if(gety()==40 &&getx()>=2920&& getx()<=3170){
		dx= dx-moveSpeed;
	}
	if(getx()==2920&& gety()>=40 && gety()<=200){
		dy= dy+moveSpeed;
	}
	

	

}
public void setLives(){
	health= maxHealth;
}
public void update(){
	
	if(health==0) return;
	getNextPosition();
	checkTileMapCollision();
	setPosition(xtemp, ytemp);
   // System.out.println(this.getx()+" "+ this.gety());
	if(flinching){
		long elapsed =(System.nanoTime()-flinchTime)/1000000;
		if(elapsed>400){
			flinching = false;
		}
	}


	//System.out.println(getx()+"   "+gety());
animation.update();
}
public void draw(Graphics2D g){
	//if(notOnScreen()) return;
	setMapPosition();
	super.draw(g);
}
}
