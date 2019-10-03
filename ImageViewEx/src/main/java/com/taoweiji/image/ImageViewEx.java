package com.taoweiji.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;


public class ImageViewEx extends ImageViewExBase {


    public ImageViewEx(Context context) {
        this(context, null);
    }

    public ImageViewEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageViewEx);

        this.roundedCornerRadius = typedArray.getDimensionPixelSize(R.styleable.ImageViewEx_imageViewEx_roundCornerRadius, roundedCornerRadius);
        this.roundTopLeft = typedArray.getBoolean(R.styleable.ImageViewEx_imageViewEx_roundTopLeft, roundTopLeft);
        this.roundTopRight = typedArray.getBoolean(R.styleable.ImageViewEx_imageViewEx_roundTopRight, roundTopRight);
        this.roundBottomLeft = typedArray.getBoolean(R.styleable.ImageViewEx_imageViewEx_roundBottomLeft, roundBottomLeft);
        this.roundBottomRight = typedArray.getBoolean(R.styleable.ImageViewEx_imageViewEx_roundBottomRight, roundBottomRight);

        this.circle = typedArray.getBoolean(R.styleable.ImageViewEx_imageViewEx_asCircle, circle);
        this.borderColor = typedArray.getColor(R.styleable.ImageViewEx_imageViewEx_borderColor, Color.WHITE);
        this.borderWidth = typedArray.getDimensionPixelSize(R.styleable.ImageViewEx_imageViewEx_borderWidth, borderWidth);

        Drawable placeholderImageTmp = typedArray.getDrawable(R.styleable.ImageViewEx_placeholderImage);
        this.fadeDuration = typedArray.getInt(R.styleable.ImageViewEx_fadeDuration, fadeDuration);
        typedArray.recycle();
        setImageDrawable(getDrawable());
        setPlaceholderImageDrawable(placeholderImageTmp);
    }
}
