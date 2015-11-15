package cn.bingoogolapple.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/15 下午3:47
 * 描述:
 */
public class BGAMonthView extends View {
    private int mPortionSize;
    private int mSmallPortionSize;

    private Paint mPaint;

    private int mSelectedBackgroundColor;
    private int mDayTextColor;
    private int mTopBackgroundColor;
    private int mCenterBackgroundColor;
    private int mBottomBackgroundColor;

    private int mDayTextSize;

    private Rect mPortionRect;
    private Rect mTopRect;
    private Rect mCenterRect;
    private Rect mBottomRect;

    public BGAMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mSelectedBackgroundColor = getResources().getColor(R.color.bga_calendar_selectedBackgroundColor);
        mDayTextColor = getResources().getColor(R.color.bga_calendar_dayTextColor);
        mTopBackgroundColor = getResources().getColor(R.color.bga_calendar_topBackgroundColor);
        mCenterBackgroundColor = getResources().getColor(R.color.bga_calendar_centerBackgroundColor);
        mBottomBackgroundColor = getResources().getColor(R.color.bga_calendar_bottomBackgroundColor);

        mDayTextSize = getResources().getDimensionPixelOffset(R.dimen.bga_calendar_dayTextSize);

        mPortionRect = new Rect();
        mTopRect = new Rect();
        mCenterRect = new Rect();
        mBottomRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mPortionSize = (int) (measureWidth / 7.0f);
        mSmallPortionSize = (int) (mPortionSize / 3.0f);
        setMeasuredDimension(widthMeasureSpec, mPortionSize * 6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                mPortionRect.set(column * mPortionSize, row * mPortionSize, (column + 1) * mPortionSize, (row + 1) * mPortionSize);
                mTopRect.set(mPortionRect.left, mPortionRect.top, mPortionRect.right, mPortionRect.top + mSmallPortionSize);
                mCenterRect.set(mPortionRect.left, mTopRect.bottom, mPortionRect.right, mTopRect.bottom + mSmallPortionSize);
                mBottomRect.set(mPortionRect.left, mCenterRect.bottom, mPortionRect.right, mCenterRect.bottom + mSmallPortionSize);

                drawBg(canvas, row, column);
                drawTop(canvas, row, column);
                drawCenter(canvas, row, column);
                drawBottom(canvas, row, column);
            }
        }
    }

    private void drawBg(Canvas canvas, int row, int column) {
        if (row == 1 && column == 2) {
            mPaint.setColor(mSelectedBackgroundColor);
            canvas.drawRect(mPortionRect, mPaint);
        }
    }

    private void drawTop(Canvas canvas, int row, int column) {
        mPaint.setColor(mTopBackgroundColor);
        canvas.drawRect(mTopRect, mPaint);
    }

    private void drawCenter(Canvas canvas, int row, int column) {
        mPaint.setColor(mCenterBackgroundColor);
        canvas.drawRect(mCenterRect, mPaint);

        mPaint.setTextSize(mDayTextSize);
        mPaint.setColor(mDayTextColor);
        canvas.drawText("(" + row + "," + column + ")", mCenterRect.centerX(), mCenterRect.bottom - (mSmallPortionSize - mDayTextSize) / 2.0f, mPaint);
    }

    private void drawBottom(Canvas canvas, int row, int column) {
        mPaint.setColor(mBottomBackgroundColor);
        canvas.drawRect(mBottomRect, mPaint);
    }
}