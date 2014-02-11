package com.vgmoose.pausebutton;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class PauseService extends Service {
    HUDView mView;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView = new HUDView(this);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.RIGHT | Gravity.TOP;
        params.setTitle("Load Average");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(mView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mView != null)
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mView);
            mView = null;
        }
    }
}

class HUDView extends ViewGroup {
    private Paint mLoadPaint;

    public HUDView(Context context) {
        super(context);
        Toast.makeText(getContext(),"Pause Button Started", Toast.LENGTH_LONG).show();

        mLoadPaint = new Paint();
        mLoadPaint.setAntiAlias(true);
        mLoadPaint.setTextSize(10);
        mLoadPaint.setARGB(255, 255, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        mLoadPaint.setColor(Color.argb(200, 255, 0, 0));
        mLoadPaint.setStyle(Style.FILL);

        canvas.drawRect(30, 30, 60, 130, mLoadPaint);
        canvas.drawRect(80, 30, 110, 130, mLoadPaint);
        
        mLoadPaint.setColor(Color.BLACK);
        mLoadPaint.setStyle(Style.STROKE);
        
        canvas.drawRect(30, 30, 60, 130, mLoadPaint);
        canvas.drawRect(80, 30, 110, 130, mLoadPaint);

    }

    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
    	Log.v("hi","hey there");
        Toast.makeText(getContext(),"onTouchEvent", Toast.LENGTH_LONG).show();
        return true;
    }
}