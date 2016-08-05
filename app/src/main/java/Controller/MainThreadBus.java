package Controller;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by Jessie
 */
public class MainThreadBus extends Bus {
    public static MainThreadBus sBus = null;

    private Handler mHandler;

    private MainThreadBus(){
        super();
        mHandler = new Handler();
    }

    public static MainThreadBus get(){
        if (sBus == null){
            sBus = new MainThreadBus();
        }
        return sBus;
    }


         @Override
    public void post(final Object event){
        if (Looper.getMainLooper() == Looper.myLooper()){
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    sBus.post(event);
                }
            });
        }
    }
}
