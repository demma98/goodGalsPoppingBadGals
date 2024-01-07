package game.blocks;

import game.render.Assets;

public class Air extends Block{
	
	public Air(Assets assets){
	      super(assets.brick);
	      solid = false;
	   }

}
