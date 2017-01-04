package com.example.admin.colorchooser_vscanvas2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ColorChooser extends FrameLayout {

    private CircleView circleViews;

    public ColorChooser(Context context) {
        super(context);
    }

    public ColorChooser(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ColorChooser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attributeSet){
        circleViews = new CircleView(getContext());
        addView(circleViews);
    }
}