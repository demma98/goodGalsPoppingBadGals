package game.blocks;

import game.render.Assets;

public class Wall extends Block{
   

   public Wall(Assets assets, int subNum){
	   super(assets.brick);
	   if(0 <= subNum && subNum <= 5)
		   sprite = assets.walls[subNum];
	   else if(subNum == 6)
		   sprite = assets.wallV;
	   else if(subNum == 7)
		   sprite = assets.wallH;
   }
	   
   public Wall(Assets assets){
	   this(assets, 0);
   }
}