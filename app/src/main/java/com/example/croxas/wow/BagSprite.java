package com.example.croxas.wow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Croxas on 19/6/18.
 */

public class BagSprite {

    private Bitmap image;
    private int imageWidth;
    private int imageHeight;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = screenWidth;
    private int x;
    private int y;

    public BagSprite(Bitmap bmp) {
        image = bmp;
        this.imageWidth = bmp.getWidth()/2;
        this.imageHeight = bmp.getHeight()/2;
        x = screenWidth/2 - imageWidth - 75;
        y = screenHeight+100;
        System.out.println("--ALTURA: "+imageHeight+ " y anchura: "+imageWidth);
    }

    public void draw(Canvas canvas) {
        update();
        Rect dst = new Rect(x, y, x + imageWidth, y + imageHeight);
        canvas.drawBitmap(image, null, dst, null);
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
