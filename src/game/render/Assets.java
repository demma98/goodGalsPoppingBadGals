package game.render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static final int width = 16, height = 16;

	public BufferedImage brick;
	public BufferedImage walls[], wallV, wallH;

	public BufferedImage shot, shots[];
	
	public BufferedImage alive1, bad1, bad2;

	public Assets(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/img/sheet.png"));

		brick = sheet.crop(0, 0, width, height);

		walls = new BufferedImage[6];
		walls[0] = sheet.crop(0, 0, width, height);
		wallV = sheet.crop(width, 0, width, height);
		wallH = sheet.crop(width * 2, 0, width, height);
		walls[5] = sheet.crop(width * 3, 0, width, height);
		walls[1] = sheet.crop(0, height, width, height);
		walls[2] = sheet.crop(width, height, width, height);
		walls[3] = sheet.crop(width * 2, height, width, height);
		walls[4] = sheet.crop(width * 3, height, width, height);
		
		shot = sheet.crop(0, height * 2, 1, 4);
		
		SpriteSheet alive = new SpriteSheet(ImageLoader.loadImage("/img/alive.png"));

		alive1 = alive.crop(0, 0, width, height);
		bad1 = alive.crop(width, 0, width, height);
		bad2 = alive.crop(width*2, 0, width, height);
	}
	
    public static BufferedImage flipImage(final BufferedImage image, final boolean horizontal,
            final boolean vertical) {
        int x = 0;
        int y = 0;
        int w = image.getWidth();
        int h = image.getHeight();

        final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = out.createGraphics();

        if (horizontal) {
            x = w;
            w *= -1;
        }

        if (vertical) {
            y = h;
            h *= -1;
        }

        g2d.drawImage(image, x, y, w, h, null);
        g2d.dispose();

        return out;
    }

}