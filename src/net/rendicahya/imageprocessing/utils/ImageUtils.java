package net.rendicahya.imageprocessing.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageUtils {

    public static BufferedImage copy(BufferedImage input) {
        ColorModel colorModel = input.getColorModel();
        WritableRaster raster = input.copyData(input.getRaster().createCompatibleWritableRaster());

        return new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
    }

    public static BufferedImage create(int width, int height, int type, Color black) {
        BufferedImage output = new BufferedImage(width, height, type);
        Graphics graphics = output.getGraphics();

        graphics.drawRect(0, 0, width, height);
        graphics.dispose();

        return output;
    }

}
