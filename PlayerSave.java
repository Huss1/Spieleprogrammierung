package Entity;

public class PlayerSave {


	private static int lives = 3;
	private static int health = 5;
	private static long time = 0;
	private static int coins=0;
	private static int scratchdamage= 6;
	private static int fireBallDamage= 5;
	
	public static void init() {
		lives = 3;
		health = 5;
		time = 0;
		coins=0;
		scratchdamage= 6;
		fireBallDamage=5;
	}
	
	public static int getLives() { return lives; }
	public static void setLives(int i) { lives = i; }
	
	public static int getHealth() { return health; }
	public static void setHealth(int i) { health = i; }
	
	public static long getTime() { return time; }
	public static void setTime(long t) { time = t; }
	public static int getCoins() { return coins; }
	public static void setCoins(int c) { coins = c; }
	public static int getscratchdamage() { return scratchdamage; }
	public static void setscratchdamage(int i) { scratchdamage = i; }
	public static int getfireBallDamage() { return fireBallDamage; }
	public static void setfireBallDamage(int i) { fireBallDamage = i; }
}