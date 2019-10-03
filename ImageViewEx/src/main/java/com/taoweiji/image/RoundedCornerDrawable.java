package com.taoweiji.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.widget.ImageView;

public class RoundedCornerDrawable extends Drawable {
    private static final String TAG = "RoundedCornerDrawable";
    final Bitmap bitmap;

    Paint borderPaint;
    Paint bitmapPaint;

    ImageView.ScaleType scaleType = ImageView.ScaleType.MATRIX;
    int borderWidth = 0;
    int borderColor = Color.WHITE;
    boolean circle = false;
    int roundedCornerRadius = 0;
    boolean roundTopLeft = true;
    boolean roundTopRight = true;
    boolean roundBottomLeft = true;
    boolean roundBottomRight = true;
    Matrix imageMatrix;


    public RoundedCornerDrawable(Bitmap bitmap, Resources r) {

        this.bitmap = bitmap;
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        bitmapPaint.setColor(Color.WHITE);

        borderPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        borderPaint.setColor(borderColor);
    }

    public static Drawable fromDrawable(Drawable drawable, Resources resources) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof RoundedCornerDrawable) {
            return drawable;
        }
        if (drawable instanceof LayerDrawable) {
            return drawable;
        }
        Bitmap bitmap = drawableToBitmap(drawable);
        if (bitmap != null) {
            return new RoundedCornerDrawable(bitmap, resources);
        } else {
            Log.w(TAG, "Failed to create bitmap from drawable!");
            return null;
        }
    }

    public static Drawable fromBitmap(Bitmap bitmap, Resources resources) {
        if (bitmap == null) {
            return null;
        }
        return new RoundedCornerDrawable(bitmap, resources);
    }

    /**
     * 绘制边框
     */
    private void drawBorder(Canvas canvas) {
        if (borderWidth == 0) {
            return;
        }
        // 绘制边框
        int width = getBounds().width();
        int height = getBounds().height();

//        boolean isCircle = circle;
//        if (!isCircle) {
//            if (width > height) {
//                isCircle = roundedCornerRadius > width / 2;
//            } else {
//
//            }
//        }

        if (!circle) {
            canvas.drawRoundRect(new RectF(0, 0, width, height), roundedCornerRadius, roundedCornerRadius, borderPaint);
            // 如果部分是直角就绘制直角覆盖物
            if (!roundTopLeft) {
                canvas.drawRect(0, 0, width / 2, height / 2, borderPaint);
            }
            if (!roundTopRight) {
                canvas.drawRect(width / 2, 0, width, height / 2, borderPaint);
            }
            if (!roundBottomLeft) {
                canvas.drawRect(0, height / 2, width / 2, height, borderPaint);
            }
            if (!roundBottomRight) {
                canvas.drawRect(width / 2, height / 2, width, height, borderPaint);
            }
        } else {
            // 绘制圆圈
//            canvas.drawCircle(getBounds().centerX(), getBounds().centerX(), getBounds().width() / 2, borderPaint);
            if (width > height) {
                // 绘制圆圈
                canvas.drawCircle(getBounds().centerX(), getBounds().centerY(), height / 2, borderPaint);
            } else {
                // 绘制圆圈
                canvas.drawCircle(getBounds().centerX(), getBounds().centerY(), width / 2, borderPaint);
            }
        }
    }


    @Override
    public void draw(Canvas canvas) {
        drawBorder(canvas);
        int innerCornerRadius = roundedCornerRadius - borderWidth;


        // 绘制边框
        int width = getBounds().width() - 2 * borderWidth;
        int height = getBounds().height() - 2 * borderWidth;
        int centerX = width / 2;
        int centerY = height / 2;
        Rect rect = new Rect(0, 0, width, height);

        Bitmap tmpBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas tmpCanvas = new Canvas(tmpBitmap);
        if (circle) {
            if (width > height) {
                // 绘制圆圈
                tmpCanvas.drawCircle(centerX, centerY, height / 2, bitmapPaint);
            } else {
                // 绘制圆圈
                tmpCanvas.drawCircle(centerX, centerY, width / 2, bitmapPaint);
            }
            // 设置混合模式
            bitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        } else if (innerCornerRadius > 0 && (roundTopLeft || roundTopRight || roundBottomLeft || roundBottomRight)) {
            tmpCanvas.drawRoundRect(new RectF(0, 0, width, height), innerCornerRadius, innerCornerRadius, bitmapPaint);
            // 如果部分是直角就绘制直角覆盖物
            if (!roundTopLeft) {
                tmpCanvas.drawRect(0, 0, width / 2, height / 2, bitmapPaint);
            }
            if (!roundTopRight) {
                tmpCanvas.drawRect(width / 2, 0, width, height / 2, bitmapPaint);
            }
            if (!roundBottomLeft) {
                tmpCanvas.drawRect(0, height / 2, width / 2, height, bitmapPaint);
            }
            if (!roundBottomRight) {
                tmpCanvas.drawRect(width / 2, height / 2, width, height, bitmapPaint);
            }
            // 设置混合模式
            bitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }

        // 绘制图形
        Matrix matrix = new Matrix();
        RectF srcRectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dstRectF = new RectF(0, 0, width, height);
        boolean isDrawBitmap = false;
        if (this.scaleType == ImageView.ScaleType.MATRIX) {
            if (this.imageMatrix != null) {
                tmpCanvas.drawBitmap(bitmap, this.imageMatrix, bitmapPaint);
                isDrawBitmap = true;
            } else {
                matrix.setRectToRect(srcRectF, dstRectF, Matrix.ScaleToFit.CENTER);
            }
        } else if (this.scaleType == ImageView.ScaleType.FIT_CENTER) {
            matrix.setRectToRect(srcRectF, dstRectF, Matrix.ScaleToFit.CENTER);
        } else if (this.scaleType == ImageView.ScaleType.FIT_START) {
            matrix.setRectToRect(srcRectF, dstRectF, Matrix.ScaleToFit.START);
        } else if (this.scaleType == ImageView.ScaleType.FIT_END) {
            matrix.setRectToRect(srcRectF, dstRectF, Matrix.ScaleToFit.END);
        } else if (this.scaleType == ImageView.ScaleType.FIT_XY) {
            matrix.setRectToRect(srcRectF, dstRectF, Matrix.ScaleToFit.FILL);
        } else if (this.scaleType == ImageView.ScaleType.CENTER_INSIDE) {
            matrix.setRectToRect(srcRectF, dstRectF, Matrix.ScaleToFit.CENTER);
        } else {
            Bitmap centerCrop = resizeBitmapByCenterCrop(bitmap, width, height);
            tmpCanvas.drawBitmap(centerCrop, new Rect(0, 0, centerCrop.getWidth(), centerCrop.getHeight()), rect, bitmapPaint);
            isDrawBitmap = true;
        }
        if (!isDrawBitmap) {
            tmpCanvas.drawBitmap(bitmap, matrix, bitmapPaint);
        }
        canvas.drawBitmap(tmpBitmap, borderWidth, borderWidth, null);
        bitmapPaint.setXfermode(null);
    }

    public Bitmap resizeBitmapByCenterCrop(Bitmap src, int dstWidth, int dstHeight) {
        if (src == null || dstWidth == 0 || dstHeight == 0) {
            return null;
        }
        float srcWHRate = src.getWidth() / (float) src.getHeight();
        float dstWHRate = dstWidth / (float) dstHeight;

        if (srcWHRate == dstWHRate) {
            return src;
        } else if (srcWHRate > dstWHRate) {
            // 撑满高度，宽度居中裁剪
            int cutWidth = (int) (dstWHRate * src.getHeight());
            int startX = (src.getWidth() - cutWidth) / 2;
            return Bitmap.createBitmap(src, startX, 0, cutWidth, src.getHeight());
        } else {
            // 撑满宽度，高度居中裁剪
            int cutHeight = (int) (src.getWidth() / dstWHRate);
            if (cutHeight < 1) {
                cutHeight = 1;
            }
            int startY = (src.getHeight() - cutHeight) / 2;
            return Bitmap.createBitmap(src, 0, startY, src.getWidth(), cutHeight);
        }
    }

    public void setRounded(boolean roundTopLeft, boolean roundTopRight, boolean roundBottomLeft, boolean roundBottomRight) {
        this.roundTopLeft = roundTopLeft;
        this.roundTopRight = roundTopRight;
        this.roundBottomLeft = roundBottomLeft;
        this.roundBottomRight = roundBottomRight;
        invalidateSelf();
    }


    @Override
    public void setAlpha(int alpha) {
        bitmapPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        bitmapPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        borderPaint.setColor(borderColor);
        invalidateSelf();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        invalidateSelf();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        invalidateSelf();
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
        invalidateSelf();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable.getIntrinsicWidth() == 0) {
            // TODO 如果设置color
        }

        Bitmap bitmap;
        int width = Math.max(drawable.getIntrinsicWidth(), 2);
        int height = Math.max(drawable.getIntrinsicHeight(), 2);
        try {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    public Matrix getImageMatrix() {
        return imageMatrix;
    }

    public void setImageMatrix(Matrix imageMatrix) {
        this.imageMatrix = imageMatrix;
        invalidateSelf();
    }

    public int getRoundedCornerRadius() {
        return roundedCornerRadius;
    }

    public void setRoundedCornerRadius(int roundedCornerRadius) {
        this.roundedCornerRadius = roundedCornerRadius;
        invalidateSelf();
    }
}