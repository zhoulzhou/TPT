package com.example.tire.frameanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AnimImageView {
    private static final String TAG = "AnimImageView";
    private static final boolean DEBUG = false;
    private static final int MSG_START = 0xf1;
    private static final int MSG_STOP = 0xf2;
    private static final int STATE_STOP = 0xf3;
    private static final int STATE_RUNNING = 0xf4;
    private int mState = STATE_RUNNING;
    private ImageView mImageView;
    private List<Integer> mResourceIdList = null;
    private Timer mTimer = null;
    private AnimTimerTask mTimeTask = null;
    private int mFrameIndex = 0;
    private boolean isLooping = false;
    private int total = 0;
    private String sStringID;
    private Message mMessage;

    private Bitmap mReusableBitmap = null;
    private Bitmap mDisplayBitmap = null;
    private BitmapFactory.Options mBitmapOptions;

    private AnimationLRUCache mBitmapLRUCache;

    public AnimImageView() {
        mTimer = new Timer();
        mBitmapLRUCache = new AnimationLRUCache(8);
    }

    public void setAnimation(ImageView imageview, List<Integer> resourceIdList, String stringId) {
        if(imageview==null){
            if(DEBUG) Log.d(TAG, "setAnimation---stringId = " + stringId);
        }
        mImageView = imageview;
        mResourceIdList = resourceIdList;
        total = mResourceIdList.size();
        sStringID = stringId;
        initReusableBitmap(mImageView, mResourceIdList.get(0));
    }

    private void initReusableBitmap(ImageView imageView, int resId){
        mBitmapOptions = new BitmapFactory.Options();
        mBitmapOptions.inMutable = true;
        try{
            mReusableBitmap = BitmapFactory.decodeResource(imageView.getResources(),resId, mBitmapOptions);
        }catch (Exception e){
            e.printStackTrace();
        }

        mBitmapOptions.inBitmap = mReusableBitmap;
        mBitmapOptions.inSampleSize = 1;
    }

    private void setAnimationImage(ImageView imageView, int resId){
        if(mReusableBitmap != null){
            mDisplayBitmap = mBitmapLRUCache.getBitmap(resId);
            if(mDisplayBitmap == null){
                try{
                    mDisplayBitmap = BitmapFactory.decodeResource(imageView.getResources(),resId,mBitmapOptions);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            if(mDisplayBitmap != null){
                imageView.setImageBitmap(mDisplayBitmap);
            } else {
                imageView.setImageResource(resId);
                mReusableBitmap.recycle();
                mReusableBitmap = null;
            }
        } else {
            imageView.setImageResource(resId);
        }
    }

    public void start(boolean loop, int duration) {
        if(DEBUG) Log.d(TAG, "start total = " + total + "---sStringID = " + sStringID);
        isLooping = loop;
        mFrameIndex = 0;
        mState = STATE_RUNNING;
        if (mTimer != null){
            if (mTimeTask != null){
                mTimeTask.cancel();
            }
            mTimeTask = new AnimTimerTask();
            mTimer.schedule(mTimeTask, 0, duration);
        }
    }

    public void stop() {
        if(DEBUG) Log.d(TAG, "stop total = " + total + "---sStringID = " + sStringID);
        if (mTimeTask != null) {
            //只要停止动画，把UI刷成第一帧
            mImageView.setBackgroundResource(mResourceIdList.get(0));
            mFrameIndex = 0;
            mState = STATE_STOP;
            mTimer.purge();
            mTimeTask.cancel();
            mTimeTask = null;
        }
    }

    private class AnimTimerTask extends TimerTask {
        @Override
        public void run() {
            if (mFrameIndex < 0 || mState == STATE_STOP) {
                Log.d(TAG,"run return");
                return;
            }
            if (mFrameIndex < mResourceIdList.size()) {
                mMessage = AnimHandler.obtainMessage(MSG_START, 0, 0, null);
                mMessage.sendToTarget();
            } else {
                mFrameIndex = 0;
                if (!isLooping) {
                    mMessage = AnimHandler.obtainMessage(MSG_STOP, 0, 0, null);
                    mMessage.sendToTarget();
                    mState = STATE_STOP;
                }else{
                    mMessage = AnimHandler.obtainMessage(MSG_START, 0, 0, null);
                    mMessage.sendToTarget();
                }

            }
        }
    }

    private Handler AnimHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_START: {
                    if (mFrameIndex >= 0 && mFrameIndex < mResourceIdList.size() && mState == STATE_RUNNING) {
                        if(mImageView!=null){
//                            mImageView.setBackgroundResource(mResourceIdList.get(mFrameIndex));
                            setAnimationImage(mImageView,mResourceIdList.get(mFrameIndex));
                        }else{
                            if(DEBUG) Log.d(TAG, "mImageView ==null");
                        }
                        mFrameIndex++;
                    }
                }
                break;
                case MSG_STOP: {
                    stop();
                }
                break;
                default:
                    break;
            }
        }
    };
}
