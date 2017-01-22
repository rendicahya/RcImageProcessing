package net.rendicahya.imageprocessing.filters;

import java.awt.image.BufferedImage;
import net.rendicahya.imageprocessing.utils.ColorUtils;

public class SkinDetector {

    public static BufferedImage filter(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = input.getRGB(x, y);
                int[] rgb = ColorUtils.split(color);

                int r = rgb[0];
                int g = rgb[1];
                int b = rgb[2];

                int Y = (int) (0.59 * g + 0.31 * r + 0.11 * b);
                int Cr = (int) (0.713 * (r - Y));

                int YCrCb = Cr > 12 ? color : 0;

                output.setRGB(x, y, YCrCb);
            }
        }

        return output;
    }
}