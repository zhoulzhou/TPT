package com.example.tire.frameanimation;

public class BitmapTestResult {
    /**
    一．动画的测试
    测试用图片：分辨率：1247 * 497
    大小：117K
    动画频率：20ms
    动画频次：10万次
    测试后的结果：
            1、	使用原来的imageview.setBackgroundReource方式，内存不会溢出，内存高频率的GC，CPU占用率15%左右。 每张图片加载时间：16-20ms之间。
            2、	使用InBitmap属性方式（内存复用），内存不会溢出， 内存低频率的GC，CPU占用率10%左右。 每张图片加载时间：13-16ms之间。

    结论：
    使用InBitmap（内存复用）的方式，因为减少了GC的次数，所以CPU使用率降低了，此方式比直接setBackgourndResource方式好。

    二、DecodeResource DecodeStream  getDrawable的测试：
    测试用图片：分辨率：1247 * 497
    大小：117K
    测试结果：
            1、	getDrawable  加载1万张图片，内存无压力，不会内存溢出。 每张图片加载时间基本为零，可以忽略。
            2、	decodeResource DecodeStream 的结构都是一致的，加载80张就内存溢出， 每张图片加载时间大概 16-20ms之间。

    通过上面的结论可以使用getDrawable的方式优化下。结果后续输出。
   **/
}
