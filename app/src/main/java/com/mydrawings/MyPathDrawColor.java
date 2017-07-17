package com.mydrawings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by sandramac on 02/04/2017.
 */

 public class MyPathDrawColor extends View {

    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    private Paint paint;
    public int width;
    public int myColor;



    public MyPathDrawColor(Context context) {
        super(context);
        initView();
    }


    public MyPathDrawColor(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView(){
        path = new Path();
        paint = new Paint();
        myColor=Color.GRAY;
        paint.setColor(myColor);
        width=9;
        paint.setStrokeWidth(width);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawPath(path, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(),event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, paint);
                path.reset();
                invalidate();
                break;
        }
        return true;
    }


    //Clear screen
    public void clear() {
        bitmap.eraseColor(Color.WHITE);
        invalidate();
        initView();
    }

    public void erase(){
        paint.setStrokeWidth(29);
        paint.setColor(Color.WHITE);
    }
    public void increaseLine(){
        width=width+3;
        setPathColor(myColor);
    }
    public void decreaseLine(){
        width=width-3;
       setPathColor(myColor);
    }

    /**
     * change path color here
     */
    public void setPathColor(int color) {
        paint.setStrokeWidth(width);
        myColor=color;
        paint.setColor(color);

    }




}
