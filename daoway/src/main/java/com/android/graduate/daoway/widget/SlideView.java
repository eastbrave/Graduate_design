package com.android.graduate.daoway.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.graduate.daoway.R;


/**
 * by y on 2016/6/7.
 */
@SuppressWarnings("ALL")
public class SlideView extends View {

    private static final String TAG = "SlideView";

    private String[] mark;

    private OnTouchListener listener;

    private Paint mPaint;

    private TextView promptBox;

    private boolean isTouch = false;


    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPromptBox();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mark = new String[]{"*",  "#", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N",  "P", "Q", "R", "S", "T",  "W", "X", "Y", "Z"};
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.listener = onTouchListener;
    }

    private void initPromptBox() {
        promptBox = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.index_box_layout, null);
        promptBox.setTextSize(40);
        promptBox.setTextColor(Color.BLACK);
        promptBox.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams =
                new WindowManager.LayoutParams(150, 150,
                        WindowManager.LayoutParams.TYPE_APPLICATION,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(promptBox, layoutParams);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int textHeight = getHeight() / mark.length;
        canvas.drawColor(Color.parseColor("#00ffffff"));//背景色透明
        for (int i = 0; i < mark.length; i++) {
            canvas.drawText(mark[i], getWidth() / 2, textHeight + (i * textHeight), mPaint);
        }



        Log.i(TAG, "onDraw  -->");
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        float y = event.getY();
        int letter = (int) (y / getHeight() * mark.length);

        if (letter < 0 || letter > mark.length - 1) {
            isTouch = false;
            invalidate();
            promptBox.setVisibility(GONE);
            return false;
        }

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:

                isTouch = true;
                touchIndex(letter);

                Log.i(TAG, "dispatchTouchEvent -- >  ACTION_MOVE    ===    " + mark[letter]);

                break;

            case MotionEvent.ACTION_DOWN:

                isTouch = true;
                touchIndex(letter);

                Log.i(TAG, "dispatchTouchEvent -- >  ACTION_DOWN    ===    " + mark[letter]);

                break;

            case MotionEvent.ACTION_UP:

                isTouch = false;
                promptBox.setVisibility(GONE);

                break;
        }

        invalidate();
        return true;
    }

    private void touchIndex(int letter) {

        if (letter >= 0 && letter < mark.length) {

            listener.onTouch(mark[letter]);
            promptBox.setVisibility(VISIBLE);
            promptBox.setText(mark[letter]);

        }

    }


    public String[] getMark() {
        return mark;
    }

    public void setMark(String[] mark) {
        this.mark = mark;
    }

    public interface OnTouchListener {

        void onTouch(String letter);

    }
}
