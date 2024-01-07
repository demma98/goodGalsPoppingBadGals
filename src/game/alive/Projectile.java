package game.alive;

import game.blocks.Table;
import game.render.Assets;

public class Projectile extends Alive{

	public int idAgaint;
	int moveCount;
	
	public Projectile(Assets assets, int x, int y, int idAgaint) {
		super(assets.shot, x, y);
		this.idAgaint = idAgaint;
	}

	public void update(Table table) {
		moveCount++;
		if(moveCount == 16) {
			super.update(table);
			if(speedY == 0)
				idAgaint = 0;
			moveCount = 0;
		}
	}
}
