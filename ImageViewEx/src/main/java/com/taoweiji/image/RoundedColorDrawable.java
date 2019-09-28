package com.taoweiji.image;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 *
 */
public class RoundedColorDrawable extends Drawable {
    private Paint paint;
    private int color;
    private boolean circle = true;
    private int roundedCornerRadius = 0;
    private boolean roundTopLeft = false;
    private boolean roundTopRight = true;
    private boolean roundBottomLeft = true;
    private boolean roundBottomRight = true;


    public RoundedColorDrawable() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }


    @Override
    public void draw(Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        if (!circle) {
            canvas.drawRoundRect(new RectF(0, 0, width, height), roundedCornerRadius, roundedCornerRadius, paint);
            // 如果部分是直角就绘制直角覆盖物
            if (!roundTopLeft) {
                canvas.drawRect(0, 0, width / 2, height / 2, paint);
            }
            if (!roundTopRight) {
                canvas.drawRect(width / 2, 0, width, height / 2, paint);
            }
            if (!roundBottomLeft) {
                canvas.drawRect(0, height / 2, width / 2, height, paint);
            }
            if (!roundBottomRight) {
                canvas.drawRect(width / 2, height / 2, width, height, paint);
            }
        } else {
            canvas.drawCircle(getBounds().centerX(), getBounds().centerX(), width / 2, paint);
        }
        //        canvas.restore();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidateSelf();
    }

    public int getRoundedCornerRadius() {
        return roundedCornerRadius;
    }

    public void setRoundedCornerRadius(int roundedCornerRadius) {
        this.roundedCornerRadius = roundedCornerRadius;
        invalidateSelf();
    }


    public void setRounded(boolean roundTopLeft, boolean roundTopRight, boolean roundBottomLeft, boolean roundBottomRight) {
        this.roundTopLeft = roundTopLeft;
        this.roundTopRight = roundTopRight;
        this.roundBottomLeft = roundBottomLeft;
        this.roundBottomRight = roundBottomRight;
        invalidateSelf();
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
        invalidateSelf();
    }
}
