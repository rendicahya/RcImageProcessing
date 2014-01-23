package net.rendicahya.imageprocessing.imagej;

public class ImageJUtils {

    public static byte[][] toTwoDimensionalArray(byte[] input, int width) {
        int height = input.length / width;
        byte[][] output = new byte[width][height];

        for (int y = 0, i = 0; y < height; y++) {
            for (int x = 0; x < width; x++, i++) {
                output[x][y] = input[i];
            }
        }

        return output;
    }

    public static byte[] toOneDimensionalArray(byte[][] input) {
        int width = input.length;
        int height = input[0].length;

        int length = width * height;
        byte[] output = new byte[length];

        for (int y = 0, i = 0; y < height; y++) {
            for (int x = 0; x < width; x++, i++) {
                output[i] = input[x][y];
            }
        }

        return output;
    }
}
