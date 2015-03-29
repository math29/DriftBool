package com.example.mathieu.driftbool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.View;

/**
 * Created by Mathieu on 27/03/2015.
 */
public class CustomDrawableView extends View{

    public ShapeDrawable mDrawable;
    private ShapeDrawable m2Drawable;
    private int x = 110;
    private int y = 110;
    private int width = 50;
    private int height = 50;
    private Canvas canvas;

    public CustomDrawableView(Context context/*, AttributeSet attrs*/) {
        super(context);

        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.getPaint().setColor(0xff74AC23);
        //mDrawable.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas c) {
        canvas = c;
        mDrawable.setBounds(x, y, x + width, y + height);
        mDrawable.draw(canvas);
    }

    public void move(int direction){
        switch(direction){
            case 1:
                y = y+30;
                break;
            case 2:
                x += 30;
                break;
            case 3:
                y -= 30;
                break;
            case 4:
                x = x-30;
                break;
            default:
                break;
        }
        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.setBounds(x, y, x + width, y + height);
        mDrawable.draw(canvas);
    }

    public void addX(int X){
        x = x+X;
        Log.v("Dans le addX ", "On est rentré dans la fonction addX !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
    }
}