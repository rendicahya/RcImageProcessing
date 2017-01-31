package net.rendicahya.imageprocessing.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.process.ImageProcessor;

public class ImageOperator {

    public static final int SCALE = 0, ADD = 1, SUBTRACT = 2, MULTIPLY = 3, DIVIDE = 4;

    private ImageOperator() {
    }

    public static ImagePlus subtract(ImagePlus i1, ImagePlus i2, int operation) {
        int width = i1.getWidth();
        int height = i1.getHeight();
        int slices1 = i1.getStackSize();
        int slices2 = i2.getStackSize(); 
        float[] ctable1 = i1.getCalibration().getCTable();
        float[] ctable2 = i2.getCalibration().getCTable();
        ImageStack stack1 = i1.getStack();
        ImageStack stack2 = i2.getStack();
        int currentSlice = i2.getCurrentSlice();
        ImagePlus output = IJ.createImage(null, i2.getType(), width, height, i2.getBitDepth());
        ImageProcessor outputProcessor = output.getProcessor();

        for (int n = 1; n <= slices2; n++) {
            ImageProcessor ip1 = stack1.getProcessor(n <= slices1 ? n : slices1);
            ImageProcessor ip2 = stack2.getProcessor(n);
            ip1.setCalibrationTable(ctable1);
            ip2.setCalibrationTable(ctable2);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    double v1 = ip1.getPixelValue(x, y);
                    double v2 = ip2.getPixelValue(x, y);

                    outputProcessor.putPixelValue(x, y, v1 - v2);
                }
            }

            if (n == currentSlice) {
                i2.getProcessor().resetMinAndMax();
                i2.updateAndDraw();
            }
        }

        return output;
    }

    public static void main(String[] args) {
        ImagePlus a = IJ.openImage("C:\\Users\\comvis\\Desktop\\a.jpg");
        ImagePlus b = IJ.openImage("C:\\Users\\comvis\\Desktop\\b.jpg");
        ImagePlus c = subtract(a, b, SUBTRACT);

        c.show();
    }
}
