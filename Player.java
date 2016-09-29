package Entity;

import Audio.AudioPlayer;
import GameState.FireBall;
import TileMap.*;

import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {

	private int Health;
	private int maxHealth;
	private int Life;
	private int maxLife;
	private int fire;
	private int points;
	private int maxPoints;
	private int maxFire;
	private boolean dead;
	private boolean flinching;
	private long flinchTime;
	private int xpos;
	private long time;
	private int ypos;
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBall> fireBalls;
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	private boolean gliding;
	private boolean boo = true;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 2, 8, 1, 2, 4, 2, 5 };
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;

	private HashMap<String, AudioPlayer> sfx;

	public Player(TileMap tm) {
		super(tm);

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		facingRight = true;
		Health = maxHealth = PlayerSave.getHealth();
		Life = PlayerSave.getLives();
		maxLife = 99;
		points = 0;
		maxPoints = 99999999;
		fire = maxFire = 600;
		fireCost = 200;
		fireBallDamage = PlayerSave.getfireBallDamage();
		fireBalls = new ArrayList<FireBall>();
		scratchDamage = PlayerSave.getscratchdamage();
		scratchRange = 40;

		try {
			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Player/playersprites.gif"));
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 7; i++) {
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for (int j = 0; j < numFrames[i]; j++) {
					if (i != SCRATCHING) {
						bi[j] = spritesheet.getSubimage(j * width, i * height,
								width, height);
					} else {
						bi[j] = spritesheet.getSubimage(j * width * 2, i
								* height, width * 2, height);
					}

				}

				sprites.add(bi);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);

		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("jump", new AudioPlayer("/SFX/jump.mp3"));
		sfx.put("scratch", new AudioPlayer("/SFX/scratch.mp3"));
		sfx.put("fireball", new AudioPlayer("/SFX/fireball.mp3"));
	}

	public int getPoints() {
		return points;
	}

	public int setHealth(int i) {
		return i;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setfalse() {
		boo = false;
	}

	public int getLife() {
		return Life;
	}

	public int getHealth() {
		return Health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getFire() {
		return fire;
	}

	public int getMaxFire() {
		return maxFire;
	}

	public void setFiring() {
		firing = true;
	}

	public void setScratching() {
		scratching = true;
	}

	public void setGliding(boolean b) {
		gliding = b;
	}

	public void checkAttack(ArrayList<Enemy> enemies) {

		// loop through enemies
		for (int i = 0; i < enemies.size(); i++) {

			Enemy e = enemies.get(i);

			// scratch attack
			if (scratching) {
				if (facingRight) {
					if (e.getx() > x && e.getx() < x + scratchRange
							&& e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(scratchDamage);
					}
				} else {
					if (e.getx() < x && e.getx() > x - scratchRange
							&& e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(scratchDamage);
					}
				}
			}

			// fireballs
			for (int j = 0; j < fireBalls.size(); j++) {
				if (fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
					fireBalls.get(j).setHit();
					break;
				}
			}

			// check enemy collision
			if (intersects(e)) {
				hit(e.getDamage());
			}
			if (e.dead == true) {
				if (e.getMaxHealth() == 47) {
					points = points + 100;
					PlayerSave.setCoins(PlayerSave.getCoins() + 100);
				}
				if (e.getMaxHealth() == 2) {
					points = points + 10;
					PlayerSave.setCoins(PlayerSave.getCoins() + 10);
				}
				if (e.getMaxHealth() == 10) {
					points = points + 25;
					PlayerSave.setCoins(PlayerSave.getCoins() + 25);
				}
			}
			if (this.dead == true) {
				Life = Life - 1;
			}
		}

	}

	public void setSpeed() {
		maxSpeed = 2.0;
		moveSpeed = 2.0;

	}

	public void returnSpeed() {
		maxSpeed = 1.6;
		moveSpeed = 0.3;

	}


	public void addLife() {
		Life = Life++;
	}

	public boolean isdead() {
		if (this.getLife() == 0) {
			return true;
		} else
			return false;

	}

	public void setLives(int i) {
		Life = i;
	}

	public void setTime(long t) {
		time = t;
	}

	public String getTimeToString() {
		int minutes = (int) (time / 3600);
		int seconds = (int) ((time % 3600) / 60);
		return seconds < 10 ? minutes + ":0" + seconds : minutes + ":"
				+ seconds;
	}

	public double getmovespeed() {
		return moveSpeed;
	}

	public long gettime() {
		return this.time;
	}

	public void loseLife() {
		Life--;
	}

	public void hit(int damage) {
		if (flinching)
			return;
		Health -= damage;
		if (Health < 0)
			Health = 0;
		if (Health == 0)
			dead = true;
		flinching = true;
		flinchTime = System.nanoTime();
	}

	private void getNextPosition() {

		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		if ((currentAction == SCRATCHING || currentAction == FIREBALL)
				&& !(jumping || falling)) {
			dx = 0;
		}
		if (jumping && !falling) {

			sfx.get("jump").player();

			dy = jumpStart;
			falling = true;
		}
		if (falling) {
			if (dy > 0 && gliding)
				dy += fallSpeed * 0.1;
			else
				dy += fallSpeed;
			if (dy > 0)
				jumping = false;
			if (dy < 0 && !jumping)
				dy += stopJumpSpeed;
			if (dy > maxFallSpeed)
				dy = maxFallSpeed;
		}
	}

	public void update() {
		
		time++;
		getNextPosition();
		checkTileMapCollision();

		setPosition(xtemp, ytemp);
		xpos = getx();
		ypos = gety();
		// System.out.println(" "+xpos+" "+ypos);

		if (currentAction == SCRATCHING) {
			if (animation.hasPlayedOnce())
				scratching = false;
		}

		if (currentAction == FIREBALL) {
			if (animation.hasPlayedOnce())
				firing = false;
		}
		fire += 1;
		if (fire > maxFire)
			fire = maxFire;
		if (firing && currentAction != FIREBALL) {
			if (fire > fireCost && currentAction != SCRATCHING) {

				sfx.get("fireball").player();

				fire -= fireCost;
				FireBall fb = new FireBall(tileMap, facingRight);

				fb.setPosition(x, y);
				fireBalls.add(fb);

			}
		}
		for (int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).update();
			if (fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				i--;
			}
		}
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTime) / 1000000;
			if (elapsed > 1000) {
				flinching = false;
			}
		}
		if (scratching) {
			if (currentAction != SCRATCHING) {
				if (boo == true) {
					sfx.get("scratch").player();
				}
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(70);
				width = 60;
			}
		} else if (firing) {
			if (currentAction != FIREBALL) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width = 30;
			}
		} else if (dy > 0) {
			if (gliding) {
				if (currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					width = 30;
				}
			} else if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		} else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		} else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}

		animation.update();
		if (currentAction != SCRATCHING && currentAction != FIREBALL) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
		}

	}

	public void draw(Graphics2D g) {
		setMapPosition();
		for (int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
		}
		if (facingRight) {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2),
					(int) (y + ymap - height / 2), null);
		} else {
			g.drawImage(animation.getImage(),
					(int) (x + xmap - width / 2 + width),
					(int) (y + ymap - height / 2), -width, height, null);

			if (flinching) {
				long elapsed = (System.nanoTime() - flinchTime) / 1000000;
				if (elapsed / 100 % 2 == 0) {
					return;
				}
			}
			super.draw(g);

		}
	}
}
