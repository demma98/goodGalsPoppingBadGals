package game.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.input.Keys;
import game.render.Assets;
import game.render.Display;
import game.scenes.GameScene;
import game.scenes.Scene;

public class Game {
   int width, height;
   String title;
   
   boolean run;

   private Display display;
   
   private BufferStrategy bs;
   private Graphics g;
   
   private Scene scene;
   
   private Assets assets;
   private Keys keys;

   public Game (String title, int width, int height) {
      this.width = width;
      this.height = height;
      this.title = title;
   
      keys = new Keys();
      
      display = new Display(title, width, height);
      
      display.getFrame().addKeyListener(keys);
      
      assets = new Assets();
      
      scene = new GameScene(assets);
   }
   
   void render(){
      bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
      g.setColor(new Color(255, 0, 255));
		g.fillRect(0, 0, width, height);
		//Draw Here!
		
      if(scene != null)
         scene.render(g);
		//End Drawing!
		bs.show();
		g.dispose();

   }
   
   void update(){
	  keys.update();
	  
      if(scene != null)
         scene.update(keys);
   }
   
   public void start(){
      run = true;
      while(run){
         render();
         update();
      }
      stop();
   }
   
   public void stop(){
   }

	public Keys getKeys() {
		return keys;
}
}