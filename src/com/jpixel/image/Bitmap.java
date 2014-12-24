package com.jpixel.image;

import com.jpixel.math.MathUtils;

/**
 * A container for 2D pixel data. The class includes some useful functions to
 * write color/image data.
 *
 * @author Denis Zhidkikh
 * @version 1.1
 * @since 26.10.2013
 */
public class Bitmap {
    protected int width, height;

    /**
     * Raw pixel data.
     */
    public int[] pixels;

    /**
     * Initializes an empty bitmap.
     *
     * @param width  Width of the bitmap.
     * @param height Height of the bitmap.
     */
    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    /**
     * Fills the whole bitmap with a single color, thus clearing the bitmap.
     *
     * @param color Color to fill with.
     */
    public void clear(int color) {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = color;
    }

    /**
     * Fills an area of the bitmap with a single color.
     *
     * @param color Color to fill with.
     * @param x     Position where to begin filling (X coordinate).
     * @param y     Position where to begin filling (Y coordinate).
     * @param w     Width of the area to fill.
     * @param h     Height of the area to fill.
     */
    public void fill(int color, int x, int y, int w, int h) {
        int x0 = x < 0 ? 0 : x;
        int x1 = x0 + w;
        int y0 = y < 0 ? 0 : y;
        int y1 = y0 + h;
        if (y1 >= height) y1 = height - 1;
        if (x1 >= width) x1 = width - 1;

        for (int yp = y0; yp <= y1; yp++)
            for (int xp = x0; xp <= x1; xp++)
                pixels[xp + yp * width] = color;
    }

    /**
     * Performs a Block Image Transfer (blit) operation. Copies all of the pixel data from given bitmap onto this one.
     *
     * @param b      Bitmap to copy.
     * @param xStart X coordinate position on this bitmap to which begin copying. Can be negative.
     * @param yStart Y coordinate position on this bitmap to which begin copying. Can be negative.
     */
    public void blit(Bitmap b, int xStart, int yStart) {
        int x0 = xStart < 0 ? 0 : xStart;
        int x1 = xStart + b.width;
        int y0 = yStart < 0 ? 0 : yStart;
        int y1 = yStart + b.height;

        if (y1 > height) y1 = height;
        if (x1 > width) x1 = width;

        for (int yp = y0; yp < y1; yp++) {
            int tp = yp * width;
            int bp = (yp - yStart) * b.width - xStart;

            for (int xp = x0; xp < x1; xp++) {
                pixels[xp + tp] = b.pixels[xp + bp];
            }
        }
    }

    /**
     * Performs a Block Image Transfer (blit) operation. Copies part of some part of pixel data from the given bitmap onto this one.
     *
     * @param b      Bitmap to copy.
     * @param xStart X coordinate position on this bitmap to which begin copying.
     * @param yStart Y coordinate position on this bitmap to which begin copying.
     * @param xb     X coordinate position on the given bitmap from which begin copying.
     * @param yb     Y coordinate position on the given bitmap from which begin copying.
     * @param w      Width of the area to copy.
     * @param h      Height of the area to copy.
     */
    public void blit(Bitmap b, int xStart, int yStart, int xb, int yb, int w, int h) {
        if (w < 0 || h < 0) return;
        if (w > b.width - xb) w = b.width - xb;
        if (h > b.height - yb) h = b.height - yb;
        if (!(xb >= 0 && yb >= 0 && xb <= w && yb <= h)) return;

        int x0 = xStart < 0 ? 0 : xStart;
        int x1 = xStart + w;
        int y0 = yStart < 0 ? 0 : yStart;
        int y1 = yStart + h;

        if (y1 > height) y1 = height;
        if (x1 > width) x1 = width;

        for (int yp = y0; yp < y1; yp++) {
            int tp = yp * width;
            int sp = (yp - yStart + yb) * b.width - (xStart - xb);

            for (int xp = x0; xp < x1; xp++) {
                pixels[tp + xp] = b.pixels[xp + sp];
            }
        }
    }

    /**
     * Gets the width of this bitmap.
     *
     * @return Width of this bitmap.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the width of this bitmap.
     *
     * @return Width of this bitmap.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Creates a resized version of the given bitmap using "nearest neighbour" approach.
     *
     * @param b      The bitmap to resize.
     * @param width  The width of the resized bitmap.
     * @param height The height of the resized bitmap.
     * @return A new instance of {@link Bitmap} which is resized version of <b>b</b>.
     */
    public static Bitmap resize(Bitmap b, int width, int height) {
        Bitmap result = new Bitmap(width, height);
        double sw = (double) b.width / width;
        double sh = (double) b.height / height;

        for (int y = 0; y < height; y++) {
            int yy = (int) (y * sh);
            for (int x = 0; x < width; x++) {
                int xx = (int) (x * sw);

                result.pixels[x + y * width] = b.pixels[xx + yy * b.width];
            }
        }

        return result;
    }

    /**
     * Creates a copy of the given bitmap.
     *
     * @param b Bitmap to create a copy from.
     * @return Copy of the given bitmap.
     */
    public static Bitmap copy(Bitmap b) {
        Bitmap result = new Bitmap(b.width, b.height);
        result.blit(b, 0, 0);
        return result;
    }

    /**
     * Rotates the bitmap around its center and outputs the result as a new object.
     *
     * @param b Bitmap to rotate.
     * @param angle Angle of rotation in radians.
     * @return An object of {@link com.jpixel.image.Bitmap} containing rotated pixels.
     */
    public static Bitmap rotate(Bitmap b, double angle){
        double vx_x = MathUtils.rotate_x(angle, 1.0, 0.0);
        double vx_y = MathUtils.rotate_y(angle, 1.0, 0.0);
        double vy_x = MathUtils.rotate_x(angle, 0.0, 1.0);
        double vy_y = MathUtils.rotate_y(angle, 0.0, 1.0);

        double ww = Math.max(Math.abs(vx_x * b.width - vy_x * b.height), Math.abs(vx_x * b.width + vy_x * b.height));
        double hh = Math.max(Math.abs(vx_y * b.width - vy_y * b.height), Math.abs(vx_y * b.width + vy_y * b.height));

        int w = (int) Math.round(ww);
        int h = (int) Math.round(hh);

        int cx = (int) ((w - b.width) / 2.0);
        int cy = (int) ((h - b.height) / 2.0);

        double nx0 = MathUtils.rotate_x(angle, -b.width / 2 - cx, -b.height / 2 - cy) + b.width / 2;
        double ny0 = MathUtils.rotate_y(angle, -b.width / 2 - cx, -b.height / 2 - cy) + b.height / 2;

        if(nx0 >= b.width) nx0--;
        if(ny0 >= b.height) ny0--;

        Bitmap result = new Bitmap(w, h);

        for (int y = 0; y < h; y++) {
            double pos2_x = nx0 + (y * vy_x);
            double pos2_y = ny0 + (y * vy_y);
            for (int x = 0; x < w; x++) {
                int xx = (int) (pos2_x + (x * vx_x));
                int yy = (int) (pos2_y + (x * vx_y));
                if(xx < 0 || xx >= b.width || yy < 0 || yy >= b.height) continue;
                result.pixels[x + y * w] = b.pixels[xx + yy * b.width];
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[BITMAP] ");
        sb.append(width).append("x").append(height);
        sb.append(" Hash: ").append(Integer.toHexString(hashCode()));
        return sb.toString();
    }
}
