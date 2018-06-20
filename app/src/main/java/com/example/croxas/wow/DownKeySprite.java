package com.example.croxas.wow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Croxas on 17/6/18.
 */

public class DownKeySprite {

    private Bitmap image;
    private int imageWidth;
    private int imageHeight;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = screenWidth;
    private static final int BMP_ROWS = 2;
    private static final int BMP_COLUMNS = 3;
    private int x;
    private int y;

    public DownKeySprite(Bitmap bmp) {
        image = bmp;
        this.imageWidth = bmp.getWidth() / BMP_COLUMNS;
        this.imageHeight = bmp.getHeight() / BMP_ROWS;
        x = screenWidth/2 - imageWidth/2;
        y = screenHeight+125 + imageHeight;
        System.out.println("--ALTURA: "+imageHeight+ " y anchura: "+imageWidth);
    }

    public void draw(Canvas canvas) {
        update();
        int srcX = 1 * imageWidth;
        int srcY = 1 * imageHeight;
        Rect src = new Rect(srcX, srcY, srcX + imageWidth, srcY + imageHeight);
        Rect dst = new Rect(x, y, x + imageWidth, y + imageHeight);
        canvas.drawBitmap(image, src, dst, null);
    }

    public void update(){
    }

    public Bitmap getImage() {
        return image;
    }

    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + imageWidth && y2 > y && y2 < y + imageHeight;
    }

}
