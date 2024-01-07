package game.alive;

import java.util.ArrayList;

import game.blocks.Table;
import game.input.Keys;
import game.render.Assets;
import game.scenes.GameScene;

public class Player extends Alive {
	
	boolean moveV, jump, flip, fall;
	int moveCount, shotCount;

	public Player(Assets assets, int x, int y) {
		super(assets.alive1, x, y);
		id = 1;
	}
	
	public Player(Assets assets) {
		this(assets, 0, 0);
	}
	
	public void update(Keys keys, Assets assets, Table table, ArrayList<Projectile> shots) {
		moveCount++;
		if(moveCount == 3) {
			super.update(table);
			moveCount = 0;
		}
		
		if(shotCount > 0)
			shotCount--;
		
		if(keys.left)
			speedX = -1;
		else if(keys.right)
			speedX = 1;
		else
			speedX = 0;
		
		if(keys.up)
			speedY = -1;
		else if(keys.down)
			speedY = 1;
		else
			speedY = 0;
		
		if(keys.fire)
			if(shotCount == 0) {
				Projectile shot = new Projectile(assets, x + (width / 2), y, 2);
				shot.speedY = -1;
				shot.width = 1;
				shot.height = 4;
				shots.add(shot);
				shotCount = 300;
			}
	}

}
