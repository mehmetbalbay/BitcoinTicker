package com.mehmetbalbay.bitcointicker.helper;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.mehmetbalbay.bitcointicker.R;
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivity;

import static com.mehmetbalbay.bitcointicker.BitcoinTickerApplication.CHANNEL_ID;

public class BackgroundRefreshService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Boolean isStart = intent.getBooleanExtra("isStart", false);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Bitcoin Ticker Live Service")
                .setSmallIcon(R.drawable.pref_icon)
                .build();


        if (isStart) {
            startForeground(1, notification);
            // TODO request here

        } else {
            stopForeground(true);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
