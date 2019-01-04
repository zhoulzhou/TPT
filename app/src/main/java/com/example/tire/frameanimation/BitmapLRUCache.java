package com.example.tire.frameanimation;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.LruCache;

import com.example.tire.common.LogUtils;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * 内存缓存的实现，包括强引用缓存和软引用缓存两个部分
 *
 * 强引用缓存采用LRUCache实现,它是Android系统为开发人员提供的缓存工具类，
 * 实际上是将强引用的对象存储在LinkedHashMap中，初始化时会设置缓存空间大小，
 * 当缓存数据达到预设值时会采用最近最少使用算法进行淘汰。
 *
 * 另外，软引用缓存同样使用LinkedHashMap作为存储结构，
 * 将从LRUCache淘汰的数据扔到软引用缓存中，之前的做法是对软引用对象不做任何处理，
 * 等待垃圾回收器自动回收。大量使用软引用的弊端(不稳定，软引用可能会被回收,或不可以预期的回收)
 * 对此做了部分改进，
 * 有限的使用软引用对象，当软引用缓存空间不足时，同样按照LRU规则淘汰并主动回收内存空间。
 */
public class BitmapLRUCache extends LruCache {
    private final int SOFT_CACHE_SIZE = 10; // 软引用缓存容量
    private LinkedHashMap mSoftBitmapCache;//软引用缓存,已清理的数据可能会再次使用
    private Drawable mCurBitmap;
    private static BitmapLRUCache mInstance;

    public static BitmapLRUCache getInstance(int maxHardSize){
        if(mInstance == null){
            mInstance = new BitmapLRUCache(maxHardSize);
        }
        return mInstance;
    }

    public BitmapLRUCache(int maxHardSize) {
        super(maxHardSize);
        final int maxSoftSize = maxHardSize / 2;
        this.mSoftBitmapCache = new LinkedHashMap(maxSoftSize, 0.75f, true)
        {// true 采用LRU排序,移除队首
            @Override
            protected boolean removeEldestEntry (Entry eldest) {
            if (size() > maxSoftSize) {//缓存数量不超过10
                if (eldest != null) {
                    SoftReference bitmapReference = (SoftReference) eldest.getValue();
                    if (bitmapReference != null) {
                        Drawable oldValue = (Drawable) bitmapReference.get();
                        LogUtils.d("BitmapLRUCache mSoftBitmapCache removeEldestEntry oldValue= " + oldValue);
                        recycleDrawable(oldValue);
                    }
                }
                return true;
            }
            return false;
        }
        } ;
    }

    public Drawable getBitmap(int url) {
        // 先从硬缓存中获取
        mCurBitmap = (Drawable) get(url);
        if (mCurBitmap != null) {
            LogUtils.d("BitmapLRUCache getBitmap HardCache bitmap= " + mCurBitmap);
            return mCurBitmap;
        }
        synchronized (mSoftBitmapCache) {
            SoftReference bitmapReference = (SoftReference) mSoftBitmapCache.get(url);
            if (bitmapReference != null) {
                mCurBitmap = (Drawable) bitmapReference.get();
                if (mCurBitmap != null) {
                    //移入硬缓存
                    put(url, mCurBitmap);
                    mSoftBitmapCache.remove(url);
                    LogUtils.d("BitmapLRUCache getBitmap SoftCache bitmap= " + mCurBitmap);
                    return mCurBitmap;
                } else {
                    mSoftBitmapCache.remove(url);
                }
            }
        }
        return null;
    }

    private int getSizeInBytes(Bitmap bitmap) {
        int size = bitmap.getRowBytes() * bitmap.getHeight();//每一行像素点所占用的字节数 *  高度
        LogUtils.d("BitmapLRUCache getSizeInBytes size= " + size);
        return size;
    }

    @Override
    protected void entryRemoved(boolean evicted, Object key, Object oldValue, Object newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if (evicted) {
            if (oldValue != null) {
                //当硬缓存满了,根据LRU规则移入软缓存
                synchronized (mSoftBitmapCache) {
                    mSoftBitmapCache.put(key, new SoftReference(oldValue));
                }
                LogUtils.d("BitmapLRUCache entryRemoved ToSoftCache oldValue= " + oldValue);
            }
        } else {//主动移除,回收无效空间
            LogUtils.d("BitmapLRUCache entryRemoved  recycleDrawable= " + oldValue);
            recycleDrawable((Drawable) oldValue);
        }
    }

    @Override
    protected int sizeOf(Object key, Object value) {
        LogUtils.d("BitmapLRUCache sizeOf  size= " + size());
        if (value != null) {
            if (value instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) value).getBitmap();
                return getSizeInBytes(bitmap);
            }
            return 1;
        } else {
            return 0;
        }

    }

    private void recycleDrawable(Drawable oldValue) {
        if (oldValue != null) {
            try {
                if (oldValue instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) oldValue).getBitmap();
                    bitmap.recycle();
                }
                LogUtils.d("BitmapLRUCache recycleDrawable oldValue：" + oldValue);
            } catch (Exception exception) {
                exception.printStackTrace();
                LogUtils.d("BitmapLRUCache recycleDrawable Failed to clear Bitmap images on close");
            } finally {
                oldValue = null;
            }
        }
    }
}