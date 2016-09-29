package GameState;

import Audio.AudioPlayer;
import Entity.PlayerSave;
public class GameStateManager {
    private boolean boo =true;
	private AudioPlayer bgmusic;
	private GameState[] gameStates;
	private int currentState;
	public int counter = 0;
	public int currentMusik;
	public PlayerSave ps;
	
	public static final int NUMGAMESTATES = 7;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int HILFE = 2;
	private static final int GAMEOVERSTATE=3;
	private static final int OPTIONSTATE=4;
	static final int SHOPSTATE=6;

	public GameStateManager() {
		gameStates = new GameState[NUMGAMESTATES];
		currentState = MENUSTATE;
		loadState(currentState);
        
	}

	 void loadState(int state) {
		if (bgmusic != null) {
			bgmusic.stop();
			
		}
	playMusic(state);
		
		
		if (state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if (state == LEVEL1STATE)
			gameStates[state] = new Level1State(this, PlayerSave.getLives());
		if (state == HILFE)
			gameStates[state] = new Hilfe(this);
		if (state == SHOPSTATE)
			gameStates[state] = new ShopState(this);
		
		if(state == GAMEOVERSTATE)
			
			gameStates[state] = new Gameover(this);
		    PlayerSave.setLives(PlayerSave.getLives());
		if(state == OPTIONSTATE)
			gameStates[state]= new Optionstate(this);

	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

    public void setfalse(){
    	boo=false;
    }
    public void settrue(){
    	boo=true;
    }
    public void setMusic(int state) {
        if (state != currentMusik) {
            if (bgmusic != null) {
                bgmusic.stop();
            }
            playMusic(state);
        }
    }

	public void musikaus(){
		
		bgmusic.stop();
		bgmusic.close();
		
	}


	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);

		// gameStates[currentState].init();
	}

	public void update() {
		try {
			gameStates[currentState].update();
		} catch (ArrayIndexOutOfBoundsException e) {
			setState(3)
			;
			
		} catch (Exception e) {

		}
	}

	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch (Exception e) {

		}

	}

	public void keyPressed(int k) {
		try{
			gameStates[currentState].keyPressed(k);
		}catch(Exception e){
		}
		
	}

	public void keyReleased(int k) {
		try{
		gameStates[currentState].keyReleased(k);
	}catch(Exception e){
		}
	}

	private void playMusic(int state) {
 currentMusik = state;
		switch (state) {
		case 0:
			if(boo==true){
			bgmusic = new AudioPlayer("/Music/epic.mp3");
			bgmusic.play();
			}
			break;
		case 1:
			if(boo==true){
			bgmusic = new AudioPlayer("/Music/out.mp3");
			bgmusic.play();
			}
			break;
		case   2:
			if(boo==true){
			bgmusic = new AudioPlayer("/Music/dk.mp3");
			bgmusic.play();
			}
			break;
		case 3:
			if(boo==true){
			bgmusic = new AudioPlayer("/Music/gameover.mp3");
			bgmusic.player();
			}
			break;
		case  4:
			if(boo==true){
			bgmusic = new AudioPlayer("/Music/boss.mp3");
			bgmusic.play();
			}		
			break;
		case 5:
			if(boo==true){
			bgmusic = new AudioPlayer("/Music/gameover.mp3");
			bgmusic.player();
			}
			break;
		}
	}
}
