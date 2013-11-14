package com.jpixlib.color;

/**
 * Basic color math.
 * 
 * @author Denis Zhidkikh
 * @version 1.0
 * @since 18.5.2013
 */
public final class ColorMath {
	public static enum EColorComponent {
		ALPHA(24, 0xFF000000),
		RED(16, 0xFF0000),
		GREEN(8, 0xFF00),
		BLUE(0, 0xFF);
		
		public final int shift, mask;
		
		private EColorComponent(int shift, int mask){
			this.shift = shift;
			this.mask = mask;
		}
	}

	/**
	 * Multiplies given RGB color by the multiplier.
	 * 
	 * @param color RGB color.
	 * @param multiplier A integer in range [0, 255]
	 * @return A color, that has been multiplied by the given number.
	 */
	public static int multiplyRGB(int color, int multiplier){
		int rb = (((color & 0xFF00FF) * multiplier) >> 8) & 0xFF00FF; 
		int g = (((color & 0xFF00) * multiplier) >> 8) & 0xFF00;
		
		return rb | g;
	}
	
	/**
	 * Blends two colors together.
	 * 
	 * @param color1
	 *            First color to blend.
	 * @param color2
	 *            Second color to blend.
	 * @param factor
	 *            How much of the second color to blend. 0 will return only
	 *            color1, 256 will return only color2.
	 * @return Blended color.
	 */
	public static int blend(int color1, int color2, int factor) {
		int f1 = 256 - factor;
		return ((((color1 & 0xFF00FF) * f1 + (color2 & 0xFF00FF) * factor) & 0xFF00FF00) | (((color1 & 0x00FF00) * f1 + (color2 & 0x00FF00) * factor) & 0x00FF0000)) >>> 8;
	}

	/**
	 * Combines all colors to ARGB value.
	 * 
	 * @param a
	 *            Alpha.
	 * @param r
	 *            Red.
	 * @param g
	 *            Green.
	 * @param b
	 *            Blue.
	 * @return All color channels combined into single integer.
	 */
	public static int toARGB(int a, int r, int g, int b) {
		return a << 24 | r << 16 | g << 8 | b;
	}

	/**
	 * Gets single color channel from ARGB integer.
	 * 
	 * @param colARGB
	 *            ARGB integer.
	 * @param colorComponent
	 *            Color channel to get.
	 * @return Color channel.
	 */
	public static int getColor(int colARGB, EColorComponent colorComponent) {
		if(colorComponent == EColorComponent.ALPHA)
			return (colARGB & colorComponent.mask) >>> colorComponent.shift;
		
		return (colARGB & colorComponent.mask) >> colorComponent.shift;
	}

}
