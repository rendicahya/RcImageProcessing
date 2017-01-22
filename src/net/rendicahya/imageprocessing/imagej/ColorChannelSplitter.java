package net.rendicahya.imageprocessing.imagej;

import ij.ImagePlus;
import ij.ImageStack;
import ij.process.ColorProcessor;

public class ColorChannelSplitter {

    private ColorChannelSplitter() {
    }

    public static ImagePlus[] split(ImagePlus imp) {
        int w = imp.getWidth();
        int h = imp.getHeight();

        ImageStack rgbStack = imp.getStack();
        ImageStack redStack = new ImageStack(w, h);
        ImageStack greenStack = new ImageStack(w, h);
        ImageStack blueStack = new ImageStack(w, h);
        byte[] r, g, b;
        ColorProcessor cp;
        int n = rgbStack.getSize();

        for (int i = 1; i <= n; i++) {
            r = new byte[w * h];
            g = new byte[w * h];
            b = new byte[w * h];
            cp = (ColorProcessor) rgbStack.getProcessor(1);

            cp.getRGB(r, g, b);
            rgbStack.deleteSlice(1);
            redStack.addSlice(null, r);
            greenStack.addSlice(null, g);
            blueStack.addSlice(null, b);
        }

        String title = imp.getTitle();

        return new ImagePlus[]{
            new ImagePlus(title + " (red)", redStack),
            new ImagePlus(title + " (green)", greenStack),
            new ImagePlus(title + " (blue)", blueStack)
        };
    }
}
