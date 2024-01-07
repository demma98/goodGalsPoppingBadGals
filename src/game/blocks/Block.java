package game.blocks;

import java.awt.image.BufferedImage;

import game.render.Assets;

public class Block{
   public BufferedImage sprite;
   public boolean solid = true;
   
   public Block(BufferedImage sprite){
      this.sprite = sprite;
   }
   
   public Block(Assets assets){
      this(assets.brick);
   }
   
   public void update(){
   }
   
}