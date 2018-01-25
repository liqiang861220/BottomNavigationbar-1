package me.sugarkawhi.mreader.anim;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;

import me.sugarkawhi.mreader.config.IReaderDirection;
import me.sugarkawhi.mreader.element.PageElement;
import me.sugarkawhi.mreader.view.MReaderView;

/**
 * page anim controller
 * Created by ZhaoZongyao on 2018/1/11.
 */

public class CoverAnimController extends PageAnimController {

    private Rect mSrcRect;
    private Rect mDstRect;
    private GradientDrawable mBackShadowDrawableLR;

    public CoverAnimController(MReaderView readerView, int readerWidth, int readerHeight, PageElement pageElement,IPageChangeListener pageChangeListener) {
        super(readerView, readerWidth, readerHeight, pageElement,pageChangeListener);
        mSrcRect = new Rect(0, 0, readerWidth, readerHeight);
        mDstRect = new Rect(0, 0, readerWidth, readerHeight);
        int[] mBackShadowColors = new int[]{0x66000000, 0x00000000};
        mBackShadowDrawableLR = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, mBackShadowColors);
        mBackShadowDrawableLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);
    }


    @Override
    void drawStatic(Canvas canvas) {
        canvas.drawBitmap(mCurrentBitmap, 0, 0, null);
    }

    //添加阴影
    public void addShadow(int left, Canvas canvas) {
        mBackShadowDrawableLR.setBounds(left, 0, left + 30, mReaderHeight);
        mBackShadowDrawableLR.draw(canvas);
    }

    @Override
    void drawMove(Canvas canvas) {
        Log.e(TAG, "drawMove: ");
        int xOffset = Math.abs(mStartX - mTouchX);
        switch (mDirection) {
            case IReaderDirection.NEXT:
                int dis = mReaderWidth - xOffset;
                if (dis > mReaderWidth) {
                    dis = mReaderWidth;
                }
                mSrcRect.left = mReaderWidth - dis;
                mDstRect.right = dis;
                canvas.drawBitmap(mNextBitmap, 0, 0, null);
                canvas.drawBitmap(mCurrentBitmap, mSrcRect, mDstRect, null);
                addShadow(dis, canvas);
                break;
            case IReaderDirection.PRE:
            case IReaderDirection.NONE:
                mSrcRect.left = mReaderWidth - xOffset;
                mDstRect.right = xOffset;
                canvas.drawBitmap(mNextBitmap, 0, 0, null);
                canvas.drawBitmap(mCurrentBitmap, mSrcRect, mDstRect, null);
                addShadow(xOffset, canvas);
                break;
        }
    }

    @Override
    void startScroll() {
        int dx;
        isScroll = true;
        switch (mDirection) {
            case IReaderDirection.NEXT:
                dx = -(mTouchX + (mReaderWidth - mStartX));
                break;
            case IReaderDirection.PRE:
                dx = (mReaderWidth - (mTouchX - mStartX));
                break;
            default:
                dx = 0;
        }
        Log.e(TAG, "startScroll: dx=" + dx);
        int duration = ((444 * Math.abs(dx)) / mReaderWidth);
        mScroller.startScroll(mTouchX, 0, dx, 0, duration);
    }



}