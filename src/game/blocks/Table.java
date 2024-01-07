package game.blocks;

import java.awt.Graphics;

import game.render.Assets;

public class Table{
   int width, height;
   public int xOff, yOff;
   public int blocks[][];

   boolean collidable;
   
   Assets assets;
   
   public Block list[];
   
   public Table(int width, int height, boolean collidable, Assets assets){
      this.width = width;
      this.height = height;
      this.collidable = collidable;
      this.assets = assets;
      
      blocks = new int[width][height];
      
      list = new Block[9];
      list[1] = new Wall(this.assets, 0);
      list[2] = new Wall(this.assets, 1);
      list[3] = new Wall(this.assets, 2);
      list[4] = new Wall(this.assets, 3);
      list[5] = new Wall(this.assets, 4);
      list[6] = new Wall(this.assets, 5);
      list[7] = new Wall(this.assets, 6);
      list[8] = new Wall(this.assets, 7);
   }
   
   public void update(){
   };
   
   public void render(Graphics g){
      for(int y = 0; y < height; y++)
         for(int x = 0; x < width; x++)
            if(blocks[x][y]!=0)
               g.drawImage(list[blocks[x][y]].sprite, x * Assets.width + xOff
               , y * Assets.height + yOff, null);
   }

}