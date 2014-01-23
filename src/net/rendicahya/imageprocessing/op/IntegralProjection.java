package net.rendicahya.imageprocessing.op;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import net.rendicahya.imageprocessing.utils.ColorUtils;

public class IntegralProjection {

    public static final int VERTICAL_PROJECTION = 0;
    public static final int HORIZONTAL_PROJECTION = 1;
    private final Color bgColor = new Color(255, 255, 255);
    private final Color brushColor = new Color(255, 0, 0);
    private BufferedImage vProj = null;
    private BufferedImage hProj = null;
    private int[] vArray = null;
    private int[] hArray = null;
    private BufferedImage input;
    private Dimension vProjDim;
    private Dimension hProjDim;
    private int vMaxIndex;
    private int vMax = 0;
    private int hMax = 0;

    public IntegralProjection(BufferedImage input, Dimension vProjDim, Dimension hProjDim) {
        this.input = input;
        this.vProjDim = vProjDim;
        this.hProjDim = hProjDim;
    }

    /**
     * Membuat array integral projection secara vertikal.
     */
    private void initVerticalArray() {
        vArray = new int[input.getHeight()];

        final int height = input.getHeight();
        final int width = input.getWidth();

        for (int y = 0; y < height; y++) {
            vArray[y] = 0;

            for (int x = 0; x < width; x++) {
                int sum = ColorUtils.getRGBSum(input.getRGB(x, y));
                vArray[y] += sum;
            }

            if (vArray[y] > vMax) {
                vMax = vArray[y];
                vMaxIndex = y;
            }
        }
    }

    /**
     * Menggambar grafik integral projection secara vertikal.
     */
    private void drawVerticalProj() {
        if (vArray == null) {
            initVerticalArray();
        }

        int height = input.getHeight();
        vProj = new BufferedImage(vProjDim.width, vProjDim.height, BufferedImage.TYPE_INT_RGB);

        Graphics g = vProj.getGraphics();
        g.setColor(bgColor);
        g.fillRect(0, 0, vProjDim.width, vProjDim.height);

        g.setColor(brushColor);

        for (int i = 0; i < height; i++) {
            g.drawLine(0, i, (int) (((float) vArray[i] / vMax) * vProjDim.width), i);
        }

        g.dispose();
    }

    /**
     * Membuat array integral projection secara horizontal.
     */
    private void initHorizontalArray() {
        hArray = new int[input.getWidth()];

        int height = input.getHeight();
        int width = input.getWidth();

        for (int y = 0; y < width; y++) {
            hArray[y] = 0;

            for (int x = 0; x < height; x++) {
                int sum = ColorUtils.getRGBSum(input.getRGB(x, y));
                hArray[y] += sum;
            }

            if (hArray[y] > hMax) {
                hMax = hArray[y];
            }
        }
    }

    /**
     * Menggambar grafik integral projection secara horizontal.
     */
    private void drawHorizontalProj() {
        if (hArray == null) {
            initHorizontalArray();
        }

        int width = input.getWidth();
        hProj = new BufferedImage(hProjDim.width, hProjDim.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = hProj.getGraphics();
        g.setColor(bgColor);
        g.fillRect(0, 0, hProjDim.width, hProjDim.height);

        g.setColor(brushColor);

        for (int i = 0; i < width; i++) {
            g.drawLine(i, 0, i, (int) (((float) hArray[i] / hMax) * hProjDim.height));
        }

        g.dispose();
    }

    /**
     * Mendapatkan array integral projection secara vertikal.
     *
     * @return array integral projection vertikal.
     */
    public int[] getVerticalArray() {
        if (vArray == null) {
            initVerticalArray();
        }

        return vArray;
    }

    /**
     * Mendapatkan grafik integral projection secara vertikal.
     *
     * @return grafik integral projection vertikal.
     */
    public BufferedImage getVerticalProjection() {
        if (vProj == null) {
            drawVerticalProj();
        }

        return vProj;
    }

    /**
     * Mendapatkan array integral projection secara horizontal.
     *
     * @return array integral projection horizontal.
     */
    public int[] getHorizontalArray() {
        if (hArray == null) {
            initHorizontalArray();
        }

        return hArray;
    }

    /**
     * Mendapatkan grafik integral projection secara horizontal.
     *
     * @return grafik integral projection horizontal.
     */
    public BufferedImage getHorizontalProjection() {
        if (hProj == null) {
            drawHorizontalProj();
        }

        return hProj;
    }

    /**
     * Mendapatkan index dari nilai maksimum integral projection secara
     * vertikal.
     *
     * @return index dari nilai maksimum integral projection vertikal.
     */
    public int getVMaxIndex() {
        if (vArray == null) {
            initVerticalArray();
        }

        return vMaxIndex;
    }

    /**
     * Mendapatkan nilai maksimum integral projection secara vertikal.
     *
     * @return nilai maksimum integral projection vertikal.
     */
    public int getVMax() {
        if (vArray == null) {
            initVerticalArray();
        }

        return vMax;
    }
}