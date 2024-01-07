package game.alive;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.blocks.Table;
import game.render.Assets;

public class Bad extends Alive{
	
	int moveCount, mode, modeCount, shotCount;
	boolean direction;

	public Bad(Assets assets, int x, int y, int mode) {
		super(assets.bad1, x, y);
		id = 2;
		this.mode = mode;
	}
	
	public void update(Table table, Assets assets, ArrayList<Projectile> shots, Player player){
		moveCount++;
		if(moveCount == 10) {
			super.update(table);
			if(speedX == 0) {
				if(player != null) {
					speedY = ((int) (Math.random() * 3) - 1);
				}
				if(direction)
					speedX = 1;
				else
					speedX = -1;
				speedX = speedX * (int)(Math.random() * (mode + 2));
				direction = !direction;
			}
			moveCount = 0;
		}
		
		if(shotCount > 0)
			shotCount--;
		else if(player != null) {
			Projectile shot = new Projectile(assets, x + (width / 2), y, 1);
			if(y <= player.y)
				shot.speedY = 1;
			else
				shot.speedY = -1;
			shot.width = 1;
			shot.height = 4;
			shots.add(shot);
			shotCount = (int) (Math.random() * 6);
			shotCount = (shotCount + 2) * 500;
		}
	}

}
