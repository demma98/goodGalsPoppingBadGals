package game.scenes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.alive.Bad;
import game.alive.Player;
import game.alive.Projectile;
import game.blocks.Table;
import game.input.Keys;
import game.render.Assets;

public class GameScene extends Scene{
   Table table;
   Player player;
   ArrayList<Projectile> shots;
   ArrayList<Bad> baddies;
   
   int nBaddies = 1, restartCount;
   
   public GameScene(Assets assets){
	      super(assets);
	      init();
   }
   
   public void init() {
	      table = new Table(30, 22, true, assets);
	      shots = new ArrayList<Projectile>();
	      
	      for(int x = 0; x < table.blocks.length; x++) {
	    	  table.blocks[x][0] = 8;
	    	  table.blocks[x][table.blocks[x].length - 1] = 8;
	      }
	      for(int y = 0; y < table.blocks[0].length; y++) {
	    	  table.blocks[0][y] = 7;
	    	  if(y == 0 || y == table.blocks[0].length - 1)
	    		  table.blocks[table.blocks.length - 8][y] = 6;
	    	  else {
	    		  table.blocks[table.blocks.length - 8][y] = 7;
		    	  for(int i = 2; i < 8; i++)
		    		  table.blocks[table.blocks.length - i][y] = 1;
	    	  }
	    	  table.blocks[table.blocks.length - 1][y] = 7;
	      }
	      table.blocks[0][0] = 2;
	      table.blocks[table.blocks.length - 1][0] = 3;
	      table.blocks[0][table.blocks[0].length - 1] = 4;
	      table.blocks[table.blocks.length - 1][table.blocks[0].length - 1] = 5;
	      
	      if(player == null)
	    	  player = new Player(assets, 300, 300);
	      
	      baddies = new ArrayList<Bad>();
	      for(int i = 0; i < nBaddies; i++) {
	    	  Bad badTemp = new Bad(assets, (i % 9) * 32 + 64, (i / 9) * 32 + 80, nBaddies / 9);
	    	  baddies.add(badTemp);
	      }
	      
	      restartCount = 0;
   }
   
   public void update(Keys keys){
	   Rectangle hit1 = null, hit2;
	   Projectile shotTemp;
	   
	   if(keys.start)
		   init();
	   
	   table.update();
	   
	   for(int i = 0; i < shots.size(); i++) {
		   shotTemp = shots.get(i);
		   if(shotTemp == null) {
			   shots.remove(i);
			   i--;
		   }
		   else if(shotTemp.x < 0 || shotTemp.y < 0) {
			   shots.remove(i);
			   i--;
		   }
		   else {
			   	if(shotTemp.idAgaint == 0) {
			   		shots.remove(i);
			   		i--;
			   	}
			   	else
			   		shots.get(i).update(table);
			   	if(shotTemp.idAgaint == 1 && player != null) {
			   		hit1 = new Rectangle(player.x, player.y, player.width, player.height);
			   		hit2 = new Rectangle(shotTemp.x, shotTemp.y, shotTemp.width, shotTemp.height);
			   		if(hit1.intersects(hit2)) {
			   				player = null;
			   				i = shots.size();
			   		}
			   	}
			   	else if(shotTemp.idAgaint == 2)
			   		for(int j = 0; j < baddies.size(); j++)
			   			if(baddies != null) {
			   				Bad baddie = baddies.get(j);
			   				hit1 = new Rectangle(baddie.x, baddie.y, baddie.width, baddie.height);
			   				hit2 = new Rectangle(shotTemp.x, shotTemp.y, shotTemp.width, shotTemp.height);
			   				if(hit1.intersects(hit2)) {
			   					baddies.remove(j);
			   					j = baddies.size();
			   				}
			   			}
		   }
	   }

	   if(player != null) {
		   	player.update(keys, assets, table, shots);
	   }
	   
	   if(player != null)
		   hit1 = hit1 = new Rectangle(player.x, player.y, player.width, player.height);
	   for(int i = 0; i < baddies.size(); i++) {
		   Bad baddie = baddies.get(i);
		   	if(baddie == null) {
			   baddies.remove(i);
			   i--;
		   	}
		   	else {
		   		if(player != null) {
		   			hit2 = new Rectangle(baddie.x, baddie.y, baddie.width, baddie.height);
		   			if(hit1.intersects(hit2))
		   				player = null;
		   		}
		   		baddies.get(i).update(table, assets, shots, player);
		   	}
	   }
	   if(baddies.size() == 0 && restartCount == 0) {
		   restartCount = 720;
	   }
	   if(restartCount > 0) {
		   restartCount--;
	   }
	   if(restartCount == 1) {
		   nBaddies++;
		   init();
	   }
   }
   
   public void render(Graphics g){
	   table.render(g);
	   if(player != null)
		   player.render(g);
	   for(int i = 0; i < baddies.size(); i++)
		   if(baddies.get(i) != null)
			   baddies.get(i).render(g);
	   for(int i = 0; i < shots.size(); i++)
		   if(shots.get(i) != null)
			   shots.get(i).render(g);
   }
}