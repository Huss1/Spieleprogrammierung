package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.Enemy;
import Entity.Explosion;
import Entity.HUD;
import Entity.Player;
import Entity.PlayerSave;
import Entity.Enemies.Bosslvl1;
import Entity.Enemies.Snail;
import Entity.Enemies.Spinne;
import Items.Herz;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import Audio.AudioPlayer;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;
	private Player player;
	private AudioPlayer bgmusic;
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	private ArrayList<Herz> hearts;
	private HUD hud;
	private boolean boo;

	public Level1State(GameStateManager gsm, int Life) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		boo = false;
		bgmusic = new AudioPlayer("/Music/epic.mp3");
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/Endgegner.map");
		tileMap.setPosition(0, 0);
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
		// bg.setVector(-2.0, 0);
		player = new Player(tileMap);
		player.setPosition(100, 100);
		;
		// player.setLives(PlayerSave.getLives()-gsm.getcounter());
		player.setTime(PlayerSave.getTime());
		populateEnemies();
		explosions = new ArrayList<Explosion>();
		hud = new HUD(player);
        populateItems();
	};
private void populateItems(){
	hearts= new ArrayList<Herz>();
	Herz h;
	Herz h1;
	Herz h2;
	Herz h3;
	h= new Herz(tileMap);
	h1= new Herz (tileMap);
	h2= new Herz(tileMap);
	h3= new Herz(tileMap);
	h.setPosition(50, 200);
	h1.setPosition(250, 200);
	h2.setPosition(1650, 200);
	h3.setPosition(2563, 80);
	hearts.add(h);
	hearts.add(h1);
	hearts.add(h2);
	hearts.add(h3);
}
	private void populateEnemies() {
		enemies = new ArrayList<Enemy>();
		Snail s;
		Snail s0;
		Snail s1;
		Snail s2;
		Snail s3;
		Snail s4;
		Snail s5;
		Snail s6;
		Snail s7;
		Snail s8;
		Bosslvl1 b1;
		Bosslvl1 b2;
		Bosslvl1 b3;
		Bosslvl1 b4;

		Spinne spin;
		Spinne spin2;
		Spinne spin3;
		Spinne spin4;
		Spinne spin5;
		Spinne spin6;
		Spinne spin7;

		s = new Snail(tileMap);
		s0 = new Snail(tileMap);
		s1 = new Snail(tileMap);
		s2 = new Snail(tileMap);
		s3 = new Snail(tileMap);
		s4 = new Snail(tileMap);
		s5 = new Snail(tileMap);
		s6 = new Snail(tileMap);
		s7 = new Snail(tileMap);
		s8 = new Snail(tileMap);
		b1 = new Bosslvl1(tileMap, true);
		b2 = new Bosslvl1(tileMap, true);
		b3 = new Bosslvl1(tileMap, true);
		spin = new Spinne(tileMap);
		spin.setPosition(326, 40);
		spin2 = new Spinne(tileMap);
		spin2.setPosition(1025, 200);
		spin3 = new Spinne(tileMap);
		spin3.setPosition(1575, 170);
		spin4 = new Spinne(tileMap);
		spin4.setPosition(1725, 170);
		spin5 = new Spinne(tileMap);
		spin5.setPosition(2175, 200);
		spin6 = new Spinne(tileMap);
		spin6.setPosition(2410, 200);
		spin7 = new Spinne(tileMap);
		spin7.setPosition(2875, 200);
		b1.setPosition(3050, 200);
		b2.setPosition(2920, 40);
		b3.setPosition(3170, 40);
		s.setPosition(150, 200);
		s7.setPosition(200, 200);
		s1.setPosition(400, 140);
		s0.setPosition(800, 200);
		s2.setPosition(1000, 200);
		s3.setPosition(1500, 200);
		s4.setPosition(1650, 200);
		s5.setPosition(1800, 200);
		s6.setPosition(2650, 200);
		s8.setPosition(2800, 200);

		enemies.add(spin7);
		enemies.add(spin6);
		enemies.add(spin5);
		enemies.add(spin4);
		enemies.add(spin3);
		enemies.add(spin2);
		enemies.add(s);
		enemies.add(s0);
		enemies.add(s1);
		enemies.add(s2);
		enemies.add(s3);
		enemies.add(s4);
		enemies.add(s5);
		enemies.add(s6);
	    enemies.add(s7);
		enemies.add(s8);
		enemies.add(spin);
		enemies.add(b1);
		enemies.add(b2);
		enemies.add(b3);

	}

	public void update() {

		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety());

		bg.setPosition(tileMap.getx(), tileMap.gety());

		player.checkAttack(enemies);

		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));

			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}

		if (player.getHealth() == 0 && (PlayerSave.getLives() >= 0)) {
			player.loseLife();
			gsm.setState(3);
		}
		if (PlayerSave.getLives() < 0) {
			gsm.setState(3);
		}

		if (player.gettime() / 3600 == 1) {
			gsm.setState(3);
		}
		for(int i=0; i<hearts.size();i++){
			if (hearts.get(i).breiterIntersect(player)){
				hearts.get(i).setstatus(false);
				if(player.getHealth()<=4){
					player.hit(-1);
				player.setLives(player.getLife()+1);
				}
			}
		}

	};

	public void draw(Graphics2D g) {

		bg.draw(g);
		tileMap.draw(g);
		
		
		player.draw(g);
		System.out.println(player.getx()+" "+player.gety());
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition((int) tileMap.getx(),
					(int) tileMap.gety());
			explosions.get(i).draw(g);
		}
		
		hud.draw(g);
		
		if (player.getx() > 2700) {
			gsm.setMusic(4);
		}
		for (int i = 0; i < hearts.size(); i++) {
			if(hearts.get(i).getstatus()==true){
			hearts.get(i).draw(g);
		
			}
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_A)
			player.setLeft(true);
		if (k == KeyEvent.VK_D)
			player.setRight(true);
		if (k == KeyEvent.VK_W)
			player.setUp(true);
		if (k == KeyEvent.VK_S)
			player.setDown(true);
		if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_UP)
			player.setJumping(true);
		if (k == KeyEvent.VK_LEFT)
			player.setGliding(true);
		if (k == KeyEvent.VK_DOWN)
			player.setScratching();
		if (k == KeyEvent.VK_RIGHT)
			player.setFiring();
		if (k == KeyEvent.VK_SHIFT)
			player.setSpeed();
		if (k == KeyEvent.VK_1)
			System.exit(0);
		if (k == KeyEvent.VK_2)
			gsm.setState(0);

	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_A)
			player.setLeft(false);
		if (k == KeyEvent.VK_D)
			player.setRight(false);
		if (k == KeyEvent.VK_W)
			player.setUp(false);
		if (k == KeyEvent.VK_S)
			player.setDown(false);
		if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_UP)
			player.setJumping(false);
		if (k == KeyEvent.VK_LEFT)
			player.setGliding(false);
		if (k == KeyEvent.VK_DOWN)
			player.setScratching();
		if (k == KeyEvent.VK_RIGHT)
			player.setFiring();
		if (k == KeyEvent.VK_SHIFT)
			player.returnSpeed();

	}
}
