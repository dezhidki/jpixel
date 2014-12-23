package com.jpixel.image;

/**
 * A sprite sheet; a bitmap that contains smaller bitmaps (sprites) of the same
 * size.
 * 
 * @author Denis Zhidkikh
 * @version 1.0
 * @since 27.10.2013
 * 
 */
public class SpriteSheet {
	protected int columns, rows, spriteWidth, spriteHeight;
	protected Bitmap[][] sheet;

	/**
	 * Initializes the sprite sheet.
	 * 
	 * @param b
	 *            Bitmap that contains the sprites.
	 * @param spriteWidth
	 *            Width of a single sprite.
	 * @param spriteHeight
	 *            Height of a single sprite.
	 */
	public SpriteSheet(Bitmap b, int spriteWidth, int spriteHeight) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.columns = b.width / spriteWidth;
		this.rows = b.height / spriteHeight;

		sheet = new Bitmap[rows][columns];

		initSpriteSheet(b);
	}

	private void initSpriteSheet(Bitmap b) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				Bitmap bb = new Bitmap(spriteWidth, spriteHeight);
				bb.blit(b, 0, 0, c * spriteWidth, r * spriteHeight, bb.width, bb.height);
				sheet[r][c] = bb;
			}
		}
	}

	/**
	 * Get a sprite.
	 * 
	 * @param column
	 *            Column where the sprite is located.
	 * @param row
	 *            Row where the sprite is located.
	 * @return A sprite at given row and column.
	 */
	public Bitmap getSprite(int column, int row) {
		if (column < 0 || column >= columns || row < 0 || row >= rows)
			return null;

		return sheet[row][column];
	}

	/**
	 * @return Columns in the sprite sheet.
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return Rows in the sprite sheet.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 
	 * @return Width of a single sprite.
	 */
	public int getSpriteWidth() {
		return spriteWidth;
	}

	/**
	 * @return Height of a single sprite.
	 */
	public int getSpriteHeight() {
		return spriteHeight;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[SPRITESHEET] ");
		sb.append("Rows: ").append(rows).append(" Columns: ").append(columns);
		sb.append(" Sprite: ").append(spriteWidth).append("x").append(spriteHeight);
		sb.append(" Hash: ").append(Integer.toHexString(hashCode()));
		return sb.toString();
	}
}
