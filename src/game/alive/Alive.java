package game.alive;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.blocks.Table;
import game.render.Assets;

public class Alive {
	
	protected BufferedImage sprite;
	
	public int x, y, width, height;

	int id;
	public int xOff, yOff;
	protected int speedX, speedY;

	public Alive(BufferedImage sprite, int x, int y){
	      this.sprite = sprite;
	      this.x = x;
	      this.y = y;
	      
	      width = sprite.getWidth();
	      height = sprite.getHeight();
	}
	
	public Alive(BufferedImage sprite){
		this(sprite, 0, 0);
	}
   
	public Alive(Assets assets){
		this(assets.alive1);
	}
	
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int xAdd, int yAdd) {
		x += xAdd;
		y += yAdd;
	}
	
	public void move(int xAdd, int yAdd, Table table) {
		int xTemp, yTemp, distTemp;
		
		xTemp = xAdd;
		yTemp = yAdd;

		if(xAdd > 0)
			for(int i = 0; i < table.blocks.length; i++) {
				distTemp = i * Assets.width - (x + width);
				for(int j = 0; j < table.blocks[i].length; j++)
					if(xTemp != 0)
						if(table.blocks[i][j] != 0)
							if((j * Assets.height <= y && y < (j + 1) * Assets.height)
								|| (j * Assets.height < y + height && y + height < (j + 1) * Assets.height))
								if(distTemp >= 0)
									if(distTemp < xTemp)
										xTemp = distTemp;
			}
		else if(xAdd < 0)
			for(int i = 0; i < table.blocks.length; i++) {
				distTemp = (i + 1) * Assets.width - x;
				for(int j = 0; j < table.blocks[i].length; j++)
					if(xTemp != 0)
						if(table.blocks[i][j] != 0)
							if((j * Assets.height <= y && y < (j + 1) * Assets.height)
								|| (j * Assets.height < y + height && y + height < (j + 1) * Assets.height))
								if(distTemp <= 0)
									if(distTemp > xTemp)
										xTemp = distTemp;
			}
		
		if(yAdd > 0)
			for(int j = 0; j < table.blocks[0].length; j++) {
				distTemp = j * Assets.height - (y + height);
				for(int i = 0; i < table.blocks.length; i++)
					if(yTemp != 0)
						if(table.blocks[i][j] != 0)
							if((i * Assets.width <= x && x < (i + 1) * Assets.width)
								|| (i * Assets.width < x + width && x + width < (i + 1) * Assets.width))
								if(distTemp >= 0)
									if(distTemp < yTemp)
										yTemp = distTemp;
			}
		else if(yAdd < 0)
			for(int j = 0; j < table.blocks[0].length; j++) {
				distTemp = (j +1) * Assets.height - y;
				for(int i = 0; i < table.blocks.length; i++)
					if(yTemp != 0)
						if(table.blocks[i][j] != 0)
							if((i * Assets.width <= x && x < (i + 1) * Assets.width)
								|| (i * Assets.width < x + width && x + width < (i + 1) * Assets.width))
								if(distTemp <= 0)
									if(distTemp > yTemp)
										yTemp = distTemp;
			}
		
		move(xTemp, yTemp);
		speedX = xTemp;
		speedY = yTemp;
	}
	
	public void update(Table table) {
		move(speedX, speedY, table);
	}
	public void update() {
		move(speedX, speedY);
	}
   
	public void render(Graphics g) {
	   g.drawImage(sprite, x + xOff, y + yOff, null);
	}
}
