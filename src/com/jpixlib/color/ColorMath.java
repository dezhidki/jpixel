package com.jpixlib.color;

/**
 * Basic color math.
 * 
 * @author Denis Zhidkikh
 * @version 1.0
 * @since 18.5.2013
 */
public final class ColorMath {
	public static enum EColorComponent{
		ALPHA, RED, GREEN, BLUE
	}
	
	/**
	 * Blends two colors together.
	 * 
	 * @param color1 First color to blend.
	 * @param color2 Second color to blend.
	 * @param factor How much of the second color to blend. 0 will return only
	 *        color1, 256 will return only color2.
	 * @return Blended color.
	 */
	public int blend(int color1, int color2, int factor) {
		int f1 = 256 - factor;
		return ((((color1 & 0xFF00FF) * f1 + (color2 & 0xFF00FF) * factor) & 0xFF00FF00) | (((color1 & 0x00FF00) * f1 + (color2 & 0x00FF00) * factor) & 0x00FF0000)) >>> 8;
	}
	
	/**
	 * Combines all colors to ARGB value.
	 * 
	 * @param a Alpha.
	 * @param r Red.
	 * @param g Green.
	 * @param b Blue.
	 * @return All color channels combined into single integer.
	 */
	public int toARGB(int a, int r, int g, int b){
		return a << 24 | r << 16 | g << 8 | b;
	}
	
	/**
	 * Gets single color channel from ARGB integer.
	 * 
	 * @param colARGB ARGB integer.
	 * @param colorComponent Color channel to get.
	 * @return Color channel.
	 */
	public int getColor(int colARGB, EColorComponent colorComponent){
		switch (colorComponent) {
		case ALPHA: return (colARGB & 0xFF000000) >>> 24;
		case RED: return (colARGB & 0x00FF0000) >> 16;
		case GREEN: return (colARGB & 0x0000FF00) >> 8;
		case BLUE: return colARGB & 0x000000FF;
		}
		return 0;
	}

}
