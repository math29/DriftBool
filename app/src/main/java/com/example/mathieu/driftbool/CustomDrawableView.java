package com.example.mathieu.driftbool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
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

    public CustomDrawableView(Context context, AttributeSet attrs) {
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

    public void update(int direction){
        switch(direction){
            case 1:
                y += 1;
                break;
            case 2:
                x += 1;
                break;
            case 3:
                y -= 1;
                break;
            case 4:
                x -= 1;
                break;
            default:
                break;
        }
        mDrawable.setBounds(x, y, x + width, y + height);
    }

    public void setX(int X){
        x = X;
    }

    public void setY(int Y){
        y = Y;
    }

    public void newOval(){
        m2Drawable = new ShapeDrawable(new OvalShape());
        m2Drawable.getPaint().setColor(0xff74AC23);
        m2Drawable.setBounds(x+100, y+100, x + width, y + height);
    }
}