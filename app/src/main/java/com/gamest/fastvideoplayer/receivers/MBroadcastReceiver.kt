package com.gamest.fastvideoplayer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.gamest.fastvideoplayer.mainUI.MainActivity

class MBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            Log.d("TAG", "onReceive: Received")

        }
    }
}