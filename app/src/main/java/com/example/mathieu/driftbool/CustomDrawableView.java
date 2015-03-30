package com.example.mathieu.driftbool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by Mathieu on 27/03/2015.
 */
public class CustomDrawableView extends View{

    public ShapeDrawable mDrawable;
    private ShapeDrawable m2Drawable;
    private int x;
    private int y;
    private int width = 50;
    private int height = 50;
    private Canvas canvas;
    TranslateAnimation anim;
    private int viewHeight, viewWidth;

    public CustomDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.getPaint().setColor(0xff74AC23);
        mDrawable.setBounds(x, y, x + width, y + height);
        //mDrawable.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas c) {
        canvas = c;
        mDrawable.draw(canvas);
    }

    public void moveViewToScreenCenter( CustomDrawableView view, String direction)
    {
        Log.v(" Dimensions ecran : ", "Hauteur max : "+viewHeight+" et la largeur max : "+viewWidth+ " | Hauteur actuelle : "+y+" Largeur actuelle : "+x);
        switch(direction){
            case "up":
                if(y > 0){
                    y -= 5;
                }
                break;
            case "down":
                if(y < (viewHeight-height)){
                    y += 5;
                }
                break;
            case "left":
                if(x > 0){
                    x -= 5;
                }
                break;
            case "right":
                if(x < (viewWidth-width)){
                    x += 5;
                }
                break;
            default:
                break;
        }
        //TranslateAnimation anim = new TranslateAnimation( 0, 250 , 0, 250 );
        //anim.setDuration(1000);
        anim = new TranslateAnimation( x, x , y, y );
        anim.setFillAfter( true );
        view.startAnimation(anim);
    }

    public void setViewHeight(int _height){
        viewHeight = _height;
    }

    public void setViewWidth(int _width){
        viewWidth = _width;
    }
}