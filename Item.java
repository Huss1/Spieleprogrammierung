package Entity;

import TileMap.TileMap;

public class Item extends MapObject {
	public int id;
	private boolean visible;
	protected boolean status;

	public Item(TileMap tm) {
		super(tm);
	}

	public boolean isvisible() {
		if (visible = true) {
			return true;
		} else {
			return false;

		}
	}

	public void update() {

	}
    public void setstatus(boolean b){
    	status=b;
    }
    public boolean getstatus(){
    	return status;
    }
	public void setID(int i) {
		id = i;
	}

}
