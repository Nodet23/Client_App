package com.example.croxas.wow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Croxas on 17/6/18.
 */

public class AntagonistSprite {
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
    Rect src;
    Rect dst;

    public AntagonistSprite(Bitmap bmp) {
        image = bmp;
        this.imageWidth = bmp.getWidth() / BMP_COLUMNS;
        this.imageHeight = bmp.getHeight() / BMP_ROWS;
        x = screenWidth / 2 -100;
        y = screenHeight / 2 -100;
        System.out.println("--ALTURA: " + imageHeight + " y anchura: " + imageWidth);
    }

    public void draw(Canvas canvas) {
        //update();
        int srcX = currentFrameX * imageWidth;
        int srcY = currentFrameY * imageHeight;
        src = new Rect(srcX, srcY, srcX + imageWidth, srcY + imageHeight);
        dst = new Rect(x, y, x + imageWidth, y + imageHeight);
        canvas.drawBitmap(image, src, dst, null);
    }

    boolean collisionProta(int tecla, CharacterSprite characterSprite){
        boolean collision = false;
        switch (tecla){
            case 0: //top
                if (((characterSprite.x >= x && characterSprite.x <= x+imageWidth)||(characterSprite.x + characterSprite.imageWidth >= x && characterSprite.x + characterSprite.imageWidth <= x+imageWidth))
                        && (characterSprite.y -20 <= y + (imageHeight)) && characterSprite.y -20 >= y)
                {
                    collision = true;
                    characterSprite.moveUp(collision, y+imageHeight+1);
                }

                break;
            case 1: //down
                if (((characterSprite.x >= x && characterSprite.x <= x+imageWidth)||(characterSprite.x + characterSprite.imageWidth >= x && characterSprite.x + characterSprite.imageWidth <= x+imageWidth))
                        && (characterSprite.y +20 + characterSprite.imageHeight >= y) && characterSprite.y +20 + characterSprite.imageHeight <= y+imageHeight)
                {
                    collision = true;
                    characterSprite.moveDown(collision, y-imageHeight-1);                }
                break;
            case 2: //right
                if ((characterSprite.x +20 + characterSprite.imageWidth >= x && characterSprite.x +20 + characterSprite.imageWidth <= x+imageWidth)
                        && ((characterSprite.y >= y -20 && characterSprite.y <= y+imageHeight +20)||(characterSprite.y + characterSprite.imageHeight>= y -20 && characterSprite.y + characterSprite.imageHeight <= y+imageHeight +20)))
                {
                    collision = true;
                    characterSprite.moveRight(collision, x-imageWidth-1);
                }
                break;
            case 3: //left
                if ((characterSprite.x -20 >= x && characterSprite.x -20 <= x+imageWidth)
                        && ((characterSprite.y >= y -20 && characterSprite.y <= y+imageHeight +20)||(characterSprite.y + characterSprite.imageHeight>= y -20 && characterSprite.y + characterSprite.imageHeight <= y+imageHeight +20)))
                {
                    collision = true;
                    characterSprite.moveLeft(collision, x+imageWidth+1);
                }
                break;
        }
        return collision;
    }
}
