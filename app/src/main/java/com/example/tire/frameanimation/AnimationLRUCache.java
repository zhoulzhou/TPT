package com.example.tire.frameanimation;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.example.tire.common.LogUtils;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

public class AnimationLRUCache extends LruCache {
    private final int SOFT_CACHE_SIZE = 8; // 软引用缓存容量
    private LinkedHashMap mSoftBitmapCache;

    public AnimationLRUCache(int maxSize) {
        super(maxSize);
        this.mSoftBitmapCache = new LinkedHashMap(SOFT_CACHE_SIZE, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Entry eldest) {
                LogUtils.d("AnimationLRUCache mSoftBitmapCache size()= " + size());
                if(size() > SOFT_CACHE_SIZE){
                    if(eldest != null){
                        SoftReference bitmapReference = (SoftReference) eldest.getValue();
                        if(bitmapReference != null){
                            Bitmap bitmap = (Bitmap) bitmapReference.get();
                            LogUtils.d("AnimationLRUCache mSoftBitmapCache removeEldestEntry bitmap= " + bitmap);
                            recycleBitmap(bitmap);
                        }
                    }
                    return  true;
                }
                return false;
            }
        };

    }

    public Bitmap getBitmap(int resId){
        Bitmap bitmap = (Bitmap) get(resId);
        if(bitmap != null){
            LogUtils.d("AnimationLRUCache getBitmapFromHardCache bitmap= " + bitmap);
            return bitmap;
        }

        synchronized (mSoftBitmapCache){
            SoftReference bitmapReference = (SoftReference) mSoftBitmapCache.get(resId);
            if(bitmapReference != null){
                bitmap = (Bitmap) bitmapReference.get();
                if(bitmap != null){
                    //移入硬缓存
                    put(resId,bitmap);
                    mSoftBitmapCache.remove(resId);
                    LogUtils.d("AnimationLRUCache getBitmapFromSoftCache bitmap= " + bitmap);
                    return bitmap;
                }else {
                    mSoftBitmapCache.remove(resId);
                }
            }
        }
        return null;
    }

    @Override
    protected void entryRemoved(boolean evicted, Object key, Object oldValue, Object newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if(evicted){
            if(oldValue != null){
                synchronized (mSoftBitmapCache){
                    mSoftBitmapCache.put(key, new SoftReference<>(oldValue));
                    LogUtils.d("AnimationLRUCache  removeEldestEntry addto mSoftBitmapCache bitmap= " + oldValue);
                }
            }
        }else {
            if(oldValue instanceof Bitmap){
                LogUtils.d("AnimationLRUCache  evicted=false  bitmap= " + oldValue);
//                recycleBitmap((Bitmap) oldValue);
            }
        }
    }

    @Override
    protected int sizeOf(Object key, Object value) {
        LogUtils.d("AnimationLRUCache sizeOf size()= " + size());
        if(value != null){
            if(value instanceof Bitmap){
                return getSizeInBytes((Bitmap) value);
            }
            return 1;
        }
        return 0;
    }

    private int getSizeInBytes(Bitmap bitmap){
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        LogUtils.d("AnimationLRUCache  getSizeInBytes  size= " + size + " bitmap= " + bitmap);
        return size;
    }

    private void recycleBitmap(Bitmap oldValue){
        if(oldValue != null){
            try{
                LogUtils.d("AnimationLRUCache  recycleBitmap  bitmap= " + oldValue);
                if(!oldValue.isRecycled()){
                    oldValue.recycle();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                oldValue = null;
            }
        }
    }
}
