package com.jpixel.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A helper class to load images as resources.
 * 
 * @author Denis Zhidkikh
 * @version 1.0
 * @since 27.10.2013
 *
 */
public class ImageLoader {

	private ImageLoader(){
	}
	
	/**
	 * Loads an image as a bitmap from the resource folder. The name of the folder depends, but
	 * the usual name for it is "res".
	 * 
	 * @param path Path to the image in the resource folder.
	 * @return A bitmap of the image.
	 * @throws IOException File not found or incompatible type.
	 */
	public static Bitmap loadImageAsResource(String path) throws IOException {
		Bitmap result = null;
		BufferedImage img = ImageIO.read(ImageLoader.class.getResourceAsStream(path));
		result = new Bitmap(img.getWidth(), img.getHeight());
		img.getRGB(0, 0, result.width, result.height, result.pixels, 0, result.width);

		return result;
	}
	
	/**
	 * Loads an image as a {@link SpriteSheet} from the resource folder.
	 * 
	 * @param path Path to the image in the resource folder.
	 * @param spriteWidth Width of a single sprite.
	 * @param spriteHeight Height of a single sprite.
	 * @return A {@link SpriteSheet} of the given image.
	 * @throws IOException File not found or incompatible type.
	 */
	public static SpriteSheet loadSpriteSheetAsResource(String path, int spriteWidth, int spriteHeight) throws IOException {
		return new SpriteSheet(loadImageAsResource(path), spriteWidth, spriteHeight);
	}
}
