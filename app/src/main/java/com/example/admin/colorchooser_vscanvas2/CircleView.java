package com.example.admin.colorchooser_vscanvas2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View {

    OnPressed onPressed;

    Paint paint;
    Bitmap bitmapAddColor, bitmapChoose;
    int width, height, radius;
    int padding = 10;
    private final int count = 12;
    int vWidth;
    int vHeight;
    int indexX, indexY = 0;
    int indexPressed = 1;
    private int selected = 1;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        canvas.drawBitmap(bitmapAddColor, padding, padding/2, null);

        for (int i = 1; i <= count ; i++) {
                paint.setColor(ColorUtil.instance().getColorFromCollections(i-1));
                if (i<=count/2) {
                    indexX = i;
                    indexY = 1;
                } else {
                    indexX = count - i;
                    indexY = 2;
                }
                canvas.drawCircle(vWidth*indexX+vWidth/2, vHeight*indexY-vHeight/2, radius, paint);
            }

        if (indexPressed==0) {
            if (onPressed!=null) {
                onPressed.addNewColor();
            }
        } else {
            if (selected!=indexPressed) {
                selected = indexPressed;
                if (onPressed != null) {
                    onPressed.changeColor(ColorUtil.instance().getColorFromCollections(selected));
                }
            }
        }

        indexX = selected >= count/2 ? vWidth*(selected-count/2) : vWidth*selected;
        indexY = selected >= count/2 ? vHeight+padding : padding;
        canvas.drawBitmap(bitmapChoose, indexX+radius-padding, indexY+radius-padding*2, null);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            width = getWidth();
            height = getHeight();

            vWidth = width/6;
            vHeight = height/2;

            radius = Math.min(vHeight, vWidth)/2-padding;

            bitmapAddColor = BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_circle_outline_white_48dp);
            bitmapAddColor = Bitmap.createScaledBitmap(bitmapAddColor,radius*2+padding*2,radius*2+padding*2,true);

            bitmapChoose = BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_grey_400_24dp);
            //bitmapChoose = Bitmap.createScaledBitmap(bitmapChoose,radius*2,radius*2,true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    indexX = (int) (event.getX()/vWidth);
                    indexY = (int) (event.getY()/vHeight);
                    indexPressed = indexY>0 ? indexX+count/2 : indexX;
                    invalidate();
            }
            return true;
    }

    public void setOnPressed(OnPressed onPressed) {
        this.onPressed = onPressed;
    }

    public interface OnPressed{
        void changeColor(int color);
        void addNewColor();
    }
}
