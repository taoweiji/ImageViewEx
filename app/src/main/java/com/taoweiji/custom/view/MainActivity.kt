package com.taoweiji.custom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var index = 0
    var scaleIndex = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val colors = arrayOf(
//            Color.BLACK,
//            Color.BLUE,
//            Color.CYAN,
//            Color.DKGRAY,
//            Color.GRAY,
//            Color.GREEN,
//            Color.RED,
//            Color.WHITE,
//            Color.parseColor("#55555555")
//        )
//        test_image_view.setOnClickListener {
//            index++
//            if (index == colors.size) {
//                index = 0
//            }
//            val drawable = test_image_view.drawable as RoundedColorDrawable
//            drawable.color = colors[index]
//
//            drawable.setRounded(true,false,true,false)
//            drawable.roundedCornerRadius  = 50
//            drawable.isCircle = false
////            drawable.setRounded()
//        }

//        val drawable = RoundedCornerDrawable.fromDrawable(
//            resources.getDrawable(R.drawable.test1),
//            resources
//        ) as RoundedCornerDrawable
//        drawable.isCircle = false
//        drawable.roundedCornerRadius = 50
//        drawable.setRounded(true, false, false, true)
//
////        test_image_view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
//        test_image_view.setImageDrawable(drawable)
//        val scale = arrayOf(
//            ImageView.ScaleType.FIT_CENTER,
//            ImageView.ScaleType.FIT_END,
//            ImageView.ScaleType.FIT_START,
//            ImageView.ScaleType.FIT_XY,
//            ImageView.ScaleType.CENTER,
//            ImageView.ScaleType.CENTER_CROP,
//            ImageView.ScaleType.CENTER_INSIDE
//        )
//        test_image_view.setOnClickListener {
//            if (scaleIndex == scale.size) {
//                scaleIndex = 0
//            }
//            drawable.scaleType = scale[scaleIndex]
//            scaleIndex++
//        }


        //初始化
//        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_test);
//        var alertBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config);
//        var canvas = Canvas(alertBitmap);
//        var paint = Paint();
//
//        //画第一个图层
//        canvas.drawCircle(70F, 70F, 40F, paint);
//
//        //设置混合模式
//        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//
//        //画第二个图
//        canvas.drawBitmap(bitmap, 0F, 0F, paint)
//        test_image_view.setImageBitmap(alertBitmap)

//        rounded_image_view.setImageResource(android.R.color.white)
    }
}
