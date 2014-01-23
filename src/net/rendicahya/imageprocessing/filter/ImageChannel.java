package net.rendicahya.imageprocessing.filter;

import java.awt.image.BufferedImage;
import net.rendicahya.imageprocessing.utils.ColorUtils;

public class ImageChannel {

    public static final int RED_CHANNEL = 0;
    public static final int GREEN_CHANNEL = 1;
    public static final int BLUE_CHANNEL = 2;

    public static BufferedImage toSingleChannel(BufferedImage input, int channel) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, input.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int singleChannel = ColorUtils.separate(input.getRGB(x, y))[channel];
                output.setRGB(x, y, ColorUtils.construct(singleChannel, singleChannel, singleChannel));
            }
        }

        return output;
    }
}
