package net.rendicahya.imageprocessing.filters;

import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;

public class ImageFilter {

    public static BufferedImage invert(BufferedImage input) {
        short[] data = new short[256];

        for (short i = 0; i < 256; i++) {
            data[i] = (short) (255 - i);
        }

        LookupTable lookupTable = new ShortLookupTable(0, data);
        LookupOp op = new LookupOp(lookupTable, null);

        return op.filter(input, null);
    }
}
