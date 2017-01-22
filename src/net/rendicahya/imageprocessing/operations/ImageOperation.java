package net.rendicahya.imageprocessing.operations;

import java.awt.image.BufferedImage;
import net.rendicahya.imageprocessing.utils.ColorUtils;
import net.rendicahya.imageprocessing.utils.ImageUtils;

public class ImageOperation {

    public static BufferedImage subtract(BufferedImage image1, BufferedImage image2) {
        int width = image1.getWidth();
        int height = image1.getHeight();

        if (width != image2.getWidth() || height != image2.getHeight()) {
            System.err.println("The two images not of the same size");

            return null;
        }

        BufferedImage result = ImageUtils.copy(image1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray1 = ColorUtils.getRed(image1.getRGB(x, y));
                int gray2 = ColorUtils.getRed(image2.getRGB(x, y));
                int gray = gray1 - gray2;

                if (gray < 0) {
                    gray = 0;
                }

                result.setRGB(x, y, ColorUtils.construct(gray, gray, gray));
            }
        }

        return result;
    }
}
