package com.taoweiji.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;


public abstract class ImageViewExBase extends ImageView {
    protected ScaleType scaleType = ScaleType.CENTER_CROP;
    protected int borderWidth = 0;
    protected int borderColor = Color.WHITE;
    protected boolean circle = false;
    protected int roundedCornerRadius = 0;
    protected boolean roundTopLeft = true;
    protected boolean roundTopRight = true;
    protected boolean roundBottomLeft = true;
    protected boolean roundBottomRight = true;
    protected Matrix imageMatrix;

    protected Drawable imageDrawable;
    protected int fadeDuration;

    public ImageViewExBase(Context context) {
        this(context, null);
    }

    public ImageViewExBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageViewExBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        this.imageDrawable = RoundedCornerDrawable.fromBitmap(bm, getResources());
        updateRoundedInfo();
        super.setImageDrawable(this.imageDrawable);
    }

    @Override
    public void setImageResource(int resId) {
        setImageDrawable(getResources().getDrawable(resId));
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        this.imageDrawable = RoundedCornerDrawable.fromDrawable(drawable, getResources());
        updateRoundedInfo();
        super.setImageDrawable(this.imageDrawable);
    }

    @Override
    public ScaleType getScaleType() {
        return scaleType;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        this.scaleType = scaleType;
        invalidateSelf();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        invalidateSelf();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        invalidateSelf();
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
        invalidateSelf();
    }

    public int getRoundedCornerRadius() {
        return roundedCornerRadius;
    }

    public void setRoundedCornerRadius(int roundedCornerRadius) {
        this.roundedCornerRadius = roundedCornerRadius;
        invalidateSelf();
    }

    @Override
    public Matrix getImageMatrix() {
        return imageMatrix;
    }

    @Override
    public void setImageMatrix(Matrix imageMatrix) {
        this.imageMatrix = imageMatrix;
        invalidateSelf();
    }

    public void setRounded(boolean roundTopLeft, boolean roundTopRight, boolean roundBottomLeft, boolean roundBottomRight) {
        this.roundTopLeft = roundTopLeft;
        this.roundTopRight = roundTopRight;
        this.roundBottomLeft = roundBottomLeft;
        this.roundBottomRight = roundBottomRight;
        invalidateSelf();
    }

    protected void updateRoundedInfo() {
        if (imageDrawable instanceof RoundedCornerDrawable) {
            RoundedCornerDrawable drawable = (RoundedCornerDrawable) imageDrawable;
            drawable.roundBottomLeft = this.roundBottomLeft;
            drawable.roundBottomRight = this.roundBottomRight;
            drawable.roundTopLeft = this.roundTopLeft;
            drawable.roundTopRight = this.roundTopRight;
            drawable.roundedCornerRadius = this.roundedCornerRadius;
            drawable.borderColor = this.borderColor;
            drawable.borderWidth = this.borderWidth;
            drawable.circle = this.circle;
            drawable.imageMatrix = this.imageMatrix;
            drawable.scaleType = this.scaleType;

            drawable.borderPaint.setColor(borderColor);
        }
    }

    protected void invalidateSelf() {
        if (imageDrawable instanceof RoundedCornerDrawable) {
            updateRoundedInfo();
            imageDrawable.invalidateSelf();
        }
    }

}
