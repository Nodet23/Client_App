package com.example.croxas.wow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Croxas on 13/6/18.
 */

public class TierraSprite {
    private Bitmap image;
    private int imageWidth;
    private int imageHeight;
    int cocienteWidth;
    int inicioWidth;
    int cocienteHeight;
    int inicioHeight;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    //private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenHeight = screenWidth;
    int unidadV;

    public TierraSprite(Bitmap bmp) {
        image = bmp;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        unidadV = screenHeight / 10;
        cocienteWidth = (int) Math.floor(screenWidth/imageWidth);
        inicioWidth = (screenWidth-(cocienteWidth*imageWidth))/2;

        cocienteHeight = (int) Math.floor(screenHeight/imageHeight);
        inicioHeight = (screenHeight-(cocienteHeight*imageHeight))/2;

        System.out.println("--ALTURA: "+imageHeight+ " y anchura: "+imageWidth);
    }

    public void draw(Canvas canvas) {
        update();
        for (int i=0; i+imageWidth<screenWidth; i=i+imageWidth){

            for (int j=0; j+imageHeight<screenHeight; j=j+imageHeight){
                canvas.drawBitmap(image, inicioWidth+i, inicioHeight+j, null);
            }
        }

    }

    public void update(){

    }
}