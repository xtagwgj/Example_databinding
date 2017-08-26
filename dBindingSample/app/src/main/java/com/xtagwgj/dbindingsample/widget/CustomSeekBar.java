package com.xtagwgj.dbindingsample.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.xtagwgj.dbindingsample.R;

/**
 * 自定义的seekBar
 * Created by xtagwgj on 2017/8/7.
 */
public class CustomSeekBar extends View {

    //选中时候的颜色
    private int selectedColor;
    //未选中时候的字体颜色
    private int normalTextColor;
    //未选中的背景
    private int cs_unselected_color;
    //文字的大小
    private float textSize;
    //分割线的颜色
    private int splitColor;

    //seekbar文字的集合
    private CharSequence[] textArray;

    private int maxStalls = 0;

    //默认选中的位置
    private int selectedIndex = 0;

    //最大的文字距离
    private float maxMeasureText = 0F;
    //一半按钮的宽度
    private float halfButtonWidth = 0F;
    //左边的文字是否随位置变化
    private boolean leftTextChangeByPos;

    private Bitmap bitmap;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float progressStart = 0;
    private float progressEnd = 0;
    private float perSize = 0;

    private GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return moveSeek(e, false);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return moveSeek(e2, false);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return moveSeek(e2, false);
        }

        private boolean moveSeek(MotionEvent event, boolean isSingleTap) {
            if (event.getX() < progressStart - halfButtonWidth || event.getX() > progressEnd + halfButtonWidth)
                return false;

            int curr = (int) ((event.getX() - (progressStart - halfButtonWidth)) / perSize);

            if (curr != selectedIndex) {
                selectedIndex = curr;
                postInvalidate();
            }

            if (isSingleTap && onSeekChangeListener != null)
                onSeekChangeListener.onChange(CustomSeekBar.this, selectedIndex);

            return true;
        }

    });

    public CustomSeekBar(Context context) {
        this(context, null);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5F);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray;
        if (attrs != null) {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSeekBar);

            leftTextChangeByPos = typedArray.getBoolean(R.styleable.CustomSeekBar_cs_leftText_by_pos,
                    true);

            selectedColor = typedArray.getColor(R.styleable.CustomSeekBar_cs_selected_color,
                    Color.parseColor("#00BF9E"));

            normalTextColor = typedArray.getColor(R.styleable.CustomSeekBar_cs_normal_textcolor,
                    Color.parseColor("#AEADBA"));

            cs_unselected_color = typedArray.getColor(R.styleable.CustomSeekBar_cs_normal_textcolor,
                    Color.parseColor("#EFEEF1"));

            textSize = typedArray.getDimension(R.styleable.CustomSeekBar_cs_text_size,
                    sp2px(context, 16F));

            splitColor = typedArray.getColor(R.styleable.CustomSeekBar_cs_split_color,
                    Color.parseColor("#DDDCDE"));

            Drawable buttonDrawable = typedArray.getDrawable(R.styleable.CustomSeekBar_cs_button_res);
            if (buttonDrawable == null)
//                buttonDrawable = context.getDrawable(R.drawable.button_seekbar_selector);
                buttonDrawable = context.getDrawable(R.mipmap.icon_control_bar);

            if (bitmap == null)
                bitmap = drawableToBitmap(buttonDrawable);


            textArray = typedArray.getTextArray(R.styleable.CustomSeekBar_cs_text_array);

            if (textArray == null || textArray.length == 0)
                textArray = context.getResources().getStringArray(R.array.array_mode);

            maxStalls = typedArray.getInt(R.styleable.CustomSeekBar_cs_max_stalls, textArray.length);

            if (maxStalls > textArray.length)
                maxStalls = textArray.length;

            selectedIndex = typedArray.getInt(R.styleable.CustomSeekBar_cs_selected_index, 0);

            typedArray.recycle();
        }

        paint.setTextSize(textSize);

        //计算最大的文字距离
        for (CharSequence charSequence : textArray) {
            if (maxMeasureText < paint.measureText(charSequence.toString()))
                maxMeasureText = paint.measureText(charSequence.toString());
        }

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        halfButtonWidth = MeasureSpec.getSize(heightMeasureSpec) / 2;
//        width = MeasureSpec.getSize(widthMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画最左方文字
        paint.setTypeface(Typeface.DEFAULT);
        paint.setColor(selectedColor);
        canvas.drawText(
                textArray[leftTextChangeByPos ? selectedIndex : 0].toString(),
                0, getHeight() / 2 + paint.measureText("档") / 3,
                paint);

        progressStart = maxMeasureText + halfButtonWidth;
        progressEnd = getWidth() - progressStart;

        perSize = (getWidth() - progressStart * 2) / (maxStalls - 1);

        paint.setStyle(Paint.Style.FILL);
        //画进度条
        canvas.drawRoundRect(progressStart, getHeight() / 3,
                progressStart + perSize * selectedIndex, getHeight() * 2 / 3,
                getHeight() / 6, getHeight() / 6,
                paint);

        paint.setColor(cs_unselected_color);
        canvas.drawRoundRect(progressStart + perSize * selectedIndex, getHeight() / 3,
                progressEnd, getHeight() * 2 / 3,
                getHeight() / 6, getHeight() / 6,
                paint);

        //画分割线
        paint.setStrokeWidth(4);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(splitColor);
        for (int i = 1; i < maxStalls - 1; i++) {
            canvas.drawLine(
                    progressStart + perSize * i, getHeight() / 3,
                    progressStart + perSize * i, getHeight() * 2 / 3,
                    paint);
        }

        //画按钮
        canvas.drawBitmap(bitmap,
                new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                new Rect(
                        (int) (progressStart + perSize * selectedIndex - halfButtonWidth * 0.8),
                        (int) (getHeight() * 0.1),
                        (int) (progressStart + perSize * selectedIndex + halfButtonWidth * 0.8),
                        (int) (getHeight() * 0.9)),
                paint
        );

        //画最右方文字
        paint.setTypeface(Typeface.DEFAULT);
        paint.setColor(normalTextColor);
        paint.setTextSize(textSize);
        canvas.drawText(textArray[maxStalls - 1].toString(), progressEnd + halfButtonWidth, getHeight() / 2 + paint.measureText("档") / 3, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled())
            if (mGestureDetector != null) {
                return mGestureDetector.onTouchEvent(event);
            }

        return super.onTouchEvent(event);
    }

    /**
     * 解决滑动不流畅的问题
     * cause：recyclerview和自定义控件之间的滑动冲突
     *
     * @param ev 时间
     * @return
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:            // 要求父控件不拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
//                isChanged = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                if (onSeekChangeListener != null) {
                    onSeekChangeListener.onChange(this, selectedIndex);
                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    /**
     * 设置档位
     */
    public void setSelectedIndex(int selectedIndex) {
        setSelectedIndex(null, selectedIndex);
    }


    public int getSelectedIndex() {
        return selectedIndex;
    }


    /**
     * 设置档位
     */
    public void setSelectedIndex(CharSequence[] textArray, int selectedIndex) {
        if (textArray != null && textArray.length > 0) {
            this.textArray = textArray;
            maxStalls = textArray.length;
        }

        if (selectedIndex > maxStalls - 1)
            this.selectedIndex = maxStalls - 1;
        else
            this.selectedIndex = selectedIndex;

        invalidate();
    }

    /**
     * 获取当前选中的进度条文字
     */
    public String getSelectedString() {
        return textArray[selectedIndex].toString();
    }

    //seek变化的监听
    public interface OnSeekChangeListener {
        void onChange(CustomSeekBar seekBar, int pos);
    }

    private OnSeekChangeListener onSeekChangeListener;


    public void setOnSeekChangeListener(OnSeekChangeListener onSeekChangeListener) {
        this.onSeekChangeListener = onSeekChangeListener;
    }
}