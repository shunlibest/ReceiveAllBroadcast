package com.shunli.receiveallbroadcast.broadlist

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import android.os.Bundle
import java.lang.Exception


class ScreenReceiver : BaseBroadcastReceiver() {

    override fun registerActions(filter: IntentFilter) {
        //屏幕开启广播
        filter.addAction(Intent.ACTION_SCREEN_ON);
        //屏幕关闭广播
        filter.addAction(Intent.ACTION_SCREEN_OFF);
    }
}