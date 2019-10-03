package com.taoweiji.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;


public abstract class ImageViewExBase extends ImageView {
    //    protected ScaleType scaleType = ScaleType.MATRIX;
    protected int borderWidth = 0;
    protected int borderColor = Color.WHITE;
    protected boolean circle = false;
    protected int roundedCornerRadius = 0;
    protected boolean roundTopLeft = true;
    protected boolean roundTopRight = true;
    protected boolean roundBottomLeft = true;
    protected boolean roundBottomRight = true;

    protected Drawable imageDrawable;
    private Drawable placeholderImage;
    protected int fadeDuration = 0;

    public ImageViewExBase(Context context) {
        this(context, null);
    }

    public ImageViewExBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageViewExBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlaceholderImageBitmap(Bitmap bm) {
        setPlaceholderImageDrawable(RoundedCornerDrawable.fromBitmap(bm, getResources()));
    }

    public void setPlaceholderImageResource(int resId) {
        if (resId == 0) {
            setPlaceholderImageDrawable(null);
        } else {
            setPlaceholderImageDrawable(getResources().getDrawable(resId));
        }

    }

    public void setPlaceholderImageDrawable(Drawable drawable) {
        this.placeholderImage = RoundedCornerDrawable.fromDrawable(drawable, getResources());
        setImageDrawable(this.imageDrawable);
    }


    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        setImageDrawable(RoundedCornerDrawable.fromBitmap(bm, getResources()));
    }

    @Override
    public void setImageResource(int resId) {
        if (resId == 0) {
            setImageDrawable(null);
        } else {
            setImageDrawable(getResources().getDrawable(resId));
        }
    }


    @Override
    public void setImageDrawable(Drawable drawable) {
        Drawable imageDrawableTmp = RoundedCornerDrawable.fromDrawable(drawable, getResources());
        boolean isSame = isSame(imageDrawableTmp, this.imageDrawable);
        this.imageDrawable = imageDrawableTmp;
        updateRoundedInfo();
        if (this.imageDrawable != null) {
            if (!isSame && !isInEditMode() && fadeDuration > 0) {
                Drawable startDrawable = getResources().getDrawable(android.R.color.transparent);
                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{startDrawable, this.imageDrawable});
                super.setImageDrawable(transitionDrawable);
                transitionDrawable.startTransition(fadeDuration);
            } else {
                super.setImageDrawable(imageDrawable);
            }
        } else {
            super.setImageDrawable(this.placeholderImage);
        }
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != getScaleType()){
            super.setScaleType(scaleType);
            invalidateSelf();
        }

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
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
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
            drawable.imageMatrix = this.getImageMatrix();
            drawable.scaleType = this.getScaleType();

            drawable.borderPaint.setColor(borderColor);
        }
        if (placeholderImage instanceof RoundedCornerDrawable) {
            RoundedCornerDrawable drawable = (RoundedCornerDrawable) placeholderImage;
            drawable.roundBottomLeft = this.roundBottomLeft;
            drawable.roundBottomRight = this.roundBottomRight;
            drawable.roundTopLeft = this.roundTopLeft;
            drawable.roundTopRight = this.roundTopRight;
            drawable.roundedCornerRadius = this.roundedCornerRadius;
            drawable.borderColor = this.borderColor;
            drawable.borderWidth = this.borderWidth;
            drawable.circle = this.circle;
            drawable.imageMatrix = this.getImageMatrix();
            drawable.scaleType = this.getScaleType();

            drawable.borderPaint.setColor(borderColor);
        }
    }

    protected void invalidateSelf() {
        if (imageDrawable instanceof RoundedCornerDrawable) {
            updateRoundedInfo();
            imageDrawable.invalidateSelf();
        }
    }

    /**
     * 判断两张图片是否相同
     */
    private boolean isSame(Drawable drawable1, Drawable drawable2) {
        if (drawable1 == null || drawable2 == null) {
            return false;
        }
        if (!(drawable1 instanceof RoundedCornerDrawable)) {
            return false;
        }
        if (!(drawable2 instanceof RoundedCornerDrawable)) {
            return false;
        }
        RoundedCornerDrawable tmp1 = (RoundedCornerDrawable) drawable1;
        RoundedCornerDrawable tmp2 = (RoundedCornerDrawable) drawable2;
        if (tmp1.bitmap.getByteCount() != tmp2.bitmap.getByteCount()) {
            return false;
        }
        int tmpPixel1 = 0;
        int tmpPixel2 = 0;
        int gap1 = tmp1.bitmap.getWidth() / 20;
        int gap2 = tmp2.bitmap.getWidth() / 20;
        if (gap1 < 5) {
            gap1 = 5;
        }
        if (gap2 < 5) {
            gap2 = 5;
        }
        for (int i = 0; i < tmp1.bitmap.getWidth(); i += gap1) {
            for (int j = 0; j < tmp1.bitmap.getHeight(); j += gap1) {
                tmpPixel1 += tmp1.bitmap.getPixel(i, j);
            }
        }
        for (int i = 0; i < tmp2.bitmap.getWidth(); i += gap2) {
            for (int j = 0; j < tmp2.bitmap.getHeight(); j += gap2) {
                tmpPixel2 += tmp2.bitmap.getPixel(i, j);
            }
        }
        return tmpPixel1 == tmpPixel2;
    }
}
