package com.example.quizapp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class BatteryLevelReceiver extends BroadcastReceiver {

    private static final String TAG = BatteryLevelReceiver.class.getSimpleName();
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryLevel = level / (float) scale;

        Log.i(TAG, "Battery level changed to: " + batteryLevel);

        String message = String.format("Battery level: %.0f%%", batteryLevel * 100);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
