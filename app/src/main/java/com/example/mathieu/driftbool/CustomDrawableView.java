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
    private ShapeDrawable mDrawable;

    public CustomDrawableView(Context context, AttributeSet attrs) {
        super(context);

        int x = 110;
        int y = 110;
        int width = 50;
        int height = 50;

        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.getPaint().setColor(0xff74AC23);
        mDrawable.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas canvas) {
        mDrawable.draw(canvas);
    }

    public void update(int direction){
        switch(direction){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}