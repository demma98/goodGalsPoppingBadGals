package game.scenes;

import java.awt.Graphics;

import game.input.Keys;
import game.render.Assets;

public abstract class Scene {
   Assets assets;

   public Scene(Assets assets){
      this.assets = assets;
   }
   
   public abstract void update(Keys keys);
   
   public abstract void render(Graphics g);
}