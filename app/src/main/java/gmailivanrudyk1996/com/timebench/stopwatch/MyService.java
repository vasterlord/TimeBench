package gmailivanrudyk1996.com.timebench.stopwatch;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import gmailivanrudyk1996.com.timebench.MainActivity;

public class MyService extends Service {
    public static void start(Context context) {
        context.startService(new Intent(context, (Class)MyService.class));
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, (Class)MyService.class));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        return 1;
    }

    public void onTaskRemoved(Intent intent) {
        this.getSharedPreferences(Stopwatch.PREFS_NAME, 0).edit().clear().commit();
        this.stopSelf();
        super.onTaskRemoved(intent);
    }
}