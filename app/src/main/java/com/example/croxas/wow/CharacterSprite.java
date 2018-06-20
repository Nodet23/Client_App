package com.example.croxas.wow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.Toast;

/**
 * Created by Croxas on 14/6/18.
 */

public class CharacterSprite {
    private Bitmap image;
    public int imageWidth;
    public int imageHeight;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = screenWidth;
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int currentFrameX = 0;
    private int currentFrameY = 0;
    public int x;
    public int y;

    public CharacterSprite(Bitmap bmp) {
        image = bmp;
        this.imageWidth = bmp.getWidth() / BMP_COLUMNS;
        this.imageHeight = bmp.getHeight() / BMP_ROWS;
        x = screenWidth/2;
        y = screenHeight/2;
        System.out.println("--ALTURA: "+imageHeight+ " y anchura: "+imageWidth);
    }

    public CharacterSprite(Bitmap bmp, int x, int y) {
        image = bmp;
        this.imageWidth = bmp.getWidth() / BMP_COLUMNS;
        this.imageHeight = bmp.getHeight() / BMP_ROWS;
        this.x = x;
        this.y = y;
        System.out.println("--ALTURA: "+imageHeight+ " y anchura: "+imageWidth);
    }

    public void draw(Canvas canvas) {
        //update();
        int srcX = currentFrameX * imageWidth;
        int srcY = currentFrameY * imageHeight;
        Rect src = new Rect(srcX, srcY, srcX + imageWidth, srcY + imageHeight);
        Rect dst = new Rect(x, y, x + imageWidth, y + imageHeight);
        canvas.drawBitmap(image, src, dst, null);
    }

    public void update(){

    }

    public void moveLeft(boolean colision, int tope){
        if (colision == true){
            x=tope;
            currentFrameY = 1;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }else{
            x=x-20;
            currentFrameY = 1;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }
    }

    public void moveRight(boolean colision, int tope){
        if (colision == true){
            x=tope;
            currentFrameY = 2;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }else{
            x=x+20;
            currentFrameY = 2;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }
    }

    public void moveUp(boolean colision, int tope){
        if (colision == true){
            y=tope;
            currentFrameY = 3;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }else{
            y=y-20;
            currentFrameY = 3;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }
    }

    public void moveDown(boolean colision, int tope){
        if (colision == true){
            y=tope;
            currentFrameY = 0;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }else{
            y=y+20;
            currentFrameY = 0;
            currentFrameX = ++currentFrameX % BMP_COLUMNS;
        }
    }
}
