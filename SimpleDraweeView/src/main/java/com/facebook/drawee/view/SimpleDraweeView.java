package com.facebook.drawee.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.facebook.drawee.replace.R;
import com.taoweiji.image.ImageViewExBase;

public class SimpleDraweeView extends ImageViewExBase {


    private int placeholderImage;

    public SimpleDraweeView(Context context) {
        this(context, null);
    }

    public SimpleDraweeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleDraweeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleDraweeView);

        this.roundedCornerRadius = typedArray.getDimensionPixelSize(R.styleable.SimpleDraweeView_roundedCornerRadius, roundedCornerRadius);
        this.roundTopLeft = typedArray.getBoolean(R.styleable.SimpleDraweeView_roundTopLeft, roundTopLeft);
        this.roundTopRight = typedArray.getBoolean(R.styleable.SimpleDraweeView_roundTopRight, roundTopRight);
        this.roundBottomLeft = typedArray.getBoolean(R.styleable.SimpleDraweeView_roundBottomLeft, roundBottomLeft);
        this.roundBottomRight = typedArray.getBoolean(R.styleable.SimpleDraweeView_roundBottomRight, roundBottomRight);


        this.circle = typedArray.getBoolean(R.styleable.SimpleDraweeView_roundAsCircle, circle);

        this.borderColor = typedArray.getColor(R.styleable.SimpleDraweeView_roundingBorderColor, Color.WHITE);
        this.borderWidth = typedArray.getDimensionPixelSize(R.styleable.SimpleDraweeView_roundingBorderWidth, borderWidth);

        int roundingBorderPadding = typedArray.getDimensionPixelSize(R.styleable.SimpleDraweeView_roundingBorderPadding, 0);
        int backgroundImage = typedArray.getResourceId(R.styleable.SimpleDraweeView_backgroundImage, 0);
        int actualImageScaleType = typedArray.getInt(R.styleable.SimpleDraweeView_actualImageScaleType, 0);
        int actualImageResource = typedArray.getResourceId(R.styleable.SimpleDraweeView_actualImageResource, 0);
        String actualImageUri = typedArray.getString(R.styleable.SimpleDraweeView_actualImageUri);

        this.placeholderImage = typedArray.getResourceId(R.styleable.SimpleDraweeView_placeholderImage, 0);
        int placeholderImageScaleType = typedArray.getDimensionPixelSize(R.styleable.SimpleDraweeView_placeholderImageScaleType, 0);

        float viewAspectRatio = typedArray.getFloat(R.styleable.SimpleDraweeView_viewAspectRatio, 0);


        if (backgroundImage != 0) {
            setBackgroundResource(backgroundImage);
        }
        if (actualImageScaleType != 0) {
            switch (actualImageScaleType) {
                case 0:setScaleType(ScaleType.MATRIX);break;
                case 1:setScaleType(ScaleType.FIT_XY);break;
                case 2:setScaleType(ScaleType.FIT_START);break;
                case 3:setScaleType(ScaleType.FIT_CENTER);break;
                case 4:setScaleType(ScaleType.FIT_END);break;
                case 5:setScaleType(ScaleType.CENTER);break;
                case 6:setScaleType(ScaleType.CENTER_CROP);break;
                case 8:setScaleType(ScaleType.CENTER_INSIDE);break;
            }
        }
        if (actualImageUri != null){
            Uri uri = Uri.parse(actualImageUri);
            if (uri.getScheme() != null  && uri.getScheme().length() > 0){
                setImageURI(uri);
            }
        }
        if (actualImageResource != 0){
            setImageResource(actualImageResource);
        }
        if (roundingBorderPadding != 0){
            setPadding(roundingBorderPadding,roundingBorderPadding,roundingBorderPadding,roundingBorderPadding);
        }


        this.fadeDuration = typedArray.getInt(R.styleable.SimpleDraweeView_fadeDuration, fadeDuration);

        typedArray.recycle();
        setImageDrawable(getDrawable());
    }

}
