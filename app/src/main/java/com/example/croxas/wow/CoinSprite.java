package com.example.croxas.wow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Croxas on 18/6/18.
 */

public class CoinSprite {

    private Bitmap image;
    public int imageWidth;
    public int imageHeight;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = screenWidth;
    public int x;
    public int y;
    Rect dst;

    public CoinSprite(Bitmap bmp) {
        image = bmp;
        this.imageWidth = bmp.getWidth() /3;
        this.imageHeight = bmp.getHeight() /4;
        x = screenWidth/2 +100;
        y = screenHeight/2 +100;
        System.out.println("--ALTURA: " + imageHeight + " y anchura: " + imageWidth);
    }
    public CoinSprite(Bitmap bmp, int x, int y) {
        image = bmp;
        this.imageWidth = bmp.getWidth() /3;
        this.imageHeight = bmp.getHeight() /4;
        this.x = x;
        this.y = y;
        System.out.println("--ALTURA: " + imageHeight + " y anchura: " + imageWidth);
    }

    public void draw(Canvas canvas) {
        //update();
        dst = new Rect(x, y, x + imageWidth, y + imageHeight);
        canvas.drawBitmap(image, null, dst, null);
    }

    boolean collisionProta(int tecla, CharacterSprite characterSprite){
        boolean collision = false;
        switch (tecla){
            case 0: //top
                if (((characterSprite.x >= x && characterSprite.x <= x+imageWidth)||(characterSprite.x + characterSprite.imageWidth >= x && characterSprite.x + characterSprite.imageWidth <= x+imageWidth))
                        && (characterSprite.y -20 <= y + (imageHeight)) && characterSprite.y -20 >= y)
                {
                    collision = true;
                    characterSprite.moveUp(false, y+imageHeight+1);
                }
                break;
            case 1: //down
                if (((characterSprite.x >= x && characterSprite.x <= x+imageWidth)||(characterSprite.x + characterSprite.imageWidth >= x && characterSprite.x + characterSprite.imageWidth <= x+imageWidth))
                        && (characterSprite.y +20 + characterSprite.imageHeight >= y) && characterSprite.y +20 + characterSprite.imageHeight <= y+imageHeight)
                {
                    collision = true;
                    characterSprite.moveDown(false, y-imageHeight-1);                }
                break;
            case 2: //right
                if ((characterSprite.x +20 + characterSprite.imageWidth >= x && characterSprite.x +20 + characterSprite.imageWidth <= x+imageWidth)
                        && ((characterSprite.y >= y -20 && characterSprite.y <= y+imageHeight +20)||(characterSprite.y + characterSprite.imageHeight>= y -20 && characterSprite.y + characterSprite.imageHeight <= y+imageHeight +20)))
                {
                    collision = true;
                    characterSprite.moveRight(false, x-imageWidth-1);
                }
                break;
            case 3: //left
                if ((characterSprite.x -20 >= x && characterSprite.x -20 <= x+imageWidth)
                        && ((characterSprite.y >= y -20 && characterSprite.y <= y+imageHeight +20)||(characterSprite.y + characterSprite.imageHeight>= y -20 && characterSprite.y + characterSprite.imageHeight <= y+imageHeight +20)))
                {
                    collision = true;
                    characterSprite.moveLeft(false, x+imageWidth+1);
                }
                break;
        }
        return collision;
    }

}
